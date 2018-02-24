package com.timi.sz.wms_android.mvp.UI.query.snform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
 * SN追溯查询
 * author: timi
 * create at: 2018/1/31 10:18
 */
public class SNFromActivity extends BaseActivity<SNFromView, SNFromPresenter> implements SNFromView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_snfrom;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("SN追溯查询");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public SNFromPresenter createPresenter() {
        return new SNFromPresenter(this);
    }

    @Override
    public SNFromView createView() {
        return this;
    }
}
