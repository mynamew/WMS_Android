package com.timi.sz.wms_android.mvp.UI.query.orderno_barcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
 * ;
 * '
 * '''''
 * <p>
 * 单据条码
 * author: timi
 * create at: 2018/2/6 13:48
 */
public class OrdernoBarcodeActivity extends BaseActivity<OrdernoBarcodeView, OrdernoBarcodePresenter> implements OrdernoBarcodeView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_orderno_barcode;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("单据条码查询");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public OrdernoBarcodePresenter createPresenter() {
        return new OrdernoBarcodePresenter(this);
    }

    @Override
    public OrdernoBarcodeView createView() {
        return this;
    }
}
