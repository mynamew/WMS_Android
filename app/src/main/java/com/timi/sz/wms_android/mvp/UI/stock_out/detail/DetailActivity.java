package com.timi.sz.wms_android.mvp.UI.stock_out.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

public class DetailActivity extends BaseActivity<DetailView,DetailPresenter> implements DetailView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
       setActivityTitle("出库详情");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public DetailPresenter createPresenter() {
        return null;
    }

    @Override
    public DetailView createView() {
        return null;
    }
}
