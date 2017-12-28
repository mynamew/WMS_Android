package com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.other.OtherOutAndInStockBean;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryTransferByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.StockOutSubmitScanMaterialEvent;
import com.timi.sz.wms_android.http.message.event.SubmitBarcodeLotPickOutSplitEvent;
import com.timi.sz.wms_android.mvp.UI.stock_out.detail.DetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.detail.outsource_bill_detail.OutsourceBillDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BARCODENO;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BATCh_AND_NORMAL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BATCh_DETAILID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_CURRENT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_DESBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIALID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_ATTR;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_CODE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_MODEL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_MATERIAL_NAME;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_OUT_QTY;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SCANID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_PRINT_SRCBILLTYPE;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_TO_DETAIL_FORM_NORMAL;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT;
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
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SALE_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SEND_OUT_PICK;

/**
 * 普通出库
 * author: timi
 * create at: 2017/11/29 17:53
 */
public class NormalOutStockActivity extends BaseActivity<NormalOutStockView, NormalOutStockPresenter> implements NormalOutStockView {
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
    @BindView(R.id.tv_have_get_total_num)
    TextView tvHaveGetTotalNum;
    @BindView(R.id.tv_can_get_total_num_tip)
    TextView tvCanGetTotalNumTip;
    @BindView(R.id.tv_can_get_total_num)
    TextView tvCanGetTotalNum;
    @BindView(R.id.tv_outstock_total_num)
    TextView tvOutstockTotalNum;
    @BindView(R.id.tv_have_count_num_tip)
    TextView tvHaveCountNumTip;
    @BindView(R.id.tv_have_count_num)
    TextView tvHaveCountNum;
    @BindView(R.id.tv_wait_point_num)
    TextView tvWaitPointNum;
    @BindView(R.id.tv_putaway_scan_material_tip)
    TextView tvPutawayScanMaterialTip;
    @BindView(R.id.et_scan_material)
    EditText etScanMaterial;
    @BindView(R.id.iv_scan_material)
    ImageView ivScanMaterial;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_num)
    TextView tvMaterialNum;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_attr)
    TextView tvMaterialAttr;
    @BindView(R.id.tv_material_nmodel)
    TextView tvMaterialNmodel;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.tv_carton_num)
    TextView tvCartonNum;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.ll_carton)
    LinearLayout llCarton;
    @BindView(R.id.ll_material_attr)
    LinearLayout llMaterialAttr;
    /**
     * 跳转的code
     */
    private int intentCode;
    /**
     * 仓库id
     */
    private int warehouseId = 0;
    /**
     * 区域Id
     */
    private int regionId = 0;
    //是否装箱
    private boolean isCarton;
    //箱号
    private int cartonNum = 0;
    //审核/制单
    private int submitType = 0;
    //明细表Id
    private int detailId;

    @Override
    public int setLayoutId() {
        return R.layout.activity_normal_out_stock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, 0);
        detailId = getIntent().getIntExtra(OUT_STOCK_PRINT_BATCh_DETAILID, -1);
        BaseMessage.register(this);
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                //设置头部的提示信息
                setActivityTitle(getString(R.string.normal_outstock_outsource_feed_title));
                tvHeadTitle.setText(R.string.normal_outstock_outsource_feed_info);
                QueryOutSourceFeedByInputResult queryOutSourceFeedByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryOutSourceFeedByInputResult.class);
                //获取 summaryResults
                QueryOutSourceFeedByInputResult.SummaryResultsBean summaryResults = queryOutSourceFeedByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResults.getBillCode(), summaryResults.getBillDate(), summaryResults.getQty(), summaryResults.getWaitQty(), summaryResults.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResults.getBillId(),
                        summaryResults.getScanId(),
                        summaryResults.getQty(),
                        summaryResults.getWaitQty(),
                        summaryResults.getScanQty(), 21, 21);
                //设置仓库id
                warehouseId = summaryResults.getWarehouseId();
                //设置区域id
                regionId = summaryResults.getRegionId();
                //scanid
                scanId = summaryResults.getScanId();
                //设置 submitType
                submitType = 1;
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                setActivityTitle(getString(R.string.normal_outstock_outsource_audit_title));
                tvHeadTitle.setText(getString(R.string.normal_outstock_outsource_audit_info));
                QueryOutSourcePickByInputResult queryOutSourcePickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryOutSourcePickByInputResult.class);
                //获取 summaryResults
                QueryOutSourcePickByInputResult.SummaryResultsBean summaryResultsAudit = queryOutSourcePickByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAudit.getBillCode(), summaryResultsAudit.getBillDate(), summaryResultsAudit.getQty(), summaryResultsAudit.getWaitQty(), summaryResultsAudit.getScanQty());
                setBundleData(summaryResultsAudit.getBillId(),
                        summaryResultsAudit.getScanId(),
                        summaryResultsAudit.getQty(),
                        summaryResultsAudit.getWaitQty(),
                        summaryResultsAudit.getScanQty(), 20, 20);
                //设置仓库id
                warehouseId = summaryResultsAudit.getWarehouseId();
                //设置区域id
                regionId = summaryResultsAudit.getRegionId();
                //scanid
                scanId = summaryResultsAudit.getScanId();
                //设置 submitType
                submitType = 1;
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                setActivityTitle(getString(R.string.normal_outstock_outsource_audit_title));
                tvHeadTitle.setText(getString(R.string.normal_outstock_outsource_bill_info));
                QueryWWPickDataByOutSourceResult queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsBill = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsBill.getBillCode(), summaryResultsBill.getBillDate(), summaryResultsBill.getQty(), summaryResultsBill.getWaitQty(), summaryResultsBill.getScanQty());
                setBundleData(summaryResultsBill.getBillId(),
                        summaryResultsBill.getScanId(),
                        summaryResultsBill.getQty(),
                        summaryResultsBill.getWaitQty(),
                        summaryResultsBill.getScanQty(), 12, 20);
                //设置仓库id
                warehouseId = summaryResultsBill.getWarehouseId();
                //设置区域id
                regionId = summaryResultsBill.getRegionId();
                //scanid
                scanId = summaryResultsBill.getScanId();
                //设置按钮文字  生成生产领料单
                btnCommit.setText(R.string.create_production_get_material_list_tip);
                //设置 submitType
                submitType = 0;
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                setActivityTitle(getString(R.string.normal_outstock_outsource_allot_title));
                tvHeadTitle.setText(R.string.normal_outstock_outsource_allot_info);
                QueryWWPickDataByOutSourceResult queryWWPickDataByOutSourceResultAllot = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsAllot = queryWWPickDataByOutSourceResultAllot.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAllot.getBillCode(), summaryResultsAllot.getBillDate(), summaryResultsAllot.getQty(), summaryResultsAllot.getWaitQty(), summaryResultsAllot.getScanQty());
                setBundleData(summaryResultsAllot.getBillId(),
                        summaryResultsAllot.getScanId(),
                        summaryResultsAllot.getQty(),
                        summaryResultsAllot.getWaitQty(),
                        summaryResultsAllot.getScanQty(), 12, 50);
                //设置scanid
                scanId = summaryResultsAllot.getScanId();
                //billId
                billId = summaryResultsAllot.getBillId();
                //设置仓库id
                warehouseId = summaryResultsAllot.getWarehouseId();
                //设置区域id
                regionId = summaryResultsAllot.getRegionId();
                //scanid
                scanId = summaryResultsAllot.getScanId();
                //设置 submitType
                submitType = 0;
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                setActivityTitle(getString(R.string.normal_outstock_production_feed_title));
                tvHeadTitle.setText(R.string.normal_outstock_production_feed_info);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryPrdFeedByInputResult queryPrdFeedByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryPrdFeedByInputResult.class);
                //获取 summaryResults
                QueryPrdFeedByInputResult.SummaryResultsBean summaryResultsProductionFeed = queryPrdFeedByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionFeed.getBillCode(), summaryResultsProductionFeed.getBillDate(), summaryResultsProductionFeed.getQty(), summaryResultsProductionFeed.getWaitQty(), summaryResultsProductionFeed.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResultsProductionFeed.getBillId(),
                        summaryResultsProductionFeed.getScanId(),
                        summaryResultsProductionFeed.getQty(),
                        summaryResultsProductionFeed.getWaitQty(),
                        summaryResultsProductionFeed.getScanQty(), 24, 24);
                //设置仓库id
                warehouseId = summaryResultsProductionFeed.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionFeed.getRegionId();
                //scanid
                scanId = summaryResultsProductionFeed.getScanId();
                //设置 submitType
                submitType = 1;
                //源单类型  目标单类型
                srcBillType = 24;
                destBillType = 24;
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                setActivityTitle(getString(R.string.normal_outstock_production_audit_title));
                tvHeadTitle.setText(R.string.normal_outstock_production_audit_info);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryProductPickByInputResult proAudit = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryProductPickByInputResult.class);
                //获取 summaryResults
                QueryProductPickByInputResult.SummaryResultsBean summaryResultsProductionAudit = proAudit.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionAudit.getBillCode(), summaryResultsProductionAudit.getBillDate(), summaryResultsProductionAudit.getQty(), summaryResultsProductionAudit.getWaitQty(), summaryResultsProductionAudit.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResultsProductionAudit.getBillId(),
                        summaryResultsProductionAudit.getScanId(),
                        summaryResultsProductionAudit.getQty(),
                        summaryResultsProductionAudit.getWaitQty(),
                        summaryResultsProductionAudit.getScanQty(), 23, 23);
                //设置仓库id
                warehouseId = summaryResultsProductionAudit.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionAudit.getRegionId();
                //scanid
                scanId = summaryResultsProductionAudit.getScanId();
                //设置 submitType
                submitType = 1;
                //源单类型  目标单类型
                srcBillType = 23;
                destBillType = 23;
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                setActivityTitle(getString(R.string.normal_outstock_production_bill_title));
                tvHeadTitle.setText(R.string.normal_outstock_production_bill_info);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryWWPickDataByOutSourceResult proBill = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsProductionBill = proBill.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionBill.getBillCode(), summaryResultsProductionBill.getBillDate(), summaryResultsProductionBill.getQty(), summaryResultsProductionBill.getWaitQty(), summaryResultsProductionBill.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResultsProductionBill.getBillId(),
                        summaryResultsProductionBill.getScanId(),
                        summaryResultsProductionBill.getQty(),
                        summaryResultsProductionBill.getWaitQty(),
                        summaryResultsProductionBill.getScanQty(), 30, 23);
                //设置仓库id
                warehouseId = summaryResultsProductionBill.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionBill.getRegionId();
                //billId
                billId = summaryResultsProductionBill.getBillId();
                //scanid
                scanId = summaryResultsProductionBill.getScanId();
                //设置按钮文字  生成生产领料单
                btnCommit.setText(R.string.create_production_get_material_list_tip);
                //设置 submitType
                submitType = 0;

                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                setActivityTitle(getString(R.string.normal_outstock_production_allot_title));
                tvHeadTitle.setText(R.string.normal_outstock_production_allot_info);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryWWPickDataByOutSourceResult proAllot = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsProductionAllot = proAllot.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionAllot.getBillCode(), summaryResultsProductionAllot.getBillDate(), summaryResultsProductionAllot.getQty(), summaryResultsProductionAllot.getWaitQty(), summaryResultsProductionAllot.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsProductionAllot.getBillId(),
                        summaryResultsProductionAllot.getScanId(),
                        summaryResultsProductionAllot.getQty(),
                        summaryResultsProductionAllot.getWaitQty(),
                        summaryResultsProductionAllot.getScanQty(), 30, 50);
                //设置scanid
                scanId = summaryResultsProductionAllot.getScanId();
                //设置仓库id
                warehouseId = summaryResultsProductionAllot.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionAllot.getRegionId();
                //billId
                billId = summaryResultsProductionAllot.getBillId();
                //设置 submitType
                submitType = 0;
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
                setActivityTitle(getString(R.string.normal_outstock_production_apply_title));
                tvHeadTitle.setText(R.string.normal_outstock_production_apply_info);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryProductPickByInputResult queryProductPickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryProductPickByInputResult.class);
                //获取 summaryResults
                QueryProductPickByInputResult.SummaryResultsBean summaryResultsProductionApply = queryProductPickByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionApply.getBillCode(), summaryResultsProductionApply.getBillDate(), summaryResultsProductionApply.getQty(), summaryResultsProductionApply.getWaitQty(), summaryResultsProductionApply.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsProductionApply.getBillId(),
                        summaryResultsProductionApply.getScanId(),
                        summaryResultsProductionApply.getQty(),
                        summaryResultsProductionApply.getWaitQty(),
                        summaryResultsProductionApply.getScanQty(), 31, 23);

                //设置scanid
                scanId = summaryResultsProductionApply.getScanId();
                //设置 submitType
                submitType = 1;
                //设置区域id
                regionId = summaryResultsProductionApply.getRegionId();
                //billId
                billId = summaryResultsProductionApply.getBillId();
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                setActivityTitle(getString(R.string.normal_outstock_sale_audit_title));
                tvHeadTitle.setText(R.string.normal_outstock_sale_audit_info);
                QueryDNByInputForOutStockResult queryDNByInputForOutStockResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForOutStockResult.class);
                QueryDNByInputForOutStockResult.SummaryResultsBean summaryResultsSaleAudit = queryDNByInputForOutStockResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSaleAudit.getBillCode(), summaryResultsSaleAudit.getBillDate(), summaryResultsSaleAudit.getQty(), summaryResultsSaleAudit.getWaitQty(), summaryResultsSaleAudit.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsSaleAudit.getBillId(),
                        summaryResultsSaleAudit.getScanId(),
                        summaryResultsSaleAudit.getQty(),
                        summaryResultsSaleAudit.getWaitQty(),
                        summaryResultsSaleAudit.getScanQty(), 42, 42);
                //设置scanid
                scanId = summaryResultsSaleAudit.getScanId();
                //设置仓库id
                warehouseId = summaryResultsSaleAudit.getWarehouseId();
                //设置区域id
                regionId = summaryResultsSaleAudit.getRegionId();
                //billId
                billId = summaryResultsSaleAudit.getBillId();
                //iscarton
                isCarton = summaryResultsSaleAudit.isIsCarton();
                //cartonnum
                cartonNum = summaryResultsSaleAudit.getCartonNo();
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                setActivityTitle(R.string.normal_outstock_sale_bill_title);
                tvHeadTitle.setText(R.string.normal_outstock_sale_bill_info);
                //设置按钮文字  销售出库单
                btnCommit.setText(R.string.create_sale_ger_material_list_tip);
                QuerySalesOutSotckByInputForOutStockResult querySalesOutSotckByInputForOutStockResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QuerySalesOutSotckByInputForOutStockResult.class);
                QuerySalesOutSotckByInputForOutStockResult.SummaryResultsBean summaryResultsSaleBill = querySalesOutSotckByInputForOutStockResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSaleBill.getBillCode(), summaryResultsSaleBill.getBillDate(), summaryResultsSaleBill.getQty(), summaryResultsSaleBill.getWaitQty(), summaryResultsSaleBill.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsSaleBill.getBillId(),
                        summaryResultsSaleBill.getScanId(),
                        summaryResultsSaleBill.getQty(),
                        summaryResultsSaleBill.getWaitQty(),
                        summaryResultsSaleBill.getScanQty(), 41, 42);

                //设置scanid
                scanId = summaryResultsSaleBill.getScanId();
                //设置仓库id
                warehouseId = summaryResultsSaleBill.getWarehouseId();
                //设置区域id
                regionId = summaryResultsSaleBill.getRegionId();
                //billId
                billId = summaryResultsSaleBill.getBillId();
                //iscarton
                isCarton = summaryResultsSaleBill.isIsCarton();
                //cartonnum
                cartonNum = summaryResultsSaleBill.getCartonNo();
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://其他审核
                tvHeadTitle.setText(R.string.normal_outstock_other_audit_info);
                setActivityTitle(getString(R.string.normal_outstock_other_audit_title));
                OtherOutAndInStockBean otherAuditSelectOrdernoBean = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), OtherOutAndInStockBean.class);
                OtherOutAndInStockBean.SummaryResultsBean summaryResultsOtherAudit = otherAuditSelectOrdernoBean.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsOtherAudit.getBillCode(), summaryResultsOtherAudit.getBillDate(), summaryResultsOtherAudit.getQty(), summaryResultsOtherAudit.getWaitQty(), summaryResultsOtherAudit.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsOtherAudit.getBillId(),
                        summaryResultsOtherAudit.getScanId(),
                        summaryResultsOtherAudit.getQty(),
                        summaryResultsOtherAudit.getWaitQty(),
                        summaryResultsOtherAudit.getScanQty(), 52, 52);

                //设置scanid
                scanId = summaryResultsOtherAudit.getScanId();
                //设置仓库id
                warehouseId = summaryResultsOtherAudit.getWarehouseId();
                //设置区域id
                regionId = summaryResultsOtherAudit.getRegionId();
                //billId
                billId = summaryResultsOtherAudit.getBillId();
                break;
            case STOCK_OUT_OTHER_OUT_BILL://其他生单
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨拣货
                tvHeadTitle.setText(R.string.normal_outstock_allot_pick_info);
                setActivityTitle(R.string.normal_outstock_allot_pick_title);
                //拣货没有制单和审核的操作
                btnCommit.setVisibility(View.GONE);
                QueryAllotOutResult queryAllotOutResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryAllotOutResult.class);
                QueryAllotOutResult.SummaryResultsBean summaryResultsAllotOut = queryAllotOutResult.getSummaryResults();

                setHeaderContent(summaryResultsAllotOut.getBillCode(), summaryResultsAllotOut.getBillDate(), summaryResultsAllotOut.getQty(), summaryResultsAllotOut.getWaitQty(), summaryResultsAllotOut.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsAllotOut.getBillId(),
                        summaryResultsAllotOut.getScanId(),
                        summaryResultsAllotOut.getQty(),
                        summaryResultsAllotOut.getWaitQty(),
                        summaryResultsAllotOut.getScanQty(), 50, 61);

                //设置scanid
                scanId = summaryResultsAllotOut.getScanId();
                //设置仓库id
                warehouseId = summaryResultsAllotOut.getWarehouseId();
                //设置区域id
                regionId = summaryResultsAllotOut.getRegionId();
                //billId
                billId = summaryResultsAllotOut.getBillId();

                break;
            case STOCK_OUT_SALE_OUT_PICK://销售拣货
                tvHeadTitle.setText(R.string.normal_outstock_sale_pick_info);
                setActivityTitle(R.string.normal_outstock_sale_pick_title);
                //拣货没有制单和审核的操作
                btnCommit.setVisibility(View.GONE);
                QueryDNByInputForPickResult salePickBean = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForPickResult.class);
                QueryDNByInputForPickResult.SummaryResultsBean summaryResultsSendPick = salePickBean.getSummaryResults();

                setHeaderContent(summaryResultsSendPick.getBillCode(), summaryResultsSendPick.getBillDate(), summaryResultsSendPick.getQty(), summaryResultsSendPick.getWaitQty(), summaryResultsSendPick.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsSendPick.getBillId(),
                        summaryResultsSendPick.getScanId(),
                        summaryResultsSendPick.getQty(),
                        summaryResultsSendPick.getWaitQty(),
                        summaryResultsSendPick.getScanQty(), 42, 61);

                //设置scanid
                scanId = summaryResultsSendPick.getScanId();
                //设置仓库id
                warehouseId = summaryResultsSendPick.getWarehouseId();
                //设置区域id
                regionId = summaryResultsSendPick.getRegionId();
                //billId
                billId = summaryResultsSendPick.getBillId();
                break;
            case STOCK_OUT_SEND_OUT_PICK://发货拣货
                tvHeadTitle.setText(R.string.normal_outstock_send_pick_info);
                setActivityTitle(R.string.normal_outstock_send_pick_title);
                //拣货没有制单和审核的操作
                btnCommit.setVisibility(View.GONE);
                QueryDNByInputForPickResult sendPick = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForPickResult.class);
                QueryDNByInputForPickResult.SummaryResultsBean summaryResultsSend = sendPick.getSummaryResults();
                setHeaderContent(summaryResultsSend.getBillCode(), summaryResultsSend.getBillDate(), summaryResultsSend.getQty(), summaryResultsSend.getWaitQty(), summaryResultsSend.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsSend.getBillId(),
                        summaryResultsSend.getScanId(),
                        summaryResultsSend.getQty(),
                        summaryResultsSend.getWaitQty(),
                        summaryResultsSend.getScanQty(), 41, 61);

                //设置scanid
                scanId = summaryResultsSend.getScanId();
                //设置仓库id
                warehouseId = summaryResultsSend.getWarehouseId();
                //设置区域id
                regionId = summaryResultsSend.getRegionId();
                //billId
                billId = summaryResultsSend.getBillId();
                break;
            case STOCK_OUT_ALLOT_OUT://调拨调出
                QueryTransferByInputForPickResult allotPick = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryTransferByInputForPickResult.class);
                QueryTransferByInputForPickResult.SummaryResultsBean summaryResultsAllotPick = allotPick.getSummaryResults();
                setHeaderContent(summaryResultsAllotPick.getBillCode()
                        , summaryResultsAllotPick.getBillDate()
                        , summaryResultsAllotPick.getQty()
                        , summaryResultsAllotPick.getWaitQty()
                        , summaryResultsAllotPick.getScanQty());
                //设置bundle 数量
                setBundleData(summaryResultsAllotPick.getBillId(),
                        summaryResultsAllotPick.getScanId(),
                        summaryResultsAllotPick.getQty(),
                        summaryResultsAllotPick.getWaitQty(),
                        summaryResultsAllotPick.getScanQty(), 50,50);

                //设置scanid
                scanId = summaryResultsAllotPick.getScanId();
                //设置仓库id
                warehouseId = summaryResultsAllotPick.getWarehouseId();
                //设置区域id
                regionId = summaryResultsAllotPick.getRegionId();
                //billId
                billId = summaryResultsAllotPick.getBillId();
                break;
            default:
                break;
        }
    }

    /**
     * 设置头部的数据
     *
     * @param billCode
     * @param billDate
     * @param qty
     * @param waitQty
     * @param scanQty
     */
    private void setHeaderContent(String billCode, String billDate, int qty, int waitQty, int scanQty) {
        tvOutsourceOrderno.setText(billCode);
        tvCreateOrdernoDate.setText(billDate);
        tvWaitPointNum.setText(String.valueOf(waitQty));
        tvOutstockTotalNum.setText(String.valueOf(qty));
        tvHaveCountNum.setText(String.valueOf(scanQty));
    }

    /**
     * @param billId
     * @param scanId
     * @param totalQty
     * @param waitQty
     * @param scanQty
     * @param srcBillType
     * @param destBillType
     */
    private void setBundleData(int billId, int scanId, int totalQty, int waitQty, int scanQty, int srcBillType, int destBillType) {
        this.billId = billId;
        this.scanId = scanId;
        this.scanQty = scanQty;
        this.totalQty = totalQty;
        this.waitQty = waitQty;
        this.srcBillType = srcBillType;
        this.destBillType = destBillType;
    }

    /**
     * 搜索单号传过来的数据
     */
    private int billId = 0;
    private int scanId = 0;
    private int scanQty = 0;
    private int totalQty = 0;
    private int waitQty = 0;
    private int srcBillType = 0;
    private int destBillType = 0;

    @Override
    public void initView() {
        /**
         * 设置查看详情的点击事件
         */
        setRightImg(R.mipmap.stockin_detail, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                /**
                 * 不同的intentcode  请求不同
                 */
                switch (intentCode) {
                    case Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                    case Constants.STOCK_OUT_OUTSOURCE_AUDIT://委外发料-审核
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;

                    case Constants.STOCK_OUT_OUTSOURCE_BILL://委外发料-生单
                    case Constants.STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                        it.setClass(NormalOutStockActivity.this, OutsourceBillDetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        it.putExtra(OUT_STOCK_TO_DETAIL_FORM_NORMAL, true);
                        break;

                    case Constants.STOCK_OUT_PRODUCTION_FEEDING://生产补料
                    case Constants.STOCK_OUT_PRODUCTION_AUDIT://生产领料-审核
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;

                    case Constants.STOCK_OUT_PRODUCTION_BILL://生产领料-生单
                    case Constants.STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                        it.setClass(NormalOutStockActivity.this, OutsourceBillDetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        it.putExtra(OUT_STOCK_TO_DETAIL_FORM_NORMAL, true);
                        break;
                    case Constants.STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        it.putExtra(OUT_STOCK_TO_DETAIL_FORM_NORMAL, true);
                        break;
                    case Constants.STOCK_OUT_SELL_OUT_AUDIT://销售领料-审核
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                    case Constants.STOCK_OUT_SELL_OUT_BILL://销售领料-生单
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                    case Constants.STOCK_OUT_OTHER_OUT_AUDIT://其他出库-审核
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                    case Constants.STOCK_OUT_OTHER_OUT_BILL://其他出库-生单
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                    case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                    case STOCK_OUT_ALLOT_OUT_PICK://调拨出库
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                    case STOCK_OUT_SALE_OUT_PICK:
                    case STOCK_OUT_SEND_OUT_PICK:
                    case STOCK_OUT_ALLOT_OUT:
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                }
                /**
                 * 查看详情
                 */
                it.putExtra(Constants.STOCK_OUT_CODE_STR, intentCode);
                startActivity(it);
            }
        });
        setEdittextListener(etScanMaterial, Constants.REQUEST_SCAN_CODE_MATERIIAL, R.string.please_input_return_matrial_code_or_scan, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
//                /**
//                 * 退料单号的 网络请求
//                 */
//                Map<String, Object> params = new HashMap<>();
//                params.put("UserId", SpUtils.getInstance().getUserId());
//                params.put("OrgId", SpUtils.getInstance().getOrgId());
//                params.put("MAC", PackageUtils.getMac());
//                params.put("BillId", billId);
//                params.put("SrcBillType", srcBillType);
//                params.put("DestBillType", destBillType);
//                params.put("ScanId", scanId);
//                params.put("BarcodeNo", result);
//                if (isCarton) {
//                    params.put("cartonNo", cartonNum);
//                }
//                getPresenter().submitBarcodeOutAudit(params);
                /**
                 *   批次拣料的请求
                 */
                Map<String, Object> params = new HashMap<>();
                /**
                 * 是否 装箱
                 */
                if (isCarton) {
                    params.put("cartonNo", cartonNum);
                }
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("MAC", PackageUtils.getMac());
                params.put("BillId", billId);
                params.put("SrcBillType", srcBillType);
                params.put("DestBillType", destBillType);
                params.put("ScanId", scanId);
                params.put("BarcodeNo", result);
                /**
                 * 如果detailid 有值 证明是从明细表过来的子件数据
                 */
                if (detailId != -1) {
                    params.put("DetailId", detailId);
                }
                //判断 批次是否为空
                params.put("bCheckMode", true);
                getPresenter().submitBarcodeLotPickOut(params);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public NormalOutStockPresenter createPresenter() {
        return new NormalOutStockPresenter(this);
    }

    @Override
    public NormalOutStockView createView() {
        return this;
    }

    @Override
    public void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data) {
        ToastUtils.showShort(getString(R.string.commit_success));
        /**
         * 设置物料的信息
         */
        tvMaterialName.setText(data.getMaterialName());
        tvMaterialCode.setText(data.getMaterialCode());
        tvMaterialAttr.setText(data.getMaterialAttribute());
        tvMaterialNmodel.setText(data.getMaterialStandard());
        /**
         * 设置 是否显示附加属性
         */
        setMaterialAttrStatus(findViewById(R.id.ll_material_attr));
        /**
         * 超出数量  跳转到拆分条吗界面
         */
        if (data.getExceedQty() > 0) {
            Intent intent = new Intent(this, SplitPrintActivity.class);
            intent.putExtra(OUT_STOCK_PRINT_BILLID, billId);
            intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
            intent.putExtra(OUT_STOCK_PRINT_SRCBILLTYPE, srcBillType);
            intent.putExtra(OUT_STOCK_PRINT_BATCh_DETAILID, detailId);
            intent.putExtra(OUT_STOCK_PRINT_BARCODENO, etScanMaterial.getText().toString().trim());
            intent.putExtra(OUT_STOCK_PRINT_DESBILLTYPE, destBillType);
            intent.putExtra(OUT_STOCK_PRINT_SCANID, data.getScanId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_ATTR, data.getMaterialAttribute());
            intent.putExtra(OUT_STOCK_PRINT_MATERIALID, data.getMaterialId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_NAME, data.getMaterialName());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_CODE, data.getMaterialCode());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_MODEL, data.getMaterialStandard());
            intent.putExtra(OUT_STOCK_PRINT_CURRENT_QTY, data.getBarcodeQty());
            intent.putExtra(OUT_STOCK_PRINT_OUT_QTY, data.getExceedQty());
            intent.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
            startActivity(intent);
        } else {
            /**
             * 设置已退数量
             */
            scanQty = scanQty + data.getBarcodeQty();
            tvMaterialNum.setText("(" + data.getBarcodeQty() + ")" + scanQty + "/" + totalQty);
            scanId = data.getScanId();
        }
    }

    @Override
    public void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data) {

    }

    @Override
    public void submitBarcodeLotPickOut(SubmitBarcodeLotPickOutResult result) {
        findViewById(R.id.ll_material_info).setVisibility(View.VISIBLE);
        /**
         * 设置物料的信息
         */
        tvMaterialName.setText(result.getMaterialName());
        tvMaterialCode.setText(result.getMaterialCode());
        tvMaterialAttr.setText(result.getMaterialAttribute());
        tvMaterialNmodel.setText(result.getMaterialStandard());
        /**
         * 是否需要拆分打码
         */
        if (result.getExceedQty() > 0) {
            Intent intent = new Intent(this, SplitPrintActivity.class);
            intent.putExtra(OUT_STOCK_PRINT_BILLID, billId);
            intent.putExtra(OUT_STOCK_PRINT_BATCh_DETAILID, detailId);
            intent.putExtra(OUT_STOCK_PRINT_SRCBILLTYPE, srcBillType);
            intent.putExtra(OUT_STOCK_PRINT_BARCODENO, etScanMaterial.getText().toString().trim());
            intent.putExtra(OUT_STOCK_PRINT_DESBILLTYPE, destBillType);
            intent.putExtra(OUT_STOCK_PRINT_SCANID, result.getScanId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_ATTR, result.getMaterialAttribute());
            intent.putExtra(OUT_STOCK_PRINT_MATERIALID, result.getMaterialId());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_NAME, result.getMaterialName());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_CODE, result.getMaterialCode());
            intent.putExtra(OUT_STOCK_PRINT_MATERIAL_MODEL, result.getMaterialStandard());
            intent.putExtra(OUT_STOCK_PRINT_CURRENT_QTY, result.getBarcodeQty());
            intent.putExtra(OUT_STOCK_PRINT_OUT_QTY, result.getExceedQty());
            intent.putExtra(OUT_STOCK_PRINT_BATCh_AND_NORMAL, true);
            startActivity(intent);
        } else {
            /**
             * True:非管控模式，让前端提醒（没有提交动作）
             * False:表示提交成功
             */
            if (result.isIsNotAllowPickOut()) {
                ToastUtils.showShort(R.string.please_comfirm_barcode_is_right);
            } else {//提交成功
                ToastUtils.showShort(getString(R.string.commit_success));
                /**
                 * 发送事件  传递 scanid
                 */
                StockOutSubmitScanMaterialEvent event = new StockOutSubmitScanMaterialEvent(StockOutSubmitScanMaterialEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS);
                event.setResult(result);
                BaseMessage.post(event);
                //设置scanid
                scanId = result.getScanId();
                /***
                 * 是否装箱
                 */
                if (isCarton) {
                    cartonNum = result.getCartonNo();
                    tvCartonNum.setText(String.valueOf(cartonNum));
                }
                /**
                 * 物料返回设置扫描的数量
                 */
                scanQty = result.getTotalScanQty();
                //设置数量
                tvMaterialNum.setText("(" + result.getBarcodeQty() + ")" + scanQty + "/" + totalQty);
                //待点  和  已点数量
                setTextViewContent(tvHaveCountNum, scanQty);
                setTextViewContent(tvWaitPointNum, totalQty - scanQty);
            }
        }
    }

    @Override
    public void submitMakeOrAuditBill() {
        ToastUtils.showShort(getString(R.string.commit_check_success));
        onBackPressed();
    }

    @OnClick({R.id.iv_scan_material, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan_material:
                scan(REQUEST_SCAN_CODE_MATERIIAL, new ScanQRCodeResultListener() {
                    @Override
                    public void scanSuccess(int requestCode, String result) {
                        etScanMaterial.setText(result);
                        /**
                         *   批次拣料的请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        /**
                         * 是否 装箱
                         */
                        if (isCarton) {
                            params.put("cartonNo", cartonNum);
                        }
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", srcBillType);
                        params.put("DestBillType", destBillType);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", result);
                        /**
                         * 如果detailid 有值 证明是从明细表过来的子件数据
                         */
                        if (detailId != -1) {
                            params.put("DetailId", detailId);
                        }
                        //判断 批次是否为空
                        params.put("bCheckMode", true);
                        getPresenter().submitBarcodeLotPickOut(params);
                    }
                });
                break;
            case R.id.btn_commit:
//                if (scanId == 0) {//scanid 为0  证明未扫过条码或者条码已经入库 或者出库过了
//                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
//                    return;
//                }
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


    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        cartonNum = 0;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void submitBarcodeSplitSuccess(SubmitBarcodeLotPickOutSplitEvent event) {
        if (event.getEvent().equals(SubmitBarcodeLotPickOutSplitEvent.SUBMIT_BARCODE_SPLIT_SUCCESS)) {
            SubmitBarcodeLotPickOutSplitResult result = event.getResult();
            /**
             * 发送事件  传递 scanid
             */
            StockOutSubmitScanMaterialEvent stockOutSubmitScanMaterialEvent = new StockOutSubmitScanMaterialEvent(StockOutSubmitScanMaterialEvent.OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS);
            SubmitBarcodeLotPickOutResult resultScanMaterial = stockOutSubmitScanMaterialEvent.getResult();
            resultScanMaterial.setBarcodeQty(result.getBarcodeQty());
            resultScanMaterial.setExceedQty(result.getExceedQty());
            resultScanMaterial.setScanId(result.getScanId());
            resultScanMaterial.setMaterialCode(result.getMaterialCode());
            resultScanMaterial.setTotalScanQty(result.getTotalScanQty());
            resultScanMaterial.setLineScanQty(result.getLineScanQty());
            stockOutSubmitScanMaterialEvent.setResult(resultScanMaterial);
            BaseMessage.post(stockOutSubmitScanMaterialEvent);
            //设置scanid
            scanId = result.getScanId();
            /**
             * 物料返回设置扫描的数量
             */
            scanQty = scanQty + result.getBarcodeQty();
            //设置数量
            tvMaterialNum.setText("(" + result.getBarcodeQty() + ")" + scanQty + "/" + totalQty);
            //待点  和  已点数量
            setTextViewContent(tvHaveCountNum, scanQty);
            setTextViewContent(tvWaitPointNum, totalQty - scanQty);
        }
    }

    @Override
    public void setBarcodeSelect() {
        etScanMaterial.setFocusable(true);
        etScanMaterial.setFocusableInTouchMode(true);
        etScanMaterial.requestFocus();
        etScanMaterial.findFocus();
        Selection.selectAll(etScanMaterial.getText());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }
}
