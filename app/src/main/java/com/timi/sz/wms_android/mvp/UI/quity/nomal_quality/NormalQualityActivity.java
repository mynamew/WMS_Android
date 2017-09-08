package com.timi.sz.wms_android.mvp.UI.quity.nomal_quality;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
 * 普通质检
 * author: timi
 * create at: 2017/9/6 17:22
 */
public class NormalQualityActivity extends BaseActivity<NormalQualityView, NormalQualityPresenter> implements NormalQualityView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_normal_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
         setActivityTitle("来料—质检详情（普检）");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public NormalQualityPresenter createPresenter() {
        return null;
    }

    @Override
    public NormalQualityView createView() {
        return null;
    }
}
