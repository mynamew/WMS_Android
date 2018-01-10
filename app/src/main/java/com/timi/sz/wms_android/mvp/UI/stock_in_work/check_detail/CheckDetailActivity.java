package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_detail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_BILLID;

/**
 * 盘点明细
 * author: timi
 * create at: 2017/12/25 15:04
 */
public class CheckDetailActivity extends BaseActivity<CheckDetailView, CheckDetailPreseneter> implements CheckDetailView {


    @BindView(R.id.ptr_detail)
    PullToRefreshRecyclerView ptrDetail;
    private RecyclerView rlvDetail;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;

    private List<StockInWorkDetailResult> mDatas = new ArrayList<>();
    private BaseRecyclerAdapter<StockInWorkDetailResult> adapter;

    private int pageSize = 10;
    private int pageIndex = 1;

    private int billId;
    @Override
    public int setLayoutId() {
        return R.layout.activity_check_detail;
    }
    private boolean isLookCheck=false;//是否盲盘
    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.check_detail);
        billId = getIntent().getIntExtra(STOCK_IN_WORK_BILLID, 0);
        isLookCheck = getIntent().getBooleanExtra("isLookCheck", false);
    }

    @Override
    public void initView() {
        rlvDetail=ptrDetail.getRefreshableView();
        ptrDetail.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pageIndex=1;
                mDatas.clear();

                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("PageSize", pageSize);
                params.put("PageIndex", pageIndex);
                getPresenter().getCheckStockDetail(params);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pageIndex=pageIndex+1;

                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("PageSize", pageSize);
                params.put("PageIndex", pageIndex);
                getPresenter().getCheckStockDetail(params);
            }
        });
    }

    @Override
    public void initData() {
        showProgressDialog();
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillId", billId);
        params.put("PageSize", pageSize);
        params.put("PageIndex", pageIndex);
        getPresenter().getCheckStockDetail(params);
    }

    @Override
    public CheckDetailPreseneter createPresenter() {
        return new CheckDetailPreseneter(this);
    }

    @Override
    public CheckDetailView createView() {
        return this;
    }

    @Override
    public void getCheckStockDetail(List<StockInWorkDetailResult> datas) {
        if(ptrDetail.isRefreshing()){
            ptrDetail.onRefreshComplete();
        }
        mDatas.addAll(datas);
        if(null==adapter){
            adapter=new BaseRecyclerAdapter<StockInWorkDetailResult>(CheckDetailActivity.this,mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_instock_detail;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, StockInWorkDetailResult item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, item.getLine());
                    holder.setTextView(R.id.tv_wait_count_num, item.getWaitQty());
                    holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                    holder.setTextView(R.id.tv_should_return_num, item.getQty());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() + item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() + item.getMaterialStandard());
                    if(isLookCheck){//盲盘
                         holder.getView(R.id.ll_repetory_total).setVisibility(View.GONE);
                         holder.getView(R.id.ll_wait_total).setVisibility(View.GONE);
                    }
                }
            };
            rlvDetail.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvDetail.setLayoutManager(linearLayoutManager);
            rlvDetail.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            rlvDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == SCROLL_STATE_IDLE) {//停止滑动
                        /**
                         * 设置点击时间
                         */
                        fbtnDetail.showUpTop(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rlvDetail.smoothScrollToPosition(0);
                            }
                        });
                    } else {
                        /**
                         * 设置当前的位置
                         */
                        int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        fbtnDetail.showCurrentItem(lastCompletelyVisibleItemPosition, mDatas.size());
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    /**
                     * 设置当前的位置
                     */
                    int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    fbtnDetail.showCurrentItem(lastCompletelyVisibleItemPosition, mDatas.size());
                }
            });
        }else
            adapter.notifyDataSetChanged();
    }
}
