package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;
import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.OutsourceAuditEvent;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_point.BatchPointActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_DETAIL_RESULTS_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_MATERIAL_RESULTS_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
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
 * 物料清单的界面（统一的界面）
 * author: timi
 * create at: 2017/11/20 9:05
 */
public class BatchPointListActivity extends BaseActivity<BatchPointListView, BatchPointListPresenter> implements BatchPointListView {
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
    @BindView(R.id.tv_line_num)
    TextView tvLineNum;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_point_num)
    TextView tvPointNum;
    @BindView(R.id.tv_spare_num)
    TextView tvSpareNum;
    @BindView(R.id.tv_arrive_good_num)
    TextView tvArriveGoodNum;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.view_divide)
    View viewDivide;
    @BindView(R.id.rlv_orderno_info)
    RecyclerView rlvOrdernoInfo;
    @BindView(R.id.btn_point_commit)
    Button btnPointCommit;
    /**
     * 跳转的code
     */
    private int intentCode;
    /**
     * scanid
     */
    private int scanId = 0;
    /**
     * billId
     */
    private int billId = 0;
    /**
     * submitType 提交类型： 审核  /  生单
     */
    private int submitType = 0;
    /**
     * 仓库id
     */
    private int warehouseId=0;
    /**
     * 区域Id
     */
    private int regionId=0;
    /**====== 委外 ======**/
    /**
     * 委外发料（审核） 搜索的结果
     */
    private QueryOutSourcePickByInputResult queryOutSourcePickByInputResult;

    /**
     * 委外发料（生单） 搜索的结果
     */
    private QueryWWPickDataByOutSourceResult queryWWPickDataByOutSourceResult;
    /**
     * 委外补料 搜索的结果
     */
    private QueryOutSourceFeedByInputResult queryOutSourceFeedByInputResult;

    /**====== 生产 ======**/
    /**
     * 生产领料（审核）
     */
    private QueryProductPickByInputResult queryProductPickByInputResult;
    /**
     * 生产补料
     */
    private QueryPrdFeedByInputResult queryPrdFeedByInputResult;
    /**
     * 适配器
     */
    private BaseRecyclerAdapter<DetailResultsBean> adapterDetail;
    private BaseRecyclerAdapter<MaterialResultsBean> adapterMaterial;
    /**
     * 链表
     */
    private List<MaterialResultsBean> mDatas = new ArrayList<>();
    private List<DetailResultsBean> mDatasDetail = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_batch_point_list  ;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        BaseMessage.register(this);
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
    }

    @Override
    public void initView() {
        setActivityTitle(getString(R.string.foods_list_title));
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                tvHeadTitle.setText(R.string.outsource_feed_point_title);
                queryOutSourceFeedByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryOutSourceFeedByInputResult.class);
                //获取 summaryResults
                QueryOutSourceFeedByInputResult.SummaryResultsBean summaryResults = queryOutSourceFeedByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResults.getBillCode(), summaryResults.getBillDate(), summaryResults.getWarehouseName(), summaryResults.getRegionName(), summaryResults.getQty(), summaryResults.getWaitQty(), summaryResults.getScanQty());
                //设置scanid
                scanId = summaryResults.getScanId();
                //设置 submitType
                submitType = 1;
                //设置仓库id
                warehouseId=summaryResults.getWarehouseId();
                //设置区域id
                regionId=summaryResults.getRegionId();
                //billId
                billId=summaryResults.getBillId();
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                tvHeadTitle.setText(R.string.outsource_audit_title);
                queryOutSourcePickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryOutSourcePickByInputResult.class);
                //获取 summaryResults
                QueryOutSourcePickByInputResult.SummaryResultsBean summaryResultsAudit = queryOutSourcePickByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAudit.getBillCode(), summaryResultsAudit.getBillDate(), summaryResultsAudit.getWarehouseName(), summaryResultsAudit.getRegionName(), summaryResultsAudit.getQty(), summaryResultsAudit.getWaitQty(), summaryResultsAudit.getScanQty());
                //设置scanid
                scanId = summaryResultsAudit.getScanId();
                //设置 submitType
                submitType = 1;
                //设置仓库id
                warehouseId=summaryResultsAudit.getWarehouseId();
                //设置区域id
                regionId=summaryResultsAudit.getRegionId();
                //billId
                billId=summaryResultsAudit.getBillId();
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                btnPointCommit.setText(R.string.create_send_material_orderno);
                tvHeadTitle.setText(R.string.outsource_send_material_bill_title);
                queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsBill = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsBill.getBillCode(), summaryResultsBill.getBillDate(), summaryResultsBill.getWarehouseName(), summaryResultsBill.getRegionName(), summaryResultsBill.getQty(), summaryResultsBill.getWaitQty(), summaryResultsBill.getScanQty());
                //设置scanid
                scanId = summaryResultsBill.getScanId();
                //设置 submitType
                submitType = 0;
                //设置仓库id
                warehouseId=summaryResultsBill.getWarehouseId();
                //设置区域id
                regionId=summaryResultsBill.getRegionId();
                //billId
                billId=summaryResultsBill.getBillId();
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                btnPointCommit.setText(R.string.create_send_material_orderno);
                tvHeadTitle.setText(R.string.outsource_allot_material_list_title);
                queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsAllot = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAllot.getBillCode(), summaryResultsAllot.getBillDate(), summaryResultsAllot.getWarehouseName(), summaryResultsAllot.getRegionName(), summaryResultsAllot.getQty(), summaryResultsAllot.getWaitQty(), summaryResultsAllot.getScanQty());
                //设置scanid
                scanId = summaryResultsAllot.getScanId();
                //设置 submitType
                submitType = 0;
                //设置仓库id
                warehouseId=summaryResultsAllot.getWarehouseId();
                //设置区域id
                regionId=summaryResultsAllot.getRegionId();
                //billId
                billId=summaryResultsAllot.getBillId();
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                tvHeadTitle.setText(R.string.material_list_pro_feed_title);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                queryPrdFeedByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryPrdFeedByInputResult.class);
                //获取 summaryResults
                QueryPrdFeedByInputResult.SummaryResultsBean summaryResultsProductionFeed = queryPrdFeedByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionFeed.getBillCode(), summaryResultsProductionFeed.getBillDate(), summaryResultsProductionFeed.getWarehouseName(), summaryResultsProductionFeed.getRegionName(), summaryResultsProductionFeed.getQty(), summaryResultsProductionFeed.getWaitQty(), summaryResultsProductionFeed.getScanQty());
                //设置scanid
                scanId = summaryResultsProductionFeed.getScanId();
                //设置 submitType
                submitType = 1;
                //设置仓库id
                warehouseId=summaryResultsProductionFeed.getWarehouseId();
                //设置区域id
                regionId=summaryResultsProductionFeed.getRegionId();
                //billId
                billId=summaryResultsProductionFeed.getBillId();
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                tvHeadTitle.setText(R.string.material_list_pro_get_title);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                queryProductPickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryProductPickByInputResult.class);
                //获取 summaryResults
                QueryProductPickByInputResult.SummaryResultsBean summaryResultsProductionAudit = queryProductPickByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionAudit.getBillCode(), summaryResultsProductionAudit.getBillDate(), summaryResultsProductionAudit.getWarehouseName(), summaryResultsProductionAudit.getRegionName(), summaryResultsProductionAudit.getQty(), summaryResultsProductionAudit.getWaitQty(), summaryResultsProductionAudit.getScanQty());
                //设置scanid
                scanId = summaryResultsProductionAudit.getScanId();
                //设置 submitType
                submitType = 1;
                //设置仓库id
                warehouseId=summaryResultsProductionAudit.getWarehouseId();
                //设置区域id
                regionId=summaryResultsProductionAudit.getRegionId();
                //billId
                billId=summaryResultsProductionAudit.getBillId();
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                btnPointCommit.setText(R.string.create_send_material_orderno);
                tvHeadTitle.setText(R.string.material_list_pro_bill_title);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsProductionBill = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionBill.getBillCode(), summaryResultsProductionBill.getBillDate(), summaryResultsProductionBill.getWarehouseName(), summaryResultsProductionBill.getRegionName(), summaryResultsProductionBill.getQty(), summaryResultsProductionBill.getWaitQty(), summaryResultsProductionBill.getScanQty());
                //设置scanid
                scanId = summaryResultsProductionBill.getScanId();
                //设置 submitType
                submitType = 0;
                //设置仓库id
                warehouseId=summaryResultsProductionBill.getWarehouseId();
                //设置区域id
                regionId=summaryResultsProductionBill.getRegionId();
                //billId
                billId=summaryResultsProductionBill.getBillId();
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                btnPointCommit.setText(R.string.create_send_material_orderno);
                tvHeadTitle.setText(R.string.material_point_production_allot_title);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsProductionAllot = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionAllot.getBillCode(), summaryResultsProductionAllot.getBillDate(), summaryResultsProductionAllot.getWarehouseName(), summaryResultsProductionAllot.getRegionName(), summaryResultsProductionAllot.getQty(), summaryResultsProductionAllot.getWaitQty(), summaryResultsProductionAllot.getScanQty());
                //设置scanid
                scanId = summaryResultsProductionAllot.getScanId();
                //设置 submitType
                submitType = 0;
                //设置仓库id
                warehouseId=summaryResultsProductionAllot.getWarehouseId();
                //设置区域id
                regionId=summaryResultsProductionAllot.getRegionId();
                //billId
                billId=summaryResultsProductionAllot.getBillId();
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                break;
            default:
                break;
        }

    }

    @Override
    public void initData() {
        initAdapter();
    }

    @Override
    public BatchPointListPresenter createPresenter() {
        return new BatchPointListPresenter(this);
    }

    @Override
    public BatchPointListView createView() {
        return this;
    }

    @Override
    public void submitMakeOrAuditBill() {

    }

    /**
     * 设置 头部的内容
     *
     * @param billCode
     * @param billDate
     * @param wareHouseName
     * @param regionName
     * @param qty
     * @param waitQty
     * @param scanQty
     */
    public void setHeaderContent(String billCode, String billDate, String wareHouseName, String regionName, int qty, int waitQty, int scanQty) {
        tvOutsourceOrderno.setText(billCode);
        tvCreateOrdernoDate.setText(TextUtils.isEmpty(billDate) ? getString(R.string.none) : billDate);
        tvStockName.setText(TextUtils.isEmpty(wareHouseName) ? getString(R.string.none) : wareHouseName);
        tvStrictName.setText(TextUtils.isEmpty(regionName) ? getString(R.string.none) : regionName);
        tvBuyNum.setText(String.valueOf(qty));
        tvWaitPointNum.setText(String.valueOf(waitQty));
        tvHaveCountNum.setText(String.valueOf(scanQty));
    }

    @OnClick({R.id.iv_show_more, R.id.btn_point_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_show_more:
                ivShowMore.setSelected(!ivShowMore.isSelected());
                initAdapter();
                break;
            case R.id.btn_point_commit:
                if (scanId == 0) {
                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("ScanId", scanId);
                params.put("SubmitType", submitType);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }

    /**
     * 初始化 adapter
     */
    private void initAdapter() {
        mDatas.clear();
        mDatasDetail.clear();
        boolean isIsMerge = false;
        List<DetailResultsBean> detailResults = null;
        List<MaterialResultsBean> materialResults = null;
        /**
         * 通过对 isIsMerge （是否合并 ） 的判断进行数据源的设置
         */
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                isIsMerge = false;
                detailResults = queryOutSourceFeedByInputResult.getDetailResults();
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                isIsMerge = true;
                materialResults = queryOutSourcePickByInputResult.getMaterialResults();
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                isIsMerge = queryWWPickDataByOutSourceResult.getSummaryResults().isIsMerge();
                detailResults = queryWWPickDataByOutSourceResult.getDetailResults();
                materialResults = queryWWPickDataByOutSourceResult.getMaterialResults();
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                isIsMerge = false;
                detailResults = queryPrdFeedByInputResult.getDetailResults();
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                isIsMerge = true;
                materialResults = queryProductPickByInputResult.getMaterialResults();
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                isIsMerge = queryWWPickDataByOutSourceResult.getSummaryResults().isIsMerge();
                detailResults = queryWWPickDataByOutSourceResult.getDetailResults();
                materialResults = queryWWPickDataByOutSourceResult.getMaterialResults();
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                break;
            default:
                break;
        }
        /**
         * 是否是合并拣货
         */
        if (isIsMerge) {
            for (int i = 0; i < materialResults.size(); i++) {
                if (!ivShowMore.isSelected()) {
                    if (materialResults.get(i).getWaitQty() > 0) {
                        mDatas.add(materialResults.get(i));
                    }
                } else {
                    mDatas.add(materialResults.get(i));
                }
            }
        } else {
            for (int i = 0; i < detailResults.size(); i++) {
                if (!ivShowMore.isSelected()) {
                    /**
                     * 如果齐套数小于采购数 则显示
                     */
                    if (detailResults.get(i).getWipQty() < detailResults.get(i).getPoQty()) {
                        mDatasDetail.add(detailResults.get(i));
                    }
                } else {
                    mDatasDetail.add(detailResults.get(i));
                }
            }
        }
        /**
         * 是否合并发料
         */
        if (isIsMerge) {
            /**
             * 设置头部
             */
            findViewById(R.id.head_material).setVisibility(View.GONE);
            findViewById(R.id.head_detail).setVisibility(View.VISIBLE);
            /**
             * 初始化adapter
             */
            if (null == adapterMaterial) {
                adapterMaterial = new BaseRecyclerAdapter<MaterialResultsBean>(this, mDatas) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_outsource_feed;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, MaterialResultsBean item) {
                        holder.setTextView(R.id.tv_line_num, item.getLine());
                        holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                        holder.setTextView(R.id.tv_send_material_num, item.getQty());
                        holder.setTextView(R.id.tv_scan_num, item.getScanQty());
                        holder.setTextView(R.id.tv_material_name, item.getMaterialName());
                    }
                };
                /**
                 * 初始化  adapter
                 */
                rlvOrdernoInfo.setAdapter(adapterMaterial);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                linearLayoutManager.setAutoMeasureEnabled(true);
                rlvOrdernoInfo.setLayoutManager(linearLayoutManager);
                rlvOrdernoInfo.setNestedScrollingEnabled(false);
                rlvOrdernoInfo.setHasFixedSize(true);
                rlvOrdernoInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));
                adapterMaterial.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int pos) {
                        Intent it = new Intent(BatchPointListActivity.this, BatchPointActivity.class);
                        it.putExtra(STOCK_OUT_CODE_STR, intentCode);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        it.putExtra(OUT_STOCK_MATERIAL_RESULTS_BEAN, new Gson().toJson(mDatas.get(pos)));
                        it.putExtra(OUT_STOCK_SCANID, scanId);
                        startActivity(it);
                    }
                });
            } else {
                adapterMaterial.notifyDataSetChanged();
            }

        } else {

            /**
             * 初始化 adapterDetail
             */
            if (null == adapterDetail) {
                /**
                 * 设置头部
                 */
                findViewById(R.id.head_material).setVisibility(View.VISIBLE);
                findViewById(R.id.head_detail).setVisibility(View.GONE);
                adapterDetail = new BaseRecyclerAdapter<DetailResultsBean>(this, mDatasDetail) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_outsource_feed;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, DetailResultsBean item) {
                        switch (intentCode) {
                            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                                holder.setTextView(R.id.tv_line_num, item.getLine());
                                break;
                            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                                holder.setTextView(R.id.tv_line_num, item.getPoLine());
                                break;
                            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                                holder.setTextView(R.id.tv_line_num, item.getPoLine());
                                break;
                            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                                holder.setTextView(R.id.tv_line_num, item.getLine());
                                break;
                            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                                holder.setTextView(R.id.tv_line_num, item.getPoLine());
                                break;
                            case STOCK_OUT_PRODUCTION_BILL://生产生单
                            case STOCK_OUT_PRODUCTION_ALLOT://生产生单
                                holder.setTextView(R.id.tv_line_num, item.getPoLine());
                                break;
                            case STOCK_OUT_PICK://拣料
                                break;
                            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                                break;
                            case STOCK_OUT_SELL_OUT_BILL://销售生单
                                break;
                            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                                break;
                            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                                break;
                            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                                break;
                            default:
                                break;
                        }
                        holder.setTextView(R.id.tv_material_code, item.getMaterialCode());
                        holder.setTextView(R.id.tv_send_material_num, item.getQty());
                        holder.setTextView(R.id.tv_scan_num, item.getWipQty());
                        holder.setTextView(R.id.tv_material_name, item.getMaterialName());
                    }
                };
                rlvOrdernoInfo.setAdapter(adapterDetail);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                linearLayoutManager.setAutoMeasureEnabled(true);
                rlvOrdernoInfo.setLayoutManager(linearLayoutManager);
                rlvOrdernoInfo.setNestedScrollingEnabled(false);
                rlvOrdernoInfo.setHasFixedSize(true);
                rlvOrdernoInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_point_divider));
                adapterDetail.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int pos) {
                        Intent it = new Intent(BatchPointListActivity.this, BatchPointActivity.class);
                        it.putExtra(STOCK_OUT_CODE_STR, intentCode);
                        it.putExtra(OUT_STOCK_DETAIL_RESULTS_BEAN, new Gson().toJson(mDatasDetail.get(pos)));
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_SCANID, scanId);
                        startActivity(it);
                    }
                });
            } else {
                adapterDetail.notifyDataSetChanged();
            }
        }
    }

    /**
     * 设置scanid
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setScanId(OutsourceAuditEvent event) {
        if (event.getEvent().equals(OutsourceAuditEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS)) {
            this.scanId = event.getScanId();
            /**
             * 对adapter 进行判断，从而对数据源进行改变
             */
            if (null != adapterMaterial) {
                for (int i = 0; i < mDatas.size(); i++) {
                    if (mDatas.get(i).getMaterialCode().equals(event.getMaterialCode())) {
                        mDatas.get(i).setScanQty(mDatas.get(i).getScanQty() + event.getBarcodeQty());
                        mDatas.get(i).setWaitQty(mDatas.get(i).getWaitQty() - event.getBarcodeQty());
                        adapterMaterial.notifyDataSetChanged();
                        break;
                    }
                }
            } else {
                for (int i = 0; i < mDatasDetail.size(); i++) {
                    if (mDatasDetail.get(i).getMaterialCode().equals(event.getMaterialCode())) {
                        mDatasDetail.get(i).setScanQty(mDatasDetail.get(i).getScanQty() + event.getBarcodeQty());
                        mDatasDetail.get(i).setWaitQty(mDatas.get(i).getWaitQty() - event.getBarcodeQty());
                        adapterDetail.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }
}
