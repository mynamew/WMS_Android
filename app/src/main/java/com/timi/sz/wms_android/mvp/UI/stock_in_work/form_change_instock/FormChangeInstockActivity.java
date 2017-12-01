package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_instock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
/** 
  * 形态转换 入库上架
  * author: timi    
  * create at: 2017/12/1 9:01
  */  
public class FormChangeInstockActivity extends BaseActivity<FormChangeInstockView,FormChangeInstockPresenter> implements  FormChangeInstockView {
    

    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_instock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
      setActivityTitle(getString(R.string.instock_putaway_form_change));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public FormChangeInstockPresenter createPresenter() {
        return null;
    }

    @Override
    public FormChangeInstockView createView() {
        return null;
    }
}
