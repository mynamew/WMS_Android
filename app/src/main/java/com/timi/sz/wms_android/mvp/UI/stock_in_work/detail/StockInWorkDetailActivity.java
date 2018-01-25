package com.timi.sz.wms_android.mvp.UI.stock_in_work.detail;

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
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_CODE_STR;

/**
 * 库内作业详情
 */
public class StockInWorkDetailActivity extends BaseActivity<StockInWorkDetailView, StockInWorkDetailPresenter> implements StockInWorkDetailView {


    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_detial)
    RecyclerView rlvDetial;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;
    @BindView(R.id.tv_tip)
    TextView tvTip;


    private List<StockInWorkDetailResult> mDatas = new ArrayList<>();
    private List<StockInWorkDetailResult> mOldDatas = new ArrayList<>();

    private BaseRecyclerAdapter<StockInWorkDetailResult> adapter;
    private int billId;
    //intentcode
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_work_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_IN_WORK_CODE_STR, 0);
        billId = getIntent().getIntExtra(STOCK_IN_WORK_BILLID, 0);
        switch (intentCode) {

            case Constants.STOCK_IN_WORK_FORM_CHANGE_IN://形态转换入库
                tvTip.setText(R.string.form_change_instock_detial_info_tip);
                setActivityTitle(R.string.stockin_work_form_change_in_title);
                break;
            case Constants.STOCK_IN_WORK_FORM_CHANGE_OUT://形态转换出库
                tvTip.setText(R.string.form_change_outstock_detial_info_tip);
                setActivityTitle(R.string.stockin_work_form_change_out_title);
                setActivityTitle(getString(R.string.stock_in_work_detial));
                break;
            case Constants.STOCK_IN_WORK_POINT://盘点
                tvTip.setText(R.string.check_orderno_info);
                setActivityTitle(R.string.stockin_work_check_title);
                setActivityTitle(getString(R.string.stock_in_work_detial));
                break;
            case Constants.STOCK_IN_WORK_ALLOT_SCAN://扫描调入
                tvTip.setText(R.string.scan_allot_in_orderno_detail);
                setActivityTitle(R.string.stockin_work_allot_in_title);
                break;
        }

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillId", billId);
        getPresenter().queryAllotScanDetail(params,intentCode);
    }

    @Override
    public StockInWorkDetailPresenter createPresenter() {
        return new StockInWorkDetailPresenter(this);
    }

    @Override
    public StockInWorkDetailView createView() {
        return this;
    }

    @Override
    public void queryAllotScanDetail(List<StockInWorkDetailResult> dataResults) {
        mDatas.clear();
        mOldDatas.clear();
        mOldDatas.addAll(dataResults);
        dealData();
        initAdapter();
    }

    /**
     * 对数据源做处理，
     */
    private void dealData() {
        mDatas.clear();
        for (int i = 0; i < mOldDatas.size(); i++) {
            if (!ivShowMore.isSelected()) {//如果是选中 则是显示所有的
                if (mOldDatas.get(i).getWaitQty() > 0) {
                    mDatas.add(mOldDatas.get(i));
                }
            } else
                mDatas.add(mOldDatas.get(i));
        }
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {

        if (null == adapter) {
            /**
             * 初始化adapter
             */
            adapter = new BaseRecyclerAdapter<StockInWorkDetailResult>(this, mDatas) {
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
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() +" "+ item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() +" "+ item.getMaterialStandard());
                }
            };
            rlvDetial.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvDetial.setLayoutManager(linearLayoutManager);
            rlvDetial.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            rlvDetial.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                rlvDetial.smoothScrollToPosition(0);
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
        } else
            adapter.notifyDataSetChanged();

    }

    @OnClick(R.id.iv_show_more)
    public void onViewClicked() {
        ivShowMore.setSelected(!ivShowMore.isSelected());
        dealData();
        if(null!=adapter){
            adapter.notifyDataSetChanged();
        }
    }
}
