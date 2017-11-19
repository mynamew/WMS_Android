package com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

public class NormalOutStockActivity extends BaseActivity<NormalOutStockView,NormalOutStockPresenter> implements NormalOutStockView {
    @Override
    public int setLayoutId() {
        return R.layout.activity_normal_out_stock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public NormalOutStockPresenter createPresenter() {
        return null;
    }

    @Override
    public NormalOutStockView createView() {
        return null;
    }

    @Override
    public void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data) {

    }

    @Override
    public void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data) {

    }

    @Override
    public void submitMakeOrAuditBill() {

    }
}
