package com.timi.sz.wms_android.mvp.UI.query.recode_in;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
  * 入库统计
  * author: timi
  * create at: 2018/2/23 10:31
  */
public class RecordInActivity extends BaseActivity<RecordInView,RecordInPresenter> implements RecordInView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_record_in;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
      setActivityTitle("入库记录");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public RecordInPresenter createPresenter() {
        return new RecordInPresenter(this);
    }

    @Override
    public RecordInView createView() {
        return this;
    }
}
