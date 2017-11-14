package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feeed;

import android.os.Bundle;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_FEED_MATERIAL_POINT_BEAN;

public class OutSourceFeedMaterialPointActivity extends BaseActivity<OutSourceFeedMaterialPointView, OutSourceFeedMaterialPointPresenter> implements OutSourceFeedMaterialPointView {

    @Override
    public int setLayoutId() {
        return R.layout.activity_out_source_feed_material_point;
    }

    private QueryOutSourceFeedByInputResult.DetailResultsBean detailResultsBean;

    @Override
    public void initBundle(Bundle savedInstanceState) {
        detailResultsBean = new Gson().fromJson(getIntent().getStringExtra(OUT_STOCK_OUT_SOURCE_FEED_MATERIAL_POINT_BEAN), QueryOutSourceFeedByInputResult.DetailResultsBean.class);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public OutSourceFeedMaterialPointPresenter createPresenter() {
        return null;
    }

    @Override
    public OutSourceFeedMaterialPointView createView() {
        return null;
    }
}
