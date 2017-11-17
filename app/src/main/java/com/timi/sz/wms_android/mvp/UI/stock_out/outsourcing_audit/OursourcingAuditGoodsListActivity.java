package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import android.content.Intent;
import android.os.Bundle;
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
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.OutsourceAuditEvent;
import com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feed.OutSourceFeedMaterialPointActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT_BEAN;

/**
 * 委外审核 的 物品清单界面
 */
public class OursourcingAuditGoodsListActivity extends BaseActivity<OursourcingAuditGoodsListView, OursourcingAuditGoodsListPresenter> implements OursourcingAuditGoodsListView {


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

    private List<QueryOutSourcePickByInputResult.MaterialResultsBean> mDatas = new ArrayList<>();
    private BaseRecyclerAdapter<QueryOutSourcePickByInputResult.MaterialResultsBean> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_oursourcing_audit_goods_list;
    }

    private QueryOutSourcePickByInputResult queryOutSourcePickByInputResult;

    @Override
    public void initBundle(Bundle savedInstanceState) {
        BaseMessage.register(this);
        setActivityTitle(getString(R.string.outsource_audit_title));
        queryOutSourcePickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_OUTSOURCE_AUDIT_BEAN), QueryOutSourcePickByInputResult.class);
    }

    @Override
    public void initView() {
        QueryOutSourcePickByInputResult.SummaryResultsBean summaryResults = queryOutSourcePickByInputResult.getSummaryResults();
        tvOutsourceOrderno.setText(summaryResults.getBillCode());
        tvCreateOrdernoDate.setText(summaryResults.getBillDate());
        tvStockName.setText(TextUtils.isEmpty(summaryResults.getWarehouseName()) ? getString(R.string.none) : summaryResults.getWarehouseName());
        tvStrictName.setText(TextUtils.isEmpty(summaryResults.getRegionName()) ? getString(R.string.none) : summaryResults.getRegionName());
        tvBuyNum.setText(String.valueOf(summaryResults.getQty()));
        tvWaitPointNum.setText(String.valueOf(summaryResults.getWaitQty()));
        tvHaveCountNum.setText(String.valueOf(summaryResults.getScanQty()));
    }

    @Override
    public void initData() {
        initAdapter();
    }

    @Override
    public OursourcingAuditGoodsListPresenter createPresenter() {
        return new OursourcingAuditGoodsListPresenter(this);
    }

    @Override
    public OursourcingAuditGoodsListView createView() {
        return this;
    }


    @OnClick({R.id.iv_show_more, R.id.btn_point_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show_more:
                ivShowMore.setSelected(!ivShowMore.isSelected());
                initAdapter();
                break;
            case R.id.btn_point_commit:
                if (queryOutSourcePickByInputResult.getSummaryResults().getScanId() == 0) {
                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("ScanId", queryOutSourcePickByInputResult.getSummaryResults().getScanId());
                params.put("SubmitType", 0);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }

    private void initAdapter() {
        mDatas.clear();
        /**
         * 是否点击了显示 进行数据的设置
         */
        final List<QueryOutSourcePickByInputResult.MaterialResultsBean> materialResults = queryOutSourcePickByInputResult.getMaterialResults();
        for (int i = 0; i < materialResults.size(); i++) {
            if (!ivShowMore.isSelected()) {
                if (materialResults.get(i).getWaitQty() > 0) {
                    mDatas.add(materialResults.get(i));
                }
            } else {
                mDatas.add(materialResults.get(i));
            }
        }
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<QueryOutSourcePickByInputResult.MaterialResultsBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_outsource_feed;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, QueryOutSourcePickByInputResult.MaterialResultsBean item) {
                    holder.setTextView(R.id.tv_line_num, item.getLine());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
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
                    Intent it = new Intent(OursourcingAuditGoodsListActivity.this, OutsourcingAuditPointActivity.class);

                    it.putExtra(OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_BEAN, new Gson().toJson(mDatas.get(pos)));
                    it.putExtra(OUT_STOCK_OUT_SOURCE_AUDIT_MATERIAL_POINT_SUMMARY_BEAN, new Gson().toJson(queryOutSourcePickByInputResult.getSummaryResults()));
                    startActivity(it);
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void submitMakeOrAuditBill() {
        ToastUtils.showShort(getString(R.string.commit_check_success));
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    /**
     * 当 物料清点的时候，将scanid传递过来进行设置
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSummaryScanid(OutsourceAuditEvent event) {
        if (event.getEvent().equals(OutsourceAuditEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS)) {
            queryOutSourcePickByInputResult.getSummaryResults().setScanId(event.getScanId());
        }
    }
}
