package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

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

/**
 * 详情
 */
public class StockInDetailActivity extends BaseActivity<StockInDetailView, StockInPresenter> implements StockInDetailView {
    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rlv_un_instock)
    RecyclerView rlvUnInstock;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    private int intentCode = Constants.COME_MATERAIL_NUM;//来料单
    private int BillId = 0;//来料单的id
    private List<OrderDetailData> mDatas = new ArrayList<>();
    private List<OrderDetailData> mOldDatas = new ArrayList<>();
    private BaseRecyclerAdapter<OrderDetailData> adapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.detail));
        //获取跳转的code 值 用来显示不同的提示
        intentCode = getIntent().getIntExtra(Constants.CODE_STR, -1);
        //如果是出库
        if (intentCode == -1) {
            intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, -1);
        }
        BillId = getIntent().getIntExtra(Constants.STOCKIN_BILLID, 0);
    }

    @Override
    public void initView() {
        switch (intentCode) {
            case Constants.COME_MATERAIL_NUM://来料图库
                tvTip.setText(R.string.order_detail_come_material_title);
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品(审核)单据详情
                tvTip.setText(R.string.order_detial_finish_goods_audit_title);
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品(生单)单据详情
                tvTip.setText(R.string.order_detial_finish_goods_bill_title);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他入库(选单)单据详情
                tvTip.setText(R.string.order_detail_other_select_bill_title);
                break;
            case Constants.OTHER_IN_STOCK_SCAN://其他入库(扫描)单据详情
                tvTip.setText(R.string.order_detial_other_scan_title);
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料单单据详情
                tvTip.setText(R.string.order_detial_outsource_title);
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料单单据详情
                tvTip.setText(R.string.order_detial_production_return_title);
                break;
            case Constants.SALE_RETURN_MATERAIL://销售退料单单据详情
                tvTip.setText(R.string.order_detial_sale_return_title);
                break;
            case Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料单单据详情
                tvTip.setText(R.string.order_detial_buy_return_title);
                break;


        }
    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("WarehouseId", 0);
        params.put("RegionId", 0);
        params.put("BillId", BillId);
        getPresenter().getReceiptDetail(params, intentCode);
    }

    @Override
    public StockInPresenter createPresenter() {
        return new StockInPresenter(this);
    }

    @Override
    public StockInDetailView createView() {
        return this;
    }

    @Override
    public void getReceiptDetail(List<OrderDetailData> datas) {
        mDatas.clear();
        mOldDatas.clear();
        mOldDatas.addAll(datas);
        dealData();
        initAdapter();
    }

    /**
     * 初始化adapter
     */
    private void initAdapter() {

        if (null == adapter) {
            /**
             * 初始化adapter
             */
            adapter = new BaseRecyclerAdapter<OrderDetailData>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_instock_detail;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, OrderDetailData item) {
                    /**
                     * 对行数进行判断
                     */
                    if (0 == item.getLine()) {
                        setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, item.getReceiptLine());
                    } else {
                        setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, item.getLine());
                    }
                    switch (intentCode) {
                        case Constants.COME_MATERAIL_NUM:
                            holder.setTextView(R.id.tv_should_return_num_tip, getString(R.string.should_instock_qty));
                            break;
                        case Constants.OUT_RETURN_MATERAIL:
                        case Constants.CREATE_RETURN_MATERAIL:
                        case Constants.SALE_RETURN_MATERAIL:
                            holder.setTextView(R.id.tv_should_return_num_tip,getString(R.string.should_return_material_qty));
                            break;
                    }
                    holder.setTextView(R.id.tv_wait_count_num, item.getWaitQty());
                    holder.setTextView(R.id.tv_should_return_num, item.getQty());
                    holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() +"  "+ item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() +"  "+ item.getMaterialStandard());
                }
            };
            rlvUnInstock.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvUnInstock.setLayoutManager(linearLayoutManager);
            rlvUnInstock.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
            rlvUnInstock.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                rlvUnInstock.smoothScrollToPosition(0);
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

    @Override
    public void getOutSourceReturnDetail(List<OrderDetailData> datas) {
        mDatas.clear();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
