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
import com.timi.sz.wms_android.bean.stockin_work.FormChangeDetailResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
    private int billId;

    private List<FormChangeDetailResult> mDatas;
    private List<FormChangeDetailResult> mOldDatas ;
    private BaseRecyclerAdapter<FormChangeDetailResult> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_form_change_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

        billId = getIntent().getIntExtra(Constants.STOCK_IN_WORK_BILLID,0);
        intentCode = getIntent().getIntExtra(Constants.STOCK_IN_WORK_CODE_STR, 0);
        /**
         * 不同的intentcode  显示不同的标题
         */
        setActivityTitle(getString(R.string.form_change_instock_detial_title));
        if (intentCode == Constants.STOCK_IN_WORK_FORM_CHANGE_IN) {
            tvTip.setText(R.string.form_change_instock_detial_info_tip);
        } else if(intentCode == Constants.STOCK_IN_WORK_FORM_CHANGE_OUT){
            tvTip.setText(R.string.form_change_outstock_detial_info_tip);
        }else {
            tvTip.setText(R.string.check_orderno_info);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        mOldDatas = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillId", billId);
        if (intentCode == Constants.STOCK_IN_WORK_FORM_CHANGE_IN) {
            getPresenter().getConvertInDetail(params);
        } else if(intentCode == Constants.STOCK_IN_WORK_FORM_CHANGE_OUT){
            getPresenter().getConvertOutDetail(params);
        }else {
            getPresenter().getCheckStockDetail(params);
        }
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
    public void getStockInWorkDetail(List<FormChangeDetailResult> datas) {
        mOldDatas.clear();
        mOldDatas.addAll(datas);
        dealData();
        initAdapter();
    }

    @Override
    public void getConvertOutDetail(List<FormChangeDetailResult> datas) {
        mOldDatas.clear();
        mOldDatas.addAll(datas);
        dealData();
        initAdapter();
    }

    @Override
    public void getCheckStockDetail(List<FormChangeDetailResult> datas) {
        mOldDatas.clear();
        mOldDatas.addAll(datas);
        dealData();
        initAdapter();
    }

    @OnClick(R.id.iv_show_more)
    public void onViewClicked() {
        ivShowMore.setSelected(!ivShowMore.isSelected());
        dealData();
        adapter.notifyDataSetChanged();
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {

        if (null == adapter) {
            /**
             * 初始化adapter
             */
            adapter = new BaseRecyclerAdapter<FormChangeDetailResult>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_instock_detail;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, FormChangeDetailResult item) {
                    /**
                     * 对行数进行判断
                     */
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, item.getLine());
                    holder.setTextView(R.id.tv_wait_count_num, item.getWaitQty());
                    holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() + item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() + item.getMaterialStandard());
                }
            };
            rlvFromChange.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvFromChange.setLayoutManager(linearLayoutManager);
            rlvFromChange.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            rlvFromChange.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == SCROLL_STATE_IDLE) {//停止滑动
                        /**
                         * 设置点击时间
                         */
                        fbtnDetail.showUpTop(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rlvFromChange.smoothScrollToPosition(0);
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
}
