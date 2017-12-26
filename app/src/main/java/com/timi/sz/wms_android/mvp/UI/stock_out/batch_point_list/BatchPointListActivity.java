package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.other.OtherOutAndInStockBean;
import com.timi.sz.wms_android.bean.outstock.detail.BillMaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;
import com.timi.sz.wms_android.bean.outstock.pick.GetSalesOutSotckByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryTransferByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockOutSubmitScanMaterialEvent;
import com.timi.sz.wms_android.http.message.event.SubmitCreateBillEvent;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_normal.BatchNormalActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_point.BatchPointActivity;
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

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_MATERIAL_RESULTS_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BATCh_DETAILID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SALE_CARTON_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SALE_IS_CARTON;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT;
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
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SALE_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SEND_OUT_PICK;

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
    @BindView(R.id.ll_stock_name)
    LinearLayout llStockName;
    @BindView(R.id.ll_strict_mame)
    LinearLayout llStrictMame;
    @BindView(R.id.ll_send_total)
    LinearLayout llSendTotal;
    @BindView(R.id.tv_wait_count_num_tip)
    TextView tvWaitCountNumTip;
    @BindView(R.id.ll_wait_total)
    LinearLayout llWaitTotal;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.ll_have_scan)
    LinearLayout llHaveScan;
    @BindView(R.id.tv_supllier)
    TextView tvSupllier;
    @BindView(R.id.ll_supplier)
    LinearLayout llSupplier;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.ll_department)
    LinearLayout llDepartment;
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
    private int warehouseId = 0;
    /**
     * 区域Id
     */
    private int regionId = 0;
    /**
     * 当前的点击位置
     */
    private int currentPosition = 0;
    /**
     * 是否显示区域 仓库
     */
    private boolean isShowStockName = false;
    /**
     * 源单类型
     */
    private int srcBillType;
    /**
     * 目标单类型
     */
    private int destBillType;
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
     * ====== 成品拣货======
     **/
    private QueryDNByInputForPickResult queryDNByInputForPickResult;
    /**
     * 销售出库 审核
     */
    private QueryDNByInputForOutStockResult queryDNByInputForOutStockResult;
    /**
     * 销售出库 生单
     */
    private QuerySalesOutSotckByInputForOutStockResult querySalesOutSotckByInputForOutStockResult;
    /**
     * 其他出库 审核
     */
    private OtherOutAndInStockBean otherAuditSelectOrdernoBean;
    /**
     * 其他出库 生单
     */
    private QueryOtherOutStockByInputResult queryOtherOutStockByInputResult;
    /**
     * 调拨出库
     */
    private QueryAllotOutResult allotOut;
    /**
     * 适配器
     */
    private BaseRecyclerAdapter<MaterialResultsBean> adapterMaterial;
    /**
     * 销售拣货
     */
    private GetSalesOutSotckByInputForPickResult salePickBean;
    /**
     * 发料拣货
     */
    private QueryDNByInputForPickResult sendPickBean;
    /**
     * 调拨拣货
     */
    private QueryTransferByInputForPickResult allotPick;
    /**
     * 链表
     */
    private List<MaterialResultsBean> mDatas = new ArrayList<>();
    /**
     * 明细Id
     */
    private int detailId;


    @Override
    public int setLayoutId() {
        return R.layout.activity_batch_point_list;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        BaseMessage.register(this);
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
        detailId = getIntent().getIntExtra(OUT_STOCK_PRINT_BATCh_DETAILID, -1);
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                srcBillType = 21;
                destBillType = 21;
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                srcBillType = 20;
                destBillType = 20;
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                srcBillType = 20;
                destBillType = 20;
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                srcBillType = 20;
                destBillType = 20;
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                srcBillType = 24;
                destBillType = 24;
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                srcBillType = 23;
                destBillType = 23;
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
                srcBillType = 31;
                destBillType = 31;
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                srcBillType = 23;
                destBillType = 23;
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                srcBillType = 50;
                destBillType = 50;
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                srcBillType = 41;
                destBillType = 42;
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                srcBillType = 42;
                destBillType = 42;
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                srcBillType = 51;
                destBillType = 52;
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                srcBillType = 52;
                destBillType = 52;
                break;
            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                srcBillType = 61;
                destBillType = 61;
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨拣货
                srcBillType = 50;
                destBillType = 61;
                break;
            case STOCK_OUT_SALE_OUT_PICK://销售拣货
                srcBillType = 42;
                destBillType = 61;
            case STOCK_OUT_SEND_OUT_PICK://发货拣货
                srcBillType = 41;
                destBillType = 61;
                break;
            case STOCK_OUT_ALLOT_OUT://调拨调出
                srcBillType = 50;
                destBillType = 50;
                break;
            default:
                break;
        }
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
                //设置区域id
                regionId = summaryResults.getRegionId();
                //billId
                billId = summaryResults.getBillId();
                // isShowStockName
                isShowStockName = summaryResults.isIsRegion();
                //设置显示供应商
                llSupplier.setVisibility(View.VISIBLE);
                tvSupllier.setText(summaryResults.getSupplierName());
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
                //设置区域id
                regionId = summaryResultsAudit.getRegionId();
                //billId
                billId = summaryResultsAudit.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsAudit.isIsRegion();
                //设置显示供应商
                llSupplier.setVisibility(View.VISIBLE);
                tvSupllier.setText(summaryResultsAudit.getSupplierName());
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
                //设置区域id
                regionId = summaryResultsBill.getRegionId();
                //billId
                billId = summaryResultsBill.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsBill.isIsRegion();
                //设置显示供应商
                llSupplier.setVisibility(View.VISIBLE);
                tvSupllier.setText(summaryResultsBill.getSupplierName());
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
                //设置区域id
                regionId = summaryResultsAllot.getRegionId();
                //billId
                billId = summaryResultsAllot.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsAllot.isIsRegion();
                //设置显示供应商
                llSupplier.setVisibility(View.VISIBLE);
                tvSupllier.setText(summaryResultsAllot.getSupplierName());
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
                //设置区域id
                regionId = summaryResultsProductionFeed.getRegionId();
                //billId
                billId = summaryResultsProductionFeed.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsProductionFeed.isIsRegion();
                //设置部门
                llDepartment.setVisibility(View.VISIBLE);
                tvDepartment.setText(summaryResultsProductionFeed.getDeptName());
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
                //设置区域id
                regionId = summaryResultsProductionAudit.getRegionId();
                //billId
                billId = summaryResultsProductionAudit.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsProductionAudit.isIsRegion();
                //设置部门
                llDepartment.setVisibility(View.VISIBLE);
                tvDepartment.setText(summaryResultsProductionAudit.getDeptName());
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL:
                tvHeadTitle.setText(R.string.get_material_material_list_tip);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                queryProductPickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryProductPickByInputResult.class);
                //获取 summaryResults
                QueryProductPickByInputResult.SummaryResultsBean summaryResultsProductionApply = queryProductPickByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionApply.getBillCode(), summaryResultsProductionApply.getBillDate(), summaryResultsProductionApply.getWarehouseName(), summaryResultsProductionApply.getRegionName(), summaryResultsProductionApply.getQty(), summaryResultsProductionApply.getWaitQty(), summaryResultsProductionApply.getScanQty());
                //设置scanid
                scanId = summaryResultsProductionApply.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsProductionApply.getRegionId();
                //billId
                billId = summaryResultsProductionApply.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsProductionApply.isIsRegion();
                //设置部门
                llDepartment.setVisibility(View.VISIBLE);
                tvDepartment.setText(summaryResultsProductionApply.getDeptName());
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                btnPointCommit.setText(R.string.create_production_get_material_list_tip);
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
                //设置区域id
                regionId = summaryResultsProductionBill.getRegionId();
                //billId
                billId = summaryResultsProductionBill.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsProductionBill.isIsRegion();
                //设置部门
                llDepartment.setVisibility(View.VISIBLE);
                tvDepartment.setText(summaryResultsProductionBill.getDeptName());
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                btnPointCommit.setText(R.string.create_production_allot_list_tip);
                tvHeadTitle.setText(R.string.material_list_production_allot_title);
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
                //设置区域id
                regionId = summaryResultsProductionAllot.getRegionId();
                //billId
                billId = summaryResultsProductionAllot.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsProductionAllot.isIsRegion();
                //设置部门
                llDepartment.setVisibility(View.VISIBLE);
                tvDepartment.setText(summaryResultsProductionAllot.getDeptName());
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                tvHeadTitle.setText(R.string.material_list_sale_title);
                queryDNByInputForOutStockResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForOutStockResult.class);
                QueryDNByInputForOutStockResult.SummaryResultsBean summaryResultsSaleAudit = queryDNByInputForOutStockResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSaleAudit.getBillCode(), summaryResultsSaleAudit.getBillDate(), summaryResultsSaleAudit.getWarehouseName(), summaryResultsSaleAudit.getRegionName(), summaryResultsSaleAudit.getQty(), summaryResultsSaleAudit.getWaitQty(), summaryResultsSaleAudit.getScanQty());
                //设置scanid
                scanId = summaryResultsSaleAudit.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsSaleAudit.getRegionId();
                //billId
                billId = summaryResultsSaleAudit.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsSaleAudit.isIsRegion();
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                tvHeadTitle.setText(R.string.material_list_sale_title);
                btnPointCommit.setText(R.string.create_sale_outstock_orderno_tip);
                querySalesOutSotckByInputForOutStockResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QuerySalesOutSotckByInputForOutStockResult.class);
                QuerySalesOutSotckByInputForOutStockResult.SummaryResultsBean summaryResultsSaleBill = querySalesOutSotckByInputForOutStockResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSaleBill.getBillCode(), summaryResultsSaleBill.getBillDate(), summaryResultsSaleBill.getWarehouseName(), summaryResultsSaleBill.getRegionName(), summaryResultsSaleBill.getQty(), summaryResultsSaleBill.getWaitQty(), summaryResultsSaleBill.getScanQty());
                //设置scanid
                scanId = summaryResultsSaleBill.getScanId();
                //设置 submitType
                submitType = 0;
                //设置区域id
                regionId = summaryResultsSaleBill.getRegionId();
                //billId
                billId = summaryResultsSaleBill.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsSaleBill.isIsRegion();
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                tvHeadTitle.setText(R.string.other_audit_material_orderno);
                otherAuditSelectOrdernoBean = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), OtherOutAndInStockBean.class);
                OtherOutAndInStockBean.SummaryResultsBean summaryResultsOtherAudit = otherAuditSelectOrdernoBean.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsOtherAudit.getBillCode(), summaryResultsOtherAudit.getBillDate(), summaryResultsOtherAudit.getWarehouseName(), summaryResultsOtherAudit.getRegionName(), summaryResultsOtherAudit.getQty(), summaryResultsOtherAudit.getWaitQty(), summaryResultsOtherAudit.getScanQty());
                //设置scanid
                scanId = summaryResultsOtherAudit.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsOtherAudit.getRegionId();
                //billId
                billId = summaryResultsOtherAudit.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsOtherAudit.isIsRegion();
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                tvHeadTitle.setText(R.string.other_bill_material_list_title);
                btnPointCommit.setText(R.string.create_other_outstock_orderno_tip);
                queryOtherOutStockByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryOtherOutStockByInputResult.class);
                QueryOtherOutStockByInputResult.SummaryResultsBean summaryResultsOtherBill = queryOtherOutStockByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsOtherBill.getBillCode(), summaryResultsOtherBill.getBillDate(), summaryResultsOtherBill.getWarehouseName(), summaryResultsOtherBill.getRegionName(), summaryResultsOtherBill.getQty(), summaryResultsOtherBill.getWaitQty(), summaryResultsOtherBill.getScanQty());
                //设置scanid
                scanId = summaryResultsOtherBill.getScanId();
                //设置 submitType
                submitType = 0;
                //设置区域id
                regionId = summaryResultsOtherBill.getRegionId();
                //billId
                billId = summaryResultsOtherBill.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsOtherBill.isIsRegion();
                break;
            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                tvHeadTitle.setText(R.string.material_point_list_finish_goods_pick_title);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                queryDNByInputForPickResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForPickResult.class);
                //获取 summaryResults
                QueryDNByInputForPickResult.SummaryResultsBean summaryResultsFinishGoodsPick = queryDNByInputForPickResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsFinishGoodsPick.getBillCode(), summaryResultsFinishGoodsPick.getBillDate(), summaryResultsFinishGoodsPick.getWarehouseName(), summaryResultsFinishGoodsPick.getRegionName(), summaryResultsFinishGoodsPick.getQty(), summaryResultsFinishGoodsPick.getWaitQty(), summaryResultsFinishGoodsPick.getScanQty());
                //设置scanid
                scanId = summaryResultsFinishGoodsPick.getScanId();
                //设置 submitType
                submitType = 0;
                //设置区域id
                regionId = summaryResultsFinishGoodsPick.getRegionId();
                //billId
                billId = summaryResultsFinishGoodsPick.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsFinishGoodsPick.isIsRegion();
            case STOCK_OUT_ALLOT_OUT://调拨出库
                tvHeadTitle.setText(R.string.material_point_list_allot_out);
                btnPointCommit.setVisibility(View.GONE);
                allotOut = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryAllotOutResult.class);
                QueryAllotOutResult.SummaryResultsBean summaryResultsAllotOut = allotOut.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAllotOut.getBillCode(), summaryResultsAllotOut.getBillDate(), summaryResultsAllotOut.getWarehouseName(), summaryResultsAllotOut.getRegionName(), summaryResultsAllotOut.getQty(), summaryResultsAllotOut.getWaitQty(), summaryResultsAllotOut.getScanQty());
                //设置scanid
                scanId = summaryResultsAllotOut.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsAllotOut.getRegionId();
                //billId
                billId = summaryResultsAllotOut.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsAllotOut.isIsRegion();
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨拣货
                btnPointCommit.setVisibility(View.GONE);
                tvHeadTitle.setText(R.string.material_point_list_allot_out);
                allotPick = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryTransferByInputForPickResult.class);
                QueryTransferByInputForPickResult.SummaryResultsBean summaryResultsAllotPick = allotPick.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAllotPick.getBillCode(), summaryResultsAllotPick.getBillDate(), summaryResultsAllotPick.getWarehouseName(), summaryResultsAllotPick.getRegionName(), summaryResultsAllotPick.getQty(), summaryResultsAllotPick.getWaitQty(), summaryResultsAllotPick.getScanQty());
                //设置scanid
                scanId = summaryResultsAllotPick.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsAllotPick.getRegionId();
                //billId
                billId = summaryResultsAllotPick.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsAllotPick.isIsRegion();
                break;
            case STOCK_OUT_SALE_OUT_PICK://销售拣货
                btnPointCommit.setVisibility(View.GONE);
                tvHeadTitle.setText(R.string.create_sale_outstock_orderno_tip);
                salePickBean = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), GetSalesOutSotckByInputForPickResult.class);
                GetSalesOutSotckByInputForPickResult.SummaryResultsBean summaryResultsSalePick = salePickBean.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSalePick.getBillCode(), summaryResultsSalePick.getBillDate(), summaryResultsSalePick.getWarehouseName(), summaryResultsSalePick.getRegionName(), summaryResultsSalePick.getQty(), summaryResultsSalePick.getWaitQty(), summaryResultsSalePick.getScanQty());
                //设置scanid
                scanId = summaryResultsSalePick.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsSalePick.getRegionId();
                //billId
                billId = summaryResultsSalePick.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsSalePick.isIsRegion();
                break;
            case STOCK_OUT_SEND_OUT_PICK://发料拣货
                btnPointCommit.setText(R.string.create_allot_out_orderno);
                btnPointCommit.setVisibility(View.GONE);
                tvHeadTitle.setText(R.string.batch_list_send_pick_title);
                sendPickBean = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForPickResult.class);
                QueryDNByInputForPickResult.SummaryResultsBean summaryResultsSendPick = sendPickBean.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSendPick.getBillCode(), summaryResultsSendPick.getBillDate(), summaryResultsSendPick.getWarehouseName(), summaryResultsSendPick.getRegionName(), summaryResultsSendPick.getQty(), summaryResultsSendPick.getWaitQty(), summaryResultsSendPick.getScanQty());
                //设置scanid
                scanId = summaryResultsSendPick.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsSendPick.getRegionId();
                //billId
                billId = summaryResultsSendPick.getBillId();
                // isShowStockName
                isShowStockName = summaryResultsSendPick.isIsRegion();
                break;
            default:
                break;
        }
        findViewById(R.id.ll_strict_mame).setVisibility(isShowStockName ? View.VISIBLE : View.GONE);
        findViewById(R.id.ll_stock_name).setVisibility(isShowStockName ? View.VISIBLE : View.GONE);
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
        BaseMessage.post(new SubmitCreateBillEvent(SubmitCreateBillEvent.SUBMIT_CREATE_BILL_SUCCESS));
        ToastUtils.showShort(R.string.commit_success);
        onBackPressed();
    }

    /**
     * 获取批次
     *
     * @param data
     */
    @Override
    public void getMaterialLotData(GetMaterialLotData data) {
        switch (data.getControlType()) {
            case 0://非管控 跳转到非管控界面
                Intent it1 = new Intent(BatchPointListActivity.this, BatchNormalActivity.class);
                it1.putExtra(STOCK_OUT_CODE_STR, intentCode);
                it1.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                it1.putExtra(OUT_STOCK_PRINT_BATCh_DETAILID, detailId);
                it1.putExtra(OUT_STOCK_MATERIAL_RESULTS_BEAN, new Gson().toJson(mDatas.get(currentPosition)));
                it1.putExtra(OUT_STOCK_SCANID, scanId);
                it1.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                /**
                 * 是否是销售 如果是销售出库 还会由箱号这个字段
                 */
                if (intentCode == STOCK_OUT_SELL_OUT_AUDIT) {
                    it1.putExtra(OUT_STOCK_SALE_IS_CARTON, queryDNByInputForOutStockResult.getSummaryResults().isIsCarton());
                    it1.putExtra(OUT_STOCK_SALE_CARTON_NUM, queryDNByInputForOutStockResult.getSummaryResults().getCartonNo());
                }
                if (intentCode == STOCK_OUT_SELL_OUT_BILL) {
                    it1.putExtra(OUT_STOCK_SALE_IS_CARTON, querySalesOutSotckByInputForOutStockResult.getSummaryResults().isIsCarton());
                    it1.putExtra(OUT_STOCK_SALE_CARTON_NUM, querySalesOutSotckByInputForOutStockResult.getSummaryResults().getCartonNo());
                }
                startActivity(it1);
                break;
            case 1://非强制管控 （出库非制定批次则提醒）
            case 2://强制管控（必须扫描指定的批次出库）
                Intent it = new Intent(BatchPointListActivity.this, BatchPointActivity.class);
                it.putExtra(STOCK_OUT_CODE_STR, intentCode);
                it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                it.putExtra(OUT_STOCK_PRINT_BATCh_DETAILID, detailId);
                it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, mDatas.get(currentPosition).getWarehouseId());
                it.putExtra(OUT_STOCK_MATERIAL_RESULTS_BEAN, new Gson().toJson(mDatas.get(currentPosition)));
                it.putExtra(OUT_STOCK_SCANID, scanId);
                it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                /**
                 * 是否是销售 如果是销售出库 还会由箱号这个字段
                 */
                if (intentCode == STOCK_OUT_SELL_OUT_AUDIT) {
                    it.putExtra(OUT_STOCK_SALE_IS_CARTON, queryDNByInputForOutStockResult.getSummaryResults().isIsCarton());
                    it.putExtra(OUT_STOCK_SALE_CARTON_NUM, queryDNByInputForOutStockResult.getSummaryResults().getCartonNo());
                }
                if (intentCode == STOCK_OUT_SELL_OUT_BILL) {
                    it.putExtra(OUT_STOCK_SALE_IS_CARTON, querySalesOutSotckByInputForOutStockResult.getSummaryResults().isIsCarton());
                    it.putExtra(OUT_STOCK_SALE_CARTON_NUM, querySalesOutSotckByInputForOutStockResult.getSummaryResults().getCartonNo());
                }
                startActivity(it);
                break;
            default:
                break;
        }
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
        tvCreateOrdernoDate.setText(billDate);
        tvStockName.setText(wareHouseName);
        tvStrictName.setText(regionName);
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
        boolean isIsMerge = false;
        List<MaterialResultsBean> materialResults = null;
        /**
         * 通过对 isIsMerge （是否合并 ） 的判断进行数据源的设置
         */
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                isIsMerge = false;
                materialResults = queryOutSourceFeedByInputResult.getDetailResults();
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                isIsMerge = true;
                materialResults = queryOutSourcePickByInputResult.getMaterialResults();
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                isIsMerge = queryWWPickDataByOutSourceResult.getSummaryResults().isIsMerge();
                if (isIsMerge) {//如果是合并拣料则直接拿MaterialResults
                    materialResults = queryWWPickDataByOutSourceResult.getMaterialResults();
                } else {
                    BillMaterialDetailResult billMaterialDetailResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_DETAIL_BEAN), BillMaterialDetailResult.class);
                    setTextViewContent(tvWaitPointNum, billMaterialDetailResult.getTotalWaitQty());
                    setTextViewContent(tvHaveCountNum, billMaterialDetailResult.getTotalScanQty());
                    setTextViewContent(tvBuyNum, billMaterialDetailResult.getTotalQty());
                    materialResults = billMaterialDetailResult.getMaterialResults();
                }
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                isIsMerge = false;
                materialResults = queryPrdFeedByInputResult.getDetailResults();
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
                isIsMerge = queryProductPickByInputResult.getSummaryResults().isIsMerge();
                materialResults = queryProductPickByInputResult.getMaterialResults();
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨isIsMerge
                isIsMerge=queryWWPickDataByOutSourceResult.getSummaryResults().isIsMerge();
                if (isIsMerge) {//如果是合并拣料则直接拿MaterialResults
                    materialResults = queryWWPickDataByOutSourceResult.getMaterialResults();
                } else {
                    BillMaterialDetailResult billMaterialDetailResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_DETAIL_BEAN), BillMaterialDetailResult.class);
                    setTextViewContent(tvWaitPointNum, billMaterialDetailResult.getTotalWaitQty());
                    setTextViewContent(tvHaveCountNum, billMaterialDetailResult.getTotalScanQty());
                    setTextViewContent(tvBuyNum, billMaterialDetailResult.getTotalQty());
                    materialResults = billMaterialDetailResult.getMaterialResults();
                }
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                isIsMerge = false;
                materialResults = queryDNByInputForOutStockResult.getDetailResults();
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                isIsMerge = false;
                materialResults = querySalesOutSotckByInputForOutStockResult.getDetailResults();
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                isIsMerge = false;
                materialResults = otherAuditSelectOrdernoBean.getDetailResults();
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                isIsMerge = false;
                BillMaterialDetailResult billMaterialDetailResultOther = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_DETAIL_BEAN), BillMaterialDetailResult.class);
                materialResults = billMaterialDetailResultOther.getMaterialResults();
                break;
            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                BillMaterialDetailResult billMaterialDetailResultFinish = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_DETAIL_BEAN), BillMaterialDetailResult.class);
                materialResults = billMaterialDetailResultFinish.getMaterialResults();
                break;
            case STOCK_OUT_ALLOT_OUT_PICK:
                materialResults = allotPick.getDetailResults();
                break;
            case STOCK_OUT_SALE_OUT_PICK://销售拣货
                materialResults = salePickBean.getDetailResults();
                break;
            case STOCK_OUT_SEND_OUT_PICK://发料拣货
                materialResults = sendPickBean.getDetailResults();
                break;
            case STOCK_OUT_ALLOT_OUT:
                materialResults = allotOut.getDetailResults();
                break;
            default:
                break;
        }
        /**
         * 对数据进行处理
         */
        for (int i = 0; i < materialResults.size(); i++) {
            if (!ivShowMore.isSelected()) {
                if (materialResults.get(i).getWaitQty() > 0) {
                    mDatas.add(materialResults.get(i));
                }
            } else {
                mDatas.add(materialResults.get(i));
            }
        }

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
                    holder.setTextView(R.id.tv_material_name, item.getMaterialName() + item.getMaterialAttribute());
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
                    currentPosition = pos;
                    /**
                     * 提交成功后 对批次信息进行修改
                     * 1、用于显示批次信息中的已点数是否发生了更改
                     */
                    MaterialResultsBean materialResultsBean = mDatas.get(pos);

                    RequestGetMaterialLotBean bean = new RequestGetMaterialLotBean();
                    bean.setMAC(PackageUtils.getMac());
                    bean.setUserId(SpUtils.getInstance().getUserId());
                    bean.setOrgId(SpUtils.getInstance().getOrgId());
                    bean.setBillId(billId);
                    bean.setSrcBillType(srcBillType);
                    bean.setDestBillType(destBillType);
                    bean.setWarehouseId(warehouseId);
                    bean.setMaterialId(materialResultsBean.getMaterialId());
                    bean.setMaterialCode(materialResultsBean.getMaterialCode());
                    bean.setMaterialAttribute(materialResultsBean.getMaterialAttribute());
                    LogUitls.e("上传的参数---->", new Gson().toJson(bean));
                    showProgressDialog();
                    getPresenter().getMaterialLotData(bean);
                }
            });
        } else {
            adapterMaterial.notifyDataSetChanged();
        }

    }

    /**
     * 设置scanid
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setScanId(StockOutSubmitScanMaterialEvent event) {
        if (event.getEvent().equals(StockOutSubmitScanMaterialEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS)) {
            SubmitBarcodeLotPickOutResult result = event.getResult();
            this.scanId = result.getScanId();
            /**
             * 如果是销售出库 并且箱号有更改则设置箱号
             */
            if(intentCode== Constants.STOCK_OUT_SELL_OUT_AUDIT||intentCode==Constants.STOCK_OUT_SELL_OUT_BILL){
                //设置箱号
                if(null!=queryDNByInputForOutStockResult){
                    queryDNByInputForOutStockResult.getSummaryResults().setCartonNo(result.getCartonNo());
                }
                //设置箱号
                if(null!=querySalesOutSotckByInputForOutStockResult){
                    querySalesOutSotckByInputForOutStockResult.getSummaryResults().setCartonNo(result.getCartonNo());
                }
            }
            /**
             * 对adapter 进行判断，从而对数据源进行改变
             */
            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i).getMaterialCode().equals(result.getMaterialCode())) {
                    mDatas.get(i).setScanQty(result.getLineScanQty());
                    mDatas.get(i).setWaitQty(mDatas.get(i).getQty() - result.getLineScanQty());
                    break;
                }
            }
            adapterMaterial.notifyDataSetChanged();
            tvWaitPointNum.setText(String.valueOf(Integer.parseInt(tvBuyNum.getText().toString()) - result.getTotalScanQty()));
            tvHaveCountNum.setText(String.valueOf(result.getTotalScanQty()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }
}
