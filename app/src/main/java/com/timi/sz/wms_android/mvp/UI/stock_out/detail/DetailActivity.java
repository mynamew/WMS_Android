package com.timi.sz.wms_android.mvp.UI.stock_out.detail;

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
import com.timi.sz.wms_android.bean.outstock.detail.MaterialDetailResult;
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
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;

/**
 * 出库单据详情
 * author: timi
 * create at: 2017/11/21 11:08
 */
public class DetailActivity extends BaseActivity<DetailView, DetailPresenter> implements DetailView {


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
    private int billId;
    private int regionId;
    private int warehouseId;

    private List<MaterialDetailResult> mDatas = new ArrayList<>();
    private List<MaterialDetailResult> mOldDatas = new ArrayList<>();

    private BaseRecyclerAdapter<MaterialDetailResult> adapter;
    //intentcode
    private int intentCode;

    @Override
    public int setLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, -1);
        if (intentCode == -1) {
            intentCode = getIntent().getIntExtra(Constants.CODE_STR, -1);
        }
        setActivityTitle(getString(R.string.detail));
        billId = getIntent().getIntExtra(OUT_STOCK_POINT_DETIAIL_BILLID, -1);
        //如果不是出库传过来的billid则是入库的billid
        if (billId == -1) {
            billId = getIntent().getIntExtra(Constants.STOCKIN_BILLID, -1);
        }
        warehouseId = getIntent().getIntExtra(OUT_STOCK_POINT_WAREHOUSEID, 0);
        regionId = getIntent().getIntExtra(OUT_STOCK_POINT_REGIONID, 0);

    }

    @Override
    public void initView() {
        switch (intentCode) {
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                tvTip.setText(R.string.order_detial_finish_googs_audit_title);
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 制单
                tvTip.setText(R.string.order_detial_finish_googs_bill_title);
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                tvTip.setText(R.string.order_detial_other_audit_title);
                break;
            case Constants.STOCK_OUT_PRODUCTION_APPLY_BILL://领料 申请
                tvTip.setText(R.string.order_detial_production_apply_title);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                tvTip.setText(R.string.order_detial_out_feed_title);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_AUDIT://委外发料审核
                tvTip.setText(R.string.order_detial_out_audit_title);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_BILL://委外发料制单
                tvTip.setText(R.string.order_detial_out_bill_title);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                tvTip.setText(R.string.order_detial_out_allot_title);
                break;
            case Constants.STOCK_OUT_PRODUCTION_FEEDING://生产补料
                tvTip.setText(R.string.order_detial_production_feed_title);
                break;
            case Constants.STOCK_OUT_OTHER_OUT_AUDIT://其他出库审核
                tvTip.setText(R.string.order_detial_other_out_audit_title);
                break;
            case Constants.STOCK_OUT_OTHER_OUT_BILL://其他出库制单
                tvTip.setText(R.string.order_detial_other_out_bill_title);
                ivShowMore.setSelected(true);
                ivShowMore.setVisibility(View.GONE);//其他出入库 制单不显示显示全部
                break;
            case Constants.OTHER_IN_STOCK_SCAN://其他入库制单
                tvTip.setText(R.string.order_detial_other_in_bill_title);
                ivShowMore.setSelected(true);
                ivShowMore.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillId", billId);
        params.put("RegionId", regionId);
        params.put("WarehouseId", warehouseId);
        getPresenter().getDetailPickData(params, intentCode);
    }

    @Override
    public DetailPresenter createPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    public DetailView createView() {
        return this;
    }

    @Override
    public void getDetailPickData(List<MaterialDetailResult> dataResults) {
        mDatas.clear();
        mOldDatas.clear();
        mOldDatas.addAll(dataResults);
        dealData();
        initAdapter();
    }

    @OnClick(R.id.iv_show_more)
    public void onViewClicked() {
        ivShowMore.setSelected(!ivShowMore.isSelected());
        dealData();
        if (null != adapter) {
            adapter.notifyDataSetChanged();
        }
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
            adapter = new BaseRecyclerAdapter<MaterialDetailResult>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_instock_detail;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, MaterialDetailResult item) {
                    setTextViewText(holder.getTextView(R.id.tv_line_name), R.string.item_line_num, item.getLine());
                    holder.setTextView(R.id.tv_wait_count_num, item.getWaitQty());
                    holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                    holder.setTextView(R.id.tv_should_return_num, item.getQty());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode() + "  " + item.getMaterialName());//合格数？ 应退数
                    holder.setTextView(R.id.tv_material_model, item.getMaterialAttribute() + "  " + item.getMaterialStandard());
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
