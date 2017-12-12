package com.timi.sz.wms_android.mvp.UI.quity.reject;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_BARCODE;

/**
 * 质检拒收
 */
public class QualityRejectActivity extends BaseActivity<QualityRejectView, QualityRejectPresenter> implements QualityRejectView {


    @BindView(R.id.tv_min_pack_code)
    TextView tvMinPackCode;
    @BindView(R.id.et_min_pack_code)
    EditText etMinPackCode;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.rlv_barcode)
    RecyclerView rlvBarcode;
    //bundle
    private GetAdvanceData mData1;
    private GetAdvance2Data mData2;
    private NormalQualityData mData;
    List<NormalQualityData.BarcodeDataBean> barcodeData;
    private int receiptId;
    private int receiptDetailId;
    private int rejectNum;
    /**
     * 条码信息
     */
    private BarcodeData mBarData;
    /**
     * 弹框
     */
    MyDialog myDialog;
    /**
     * 包装码
     */
    private String barcodeStr;

    private BaseRecyclerAdapter<NormalQualityData.BarcodeDataBean> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_quality_reject;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        //bundle
        mData = new Gson().fromJson(getIntent().getStringExtra("mData"), NormalQualityData.class);
        if (null != mData) {
            barcodeData = mData.getBarcodeData();
            receiptId = mData.getNormalSummary().getReceiptId();
            receiptDetailId = mData.getNormalSummary().getReceiptDetailId();
        } else {
            mData1 = new Gson().fromJson(getIntent().getStringExtra("mData"), GetAdvanceData.class);
            if (null != mData1) {
                barcodeData = mData1.getBarcodeData();
                receiptId = mData1.getNormalSummary().getReceiptId();
                receiptDetailId = mData1.getNormalSummary().getReceiptDetailId();
            } else {
                mData2 = new Gson().fromJson(getIntent().getStringExtra("mData"), GetAdvance2Data.class);
                barcodeData = mData2.getBarcodeData();
                receiptId = mData2.getNormalSummary().getReceiptId();
                receiptDetailId = mData2.getNormalSummary().getReceiptDetailId();
            }
        }
        rejectNum = getIntent().getIntExtra("rejectNum", 0);
        setActivityTitle(getString(R.string.quality_reject_title));
    }

    @Override
    public void initView() {
        /**
         * 设置输入框的监听事件
         */
        setEdittextListener(etMinPackCode, EDITTEXT_BARCODE, R.string.please_scan_assign_material_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 设置条码
                 */
                barcodeStr = result;
                /**
                 * 设置包装码
                 */
                etMinPackCode.setText(result);
                /**
                 * 设置拒收数量 获取信息
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("mac", PackageUtils.getMac());
                params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                params.put("RejectQty", rejectNum);
                params.put("BarcodeNo", result);
                getPresenter().getBarcodeData(params, result);
            }
        });
        //如果 barcodeData 为空则进行初始化(为了处理 没有条码信息)
        if (null == barcodeData) {
            barcodeData = new ArrayList<>();
        }
        /**
         * 初始化 adapter
         */
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<NormalQualityData.BarcodeDataBean>(this, barcodeData) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_quality_reject;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, NormalQualityData.BarcodeDataBean item) {
                    holder.setTextView(R.id.tv_material_code, item.getBarcodeNo());
                    holder.setTextView(R.id.tv_first_pack_num, item.getPackQty());
                    holder.setTextView(R.id.tv_real_pack_num, item.getCurrentQty());
                    holder.setTextView(R.id.tv_reject_num, item.getRejectQty());
                }
            };
        }
        rlvBarcode.setAdapter(adapter);
        rlvBarcode.setLayoutManager(new LinearLayoutManager(this));
        rlvBarcode.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));
    }

    @Override
    public void initData() {

    }

    @Override
    public QualityRejectPresenter createPresenter() {
        return new QualityRejectPresenter(this);
    }

    @Override
    public QualityRejectView createView() {
        return this;
    }


    @Override
    public void getBarcodeData(BarcodeData data, String result) {
        ToastUtils.showShort("条码有效！");
        mBarData = data;
        /**
         * 设置数据
         */
        //如果为空 则初始化
        if (null == barcodeData) {
            barcodeData = new ArrayList<>();
        }
        showBarcodeUpdateDialog(data);
    }

    @Override
    public void setBarcodeData(BarcodeData data) {
        mBarData = data;
        //当前扫描的barcode是否包含在链表中
        boolean isContainCurrentBarcode = false;
        /**
         * 设置数据
         */
        for (int i = 0; i < barcodeData.size(); i++) {
            if (barcodeStr.equals(barcodeData.get(i).getBarcodeNo())) {
                barcodeData.get(i).setBarcodeNo(barcodeStr);
                barcodeData.get(i).setCurrentQty(data.getCurrentQty());
                barcodeData.get(i).setPackQty(data.getInitialQty());
                barcodeData.get(i).setRejectQty(data.getRejectQty());
                isContainCurrentBarcode = true;
            }
        }
        /**
         * 发送  质检拒收成功 的广播 ，更新前面的数据
         */
        QualityEvent qualityEvent = new QualityEvent(QualityEvent.QUALITY_REJECT_SUCCESS);
        NormalQualityData.BarcodeDataBean barcodeDataBean = new NormalQualityData.BarcodeDataBean();
        barcodeDataBean.setBarcodeNo(barcodeStr);
        barcodeDataBean.setPackQty(data.getInitialQty());
        barcodeDataBean.setCurrentQty(data.getCurrentQty());
        barcodeDataBean.setRejectQty(data.getRejectQty());
        qualityEvent.setNewBarDataBean(barcodeDataBean);
        BaseMessage.post(qualityEvent);
        /**
         * 如果不包含则加入链表并刷新adapter
         */
        if (!isContainCurrentBarcode) {
            barcodeData.add(barcodeDataBean);
        }
        if (null != adapter) {
            adapter.notifyDataSetChanged();
        }
        if (null != myDialog) {
            /**
             * 初始包装  和实际包装
             */
            setTextViewText(myDialog.getTextView(R.id.tv_start_pack_num), R.string.start_pack_num, data.getInitialQty());
            setTextViewText(myDialog.getTextView(R.id.tv_real_pack_num), R.string.real_pack_num, data.getCurrentQty());
        }
    }

    @Override
    public void submitFinish() {
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_CONFRIM));
        onBackPressed();
    }

    /**
     * 一旦后台返回 输入的最小包装码不正确 则设置起选中
     */
    @Override
    public void setEdittextSelect() {
        etMinPackCode.selectAll();
    }


    @OnClick({R.id.tv_quality_complete, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_quality_complete://质检完成
                new MyDialog(QualityRejectActivity.this, R.layout.dialog_logout)
                        .setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                            }
                        }).setTextViewContent(R.id.tv_title, getString(R.string.quality_complete))
                        .setTextViewContent(R.id.tv_content, getString(R.string.please_confirm_quality_complete))
                        .setButtonListener(R.id.tv_logout_confirm, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                                /**
                                 * 质检确认
                                 */
                                Map<String, Object> params = new HashMap<>();
                                params.put("UserId", SpUtils.getInstance().getUserId());
                                params.put("OrgId", SpUtils.getInstance().getOrgId());
                                params.put("mac", PackageUtils.getMac());
                                params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                                params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                                getPresenter().submitFinish(params);
                            }
                        })
                        .setButtonListener(R.id.tv_logout_cancel, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                            }
                        })
                        .show();

                break;
            case R.id.iv_scan://扫描包装码
                scan(Constants.REQUEST_SCAN_CODE_BARCODE, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        /**
                         * 设置条码
                         */
                        barcodeStr = result;
                        /**
                         * 设置包装码
                         */
                        etMinPackCode.setText(result);
                        /**
                         * 设置拒收数量 获取信息
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("mac", PackageUtils.getMac());
                        params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                        params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                        params.put("RejectQty", rejectNum);
                        params.put("BarcodeNo", result);
                        getPresenter().getBarcodeData(params, result);
                    }
                });
                break;
        }
    }

    /**
     * 显示修改条码信息
     *
     * @param data
     */
    public void showBarcodeUpdateDialog(final BarcodeData data) {
        if (null == myDialog) {
            /**
             * 弹出设置拒收数量的dialog
             */
            myDialog = new MyDialog(QualityRejectActivity.this, R.layout.dialog_quality_reject);
            NormalQualityData.NormalSummaryBean normalSummary = mData.getNormalSummary();
            /**
             * 物料编码
             */
            setTextViewText(myDialog.getTextView(R.id.tv_material_code), R.string.material_code, normalSummary.getMaterialCode());
            /**
             * 物料名称
             */
            setTextViewText(myDialog.getTextView(R.id.tv_material_name), R.string.material_name, normalSummary.getMaterialName());

            setTextViewText(myDialog.getTextView(R.id.tv_material_model), R.string.material_model, normalSummary.getMaterialStandard());
            setTextViewText(myDialog.getTextView(R.id.tv_start_pack_num), R.string.start_pack_num, data.getInitialQty());
            setTextViewText(myDialog.getTextView(R.id.tv_real_pack_num), R.string.real_pack_num, data.getCurrentQty());

            myDialog.getEdittext(R.id.et_reject_num).setText(String.valueOf(rejectNum));
            myDialog.setButtonListener(R.id.btn_commit, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    InputMethodUtils.hide(QualityRejectActivity.this);
                    String rejectNumStr = myDialog.getEdittext(R.id.et_reject_num).getText().toString();
                    if (TextUtils.isEmpty(rejectNumStr)) {
                        ToastUtils.showShort(getString(R.string.please_input_reject_num));
                        return;
                    }
                    dialog.dismiss();
                    /**
                     *提交质检拒收
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("mac", PackageUtils.getMac());
                    params.put("ReceiptId", mData.getNormalSummary().getReceiptId());
                    params.put("RejectQty", Integer.parseInt(rejectNumStr));
                    params.put("BarcodeNo", barcodeStr);
                    params.put("ReceiptDetailId", mData.getNormalSummary().getReceiptDetailId());
                    getPresenter().setBarcodeData(params);
                }
            });
            myDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    InputMethodUtils.hide(QualityRejectActivity.this);
                    dialog.dismiss();
                }
            }).setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    InputMethodUtils.hide(QualityRejectActivity.this);
                    dialog.dismiss();
                }
            });
        }
        setTextViewText(myDialog.getTextView(R.id.tv_start_pack_num), R.string.start_pack_num, data.getInitialQty());
        setTextViewText(myDialog.getTextView(R.id.tv_real_pack_num), R.string.real_pack_num, data.getCurrentQty());
        /**
         * 设置拒收数选中
         */
        Selection.selectAll(myDialog.getEdittext(R.id.et_reject_num).getText());
        myDialog.show();
    }

}
