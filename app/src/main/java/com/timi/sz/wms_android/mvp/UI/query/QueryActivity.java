package com.timi.sz.wms_android.mvp.UI.query;

import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 综合查询
 * author: timi
 * create at: 2017/8/17 14:51
 */
public class QueryActivity extends BaseNoMvpActivity {
    @Override
    public int setLayoutId() {
        return R.layout.activity_query;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("综合查询");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.tv_query_repertory, R.id.tv_query_todayin, R.id.tv_query_todayout, R.id.tv_query_in_record, R.id.tv_query_materail_sn_from, R.id.tv_query_pro_sn_from, R.id.tv_query_out_record, R.id.tv_query_in_total, R.id.tv_query_out_total, R.id.activity_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_query_repertory://库存查询
                break;
            case R.id.tv_query_todayin://今日入库
                break;
            case R.id.tv_query_todayout://今日出库
                break;
            case R.id.tv_query_in_record://入库记录
                break;
            case R.id.tv_query_materail_sn_from://原材料 sn追溯
                break;
            case R.id.tv_query_pro_sn_from://成品sn追溯
                break;
            case R.id.tv_query_out_record://出库记录
                break;
            case R.id.tv_query_in_total://入库统计
                break;
            case R.id.tv_query_out_total://出库统计
                break;
        }
    }
}
