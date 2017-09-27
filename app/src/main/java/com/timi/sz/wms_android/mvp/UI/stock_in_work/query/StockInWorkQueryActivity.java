package com.timi.sz.wms_android.mvp.UI.stock_in_work.query;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
/** 
  * 库内作业 查询的界面
  * author: timi    
  * create at: 2017/9/22 13:19
  */  
public class StockInWorkQueryActivity extends BaseActivity<StockInWorkQueryView,StockInWorkQueryPresenter> implements StockInWorkQueryView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_work_query;
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
    public StockInWorkQueryPresenter createPresenter() {
        return new StockInWorkQueryPresenter(this);
    }

    @Override
    public StockInWorkQueryView createView() {
        return this;
    }
}
