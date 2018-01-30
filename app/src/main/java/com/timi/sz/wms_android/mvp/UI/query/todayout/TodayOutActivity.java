package com.timi.sz.wms_android.mvp.UI.query.todayout;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

public class TodayOutActivity extends BaseActivity<TodayOutView,TodayOutPresenter> implements TodayOutView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_today_out;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("今日出库");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public TodayOutPresenter createPresenter() {
        return new TodayOutPresenter(this);
    }

    @Override
    public TodayOutView createView() {
        return this;
    }
}
