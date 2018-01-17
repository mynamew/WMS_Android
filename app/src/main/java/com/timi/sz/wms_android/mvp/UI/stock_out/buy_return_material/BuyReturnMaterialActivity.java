package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material.ScanReturnMaterialActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno.BuyReturnMaterialOrderNoActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_RETURN_MATERIAL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;

/**
  * 采购退料 的界面
  * author: timi    
  * create at: 2017/11/29 16:43
  */  
public class BuyReturnMaterialActivity extends BaseActivity<BuyReturnMaterialView, BuyReturnMaterialPresenter> implements BuyReturnMaterialView, BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.et_putaway_scan_material)
    EditText etPutawayScanMaterial;
    @BindView(R.id.iv_putaway_scan_material)
    ImageView ivPutawayScanMaterial;
    @BindView(R.id.tv_buy_return_material_orderno)
    TextView tvBuyReturnMaterialOrderno;
    @BindView(R.id.et_buy_return_material_orderno)
    EditText etBuyReturnMaterialOrderno;
    @BindView(R.id.iv_buy_return_material_orderno)
    ImageView ivBuyReturnMaterialOrderno;
    private int intentCode;

    private  boolean isCreateBill=false;//是否是制单
    @Override
    public int setLayoutId() {
        return R.layout.activity_buy_return_material;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.out_stock_buy_return_material));
        intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, -1);
        isCreateBill = getIntent().getBooleanExtra("isCreateBill", false);
    }

    @Override
    public void initView() {
        setEdittextListener( etBuyReturnMaterialOrderno,Constants.REQUEST_SCAN_CODE_RETURN_MATERIAL,R.string.please_input_return_material_orderno_or_scan,0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 退料单号的 网络请求
                 */
                Map<String,Object> params=new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillNo", result);
                getPresenter().returnMaterialScanNetWork(params);
            }
        });
        setEdittextListener(etPutawayScanMaterial,Constants.REQUEST_SCAN_CODE_ORDERNO,R.string.please_scan_material_code,0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                /**
                 * 物料扫描的 网络请求
                 */
                Map<String,Object> params=new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillNo", result);
                getPresenter().materialScanNetWork(params);
            }
        });
        /**
         * 是否是制单
         */
        if(isCreateBill){
            findViewById(R.id.rl_return_material_orderno).setVisibility(View.GONE);
        }else {
            findViewById(R.id.rl_return_material).setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public BuyReturnMaterialPresenter createPresenter() {
        return new BuyReturnMaterialPresenter(this);
    }

    @Override
    public BuyReturnMaterialView createView() {
        return this;
    }

    @OnClick({ R.id.iv_putaway_scan_material,R.id.iv_buy_return_material_orderno})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_putaway_scan_material://扫物料码的点击事件
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL, this);
                break;
            case R.id.iv_buy_return_material_orderno://退料单号
                scan(REQUEST_SCAN_CODE_RETURN_MATERIAL, this);
                break;
        }
    }

    @Override
    public void materialScanResult(BuyReturnMaterialByMaterialCodeData bean) {
        Intent intent = new Intent(this, ScanReturnMaterialActivity.class);
        intent.putExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        startActivity(intent);
    }

    @Override
    public void ReturnMaterialOrderNoScanResult(BuyReturnMaterialByOrdernoData bean) {
        Intent intent = new Intent(this, BuyReturnMaterialOrderNoActivity.class);
        intent.putExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        startActivity(intent);
    }

    @Override
    public void setMaterialEdittextSelect() {
        etPutawayScanMaterial.setText(etPutawayScanMaterial.getText());
        etPutawayScanMaterial.setFocusable(true);
        etPutawayScanMaterial.setFocusableInTouchMode(true);
        etPutawayScanMaterial.requestFocus();
        etPutawayScanMaterial.findFocus();
        Selection.selectAll(etPutawayScanMaterial.getText());
    }

    @Override
    public void setReturnMaterialOrderNoSelect() {
        etBuyReturnMaterialOrderno.setText(etBuyReturnMaterialOrderno.getText());
        etBuyReturnMaterialOrderno.setFocusable(true);
        etBuyReturnMaterialOrderno.setFocusableInTouchMode(true);
        etBuyReturnMaterialOrderno.requestFocus();
        etBuyReturnMaterialOrderno.findFocus();
        Selection.selectAll(etBuyReturnMaterialOrderno.getText());
    }

    @Override
    public void scanSuccess(int requestCode, String result) {
        switch (requestCode) {
            case REQUEST_SCAN_CODE_MATERIIAL://物料扫码
                LogUitls.e("物料码扫码--->", result);
                /**
                 * 设置扫描返回结果
                 */
                etPutawayScanMaterial.setText(result);
                /**
                 * 扫物料码的网络请求
                 */
                Map<String,Object> paramsMaterial=new HashMap<>();
                paramsMaterial.put("UserId", SpUtils.getInstance().getUserId());
                paramsMaterial.put("OrgId", SpUtils.getInstance().getOrgId());
                paramsMaterial.put("MAC", PackageUtils.getMac());
                paramsMaterial.put("BillNo", result);
                getPresenter().materialScanNetWork(paramsMaterial);
                break;
            case REQUEST_SCAN_CODE_RETURN_MATERIAL://退料单号
                LogUitls.e("退了单号--->", result);
                /**
                 * 设置扫描返回结果
                 */
                etBuyReturnMaterialOrderno.setText(result);
                /**
                 * 退料单号的 网络请求
                 */
                Map<String,Object> params=new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillNo", result);
                getPresenter().returnMaterialScanNetWork(params);
                break;
        }
    }
}
