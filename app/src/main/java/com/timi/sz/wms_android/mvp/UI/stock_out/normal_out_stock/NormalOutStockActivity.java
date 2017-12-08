package com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.mvp.UI.stock_out.detail.DetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.detail.outsource_bill_detail.OutsourceBillDetailActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.divide_print.SplitPrintActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_DETIAIL_BILLID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_REGIONID;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_STOCK_POINT_WAREHOUSEID;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_SCAN_CODE_MATERIIAL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;
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

    @Override
    public int setLayoutId() {
        return R.layout.activity_normal_out_stock;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(Constants.STOCK_OUT_CODE_STR, 0);
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                //设置头部的提示信息
                tvHeadTitle.setText(R.string.outsource_feed_orderno_info);
                setActivityTitle(getString(R.string.outsource_feed_point_title));
                QueryOutSourceFeedByInputResult queryOutSourceFeedByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryOutSourceFeedByInputResult.class);
                //获取 summaryResults
                QueryOutSourceFeedByInputResult.SummaryResultsBean summaryResults = queryOutSourceFeedByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResults.getBillCode(), summaryResults.getBillDate(), summaryResults.getQty(), summaryResults.getWaitQty(), summaryResults.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResults.getBillId(), summaryResults.getScanId(), summaryResults.getQty(), summaryResults.getWaitQty(), summaryResults.getScanQty(), 21, 21);
                //设置仓库id
                warehouseId = summaryResults.getWarehouseId();
                //设置区域id
                regionId = summaryResults.getRegionId();
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                tvHeadTitle.setText(getString(R.string.outsource_send_material_oderno_info));
                setActivityTitle(getString(R.string.out_source_title));
                QueryOutSourcePickByInputResult queryOutSourcePickByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryOutSourcePickByInputResult.class);
                //获取 summaryResults
                QueryOutSourcePickByInputResult.SummaryResultsBean summaryResultsAudit = queryOutSourcePickByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAudit.getBillCode(), summaryResultsAudit.getBillDate(), summaryResultsAudit.getQty(), summaryResultsAudit.getWaitQty(), summaryResultsAudit.getScanQty());
                setBundleData(summaryResultsAudit.getBillId(), summaryResultsAudit.getScanId(), summaryResultsAudit.getQty(), summaryResultsAudit.getWaitQty(), summaryResultsAudit.getScanQty(), 20, 20);
                //设置仓库id
                warehouseId = summaryResultsAudit.getWarehouseId();
                //设置区域id
                regionId = summaryResultsAudit.getRegionId();
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外生单
                tvHeadTitle.setText(getString(R.string.outsource_send_material_oderno_info));
                setActivityTitle(getString(R.string.out_source_title));
                QueryWWPickDataByOutSourceResult queryWWPickDataByOutSourceResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsBill = queryWWPickDataByOutSourceResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsBill.getBillCode(), summaryResultsBill.getBillDate(), summaryResultsBill.getQty(), summaryResultsBill.getWaitQty(), summaryResultsBill.getScanQty());
                setBundleData(summaryResultsBill.getBillId(), summaryResultsBill.getScanId(), summaryResultsBill.getQty(), summaryResultsBill.getWaitQty(), summaryResultsBill.getScanQty(), 20, 20);
                //设置仓库id
                warehouseId = summaryResultsBill.getWarehouseId();
                //设置区域id
                regionId = summaryResultsBill.getRegionId();
                //设置按钮文字  生成生产领料单
                btnCommit.setText(R.string.create_production_get_material_list_tip);
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                tvHeadTitle.setText(R.string.outsource_allot_info_tip);
                setActivityTitle(getString(R.string.outsource_allot));
                QueryWWPickDataByOutSourceResult queryWWPickDataByOutSourceResultAllot = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsAllot = queryWWPickDataByOutSourceResultAllot.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsAllot.getBillCode(), summaryResultsAllot.getBillDate(), summaryResultsAllot.getQty(), summaryResultsAllot.getWaitQty(), summaryResultsAllot.getScanQty());
                setBundleData(summaryResultsAllot.getBillId(), summaryResultsAllot.getScanId(), summaryResultsAllot.getQty(), summaryResultsAllot.getWaitQty(), summaryResultsAllot.getScanQty(), 50, 50);
                //设置scanid
                scanId = summaryResultsAllot.getScanId();
                //billId
                billId = summaryResultsAllot.getBillId();
                //设置仓库id
                warehouseId = summaryResultsAllot.getWarehouseId();
                //设置区域id
                regionId = summaryResultsAllot.getRegionId();
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补料
                setActivityTitle(getString(R.string.stock_out_create_add_materail));
                tvHeadTitle.setText(R.string.production_feed_orderno_info_tip);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryPrdFeedByInputResult queryPrdFeedByInputResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryPrdFeedByInputResult.class);
                //获取 summaryResults
                QueryPrdFeedByInputResult.SummaryResultsBean summaryResultsProductionFeed = queryPrdFeedByInputResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionFeed.getBillCode(), summaryResultsProductionFeed.getBillDate(), summaryResultsProductionFeed.getQty(), summaryResultsProductionFeed.getWaitQty(), summaryResultsProductionFeed.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResultsProductionFeed.getBillId(), summaryResultsProductionFeed.getScanId(), summaryResultsProductionFeed.getQty(), summaryResultsProductionFeed.getWaitQty(), summaryResultsProductionFeed.getScanQty(), 24, 24);
                //设置仓库id
                warehouseId = summaryResultsProductionFeed.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionFeed.getRegionId();
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产审核
                setActivityTitle(getString(R.string.stock_out_create_check));
                tvHeadTitle.setText(R.string.production_get_orderno_info_tip);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryProductPickByInputResult proAudit = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryProductPickByInputResult.class);
                //获取 summaryResults
                QueryProductPickByInputResult.SummaryResultsBean summaryResultsProductionAudit = proAudit.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionAudit.getBillCode(), summaryResultsProductionAudit.getBillDate(), summaryResultsProductionAudit.getQty(), summaryResultsProductionAudit.getWaitQty(), summaryResultsProductionAudit.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResultsProductionAudit.getBillId(), summaryResultsProductionAudit.getScanId(), summaryResultsProductionAudit.getQty(), summaryResultsProductionAudit.getWaitQty(), summaryResultsProductionAudit.getScanQty(), 23, 23);
                //设置仓库id
                warehouseId = summaryResultsProductionAudit.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionAudit.getRegionId();
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产生单
                setActivityTitle(getString(R.string.stock_out_create_create_order));
                tvHeadTitle.setText(R.string.production_get_orderno_info_tip);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryWWPickDataByOutSourceResult proBill = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsProductionBill = proBill.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionBill.getBillCode(), summaryResultsProductionBill.getBillDate(), summaryResultsProductionBill.getQty(), summaryResultsProductionBill.getWaitQty(), summaryResultsProductionBill.getScanQty());
                //设置bundle的数据
                setBundleData(summaryResultsProductionBill.getBillId(), summaryResultsProductionBill.getScanId(), summaryResultsProductionBill.getQty(), summaryResultsProductionBill.getWaitQty(), summaryResultsProductionBill.getScanQty(), 23, 23);
                //设置仓库id
                warehouseId = summaryResultsProductionBill.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionBill.getRegionId();
                //billId
                billId = summaryResultsProductionBill.getBillId();
                //设置按钮文字  生成生产领料单
                btnCommit.setText(R.string.create_production_get_material_list_tip);
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                setActivityTitle(getString(R.string.production_allot));
                tvHeadTitle.setText(R.string.production_allot_info_tip);
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryWWPickDataByOutSourceResult proAllot = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryWWPickDataByOutSourceResult.class);
                //获取 summaryResults
                QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResultsProductionAllot = proAllot.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsProductionAllot.getBillCode(), summaryResultsProductionAllot.getBillDate(), summaryResultsProductionAllot.getQty(), summaryResultsProductionAllot.getWaitQty(), summaryResultsProductionAllot.getScanQty());
                //设置scanid
                scanId = summaryResultsProductionAllot.getScanId();
                //设置仓库id
                warehouseId = summaryResultsProductionAllot.getWarehouseId();
                //设置区域id
                regionId = summaryResultsProductionAllot.getRegionId();
                //billId
                billId = summaryResultsProductionAllot.getBillId();
                break;
            case STOCK_OUT_PICK://拣料
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                setActivityTitle(getString(R.string.sale_outstock_title));
                tvHeadTitle.setText(R.string.sale_outstock_orderno_info);
                QueryDNByInputForOutStockResult queryDNByInputForOutStockResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForOutStockResult.class);
                QueryDNByInputForOutStockResult.SummaryResultsBean summaryResultsSaleAudit = queryDNByInputForOutStockResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSaleAudit.getBillCode(), summaryResultsSaleAudit.getBillDate(), summaryResultsSaleAudit.getQty(), summaryResultsSaleAudit.getWaitQty(), summaryResultsSaleAudit.getScanQty());
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
                setActivityTitle(getString(R.string.sale_outstock_title));
                tvHeadTitle.setText(R.string.production_allot_info_tip);
                //设置按钮文字  销售出库单
                btnCommit.setText(R.string.create_sale_ger_material_list_tip);
                QuerySalesOutSotckByInputForOutStockResult querySalesOutSotckByInputForOutStockResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QuerySalesOutSotckByInputForOutStockResult.class);
                QuerySalesOutSotckByInputForOutStockResult.SummaryResultsBean summaryResultsSaleBill = querySalesOutSotckByInputForOutStockResult.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsSaleBill.getBillCode(), summaryResultsSaleBill.getBillDate(), summaryResultsSaleBill.getQty(), summaryResultsSaleBill.getWaitQty(), summaryResultsSaleBill.getScanQty());
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
                tvHeadTitle.setText(R.string.other_outstock_info);
                setActivityTitle(getString(R.string.other_outstock_title));
                OtherAuditSelectOrdernoBean otherAuditSelectOrdernoBean = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), OtherAuditSelectOrdernoBean.class);
                OtherAuditSelectOrdernoBean.SummaryResultsBean summaryResultsOtherAudit = otherAuditSelectOrdernoBean.getSummaryResults();
                //设置内容
                setHeaderContent(summaryResultsOtherAudit.getBillCode(), summaryResultsOtherAudit.getBillDate(), summaryResultsOtherAudit.getQty(), summaryResultsOtherAudit.getWaitQty(), summaryResultsOtherAudit.getScanQty());
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
            case STOCK_OUT_FINISH_GOODS_PICK://成品拣货
                tvHeadTitle.setText(R.string.finish_goods_orderno_info);
                setActivityTitle(getString(R.string.finish_goods_pick_tip));
                /**
                 * 生产生单 和委外生单的返回结果是一样的 所以直接一起处理
                 */
                QueryDNByInputForPickResult queryDNByInputForPickResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryDNByInputForPickResult.class);
                //获取 summaryResults
                QueryDNByInputForPickResult.SummaryResultsBean summaryResultsFinishGoodsPick = queryDNByInputForPickResult.getSummaryResults();
                setHeaderContent(summaryResultsFinishGoodsPick.getBillCode(), summaryResultsFinishGoodsPick.getBillDate(), summaryResultsFinishGoodsPick.getQty(), summaryResultsFinishGoodsPick.getWaitQty(), summaryResultsFinishGoodsPick.getScanQty());
                //设置scanid
                scanId = summaryResultsFinishGoodsPick.getScanId();
                //设置仓库id
                warehouseId = summaryResultsFinishGoodsPick.getWarehouseId();
                //设置区域id
                regionId = summaryResultsFinishGoodsPick.getRegionId();
                //billId
                billId = summaryResultsFinishGoodsPick.getBillId();
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨调出
                QueryAllotOutResult queryAllotOutResult = new Gson().fromJson(getIntent().getStringExtra(STOCK_OUT_BEAN), QueryAllotOutResult.class);
                QueryAllotOutResult.SummaryResultsBean summaryResultsAllotOut = queryAllotOutResult.getSummaryResults();

                setHeaderContent(summaryResultsAllotOut.getBillCode(), summaryResultsAllotOut.getBillDate(), summaryResultsAllotOut.getQty(), summaryResultsAllotOut.getWaitQty(), summaryResultsAllotOut.getScanQty());
                //设置scanid
                scanId = summaryResultsAllotOut.getScanId();
                //设置仓库id
                warehouseId = summaryResultsAllotOut.getWarehouseId();
                //设置区域id
                regionId = summaryResultsAllotOut.getRegionId();
                //billId
                billId = summaryResultsAllotOut.getBillId();
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
                        it.setClass(NormalOutStockActivity.this, OutsourceBillDetailActivity.class);
                        it.setClass(NormalOutStockActivity.this, DetailActivity.class);
                        it.putExtra(OUT_STOCK_POINT_WAREHOUSEID, warehouseId);
                        it.putExtra(OUT_STOCK_POINT_REGIONID, regionId);
                        it.putExtra(OUT_STOCK_POINT_DETIAIL_BILLID, billId);
                        break;
                    case STOCK_OUT_ALLOT_OUT_PICK://调拨出库
                        it.setClass(NormalOutStockActivity.this, OutsourceBillDetailActivity.class);
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
        etScanMaterial.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /**
                     * 输入的内容
                     */
                    String inputStr = etScanMaterial.getText().toString().trim();
                    if (TextUtils.isEmpty(inputStr)) {
                        ToastUtils.showShort(getString(R.string.please_input_return_matrial_code_or_scan));
                    } else {
                        /**
                         * 退料单号的 网络请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", srcBillType);
                        params.put("DestBillType", destBillType);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", inputStr);
                        if (isCarton) {
                            params.put("cartonNo", cartonNum);
                        }
                        getPresenter().submitBarcodeOutAudit(params);
                    }
                }
                return false;
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
        tvMaterialAttr.setText(TextUtils.isEmpty(data.getMaterialAttribute()) ? getString(R.string.none) : data.getMaterialAttribute());
        tvMaterialNmodel.setText(TextUtils.isEmpty(data.getMaterialStandard()) ? getString(R.string.none) : data.getMaterialStandard());
        /**
         * 设置物料数量
         */
        scanQty = scanQty + data.getBarcodeQty();
        tvMaterialNum.setText("(" + data.getBarcodeQty() + ")" + scanQty + "/" + totalQty);
        /**
         * 设置 是否显示附加属性
         */
        setMaterialAttrStatus(findViewById(R.id.ll_material_attr));
        /**
         * 超出数量  跳转到拆分条吗界面
         */
        if (data.getExceedQty() > 0) {
            startActivity(new Intent(this, SplitPrintActivity.class));
        } else {
            /**
             * 设置已退数量
             */
            scanQty = scanQty + data.getBarcodeQty();
            tvMaterialNum.setText(scanQty + "/" + totalQty);
            scanId = data.getScanId();
            /***
             * 是否装箱
             */
            if (isCarton) {
                cartonNum = data.getCartonNo();
                tvCartonNum.setText(String.valueOf(cartonNum));
            }
        }
    }

    @Override
    public void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data) {

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
                         * 退料单号的 网络请求
                         */
                        Map<String, Object> params = new HashMap<>();
                        params.put("UserId", SpUtils.getInstance().getUserId());
                        params.put("OrgId", SpUtils.getInstance().getOrgId());
                        params.put("MAC", PackageUtils.getMac());
                        params.put("BillId", billId);
                        params.put("SrcBillType", srcBillType);
                        params.put("DestBillType", destBillType);
                        params.put("ScanId", scanId);
                        params.put("BarcodeNo", result);
                        if (isCarton) {
                            params.put("cartonNo", cartonNum);
                        }
                        getPresenter().submitBarcodeOutAudit(params);
                    }
                });
                break;
            case R.id.btn_commit:
                if (TextUtils.isEmpty(etScanMaterial.getText().toString().trim())) {
                    ToastUtils.showShort(getString(R.string.please_input_return_matrial_code_or_scan));
                    return;
                }
                if (scanId == 0) {//scanid 为0  证明未扫过条码或者条码已经入库 或者出库过了
                    ToastUtils.showShort(getString(R.string.please_inpiut_or_scan_visible_material_code));
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("UserId", SpUtils.getInstance().getUserId());
                params.put("MAC", PackageUtils.getMac());
                params.put("OrgId", SpUtils.getInstance().getOrgId());
                params.put("ScanId", scanId);
                params.put("SubmitType", 0);
                getPresenter().submitMakeOrAuditBill(params);
                break;
        }
    }


    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        cartonNum = 0;
    }
}
