package com.timi.sz.wms_android.mvp.UI.stock_out.detail.outsource_bill_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.divider.DividerItemDecoration;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.detail.BillMaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list.BatchPointListActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAILId;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_DETAIL_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;
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


    @BindView(R.id.tv_orderno_tip)
    TextView tvOrdernoTip;
    @BindView(R.id.tv_outsource_orderno)
    TextView tvOutsourceOrderno;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.iv_show_more)
    ImageView ivShowMore;
    @BindView(R.id.rl_top_menu)
    RelativeLayout rlTopMenu;
    @BindView(R.id.tv_line_num)
    TextView tvLineNum;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_point_num)
    TextView tvPointNum;
    @BindView(R.id.tv_spare_num)
    TextView tvSpareNum;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_orderno_info)
    RecyclerView rlvOrdernoInfo;
    private BaseRecyclerAdapter<DetailResultsBean> adapter;
    //adapter的数据源
    private List<DetailResultsBean> mDatas = new ArrayList<>();
    //源数据的detial
    private List<DetailResultsBean> detailResults;
    private int billId;
    private int detailId;
    //跳转的code
    private int intentCode;
    //委外生单
    private QueryWWPickDataByOutSourceResult queryWWPickDataByOutSourceResult;
    //生产领料申请单
    private QueryProductPickByInputResult queryProductPickByInputResult;
    //成品拣货
    private QueryDNByInputForPickResult queryDNByInputForPickResult;
    //调拨调出
    private QueryAllotOutResult queryAllotOutResult;
    //其他生单
    private QueryOtherOutStockByInputResult queryOtherOutStockByInputResult;
    //销售生单
    private QuerySalesOutSotckByInputForOutStockResult querySalesOutSotckByInputForOutStockResult;
    //目标单据类型
    private int destBillType = -1;

    @Override
    public int setLayoutId() {
        return R.layout.activity_outsource_bill_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsBill = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置目标单据类型
                destBillType = 20;
                //设置数据源
                detailResults = queryWWPickDataByOutSourceResult.getDetailResults();
                //billId
                billId = summaryResultsBill.getBillId();
                //
                setHeaderContent(summaryResultsBill.getBillCode(),summaryResultsBill.getBillDate(),summaryResultsBill.getSupplierName());
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsProductionAllot = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置数据源
                detailResults = queryWWPickDataByOutSourceResult.getDetailResults();
                //设置目标单据类型
                destBillType = 23;
                //billId
                billId = summaryResultsProductionAllot.getBillId();
                setHeaderContent(summaryResultsProductionAllot.getBillCode(),summaryResultsProductionAllot.getBillDate(),summaryResultsProductionAllot.getSupplierName());

                break;
            default:
                break;
        }
    }

    @Override
    public void initView() {
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                setActivityTitle(getString(R.string.orderno_detail_outsource_bill_title));
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                setActivityTitle(getString(R.string.orderno_detail_outsource_allot_title));
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                setActivityTitle(getString(R.string.orderno_detail_production_bill_title));
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                setActivityTitle(getString(R.string.orderno_detail_production_allot_title));
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        dealData();
        initAdapter();
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
        Intent intent = new Intent(this, BatchPointListActivity.class);
        switch (intentCode) {
            case STOCK_OUT_PRODUCTION_BILL://生产生单
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(queryWWPickDataByOutSourceResult));
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(queryWWPickDataByOutSourceResult));
                break;
        }
        intent.putExtra(STOCK_OUT_DETAIL_BEAN, new Gson().toJson(result));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        startActivity(intent);
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
        for (int i = 0; i < detailResults.size(); i++) {
            if (!ivShowMore.isSelected()) {//如果是选中 则是显示所有的
                if (detailResults.get(i).getWipQty()<detailResults.get(i).getPoQty()) {
                    mDatas.add(detailResults.get(i));
                }
            } else
                mDatas.add(detailResults.get(i));
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
            adapter = new BaseRecyclerAdapter<DetailResultsBean>(this, mDatas) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_outsource_feed;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, DetailResultsBean item) {
                    holder.setTextView(R.id.tv_line_num, item.getLine());
                    holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                    holder.setTextView(R.id.tv_send_material_num, item.getPoQty());
                    holder.setTextView(R.id.tv_scan_num, item.getWipQty());
                    holder.setTextView(R.id.tv_material_name, item.getMaterialName() + item.getMaterialAttribute());
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    /**
                     * 退料单号的 网络请求
                     */
                    Map<String, Object> params = new HashMap<>();
                    params.put("UserId", SpUtils.getInstance().getUserId());
                    params.put("OrgId", SpUtils.getInstance().getOrgId());
                    params.put("MAC", PackageUtils.getMac());
                    params.put("MainId", billId);
                    params.put("DetailId", mDatas.get(pos).getDetailId());
                    //是否传入目标单据类型
                    if (destBillType != -1) {
                        params.put("DestBillType", destBillType);
                    }
                    getPresenter().getDetailData(params, intentCode);
                }
            });
            rlvOrdernoInfo.setAdapter(adapter);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rlvOrdernoInfo.setLayoutManager(linearLayoutManager);
            rlvOrdernoInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
        } else
            adapter.notifyDataSetChanged();
    }

    /**
     * 设置头部信息
     * @param orderno
     * @param date
     * @param supplier
     */
    public void setHeaderContent(String orderno,String date,String supplier){
        tvOutsourceOrderno.setText(orderno);
        tvDate.setText(date);
        tvSupplier.setText(supplier);
    }
}
