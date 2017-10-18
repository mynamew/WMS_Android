package com.timi.sz.wms_android.mvp.UI.quity.nomal_quality;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.QualityEvent;
import com.timi.sz.wms_android.mvp.UI.quity.reject.QualityRejectActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 普通质检
 * author: timi
 * create at: 2017/9/6 17:22
 */
public class NormalQualityActivity extends BaseActivity<NormalQualityView, NormalQualityPresenter> implements NormalQualityView {


    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_receive_material_date)
    TextView tvReceiveMaterialDate;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_receive_num)
    TextView tvReceiveNum;
    @BindView(R.id.et_spot_check_num)
    EditText etSpotCheckNum;
    @BindView(R.id.et_refuse_receive_num)
    TextView etRefuseReceiveNum;
    @BindView(R.id.tv_badness_total_num)
    TextView tvBadnessTotalNum;
    @BindView(R.id.et_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.rlv_quality)
    RecyclerView rlvQuality;
    @BindView(R.id.rd_qualified)
    RadioButton rdQualified;
    @BindView(R.id.rd_unqualified)
    RadioButton rdUnqualified;
    @BindView(R.id.rg_qualified)
    RadioGroup rgQualified;

    private MyDialog faultDataDialog;
    //bundle
    private int receiptId;
    private int receiptDetailId;
    //data
    /**
     * 保存的数据
     */
    private NormalQualityData.NormalSummaryBean mNormalSummary;
    private List<NormalQualityData.FaultDataBean> mFaultData;
    /**
     * 不良原因
     */
    private List<FaultData> mSelectFaultData = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_normal_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        BaseMessage.register(this);
        setActivityTitle(getString(R.string.normal_quality_title));
        receiptId = getIntent().getIntExtra("ReceiptId", -1);
        receiptDetailId = getIntent().getIntExtra("ReceiptDetailId", -1);
        /**
         * 设置从质检清单获取到的数据，设置到当前界面
         */
        setTextViewText(tvOrderno, R.string.receive_pro_num, "");
        setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, "");
        setTextViewText(tvOrderNum, R.string.order_no, "");
        setTextViewText(tvSupplier, R.string.buy_from, "");
        setTextViewText(tvMaterialCode, R.string.material_code, "");
        setTextViewText(tvMaterialName, R.string.material_name, "");
        setTextViewText(tvMaterialModel, R.string.material_model, "");
        setTextViewText(tvReceiveNum, R.string.receive_num, "");
    }

    @Override
    public void initView() {
        /**
         * 默认选择合格
         */
        rdQualified.setChecked(true);

    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        getPresenter().getNormalQualityData(params);
    }

    @Override
    public NormalQualityPresenter createPresenter() {
        return new NormalQualityPresenter(this);
    }

    @Override
    public NormalQualityView createView() {
        return this;
    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        /**
         * 抽检数
         */
        String spotCheckNum = etSpotCheckNum.getText().toString();
        if (TextUtils.isEmpty(spotCheckNum)) {
            ToastUtils.showShort(getString(R.string.please_input_spot_check_num));
            return;
        }
        /**
         * 拒收数
         */
        String refuseReceiveNum = etRefuseReceiveNum.getText().toString();
        if (TextUtils.isEmpty(refuseReceiveNum)) {
            ToastUtils.showShort(getString(R.string.please_input_reject_num));
            return;
        }
        /**
         * 不良总数
         */
        String badnessNum = tvBadnessTotalNum.getText().toString();
        /**
         * 不良率
         */
        String badnessPercent = tvBadnessPercent.getText().toString();
        /**
         * 是否合格
         */
        boolean isQualified = rdQualified.isChecked();
        /**
         * 网络请求 进行下一步
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        params.put("SampleQty", Integer.parseInt(spotCheckNum));
        params.put("NGQty", Integer.parseInt(badnessNum));
        LogUitls.e("NGQty--->", Integer.parseInt(badnessNum));
        /**
         */
        params.put("RejectQty", Integer.parseInt(refuseReceiveNum));
        /**
         * 更改质检状态 质检已完成
         */
        params.put("QCStatus", 3);
        /**
         * 1  合格
         * 3  不合格
         */
        params.put("QCResult", isQualified ? 1 : 3);
        params.put("Remark", "");
        params.put("FatalQty", 0);
        params.put("SeriousQty", 0);
        params.put("CommonlyQty", 0);
        params.put("SlightQty", 0);
        params.put("FaultData", mSelectFaultData);
        LogUitls.e("params--->", params);
        getPresenter().setNormalQualityData(params, isQualified, Integer.parseInt(refuseReceiveNum));
    }

    @Override
    public void getNormalQualityData(NormalQualityData result) {
        /**
         * 设置从质检清单获取到的数据，设置到当前界面
         */
        NormalQualityData.NormalSummaryBean normalSummary = result.getNormalSummary();
        if (null != normalSummary) {
            /**
             * 保存实体数据
             */
            this.mNormalSummary = normalSummary;

            setTextViewText(tvOrderno, R.string.receive_pro_num, normalSummary.getReceiptCode());
            setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, normalSummary.getReceiptDate());
            setTextViewText(tvOrderNum, R.string.order_no, normalSummary.getSourceBillCode());
            setTextViewText(tvSupplier, R.string.buy_from, normalSummary.getSupplierName());
            setTextViewText(tvMaterialCode, R.string.material_code, normalSummary.getMaterialCode());
            setTextViewText(tvMaterialName, R.string.material_name, normalSummary.getMaterialName());
            setTextViewText(tvMaterialModel, R.string.material_model, normalSummary.getMaterialStandard());
            setTextViewText(tvReceiveNum, R.string.receive_num, normalSummary.getReceiveQty());
        }
        /**
         * 不良原因
         */
        final List<NormalQualityData.FaultDataBean> faultData = result.getFaultData();
        if (null != faultData) {
            mFaultData = faultData;
            final CommonSimpleTypeAdapter<NormalQualityData.FaultDataBean> commonSimpleTypeAdapter = new CommonSimpleTypeAdapter<NormalQualityData.FaultDataBean>(faultData) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.item_normal_quality;
                }

                @Override
                public void convert(CommonViewHolder holder, NormalQualityData.FaultDataBean data, int position) {
                    holder.getTextView(R.id.tv_badness_code).setText(data.getFaultCode());
                    holder.getTextView(R.id.tv_badness_reason).setText(data.getFaultName());
                    holder.getTextView(R.id.tv_badness_num).setText(data.getFaultQty() + "");
                }
            };
            rlvQuality.setLayoutManager(new LinearLayoutManager(this));
            rlvQuality.setAdapter(commonSimpleTypeAdapter);
            /**
             * 点击不良原因的条目，弹出提示框 输入相应的不良数量
             */
            commonSimpleTypeAdapter.setOnItemClickListener(R.id.ll_content, new CommonSimpleTypeAdapter.ItemClickListener() {
                @Override
                public void onItemClicked(View view, final int position) {
                    if (null == faultDataDialog) {
                        faultDataDialog = new MyDialog(NormalQualityActivity.this, R.layout.dialog_quality_faultdata);
                    }
                    /**
                     * 确定
                     */
                    faultDataDialog.setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            /**
                             * 不良数
                             */
                            String badnessNumStr = dialog.getEdittext(R.id.et_badness_num).getText().toString();
                            if (TextUtils.isEmpty(badnessNumStr)) {
                                ToastUtils.showShort(getString(R.string.please_input_badness_num));
                                return;
                            }
                            /**
                             * 获取 点击的不良原因的位置
                             */
                            int selectFaultDataPosition = -1;
                            for (int i = 0; i < mSelectFaultData.size(); i++) {
                                if (mSelectFaultData.get(i).FaultId == mFaultData.get(position).getFaultId()) {
                                    selectFaultDataPosition = i;
                                }
                            }
                            /**
                             * 1、如果 未选择过这条不良原因 则直接加入一条不良原因s
                             * 2、否则则直接设置其不良数
                             */
                            if (selectFaultDataPosition == -1) {
                                mSelectFaultData.add(new FaultData(mFaultData.get(position).getFaultId(), Integer.parseInt(badnessNumStr)));
                            } else {
                                /**
                                 * 设置选择的数据
                                 */
                                mSelectFaultData.get(selectFaultDataPosition).FaultQty = Integer.parseInt(badnessNumStr);
                            }
                            /**
                             * 设置原数据
                             */
                            mFaultData.get(position).setFaultQty(Integer.parseInt(badnessNumStr));
                            commonSimpleTypeAdapter.notifyDataSetChanged();
                            /**
                             * 设置文本的不良总数
                             */
                            int totalBadnessNum = 0;
                            /**
                             * 计算获取所有的不良数的和
                             */
                            for (int i = 0; i < mSelectFaultData.size(); i++) {
                                totalBadnessNum = totalBadnessNum + mSelectFaultData.get(i).FaultQty;
                            }
                            tvBadnessTotalNum.setText(String.valueOf(totalBadnessNum));
                            /**
                             * 计算文本的不良率
                             */
                            //不良总数
                            double dTotalBadnessNum = (double) totalBadnessNum;
                            //实收数
                            double dReceiveNum = (double) mNormalSummary.getReceiveQty();
                            //转换成百分比
                            NumberFormat nFromat = NumberFormat.getPercentInstance();
                            String rates = nFromat.format(dTotalBadnessNum / dReceiveNum);
                            tvBadnessPercent.setText(rates);
                            //dismiss
                            dialog.dismiss();
                        }
                    });
                    /**
                     * 取消
                     */
                    faultDataDialog.setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    });
                    /**
                     * 设置不良原因
                     */
                    setTextViewText(faultDataDialog.getTextView(R.id.tv_badness_reason), R.string.badness_reason_tip, mFaultData.get(position).getFaultName());
                    faultDataDialog.show();
                }
            });
        }
    }

    @Override
    public void setNormalQualityData(boolean isQualified, int rejectNum) {
        /**
         * 发送质检成功的消息
         */
        BaseMessage.post(new QualityEvent(QualityEvent.QUALITY_SUCCESS));
        /**
         * 参数
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("mac", PackageUtils.getMac());
        params.put("ReceiptId", receiptId);
        params.put("ReceiptDetailId", receiptDetailId);
        /**
         * 设置按钮文字
         */
        if (isQualified) {//合格
            if (rejectNum > 0) {//拒收数大于0  跳转到质检拒收
                /**
                 * 跳转到质检拒收
                 */
                Intent intent = new Intent(NormalQualityActivity.this, QualityRejectActivity.class);
                intent.putExtra("ReceiptId", receiptId);
                intent.putExtra("rejectNum", rejectNum);
                intent.putExtra("ReceiptDetailId", receiptDetailId);
                startActivity(intent);
            } else {
                tvNext.setText(getString(R.string.quality_complete));
                ToastUtils.showShort(getString(R.string.normal_quality_tip));
                getPresenter().submitFinish(params);
            }
        } else {//不合格
            ToastUtils.showShort(getString(R.string.normal_unquality_tip));
            getPresenter().submitFinish(params);
        }
    }

    @Override
    public void submitFinish() {
        ToastUtils.showShort("质检确认完成！");
        /**
         * 关闭当前界面
         */
        onBackPressed();
    }

    /**
     * 选择不良原因的实体类
     */
    class FaultData {
        int FaultId;
        int FaultQty;

        public FaultData(int faultId, int faultQty) {
            FaultId = faultId;
            FaultQty = faultQty;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void closNormalQuality(QualityEvent event) {
        if (event.getEvent().equals(QualityEvent.QUALITY_CONFRIM)) {
            onBackPressed();
        }
    }
}
