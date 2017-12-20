package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import android.os.Bundle;
import android.text.Selection;
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
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;

/**
 * 扫退料获取到的采购单信息 进行采购退料的界面
 * author: timi
 * create at: 2017/11/7 8:51
 */
public class ScanReturnMaterialActivity extends BaseActivity<ScanReturnMaterialView, ScanReturnMaterialPresenter> implements ScanReturnMaterialView, BaseActivity.ScanQRCodeResultListener {

    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateOrdernoDate;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_buyer)
    TextView tvBuyer;
    @BindView(R.id.tv_instock_num)
    TextView tvInstockNum;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.tv_material_scan_tip)
    TextView tvMaterialScanTip;
    @BindView(R.id.tv_buy_num)
    TextView tvBuyNum;
    @BindView(R.id.et_material_scan)
    EditText etMaterialScan;
    @BindView(R.id.iv_material_scan)
    ImageView ivMaterialScan;
    @BindView(R.id.tv_return_num)
    TextView tvReturnNum;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    //退料单的实体
    private BuyReturnMaterialByMaterialCodeData ordernoBean;
    //扫到的物料码
    private String materialCode = "";
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_scan_return_material;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.buy_return_material_orderno_title));
        intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, -1);

    }

    @Override
    public void initView() {
        etMaterialScan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /**
                     * 输入的内容
                     */
                    String inputStr = etMaterialScan.getText().toString().trim();
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
                        params.put("BillId", ordernoBean.getBillId());
                        params.put("SrcBillType", ordernoBean.getBillType());
                        params.put("BillDetailId", ordernoBean.getBillDetailId());
                        params.put("ScanId", ordernoBean.getScanId());
                        params.put("BarcodeNo", inputStr);
                        getPresenter().submitBarcodePurReturn(params);
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        ordernoBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN), BuyReturnMaterialByMaterialCodeData.class);
        /**
         * 上个界面传过来的数据
         */
        tvOrderno.setText(ordernoBean.getBillCode());
        tvCreateOrdernoDate.setText(ordernoBean.getBillDate());
        tvSupplier.setText(ordernoBean.getSupplierName());
        tvBuyer.setText(ordernoBean.getPurEmployeeName());
        tvBuyNum.setText(String.valueOf(ordernoBean.getPoQty()));
        tvInstockNum.setText(String.valueOf(ordernoBean.getInStockQty()));
        tvMaterialCode.setText(ordernoBean.getMaterialCode());
        tvMaterialName.setText(ordernoBean.getMaterialName());
        tvMaterialModel.setText(ordernoBean.getMaterialStandard());
        tvMaterialAttr.setText(((TextUtils.isEmpty(ordernoBean.getMaterialAttribute())) ? getString(R.string.none) : ordernoBean.getMaterialAttribute()));
        tvReturnNum.setText(ordernoBean.getReturnQty() + "/" + ordernoBean.getUsedQty());

    }

    @Override
    public ScanReturnMaterialPresenter createPresenter() {
        return new ScanReturnMaterialPresenter(this);
    }

    @Override
    public ScanReturnMaterialView createView() {
        return this;
    }

    @OnClick({R.id.iv_material_scan, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_material_scan:
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.btn_commit://退料出库扫描 将扫描结果提交到服务器
                if(TextUtils.isEmpty(etMaterialScan.getText().toString().trim())){
                    ToastUtils.showShort(getString(R.string.please_input_return_matrial_code_or_scan));
                    return;
                }
                if(ordernoBean.getScanId()==0){//scanid 为0  证明未扫过条码或者条码已经入库 或者出库过了
                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("ScanId", ordernoBean.getScanId());
                params.put("SubmitType", 0);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }


    @Override
    public void submitBarcodeOutAudit(SubmitBarcodeOutAuditData bean) {

        onBackPressed();
    }

    @Override
    public void submitBarcodePurReturn(SubmitBarcodePurReturnData bean) {
        ToastUtils.showShort(getString(R.string.commit_success));
        /**
         * 设置已退数量
         */
        ordernoBean.setReturnQty(ordernoBean.getReturnQty() + bean.getBarcodeQty());
        tvReturnNum.setText(ordernoBean.getReturnQty() + "/" + ordernoBean.getUsedQty());
        //设置 scanId
        ordernoBean.setScanId(bean.getScanId());
    }

    @Override
    public void submitMakeOrAuditBill() {
        ToastUtils.showShort(getString(R.string.commit_check_success));
        onBackPressed();
    }

    @Override
    public void setBarcodeSelect() {
        etMaterialScan.setFocusable(true);
        etMaterialScan.setFocusableInTouchMode(true);
        etMaterialScan.requestFocus();
        etMaterialScan.findFocus();
        Selection.selectAll(etMaterialScan.getText());
    }

    @Override
    public void scanSuccess(int requestCode, String result) {
        LogUitls.d("物料码扫码--->", result);
        /**
         * 设置扫描返回结果
         */
        materialCode = result;
        etMaterialScan.setText(materialCode);
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillId", ordernoBean.getBillId());
        params.put("BillType", ordernoBean.getBillType());
        params.put("BillDetailId", ordernoBean.getBillDetailId());
        params.put("ScanId", ordernoBean.getScanId());
        params.put("BarcodeNo", materialCode);
        getPresenter().submitBarcodePurReturn(params);
    }
}
