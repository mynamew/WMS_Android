package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_record;

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
import com.timi.sz.wms_android.bean.stockin_work.CheckRecordResult;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.check_detail.CheckDetailActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_BILLID;

/**
 * 盘点记录
 * author: timi
 * create at: 2017/12/11 16:12
 */
public class CheckRecordActivity extends BaseActivity<CheckRecordView, CheckRecordPresenter> implements CheckRecordView {

    @BindView(R.id.ptr_record)
    PullToRefreshRecyclerView ptrRecord;
    private RecyclerView rlvRecord;
    @BindView(R.id.fbtn_record)
    FloatCircleButtonUpTopView fbtnRecord;
    private int billId;
    private int pageSize = 10;
    private int pageIndex = 1;

    private List<CheckRecordResult> mDatas = new ArrayList<>();
    private BaseRecyclerAdapter<CheckRecordResult> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_check_record;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        billId = getIntent().getIntExtra(STOCK_IN_WORK_BILLID, 0);
        setActivityTitle(R.string.check_record_title);
    }

    @Override
    public void initView() {
        rlvRecord = ptrRecord.getRefreshableView();
        ptrRecord.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pageIndex = 1;
                mDatas.clear();

                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("PageSize", pageSize);
                params.put("PageIndex", pageIndex);
                getPresenter().getCheckStockRecord(params);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pageIndex = pageIndex + 1;

                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("PageSize", pageSize);
                params.put("PageIndex", pageIndex);
                getPresenter().getCheckStockRecord(params);
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
        getPresenter().getCheckStockRecord(params);
    }

    @Override
    public CheckRecordPresenter createPresenter() {
        return new CheckRecordPresenter(this);
    }

    @Override
    public CheckRecordView createView() {
        return this;
    }


    @Override
    public void getCheckStockPageRecord(List<CheckRecordResult> datas) {
        if (ptrRecord.isRefreshing()) {
            ptrRecord.onRefreshComplete();
        }
        mDatas.addAll(datas);
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<CheckRecordResult>(CheckRecordActivity.this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_check_recordl;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, CheckRecordResult item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, position + 1);
                    holder.setTextView(R.id.tv_check_num, item.getCheckQty());
                    holder.setTextView(R.id.tv_check_date, item.getCheckTime());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() + item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() + item.getMaterialStandard());
                }
            };
            rlvRecord.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvRecord.setLayoutManager(linearLayoutManager);
            rlvRecord.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            rlvRecord.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == SCROLL_STATE_IDLE) {//停止滑动
                        /**
                         * 设置点击时间
                         */
                        fbtnRecord.showUpTop(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rlvRecord.smoothScrollToPosition(0);
                            }
                        });
                    } else {
                        /**
                         * 设置当前的位置
                         */
                        int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        fbtnRecord.showCurrentItem(lastCompletelyVisibleItemPosition, mDatas.size());
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    /**
                     * 设置当前的位置
                     */
                    int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    fbtnRecord.showCurrentItem(lastCompletelyVisibleItemPosition, mDatas.size());
                }
            });
        } else
            adapter.notifyDataSetChanged();
    }
}
