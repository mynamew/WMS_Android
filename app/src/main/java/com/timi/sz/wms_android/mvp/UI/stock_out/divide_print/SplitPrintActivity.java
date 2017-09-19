package com.timi.sz.wms_android.mvp.UI.stock_out.divide_print;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
/** 
  * 拆分打码的界面
  * author: timi    
  * create at: 2017/9/18 13:46
  */  
public class SplitPrintActivity extends BaseActivity<SplitPrintView, SplitPrintPresenter> implements SplitPrintView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_split_print;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
      setActivityTitle("拆分打码");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public SplitPrintPresenter createPresenter() {
        return new SplitPrintPresenter(this);
    }

    @Override
    public SplitPrintView createView() {
        return this;
    }
}
