package com.timi.sz.wms_android.mvp.UI.query;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 综合查询
 * author: timi
 * create at: 2017/8/17 14:51
 */
public class QueryActivity extends BaseNoMvpActivity {
    @BindView(R.id.nescroll_query)
    NestedScrollView nescrollQuery;
    @BindView(R.id.iv_title_bg)
    ImageView ivTitleBg;
    @BindView(R.id.iv_banner_bg)
    ImageView ivBannerBg;

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
        nescrollQuery.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                /**
                 * 设置可见 及透明度的变化
                 */
                ivTitleBg.setVisibility(View.VISIBLE);
                ivTitleBg.setAlpha(scrollY);
                ivBannerBg.setAlpha(255 - scrollY > 1 ? 255 - scrollY : 1);
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_query_library_transfer, R.id.tv_query_today_out, R.id.tv_query_today_in, R.id.tv_query_materail_sn_from, R.id.tv_query_pro_sn_from, R.id.tv_query_in_record, R.id.tv_query_out_record, R.id.tv_query_in_total, R.id.tv_query_out_total})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_query_library_transfer:
                break;
            case R.id.tv_query_today_out:
                break;
            case R.id.tv_query_today_in:
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
