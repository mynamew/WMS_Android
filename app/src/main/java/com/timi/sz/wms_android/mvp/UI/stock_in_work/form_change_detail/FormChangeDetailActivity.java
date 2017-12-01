package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
 * 形态转换详情
 * author: timi
 * create at: 2017/12/1 9:49
 */
public class FormChangeDetailActivity extends BaseActivity<FormChangeDetailView, FormChangeDetailPresenter> implements FormChangeDetailView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.form_change_instock_detial_title));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public FormChangeDetailPresenter createPresenter() {
        return new FormChangeDetailPresenter(this);
    }

    @Override
    public FormChangeDetailView createView() {
        return this;
    }
}
