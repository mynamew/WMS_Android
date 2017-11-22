package com.timi.sz.wms_android.mvp.UI.stock_out.detail.outsource_bill_detail;

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
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.outstock.detail.BillMaterialDetailResult;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.view.FloatCircleButtonUpTopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAILId;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;

/**
 * 委外生单 明细
 * author: timi
 * create at: 2017/11/21 1:36
 */
public class OutsourceBillDetailActivity extends BaseActivity<OutsourceBillDetaiView, OutsourceBillDetaiPresenter> implements OutsourceBillDetaiView {


    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.rlv_detial)
    RecyclerView rlvDetial;
    @BindView(R.id.fbtn_detail)
    FloatCircleButtonUpTopView fbtnDetail;

    private BaseRecyclerAdapter<BillMaterialDetailResult.MaterialResultsBean> adapter;

    private List<BillMaterialDetailResult.MaterialResultsBean> mDatas = new ArrayList<>();
    private List<BillMaterialDetailResult.MaterialResultsBean> mOldDatas = new ArrayList<>();
    private int billId;
    private int detailId;
    //跳转的code
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_outsource_bill_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
        billId = getIntent().getIntExtra(OUT_STOCK_POINT_DETIAIL_BILLID, 0);
        detailId = getIntent().getIntExtra(OUT_STOCK_POINT_DETIAILId, 0);

    }

    @Override
    public void initView() {
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                setActivityTitle(getString(R.string.orderno_detail_outsource_bill_title));
                tvTip.setText(R.string.stock_out_out_create_order);
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                setActivityTitle(getString(R.string.orderno_detail_outsource_allot_title));
                tvTip.setText(R.string.outsource_allot);
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                setActivityTitle(getString(R.string.orderno_detail_production_bill_title));
                tvTip.setText(R.string.stock_out_create_create_order);
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                setActivityTitle(getString(R.string.orderno_detail_production_allot_title));
                tvTip.setText(R.string.production_allot);
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        /**
         * 退料单号的 网络请求
         */
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("WWPoId", billId);
        params.put("WWPoDetailId", detailId);
        params.put("DestBillType", 20);
        getPresenter().getDetailData(params, intentCode);
    }

    @Override
    public OutsourceBillDetaiPresenter createPresenter() {
        return new OutsourceBillDetaiPresenter(this);
    }

    @Override
    public OutsourceBillDetaiView createView() {
        return this;
    }

    @Override
    public void getDetailData(BillMaterialDetailResult result) {
        mDatas.clear();
        mOldDatas.clear();
        mOldDatas.addAll(result.getMaterialResults());
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

    /**
     * 初始化adapter
     */
    private void initAdapter() {

        if (null == adapter) {
            /**
             * 初始化adapter
             */
            adapter = new BaseRecyclerAdapter<BillMaterialDetailResult.MaterialResultsBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_instock_detail;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, BillMaterialDetailResult.MaterialResultsBean item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, item.getLine());
                    holder.setTextView(R.id.tv_wait_count_num, item.getWaitQty());
                    holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                    holder.setTextView(R.id.tv_should_return_num, item.getQty());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() + item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() + item.getMaterialStandard());
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

}
