package com.timi.sz.wms_android.mvp.UI.query;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.UI.query.todayin.TodayInActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 综合查询
 * author: timi
 * create at: 2017/8/17 14:51
 */
public class QueryActivity extends BaseNoMvpActivity {
    @BindView(R.id.nescroll_query)
    NestedScrollView nescrollQuery;
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

    @OnClick({R.id.tv_query_library_transfer, R.id.tv_query_today_out, R.id.tv_query_today_in, R.id.tv_query_materail_sn_from, R.id.tv_query_pro_sn_from, R.id.tv_query_in_record, R.id.tv_query_out_record, R.id.tv_query_in_total, R.id.tv_query_out_total})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            case R.id.tv_query_library_transfer:
                break;
            case R.id.tv_query_today_out:
                break;
            case R.id.tv_query_today_in:
                intent.setClass(this, TodayInActivity.class);
                break;
            case R.id.tv_query_materail_sn_from:
                break;
            case R.id.tv_query_pro_sn_from:
                break;
            case R.id.tv_query_in_record:
                break;
            case R.id.tv_query_out_record:
                break;
            case R.id.tv_query_in_total:
                break;
            case R.id.tv_query_out_total:
                break;
        }
        startActivity(intent);
    }

}
