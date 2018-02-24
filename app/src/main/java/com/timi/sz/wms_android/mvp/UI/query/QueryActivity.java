package com.timi.sz.wms_android.mvp.UI.query;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.UI.query.count_todayin.CountTodayinActivity;
import com.timi.sz.wms_android.mvp.UI.query.count_todayout.CountTodayOutActivity;
import com.timi.sz.wms_android.mvp.UI.query.orderno_barcode.OrdernoBarcodeActivity;
import com.timi.sz.wms_android.mvp.UI.query.recode_in.RecordInActivity;
import com.timi.sz.wms_android.mvp.UI.query.snform.SNFromActivity;
import com.timi.sz.wms_android.mvp.UI.query.todayin.TodayInActivity;
import com.timi.sz.wms_android.mvp.UI.query.todayout.TodayOutActivity;
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
                intent.setClass(this, TodayOutActivity.class);
                break;
            case R.id.tv_query_today_out://今日出库
                intent.setClass(this, TodayOutActivity.class);
                break;
            case R.id.tv_query_today_in://今日入库
                intent.setClass(this, TodayInActivity.class);
                break;
            case R.id.tv_query_materail_sn_from://snfrom
                intent.setClass(this, SNFromActivity.class);
                break;
            case R.id.tv_query_pro_sn_from:
                intent.setClass(this, OrdernoBarcodeActivity.class);
                break;
            case R.id.tv_query_in_record:
                intent.setClass(this, RecordInActivity.class);
                break;
            case R.id.tv_query_out_record:
                intent.setClass(this, TodayOutActivity.class);
                break;
            case R.id.tv_query_in_total://今日入库统计
                intent.setClass(this, CountTodayinActivity.class);
                break;
            case R.id.tv_query_out_total://今日出库统计
                intent.setClass(this, CountTodayOutActivity.class);
                break;
        }
        startActivity(intent);
    }

}
