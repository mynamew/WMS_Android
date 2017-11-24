package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust_detail;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import butterknife.BindView;
import butterknife.ButterKnife;
/** 
  * 库存调整详情
  * author: timi    
  * create at: 2017/11/24 16:29
  */  
public class LibAdjustDetailActivity extends BaseActivity<LibAdjustDetailView, LibAdjustDetailPresenter> implements LibAdjustDetailView {


    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_un_instock)
    RecyclerView rlvUnInstock;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;

    @Override
    public int setLayoutId() {
        return R.layout.activity_lib_adjust_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public LibAdjustDetailPresenter createPresenter() {
        return null;
    }

    @Override
    public LibAdjustDetailView createView() {
        return null;
    }

    @Override
    public void getDetail() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
