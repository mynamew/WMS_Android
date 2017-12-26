package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
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
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockOutSubmitScanMaterialEvent;
import com.timi.sz.wms_android.http.message.event.SubmitBarcodeLotPickOutSplitEvent;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.detail.StockInWorkDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BARCODENO;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BATCh_DETAILID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_CURRENT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DESBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIALID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_ATTR;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_CODE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_MODEL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_NAME;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_NORMAL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_OUT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SRCBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;

/**
 * 形态转换出库
 * author: timi
 * create at: 2017/12/1 8:34
 */
public class FormChangeOutstockActivity extends BaseActivity<FormChangeOutstockView, FormChangeOutstockPresenter> implements FormChangeOutstockView {

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
    @BindView(R.id.tv_scan_material_tip)
    TextView tvScanMaterialTip;
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
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.ll_material_info)
    LinearLayout llMaterialInfo;

    private FormChangeOutResult formChangeOutResult;
    private int intentCode;
    private int scanId;
    private int billId;

    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_outstock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.outstock_scan_form_change));
        formChangeOutResult = new Gson().fromJson(getIntent().getStringExtra(Constants.STOCK_IN_WORK_BEAN), FormChangeOutResult.class);
        intentCode = getIntent().getIntExtra(Constants.STOCK_IN_WORK_CODE_STR, 0);
        scanId = formChangeOutResult.getScanId();
        billId = formChangeOutResult.getBillId();
    }

    @Override
    public void initView() {
        /**
         * 设置查看详情的点击事件
         */
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 查看详情
                 */
                Intent it = new Intent(FormChangeOutstockActivity.this, StockInWorkDetailActivity.class);
                it.putExtra(Constants.STOCK_IN_WORK_CODE_STR, intentCode);
                it.putExtra(Constants.STOCK_IN_WORK_BILLID, formChangeOutResult.getBillId());
                startActivity(it);
            }
        });
        setEdittextListener(etScanMaterial, Constants.REQUEST_SCAN_CODE_MATERIIAL, R.string.please_scan_material_code, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 物料扫码并上架的网络请求
                 */
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("SrcBillType", 53);
                params.put("DestBillType", 53);
                params.put("ScanId", scanId);
                params.put("BarcodeNo", result);
                getPresenter().submitBarcodeOutAudit(params);
            }
        });
    }

    @Override
    public void initData() {
        setHeadContent(formChangeOutResult.getBillCode(), formChangeOutResult.getBillDate(), formChangeOutResult.getQty(), formChangeOutResult.getWaitQty(), formChangeOutResult.getScanQty());
    }

    @Override
    public FormChangeOutstockPresenter createPresenter() {
        return new FormChangeOutstockPresenter(this);
    }

    @Override
    public FormChangeOutstockView createView() {
        return this;
    }

    /**
     * 设置 头部的信息
     *
     * @param orderno
     * @param date
     * @param outStockNum
     * @param waitCountNum
     * @param haveCountNum
     */
    public void setHeadContent(String orderno, String date, int outStockNum, int waitCountNum, int haveCountNum) {
        tvOrderno.setText(orderno);
        tvDate.setText(date);
        tvOutstockTotalNum.setText(String.valueOf(outStockNum));
        tvWaitPointNum.setText(String.valueOf(waitCountNum));
        tvHaveCountNum.setText(String.valueOf(haveCountNum));
    }

    @OnClick({ R.id.iv_scan_material})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan_material:
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etScanMaterial.setText(result);
                        /**
                         * 物料扫码并上架的网络请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", 53);
                        params.put("DestBillType", 53);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", result);
                        getPresenter().submitBarcodeOutAudit(params);
                    }
                });
                break;
        }
    }

    @Override
    public void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data) {
        llMaterialInfo.setVisibility(View.VISIBLE);
        /**
         * 设置物料的信息
         */
        tvMaterialName.setText(data.getMaterialName());
        tvMaterialCode.setText(data.getMaterialCode());
        tvMaterialAttr.setText(data.getMaterialAttribute());
        tvMaterialNmodel.setText(data.getMaterialStandard());
        /**
         * 设置 是否显示附加属性
         */
        setMaterialAttrStatus(findViewById(R.id.ll_material_attr));
        /**
         * 超出数量  跳转到拆分条吗界面
         */
        if (data.getExceedQty() > 0) {
            Intent intent = new Intent(this, SplitPrintActivity.class);
            intent.putExtra(OUT_STOCK_PRINT_BILLID, billId);
            intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
            intent.putExtra(OUT_STOCK_PRINT_SRCBILLTYPE, 53);
            intent.putExtra(OUT_STOCK_PRINT_BARCODENO, etScanMaterial.getText().toString().trim());
            intent.putExtra(OUT_STOCK_PRINT_DESBILLTYPE, 53);
            intent.putExtra(OUT_STOCK_PRINT_SCANID, data.getScanId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_ATTR, data.getMaterialAttribute());
            intent.putExtra(OUT_STOCK_PRINT_MATERIALID, data.getMaterialId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_NAME, data.getMaterialName());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_CODE, data.getMaterialCode());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_MODEL, data.getMaterialStandard());
            intent.putExtra(OUT_STOCK_PRINT_CURRENT_QTY, data.getBarcodeQty());
            intent.putExtra(OUT_STOCK_PRINT_OUT_QTY, data.getExceedQty());
            intent.putExtra(OUT_STOCK_PRINT_NORMAL, true);
            startActivity(intent);
        } else {
            ToastUtils.showShort(getString(R.string.commit_success));
            scanId = data.getScanId();
            /**
             * 设置物料数量
             */
            setTextViewContent(tvWaitPointNum, formChangeOutResult.getQty() - data.getTotalScanQty());
            setTextViewContent(tvHaveCountNum, data.getTotalScanQty());
            tvMaterialNum.setText("(" + data.getBarcodeQty() + ")" + data.getTotalScanQty() + "/" + formChangeOutResult.getQty());

        }
    }

    @Override
    public void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data) {

    }

    @Override
    public void submitMakeOrAuditBill() {
    }

    @Override
    public void setMaterialEdittextSelect() {
        etScanMaterial.setFocusable(true);
        etScanMaterial.setFocusableInTouchMode(true);
        etScanMaterial.requestFocus();
        etScanMaterial.findFocus();
        Selection.selectAll(etScanMaterial.getText());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void submitBarcodeSplitSuccess(SubmitBarcodeLotPickOutSplitEvent event) {
        if (event.getEvent().equals(SubmitBarcodeLotPickOutSplitEvent.SUBMIT_BARCODE_SPLIT_SUCCESS)) {
            SubmitBarcodeLotPickOutSplitResult result = event.getResult();
            /**
             * 发送事件  传递 scanid
             */
            StockOutSubmitScanMaterialEvent stockOutSubmitScanMaterialEvent = new StockOutSubmitScanMaterialEvent(StockOutSubmitScanMaterialEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS);
            SubmitBarcodeLotPickOutResult resultScanMaterial = new SubmitBarcodeLotPickOutResult();
            resultScanMaterial.setBarcodeQty(result.getBarcodeQty());
            resultScanMaterial.setExceedQty(result.getExceedQty());
            resultScanMaterial.setScanId(result.getScanId());
            resultScanMaterial.setMaterialCode(result.getMaterialCode());
            resultScanMaterial.setTotalScanQty(result.getTotalScanQty());
            resultScanMaterial.setLineScanQty(result.getLineScanQty());
            stockOutSubmitScanMaterialEvent.setResult(resultScanMaterial);
            BaseMessage.post(stockOutSubmitScanMaterialEvent);
            //设置scanid
            scanId = result.getScanId();
            //设置数量
            setTextViewContent(tvHaveCountNum, result.getTotalScanQty());
            setTextViewContent(tvWaitPointNum, formChangeOutResult.getQty() - result.getTotalScanQty());
            tvMaterialNum.setText("(" + result.getBarcodeQty() + ")" + result.getTotalScanQty() + "/" + formChangeOutResult.getQty());
        }
    }
}
