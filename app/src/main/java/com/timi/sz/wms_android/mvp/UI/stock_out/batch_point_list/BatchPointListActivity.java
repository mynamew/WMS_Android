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
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;
import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
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
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SALE_CARTON_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SALE_IS_CARTON;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
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
    private int warehouseId = 0;
    /**
     * 区域Id
     */
    private int regionId = 0;
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
    private OtherAuditSelectOrdernoBean otherAuditSelectOrdernoBean;
    /**
     * 其他出库 生单
     */
    private QueryOtherOutStockByInputResult queryOtherOutStockByInputResult;   /**
     * 调拨出库
     */
    private QueryAllotOutResult queryAllotOutResult;
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
        return R.layout.activity_batch_point_list;
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
                //设置区域id
                regionId = summaryResults.getRegionId();
                //billId
                billId = summaryResults.getBillId();
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
                //设置区域id
                regionId = summaryResultsProductionBill.getRegionId();
                //billId
                billId = summaryResultsProductionBill.getBillId();
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                btnPointCommit.setText(R.string.create_send_material_orderno);
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
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                tvHeadTitle.setText(R.string.other_audit_material_orderno);
                otherAuditSelectOrdernoBean = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), OtherAuditSelectOrdernoBean.class);
                OtherAuditSelectOrdernoBean.SummaryResultsBean summaryResultsOtherAudit = otherAuditSelectOrdernoBean.getSummaryResults();
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
                break;
            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                btnPointCommit.setText(R.string.create_send_material_orderno);
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
            case STOCK_OUT_ALLOT_OUT_PICK://调拨调出
                btnPointCommit.setText(R.string.create_allot_out_orderno);
                tvHeadTitle.setText(R.string.material_point_list_allot_out);
                queryAllotOutResult=new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryAllotOutResult.class);
                QueryAllotOutResult.SummaryResultsBean summaryResultsAllotOut = queryAllotOutResult.getSummaryResults();
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
            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
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
                isIsMerge = false;
                detailResults = queryDNByInputForOutStockResult.getDetailResults();
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                isIsMerge = false;
                detailResults = querySalesOutSotckByInputForOutStockResult.getDetailResults();
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                isIsMerge = false;
                detailResults = otherAuditSelectOrdernoBean.getDetailResults();
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                isIsMerge=false;
                detailResults=queryOtherOutStockByInputResult.getDetailResults();
                break;
            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                isIsMerge = false;
                detailResults = queryDNByInputForPickResult.getDetailResults();
                break;
            case STOCK_OUT_ALLOT_OUT_PICK:
                isIsMerge = false;
                detailResults = queryAllotOutResult.getDetailResults();
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
                if (ivShowMore.isSelected()) {
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
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, mDatas.get(pos).getWarehouseId());
                        it.putExtra(OUT_STOCK_MATERIAL_RESULTS_BEAN, new Gson().toJson(mDatas.get(pos)));
                        it.putExtra(OUT_STOCK_SCANID, scanId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        /**
                         * 是否是销售 如果是销售出库 还会由箱号这个字段
                         */
                        if(intentCode==STOCK_OUT_OTHER_OUT_AUDIT){
                            it.putExtra(OUT_STOCK_SALE_IS_CARTON, otherAuditSelectOrdernoBean.getSummaryResults().isIsCarton());
                            it.putExtra(OUT_STOCK_SALE_CARTON_NUM, otherAuditSelectOrdernoBean.getSummaryResults().getCartonNo());
                        }
                        if(intentCode==STOCK_OUT_OTHER_OUT_BILL){
                            it.putExtra(OUT_STOCK_SALE_IS_CARTON, queryOtherOutStockByInputResult.getSummaryResults().isIsCarton());
                            it.putExtra(OUT_STOCK_SALE_CARTON_NUM, queryOtherOutStockByInputResult.getSummaryResults().getCartonNo());
                        }
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
                            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
                                holder.setTextView(R.id.tv_line_num, item.getPoLine());
                                break;
                            case STOCK_OUT_PRODUCTION_BILL://生产生单
                            case STOCK_OUT_PRODUCTION_ALLOT://生产生单
                                holder.setTextView(R.id.tv_line_num, item.getPoLine());
                                break;
                            case STOCK_OUT_PICK://拣料
                                break;
                            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                                holder.setTextView(R.id.tv_line_num, item.getLine());                                break;
                            case STOCK_OUT_SELL_OUT_BILL://销售生单
                                holder.setTextView(R.id.tv_line_num, item.getLine());
                                break;
                            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                                break;
                            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                                holder.setTextView(R.id.tv_line_num, item.getLine());
                                break;
                            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                                holder.setTextView(R.id.tv_line_num, item.getLine());
                                break;
                            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                                holder.setTextView(R.id.tv_line_num, item.getLine());
                                break;
                            case STOCK_OUT_ALLOT_OUT_PICK://调拨调出
                                holder.setTextView(R.id.tv_line_num, item.getLine());
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
                rlvOrdernoInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.item_badness_divider));
                adapterDetail.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int pos) {
                        Intent it = new Intent(BatchPointListActivity.this, BatchPointActivity.class);
                        it.putExtra(STOCK_OUT_CODE_STR, intentCode);
                        it.putExtra(OUT_STOCK_DETAIL_RESULTS_BEAN, new Gson().toJson(mDatasDetail.get(pos)));
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
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
            int waitQty = Integer.parseInt(tvWaitPointNum.getText().toString());
            int scanQty = Integer.parseInt(tvHaveCountNum.getText().toString());
            tvWaitPointNum.setText(String.valueOf(waitQty - event.getBarcodeQty()));
            tvHaveCountNum.setText(String.valueOf(scanQty + event.getBarcodeQty()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }
}
