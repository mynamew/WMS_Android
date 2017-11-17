package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_FEED_MATERIAL_POINT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_BEAN;

/**
 * 委外补料的 物料清单界面
 * author: timi
 * create at: 2017/11/9 10:29
 */
public class OutSourceFeedActivity extends BaseActivity<OutSourceFeedView, OutSourceFeedPresenter> implements OutSourceFeedView {

    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_orderno_tip)
    TextView tvOrdernoTip;
    @BindView(R.id.tv_outsource_orderno)
    TextView tvOutsourceOrderno;
    @BindView(R.id.tv_create_orderno_date_tip)
    TextView tvCreateOrdernoDateTip;
    @BindView(R.id.tv_create_orderno_date)
    TextView tvCreateOrdernoDate;
    @BindView(R.id.tv_stock_name_tip)
    TextView tvStockNameTip;
    @BindView(R.id.tv_stock_name)
    TextView tvStockName;
    @BindView(R.id.tv_strict_name_tip)
    TextView tvStrictNameTip;
    @BindView(R.id.tv_strict_name)
    TextView tvStrictName;
    @BindView(R.id.tv_send_material_total_num_tip)
    TextView tvSendMaterialTotalNumTip;
    @BindView(R.id.tv_buy_num)
    TextView tvBuyNum;
    @BindView(R.id.tv_have_get_total_num)
    TextView tvHaveGetTotalNum;
    @BindView(R.id.tv_can_get_total_num_tip)
    TextView tvCanGetTotalNumTip;
    @BindView(R.id.tv_can_get_total_num)
    TextView tvCanGetTotalNum;
    @BindView(R.id.tv_wait_count_num_tip)
    TextView tvWaitCountNumTip;
    @BindView(R.id.tv_wait_point_num)
    TextView tvWaitPointNum;
    @BindView(R.id.tv_have_count_num_tip)
    TextView tvHaveCountNumTip;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_orderno_info)
    RecyclerView rlvOrdernoInfo;
    @BindView(R.id.btn_point_commit)
    Button btnPointCommit;
    @BindView(R.id.nescroll_feed)
    NestedScrollView nescrollFeed;

    @Override
    public int setLayoutId() {
        return R.layout.activity_out_source_feed;
    }

    private QueryOutSourceFeedByInputResult queryOutSourceFeedByInputResult;
    private List<QueryOutSourceFeedByInputResult.DetailResultsBean> mDatas = new ArrayList<>();
    private BaseRecyclerAdapter<QueryOutSourceFeedByInputResult.DetailResultsBean> adapter;

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.outsource_feed_point_title));
        queryOutSourceFeedByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_OUTSOURCE_FEED_BEAN), QueryOutSourceFeedByInputResult.class);
    }

    @Override
    public void initView() {
        QueryOutSourceFeedByInputResult.SummaryResultsBean summaryResults = queryOutSourceFeedByInputResult.getSummaryResults();
        if (summaryResults.isIsLotPick()) {
            initAdapter();
        } else {
            rlvOrdernoInfo.setVisibility(View.GONE);
        }
    }

    private void initAdapter() {
        mDatas.clear();
        /**
         * 是否点击了显示 进行数据的设置
         */
        if (ivShowMore.isSelected()) {
            mDatas.addAll(queryOutSourceFeedByInputResult.getDetailResults());
        } else {
            List<QueryOutSourceFeedByInputResult.DetailResultsBean> detailResults = queryOutSourceFeedByInputResult.getDetailResults();
            for (int i = 0; i < detailResults.size(); i++) {
                if (detailResults.get(i).getWaitQty() > 0) {
                    mDatas.add(detailResults.get(i));
                }
            }
        }
        /**
         * 初始化 adapter
         */
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<QueryOutSourceFeedByInputResult.DetailResultsBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_outsource_feed;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, QueryOutSourceFeedByInputResult.DetailResultsBean item) {
                    holder.setTextView(R.id.tv_line_num, item.getLine());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialName());
                    holder.setTextView(R.id.tv_send_material_num, item.getQty());
                    holder.setTextView(R.id.tv_wait_count_num, item.getWaitQty());
                    holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                    holder.setTextView(R.id.tv_material_name, item.getMaterialName());
                    holder.setTextView(R.id.tv_material_model, item.getMaterialStandard());
                }
            };
            rlvOrdernoInfo.setAdapter(adapter);
            rlvOrdernoInfo.setLayoutManager(new LinearLayoutManager(this));
            rlvOrdernoInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    Intent it = new Intent(OutSourceFeedActivity.this, OutSourceFeedMaterialPointActivity.class);
                    it.putExtra(OUT_STOCK_OUT_SOURCE_FEED_MATERIAL_POINT_BEAN, new Gson().toJson(mDatas.get(pos)));
                    it.putExtra(OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN, new Gson().toJson(queryOutSourceFeedByInputResult.getSummaryResults()));
                    startActivity(it);
                }
            });
            rlvOrdernoInfo.scrollToPosition(0);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initData() {
        QueryOutSourceFeedByInputResult.SummaryResultsBean summaryResults = queryOutSourceFeedByInputResult.getSummaryResults();
        tvOutsourceOrderno.setText(summaryResults.getBillCode());
        tvCreateOrdernoDate.setText(summaryResults.getBillDate());
        tvStockName.setText(TextUtils.isEmpty(summaryResults.getWarehouseName()) ? getString(R.string.none) : summaryResults.getWarehouseName());
        tvStrictName.setText(TextUtils.isEmpty(summaryResults.getRegionName()) ? getString(R.string.none) : summaryResults.getRegionName());
        tvBuyNum.setText(String.valueOf(summaryResults.getQty()));
        tvWaitPointNum.setText(String.valueOf(summaryResults.getWaitQty()));
        tvHaveCountNum.setText(String.valueOf(summaryResults.getScanQty()));
    }

    @Override
    public OutSourceFeedPresenter createPresenter() {
        return new OutSourceFeedPresenter(this);
    }

    @Override
    public OutSourceFeedView createView() {
        return this;
    }

    @OnClick({R.id.btn_point_commit, R.id.iv_show_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show_more:
                ivShowMore.setSelected(!ivShowMore.isSelected());
                initAdapter();
                break;
            case R.id.btn_point_commit:
                if (queryOutSourceFeedByInputResult.getSummaryResults().getScanId() == 0) {
                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("ScanId", queryOutSourceFeedByInputResult.getSummaryResults().getScanId());
                params.put("SubmitType", 0);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }

    @Override
    public void submitMakeOrAuditBill() {
        ToastUtils.showShort(getString(R.string.commit_check_success));
        onBackPressed();
    }
}
