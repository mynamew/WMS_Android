package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.UI.query.QueryActivity;
import com.timi.sz.wms_android.mvp.UI.quity.QulityActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.StockInActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.StockInWorkActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.StockOutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 主页的碎片
 * author: timi
 * create at: 2017-08-17 11:30
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.iv_home_todayin)
    ImageView ivHomeTodayin;
    @BindView(R.id.tv_home_todayin)
    TextView tvHomeTodayin;
    @BindView(R.id.tv_home_todayin_num)
    TextView tvHomeTodayinNum;
    @BindView(R.id.tv_home_todayin_detail)
    TextView tvHomeTodayinDetail;
    @BindView(R.id.iv_home_todayout)
    ImageView ivHomeTodayout;
    @BindView(R.id.tv_home_todayout)
    TextView tvHomeTodayout;
    @BindView(R.id.tv_home_todayout_num)
    TextView tvHomeTodayoutNum;
    @BindView(R.id.tv_home_todayout_detail)
    TextView tvHomeTodayoutDetail;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ll_home_in_lib)
    LinearLayout llHomeInLib;
    @BindView(R.id.ll_home_out_lib)
    LinearLayout llHomeOutLib;
    @BindView(R.id.tv_home_lib_in)
    TextView tvHomeLibIn;
    @BindView(R.id.tv_home_query)
    TextView tvHomeQuery;
    @BindView(R.id.ll_home_quality)
    LinearLayout llHomeQuality;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_home_todayin_detail, R.id.tv_home_todayout_detail, R.id.ll_home_in_lib, R.id.ll_home_out_lib, R.id.tv_home_lib_in, R.id.tv_home_query, R.id.ll_home_quality})
    public void onViewClicked(View view) {
        Intent it=new Intent();
        switch (view.getId()) {
            case R.id.tv_home_todayin_detail://今日入库详情
                break;
            case R.id.tv_home_todayout_detail://今日出库详情
                break;
            case R.id.ll_home_in_lib://入库作业
                it.setClass(getActivity(), StockInActivity.class);
                break;
            case R.id.ll_home_out_lib://出库作业
                it.setClass(getActivity(), StockOutActivity.class);
                break;
            case R.id.tv_home_lib_in://库内作业
                it.setClass(getActivity(), StockInWorkActivity.class);
                break;
            case R.id.tv_home_query://查询
                it.setClass(getActivity(), QueryActivity.class);
                break;
            case R.id.ll_home_quality://质量作业
                it.setClass(getActivity(), QulityActivity.class);
                break;
        }
        startActivity(it);
    }
}
