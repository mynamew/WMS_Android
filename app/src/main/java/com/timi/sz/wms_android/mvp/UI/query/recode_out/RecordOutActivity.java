package com.timi.sz.wms_android.mvp.UI.query.recode_out;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

public class RecordOutActivity extends BaseActivity<RecordOutView, RecordOutPresenter> implements RecordOutView {

    @Override
    public int setLayoutId() {
        return R.layout.activity_record_out;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("出库记录 ");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public RecordOutPresenter createPresenter() {
        return null;
    }

    @Override
    public RecordOutView createView() {
        return null;
    }
}
