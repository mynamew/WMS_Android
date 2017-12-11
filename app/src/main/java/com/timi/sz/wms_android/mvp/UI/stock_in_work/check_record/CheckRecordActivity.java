package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_record;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
 * 盘点记录
 * author: timi
 * create at: 2017/12/11 16:12
 */
public class CheckRecordActivity extends BaseActivity<CheckRecordView, CheckRecordPresenter> implements CheckRecordView {

    @Override
    public int setLayoutId() {
        return R.layout.activity_check_record;
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
    public CheckRecordPresenter createPresenter() {
        return new CheckRecordPresenter(this);
    }

    @Override
    public CheckRecordView createView() {
        return this;
    }
}
