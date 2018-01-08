package com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_scan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.detail.StockInWorkDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_LIB_LOATION;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

/**
 * 扫描调入
 * author: timi
 * create at: 2017/12/1 15:34
 */
public class AllotScanActivity extends BaseActivity<AllotScanView, AllotScanPresenter> implements AllotScanView, BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_outstock_total_num)
    TextView tvOutstockTotalNum;
    @BindView(R.id.tv_wait_point_num)
    TextView tvWaitPointNum;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_putaway_scan_location_tip)
    TextView tvPutawayScanLocationTip;
    @BindView(R.id.et_scan_location)
    EditText etScanLocation;
    @BindView(R.id.iv_putaway_scan_location)
    ImageView ivPutawayScanLocation;
    @BindView(R.id.tv_material_code_tip)
    TextView tvMaterialCodeTip;
    @BindView(R.id.et_material_code)
    EditText etMaterialCode;
    @BindView(R.id.iv_can_material_code)
    ImageView ivCanMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_outowner_name)
    TextView tvOutownerName;
    @BindView(R.id.tv_inowner_name)
    TextView tvInownerName;
    @BindView(R.id.tv_create_orderno_name)
    TextView tvCreateOrdernoName;
    private AllotScanResult allotScanResult;
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_allot_scan;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.siw_scan_in));
        allotScanResult = new Gson().fromJson(getIntent().getStringExtra(Constants.STOCK_IN_WORK_BEAN), AllotScanResult.class);
        intentCode = getIntent().getIntExtra(Constants.STOCK_IN_WORK_CODE_STR, 0);

    }

    /**
     * 库位码
     */
    private String locationCode;
    private int scanId;

    @Override
    public void initView() {
        /**
         * 设置查看详情的点击事件
         */
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AllotScanActivity.this, StockInWorkDetailActivity.class);
                it.putExtra(Constants.STOCK_IN_WORK_CODE_STR, intentCode);
                it.putExtra(Constants.STOCK_IN_WORK_BILLID, allotScanResult.getSummaryResults().getBillId());
                startActivity(it);
            }
        });
        setEdittextListener(etMaterialCode, Constants.REQUEST_SCAN_CODE_MATERIIAL, R.string.please_scan_material_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("BillId", allotScanResult.getSummaryResults().getBillId());
                params1.put("SrcBillType", 50);
                params1.put("DestBillType", 50);
                params1.put("ScanId", scanId);
                params1.put("BinCode", locationCode);
                params1.put("BarcodeNo", result);
                getPresenter().materialScanNetWork(params1, result);

            }
        });
        setEdittextListener(etScanLocation, Constants.REQUEST_SCAN_CODE_LIB_LOATION, R.string.please_scan_lib_location_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 保存库位码
                 */
                locationCode = result;
                /**
                 * 发起请求
                 */
                /**
                 * 判断库位码是否有效
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BinCode", result);
                getPresenter().vertifyLocationCode(params);
            }
        });

    }

    @Override
    public void initData() {
        AllotScanResult.SummaryResultsBean summaryResults = allotScanResult.getSummaryResults();
        scanId = summaryResults.getScanId();
        setHeaderContent(summaryResults.getBillCode(), summaryResults.getBillDate(), summaryResults.getQty(), summaryResults.getWaitQty(), summaryResults.getScanQty());
        tvInownerName.setText(allotScanResult.getSummaryResults().getInOwner());
        tvOutownerName.setText(allotScanResult.getSummaryResults().getOutOwner());
        tvCreateOrdernoName.setText(allotScanResult.getSummaryResults().getCreaterName());
    }

    @Override
    public AllotScanPresenter createPresenter() {
        return new AllotScanPresenter(this);
    }

    @Override
    public AllotScanView createView() {
        return this;
    }

    /**
     * 设置头部信息
     *
     * @param orderno
     * @param date
     * @param outStockNum
     * @param waitCountNum
     * @param haveCountNum
     */
    public void setHeaderContent(String orderno, String date, int outStockNum, int waitCountNum, int haveCountNum) {
        tvOrderno.setText(orderno);
        tvDate.setText(date);
        tvOutstockTotalNum.setText(String.valueOf(outStockNum));
        tvWaitPointNum.setText(String.valueOf(waitCountNum));
        tvHaveCountNum.setText(String.valueOf(haveCountNum));
    }

    @OnClick({R.id.btn_commit, R.id.iv_putaway_scan_location, R.id.iv_can_material_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                /**
                 * 提交审核
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("ScanId", scanId);
                params.put("SubmitType", 1);
                getPresenter().createInSockOrderno(params);
                break;
            case R.id.iv_putaway_scan_location:
                scan(Constants.REQUEST_SCAN_CODE_LIB_LOATION, this);
                break;
            case R.id.iv_can_material_code:
                if (TextUtils.isEmpty(locationCode)) {
                    ToastUtils.showShort(getString(R.string.please_scan_lib_location_code));
                    return;
                }
                if (!locationCodeIsUse) {
                    ToastUtils.showShort(getString(R.string.location_code_no_user));
                    return;
                }
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
        }
    }

    @Override
    public void materialScanResult(MaterialScanPutAwayBean bean) {
        ToastUtils.showShort(getString(R.string.material_scan_putaway_success));
        /**
         * 设置物料的信息
         */
        tvMaterialName.setText(bean.getMaterialName());
        tvMaterialCode.setText(bean.getMaterialCode());
        tvMaterialAttr.setText(bean.getMaterialAttribute());
        tvMaterialModel.setText(bean.getMaterialStandard());
        /**
         * 设置扫描数量
         */
        tvMaterialNum.setText("(" + bean.getBarcodeQty() + ")" + bean.getTotalScanQty() + "/" + allotScanResult.getSummaryResults().getQty());
        /**
         * 设置 是否显示附加属性
         */
        setMaterialAttrStatus(findViewById(R.id.ll_material_attr));
        /**
         * 设置已点总数
         */
        tvHaveCountNum.setText(String.valueOf(bean.getTotalScanQty()));
        /**
         * 设置待点总数
         */
        tvWaitPointNum.setText(String.valueOf(allotScanResult.getSummaryResults().getQty() - bean.getTotalScanQty()));
        /**
         * 设置扫码Id
         */
        scanId = bean.getScanId();
    }

    private VertifyLocationCodeBean mVertifyLocationCodeBean;
    private boolean locationCodeIsUse = false;

    @Override
    public void vertifyLocationCode(VertifyLocationCodeBean bean) {
        locationCodeIsUse = true;
        ToastUtils.showShort(getString(R.string.location_code_is_visible));
        mVertifyLocationCodeBean = bean;
    }

    @Override
    public void createInStockOrderno() {
        ToastUtils.showShort(getString(R.string.commit_check_success));
        onBackPressed();
    }

    /**
     * 扫码的返回方法
     *
     * @param requestCode
     * @param result
     */
    @Override
    public void scanSuccess(int requestCode, String result) {
        switch (requestCode) {
            case REQUEST_SCAN_CODE_MATERIIAL:
                LogUitls.d("物料码扫码--->", result);
                etMaterialCode.setText(result);
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params1 = new HashMap<>();
                params1.put("UserId", SpUtils.getInstance().getUserId());
                params1.put("OrgId", SpUtils.getInstance().getOrgId());
                params1.put("MAC", PackageUtils.getMac());
                params1.put("BillId", allotScanResult.getSummaryResults().getBillId());
                params1.put("SrcBillType", 50);
                params1.put("DestBillType", 50);
                params1.put("ScanId", scanId);
                params1.put("BinCode", locationCode);
                params1.put("BarcodeNo", result);
                getPresenter().materialScanNetWork(params1, result);
                break;
            case REQUEST_SCAN_CODE_LIB_LOATION:
                /**
                 * 重新扫描库位码的时候 将库位码是否有效的标识更改成false
                 */
                locationCodeIsUse = false;
                LogUitls.d("库位码扫码--->", result);
                //保存库位码
                locationCode = result;
                //设置库位码
                etScanLocation.setText(locationCode);
                /**
                 * 判断库位码是否有效
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BinCode", locationCode);
                getPresenter().vertifyLocationCode(params);
                break;
        }

    }

    @Override
    public void setMaterialEdittextSelect() {
        etMaterialCode.setFocusable(true);
        etMaterialCode.setFocusableInTouchMode(true);
        etMaterialCode.requestFocus();
        etMaterialCode.findFocus();
        Selection.selectAll(etMaterialCode.getText());
    }

    @Override
    public void setLocationSelect() {
        etScanLocation.setText(etScanLocation.getText());
        etScanLocation.setFocusable(true);
        etScanLocation.setFocusableInTouchMode(true);
        etScanLocation.requestFocus();
        etScanLocation.findFocus();
        Selection.selectAll(etScanLocation.getText());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
