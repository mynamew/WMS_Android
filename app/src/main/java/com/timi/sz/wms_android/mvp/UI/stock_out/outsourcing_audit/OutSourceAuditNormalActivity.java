package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.PutAwayActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.detail.DetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT_BEAN;

/**
 * 普通委外发料 出库
 * author: timi
 * create at: 2017/11/14 15:48
 */
public class OutSourceAuditNormalActivity extends BaseActivity<OutSourceAuditNormalView, OutSourceAuditNormalPresenter> implements OutSourceAuditNormalView {


    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_orderno_tip)
    TextView tvOrdernoTip;
    @BindView(R.id.tv_outsource_orderno)
    TextView tvOutsourceOrderno;
    @BindView(R.id.tv_create_orderno_date_tip)
    TextView tvCreateOrdernoDateTip;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateOrdernoDate;
    @BindView(R.id.tv_have_get_total_num)
    TextView tvHaveGetTotalNum;
    @BindView(R.id.tv_can_get_total_num_tip)
    TextView tvCanGetTotalNumTip;
    @BindView(R.id.tv_can_get_total_num)
    TextView tvCanGetTotalNum;
    @BindView(R.id.tv_outstock_total_num)
    TextView tvOutstockTotalNum;
    @BindView(R.id.tv_have_count_num_tip)
    TextView tvHaveCountNumTip;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_wait_point_num)
    TextView tvWaitPointNum;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.et_scan_material)
    EditText etScanMaterial;
    @BindView(R.id.iv_scan_material)
    ImageView ivScanMaterial;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_nmodel)
    TextView tvMaterialNmodel;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    private QueryOutSourcePickByInputResult queryOutSourcePickByInputResult;
    //跳转的code
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_out_source_audit_normal;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.out_source_title));
        intentCode=getIntent().getIntExtra(Constants.CODE_STR,0);
        queryOutSourcePickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_OUTSOURCE_AUDIT_BEAN), QueryOutSourcePickByInputResult.class);
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(OutSourceAuditNormalActivity.this, DetailActivity.class);
                it.putExtra(Constants.CODE_STR, intentCode);
                it.putExtra("BillId", queryOutSourcePickByInputResult.getSummaryResults().getBillId());
                startActivity(it);
            }
        });
    }

    @Override
    public void initView() {
        etScanMaterial.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /**
                     * 输入的内容
                     */
                    String inputStr = etScanMaterial.getText().toString().trim();
                    if (TextUtils.isEmpty(inputStr)) {
                        ToastUtils.showShort(getString(R.string.please_input_return_matrial_code_or_scan));
                    } else {
                        /**
                         * 退料单号的 网络请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", queryOutSourcePickByInputResult.getSummaryResults().getBillId());
                        params.put("SrcBillType", queryOutSourcePickByInputResult.getSummaryResults().getSrcBillType());
                        params.put("DestBillType", queryOutSourcePickByInputResult.getSummaryResults().getDestBillType());
                        params.put("ScanId", queryOutSourcePickByInputResult.getSummaryResults().getScanId());
                        params.put("BarcodeNo", inputStr);
                        getPresenter().submitBarcodeOutAudit(params);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public OutSourceAuditNormalPresenter createPresenter() {
        return new OutSourceAuditNormalPresenter(this);
    }

    @Override
    public OutSourceAuditNormalView createView() {
        return this;
    }

    @Override
    public void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data) {
        ToastUtils.showShort(getString(R.string.commit_success));
        /**
         * 设置已退数量
         */
        QueryOutSourcePickByInputResult.SummaryResultsBean summaryResults = queryOutSourcePickByInputResult.getSummaryResults();
        summaryResults.setScanQty(summaryResults.getScanQty() + data.getBarcodeQty());
        tvMaterialNum.setText(summaryResults.getScanQty() + "/" + summaryResults.getQty());
        queryOutSourcePickByInputResult.getSummaryResults().setScanId(data.getScanId());
        /**
         * 超出数量  跳转到拆分条吗界面
         */
        if(data.getExceedQty()>0){
            startActivity(new Intent(this,SplitPrintActivity.class));
        }
    }

    @Override
    public void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data) {

    }

    @Override
    public void submitMakeOrAuditBill() {
        ToastUtils.showShort(getString(R.string.commit_check_success));
        onBackPressed();
    }
    @OnClick({R.id.iv_scan_material, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan_material:
                scan(REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etScanMaterial.setText(result);
                        /**
                         * 退料单号的 网络请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", queryOutSourcePickByInputResult.getSummaryResults().getBillId());
                        params.put("SrcBillType", queryOutSourcePickByInputResult.getSummaryResults().getSrcBillType());
                        params.put("DestBillType", queryOutSourcePickByInputResult.getSummaryResults().getDestBillType());
                        params.put("ScanId", queryOutSourcePickByInputResult.getSummaryResults().getScanId());
                        params.put("BarcodeNo", result);
                        getPresenter().submitBarcodeOutAudit(params);
                    }
                });
                break;
            case R.id.btn_commit:
                if(TextUtils.isEmpty(etScanMaterial.getText().toString().trim())){
                    ToastUtils.showShort(getString(R.string.please_input_return_matrial_code_or_scan));
                    return;
                }
                if(queryOutSourcePickByInputResult.getSummaryResults().getScanId()==0){//scanid 为0  证明未扫过条码或者条码已经入库 或者出库过了
                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("ScanId", queryOutSourcePickByInputResult.getSummaryResults().getScanId());
                params.put("SubmitType", 0);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }
}
