package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.OutsourceAuditEvent;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.MyDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

/**
 * 委外发料 的物品清点界面
 * author: timi
 * create at: 2017/11/14 9:01
 */
public class OutsourcingAuditPointActivity extends BaseActivity<OutsourcingAuditPointView, OutsourcingAuditPointPresenter> implements OutsourcingAuditPointView {

    QueryOutSourcePickByInputResult.MaterialResultsBean materialResultsBean;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_strict_name_tip)
    TextView tvStrictNameTip;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.tv_send_material_total_num_tip)
    TextView tvSendMaterialTotalNumTip;
    @BindView(R.id.tv_send_material_num)
    TextView tvSendMaterialNum;
    @BindView(R.id.tv_have_get_total_num)
    TextView tvHaveGetTotalNum;
    @BindView(R.id.tv_can_get_total_num_tip)
    TextView tvCanGetTotalNumTip;
    @BindView(R.id.tv_can_get_total_num)
    TextView tvCanGetTotalNum;
    @BindView(R.id.tv_send_material_lib_tip)
    TextView tvSendMaterialLibTip;
    @BindView(R.id.tv_send_material_lib)
    TextView tvSendMaterialLib;
    @BindView(R.id.tv_controll_type)
    TextView tvControllType;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_orderno_info)
    RecyclerView rlvOrdernoInfo;
    @BindView(R.id.btn_close)
    Button btnClose;
    @BindView(R.id.et_material_scan)
    EditText etMaterialScan;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.tv_material_scan_tip)
    TextView tvMaterialScanTip;
    @BindView(R.id.iv_material_scan)
    ImageView ivMaterialScan;
    @BindView(R.id.tv_bathch_num)
    TextView tvBathchNum;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;

    private BaseRecyclerAdapter<GetMaterialLotData.LotDetailBean> adapter;//适配器
    private List<GetMaterialLotData.LotDetailBean> mDatas = new ArrayList<>();//列表的数据源
    private QueryOutSourcePickByInputResult.SummaryResultsBean summaryResultsBean;//搜索结果带过来的信息
    private GetMaterialLotData mData;//获取到的批次拣货信息

    @Override
    public int setLayoutId() {
        return R.layout.activity_outsourcing_audit_point;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.oursource_audit_maiterial_point_title));
        materialResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_BEAN), QueryOutSourcePickByInputResult.MaterialResultsBean.class);
        summaryResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN), QueryOutSourcePickByInputResult.SummaryResultsBean.class);
    }

    @Override
    public void initView() {
        tvMaterialName.setText(materialResultsBean.getMaterialName());
        tvMaterialCode.setText(materialResultsBean.getMaterialCode());
        tvMaterialAttr.setText(TextUtils.isEmpty(materialResultsBean.getMaterialAttribute()) ? getString(R.string.none) : materialResultsBean.getMaterialAttribute());
        tvMaterialModel.setText(materialResultsBean.getMaterialStandard());
        tvSendMaterialLib.setText(materialResultsBean.getWarehouseName());
        tvSendMaterialNum.setText("(0)" + materialResultsBean.getScanQty() + "/" + materialResultsBean.getQty());
        etMaterialScan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /**
                     * 输入的内容
                     */
                    String inputStr = etMaterialScan.getText().toString().trim();
                    if (TextUtils.isEmpty(inputStr)) {
                        ToastUtils.showShort(getString(R.string.please_scan_material_code));
                    } else {
                        /**
                         *   批次拣料的请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", summaryResultsBean.getBillId());
                        params.put("SrcBillType", 12);
                        params.put("DestBillType", 20);
                        params.put("ScanId", summaryResultsBean.getScanId());
                        params.put("BarcodeNo", inputStr);
                        params.put("DateCode", mData.getLotDetail().get(0).getDateCode());
                        params.put("bCheckMode", true);
                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

        RequestGetMaterialLotBean bean = new RequestGetMaterialLotBean();
        bean.setMAC(PackageUtils.getMac());
        bean.setUserId(SpUtils.getInstance().getUserId());
        bean.setOrgId(SpUtils.getInstance().getOrgId());
        bean.setBillId(materialResultsBean.getDetailId());
        bean.setSrcBillType(12);
        bean.setDestBillType(20);
        bean.setWarehouseId(materialResultsBean.getWarehouseId());
        bean.setMaterialId(materialResultsBean.getMaterialId());
        bean.setMaterialCode(materialResultsBean.getMaterialCode());
        bean.setMaterialAttribute(materialResultsBean.getMaterialAttribute());
        LogUitls.e("上传的参数---->", new Gson().toJson(bean));
        getPresenter().getMaterialLotData(bean);
    }

    @Override
    public OutsourcingAuditPointPresenter createPresenter() {
        return new OutsourcingAuditPointPresenter(this);
    }

    @Override
    public OutsourcingAuditPointView createView() {
        return this;
    }


    @OnClick({R.id.iv_material_scan, R.id.btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_material_scan:
                scan(REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etMaterialScan.setText(result);
                        /**
                         *   批次拣料的请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", summaryResultsBean.getBillId());
                        params.put("SrcBillType", 12);
                        params.put("DestBillType", 20);
                        params.put("ScanId", summaryResultsBean.getScanId());
                        params.put("BarcodeNo", result);
                        params.put("DateCode", mData.getLotDetail().get(0).getDateCode());
                        params.put("bCheckMode", true);
                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                });
                break;
            case R.id.btn_close:
                onBackPressed();
                break;
        }
    }

    @Override
    public void getMaterialLotDataHttpSubscriber(GetMaterialLotData data) {
        mData = data;
        mDatas.addAll(data.getLotDetail());
        switch (data.getControlType()) {
            case 0://非管控
                tvControllType.setText(R.string.no_controll_tip);
                break;
            case 1://非强制管控 （出库非制定批次则提醒）
                tvControllType.setText(getString(R.string.first_in_first_out_dont_constranct) + data.getFifoIntervalDays() + ")");
                break;
            case 2://强制管控（必须扫描指定的批次出库）
                tvControllType.setText(getString(R.string.first_in_first_out_constranct) + data.getFifoIntervalDays() + ")");
                break;
            default:
                tvControllType.setText(R.string.no_controll_tip);
                break;
        }
        initAdapter();
    }

    @Override
    public void submitBarcodeLotPickOutSplit(SubmitBarcodeLotPickOutSplitResult result) {

    }

    @Override
    public void submitBarcodeLotPickOut(SubmitBarcodeLotPickOutResult result) {
        /**
         * 发送时间  传递 scanid
         */
        OutsourceAuditEvent outsourceAuditEvent = new OutsourceAuditEvent(OutsourceAuditEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS);
        outsourceAuditEvent.setScanId(result.getScanId());
        BaseMessage.post(outsourceAuditEvent);
        //设置scanid
        summaryResultsBean.setScanId(result.getScanId());
        materialResultsBean.setScanQty(materialResultsBean.getScanQty() + result.getBarcodeQty());
        //设置数量
        tvSendMaterialNum.setText("(" + result.getBarcodeQty() + ")" + materialResultsBean.getScanQty() + "/" + materialResultsBean.getQty());
        /**
         * True:非管控模式，让前端提醒（没有提交动作）
         * False:表示提交成功
         */
        if (result.isIsNotAllowPickOut()) {
            /**
             * 显示提醒的对话框
             */
            new MyDialog(this, R.layout.dialog_batch_tip).setButtonListener(R.id.btn_confirm, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                    /**
                     *   批次拣料的请求
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("BillId", summaryResultsBean.getBillId());
                    params.put("SrcBillType", 12);
                    params.put("DestBillType", 20);
                    params.put("ScanId", summaryResultsBean.getScanId());
                    params.put("BarcodeNo", etMaterialScan.getText().toString());
                    params.put("DateCode", mData.getLotDetail().get(0).getDateCode());
                    params.put("bCheckMode", true);
                    getPresenter().submitBarcodeLotPickOut(params);
                }
            }).setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            }).show();
        } else {//提交成功
            ToastUtils.showShort(getString(R.string.commit_success));
        }
        /**
         * 需要拆分打码
         */
        if (result.getExceedQty() > 0) {
            startActivity(new Intent(this, SplitPrintActivity.class));
        }
    }

    private void initAdapter() {
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<GetMaterialLotData.LotDetailBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_batch;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, GetMaterialLotData.LotDetailBean item) {
                    holder.setTextView(R.id.tv_line_num, position + 1);
                    holder.setTextView(R.id.tv_libcode, item.getLocationCode());
                    holder.setTextView(R.id.tv_bathch_num, item.getDateCode());
                    holder.setTextView(R.id.tv_have_count_num, item.getLotScanQty());
                    holder.setTextView(R.id.tv_can_user_lib, item.getLotUseQty());
                    holder.setTextView(R.id.tv_useful_life, TextUtils.isEmpty(item.getPeriod()) ? getString(R.string.none) : item.getPeriod());
                }
            };
            rlvOrdernoInfo.setAdapter(adapter);
            rlvOrdernoInfo.setLayoutManager(new LinearLayoutManager(this));
            rlvOrdernoInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));

        } else
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
