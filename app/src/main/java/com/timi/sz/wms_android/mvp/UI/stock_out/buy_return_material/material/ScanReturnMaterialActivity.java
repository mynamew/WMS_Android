package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_BUY_RETURN_ORDERNO_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;

public class ScanReturnMaterialActivity extends BaseActivity<ScanReturnMaterialView, ScanReturnMaterialPresenter> implements ScanReturnMaterialView,BaseActivity.ScanQRCodeResultListener {
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_orderno_date)
    TextView tvOrdernoDate;
    @BindView(R.id.tv_material_from)
    TextView tvMaterialFrom;
    @BindView(R.id.tv_material_buyer)
    TextView tvMaterialBuyer;
    @BindView(R.id.tv_instock_num)
    TextView tvInstockNum;
    @BindView(R.id.tv_buy_num)
    TextView tvBuyNum;
    @BindView(R.id.tv_material_scan_tip)
    TextView tvMaterialScanTip;
    @BindView(R.id.tv_material_scan)
    TextView tvMaterialScan;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_have_return_num)
    TextView tvHaveReturnNum;
    //退料单的实体
    private BuyReturnMaterialOrdernoBean ordernoBean;
    //扫到的物料码
    private String  materialCode="";
    @Override
    public int setLayoutId() {
        return R.layout.activity_scan_return_material;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.buy_return_material_scan));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ordernoBean = getIntent().getParcelableExtra(OUT_STOCK_BUY_RETURN_ORDERNO_BEAN);
        /**
         * 上个界面传过来的数据
         */
        setTextViewText(tvOrderno, R.string.order_no, ordernoBean.orderNo);
        setTextViewText(tvOrdernoDate, R.string.buy_date, ordernoBean.date);
        setTextViewText(tvMaterialFrom, R.string.buy_from, ordernoBean.supplier);
        setTextViewText(tvMaterialBuyer, R.string.buyer, ordernoBean.buyer);
        setTextViewText(tvBuyNum, R.string.buy_num, ordernoBean.buyNum);
        setTextViewText(tvInstockNum, R.string.in_stock_num, ordernoBean.instockNum);
        setTextViewText(tvHaveReturnNum, R.string.have_return_num, ordernoBean.instockNum);
        /**
         * 扫码出来的数据
         */
        MaterialBean materialBean = ordernoBean.bean;
        setTextViewText(tvMaterialCode, R.string.material_code, materialBean.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, materialBean.getMaterialName());
        setTextViewText(tvMaterialModel, R.string.material_model, materialBean.getMaterialModel());
    }

    @Override
    public ScanReturnMaterialPresenter createPresenter() {
        return new ScanReturnMaterialPresenter(this);
    }

    @Override
    public ScanReturnMaterialView createView() {
        return this;
    }



    @OnClick({R.id.tv_material_scan, R.id.iv_material_scan, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_material_scan:
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL,this);
                break;
            case R.id.iv_material_scan:
                scan(Constants.REQUEST_SCAN_CODE_MATERIIAL,this);
                break;
            case R.id.btn_commit://退料出库扫描 将扫描结果提交到服务器
                getPresenter().commitMaterialScanToOrederno(materialCode);
                break;
        }
    }

    @Override
    public void materialScan(MaterialBean bean) {
        /**
         * 设置已退数量
         */
        setTextViewText(tvHaveReturnNum, R.string.have_return_num, "(" + bean.getMaterialBuyNum() + ")" + (ordernoBean.instockNum + Integer.parseInt(bean.getMaterialBuyNum())));
        /**
         * 设置物料信息
         */
        setTextViewText(tvMaterialCode, R.string.material_code, bean.getMaterialCode());
        setTextViewText(tvMaterialName, R.string.material_name, bean.getMaterialName());
        setTextViewText(tvMaterialModel, R.string.material_model, bean.getMaterialModel());

    }

    @Override
    public void commitMaterialScanToOrederno(CommitMaterialScanToOredernoBean bean) {
        ToastUtils.showShort("提交成功");
        onBackPressed();
    }

    @Override
    public void scanSuccess(int requestCode, String result) {
        LogUitls.d("物料码扫码--->", result);
        /**
         * 设置扫描返回结果
         */
        materialCode=result;
        tvMaterialScan.setText(materialCode);
        getPresenter().materialScan(materialCode);
    }
}
