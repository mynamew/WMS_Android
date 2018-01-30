package com.timi.sz.wms_android.mvp.UI.query.todayin;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
 * 今日入库
 */
public class TodayInActivity extends BaseActivity<TodayInView, TodayInPresenter> implements TodayInView {
    @Override
    public int setLayoutId() {
        return R.layout.activity_today_in;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("今日入库");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public TodayInPresenter createPresenter() {
        return new TodayInPresenter(this);
    }

    @Override
    public TodayInView createView() {
        return this;
    }
}
