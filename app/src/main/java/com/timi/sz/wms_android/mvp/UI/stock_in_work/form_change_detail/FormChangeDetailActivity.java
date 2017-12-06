package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.instock.OrderDetailData;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * 形态转换详情
 * author: timi
 * create at: 2017/12/1 9:49
 */
public class FormChangeDetailActivity extends BaseActivity<FormChangeDetailView, FormChangeDetailPresenter> implements FormChangeDetailView {

    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_from_change)
    RecyclerView rlvFromChange;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;
    private int intentCode;
    private String billId;

    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.form_change_instock_detial_title));
        billId = getIntent().getStringExtra(Constants.STOCK_IN_WORK_BILLID);
        intentCode = getIntent().getIntExtra(Constants.STOCK_IN_WORK_CODE_STR, 0);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }

    @Override
    public FormChangeDetailPresenter createPresenter() {
        return new FormChangeDetailPresenter(this);
    }

    @Override
    public FormChangeDetailView createView() {
        return this;
    }

    @Override
    public void getStockInWorkDetail(List<StockInWorkDetailResult> datas) {
    }


}
