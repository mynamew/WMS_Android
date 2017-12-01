package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
/** 
  * 形态转换出库
  * author: timi    
  * create at: 2017/12/1 8:34
  */  
public class FormChangeOutstockActivity extends BaseActivity<FormChangeOutstockView,FormChangeOutstockPresenter> implements FormChangeOutstockView {

    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_outstock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
         setActivityTitle(getString(R.string.outstock_scan_form_change));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public FormChangeOutstockPresenter createPresenter() {
        return new FormChangeOutstockPresenter(this);
    }

    @Override
    public FormChangeOutstockView createView() {
        return this;
    }
}
