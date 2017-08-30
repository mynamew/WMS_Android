package com.timi.sz.wms_android.mvp.UI.stock_out.other.scan;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

public class OtherOutStockScanActivity extends BaseActivity<OtherOutStockScanView,OtherOutStockScanPresenter> implements OtherOutStockScanView {
    @Override
    public int setLayoutId() {
        return R.layout.activity_other_out_stock_scan;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
         setActivityTitle(getString(R.string.other_out_stock_scan_title));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public OtherOutStockScanPresenter createPresenter() {
        return new OtherOutStockScanPresenter(this);
    }

    @Override
    public OtherOutStockScanView createView() {
        return this;
    }
}
