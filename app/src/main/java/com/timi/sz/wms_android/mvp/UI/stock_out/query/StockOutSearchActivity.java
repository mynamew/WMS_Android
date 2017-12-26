package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.other.OtherOutAndInStockBean;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QuerySalesOutSotckByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryTransferByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.mvp.UI.stock_in.putaway.OtherAuditActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list.BatchPointListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock.NormalOutStockActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.EDITTEXT_ORDERNO;
import static com.timi.sz.wms_android.base.uils.Constants.IN_STOCK_FINISH_OTHER_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SALE_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SEND_OUT_PICK;

/**
 * 出库搜索界面
 * author: timi
 * create at: 2017/9/4 8:51
 */
public class StockOutSearchActivity extends BaseActivity<StockOutSearchView, StockOutSearchPresenter> implements StockOutSearchView {
    @BindView(R.id.tv_stockout_tip)
    TextView tvStockoutTip;
    @BindView(R.id.et_stockout_input)
    EditText etStockoutInput;
    @BindView(R.id.iv_sotckout_scan)
    ImageView ivSotckoutScan;
    @BindView(R.id.tv_query_title)
    TextView tvQueryTitle;
    private int intentCode = 5020;// code，码 默认是委外退料单

    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_out_search;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        intentCode = getIntent().getIntExtra(STOCK_OUT_CODE_STR, STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT);
    }

    @Override
    public void initView() {
        /**
         *设置title 和 text
         */
        switch (intentCode) {
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                setActivityTitle(getString(R.string.stock_out_outsource_feed_title));
                tvQueryTitle.setText(R.string.stock_out_out_add_materail);
                tvStockoutTip.setText(R.string.stock_out_outsource_feed_tip);
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外审核
                setActivityTitle(getString(R.string.stock_out_outsource_audit_title));
                tvQueryTitle.setText(R.string.out_source_title);
                tvStockoutTip.setText(R.string.stock_out_outsource_audit_tip);
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外 生单
                setActivityTitle(getString(R.string.stock_out_outsource_bill_title));
                tvQueryTitle.setText(R.string.out_source_title);
                tvStockoutTip.setText(R.string.stock_out_outsource_bill_tip);
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                setActivityTitle(getString(R.string.outsource_allot));
                tvQueryTitle.setText(R.string.outsource_allot);
                tvStockoutTip.setText(R.string.stock_out_outsource_bill_tip);
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产 补料
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvQueryTitle.setText(R.string.stock_out_create_add_materail);
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产 审核
                setActivityTitle(getString(R.string.stock_out_create_check));
                tvQueryTitle.setText(R.string.production_pick_material_title);
                tvStockoutTip.setText(R.string.stock_out_production_audit_tip);
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产 生单
                setActivityTitle(getString(R.string.stock_out_create_create_order));
                tvQueryTitle.setText(R.string.production_pick_material_title);
                tvStockoutTip.setText(R.string.stock_out_production_audit_tip);
                break;
            case STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                setActivityTitle(getString(R.string.production_allot));
                tvQueryTitle.setText(R.string.production_allot);
                tvStockoutTip.setText(R.string.production_allot_orderno);
                break;
            case STOCK_OUT_SELL_OUT_AUDIT://销售 审核
                setActivityTitle(getString(R.string.sale_outstock_audit_title));
                tvQueryTitle.setText(R.string.sale_outstock_title);
                tvStockoutTip.setText(R.string.sale_outstock_orderno_tip);
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售 生单
                setActivityTitle(getString(R.string.sale_outstock_bill_title));
                tvQueryTitle.setText(R.string.sale_outstock_title);
                tvStockoutTip.setText(R.string.sale_orderno_tip);
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT:// 其他 审核
                setActivityTitle(getString(R.string.other_outstock_audit_title));
                tvQueryTitle.setText(R.string.stock_out_other_check);
                tvStockoutTip.setText(R.string.stock_out_other_audit_tip);
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL:// 申请审核
                setActivityTitle(getString(R.string.get_material_apply_audit_title));
                tvQueryTitle.setText(R.string.get_material_apply_tip);
                tvStockoutTip.setText(R.string.get_material_apply_orderno_tip);
                break;

            case STOCK_OUT_FINISH_GOODS_PICK:// 产成品拣货
                setActivityTitle(getString(R.string.finish_goods_pick_tip));
                tvStockoutTip.setText(R.string.stock_out_pick_send_orderno);
                tvQueryTitle.setText(R.string.finish_goods_outstock_tip);
                break;
            case STOCK_OUT_SEND_OUT_PICK://发货拣货
                setActivityTitle(R.string.stock_out_pick_send_tab);
                tvStockoutTip.setText(R.string.stock_out_pick_send_orderno);
                tvQueryTitle.setText(R.string.stock_out_pick_send_tab);
                break;
            case STOCK_OUT_SALE_OUT_PICK://销售拣货
                setActivityTitle(getString(R.string.stock_out_pick_sale_tab));
                tvStockoutTip.setText(R.string.stock_out_pick_sell_outstock_orderno);
                tvQueryTitle.setText(R.string.stock_out_pick_sale_tab);
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨拣货
                setActivityTitle(getString(R.string.stock_out_pick_tab));
                tvStockoutTip.setText(R.string.query_allot_scan_tip);
                tvQueryTitle.setText(R.string.stock_out_pick_tab);
                break;
            case  Constants.STOCK_OUT_ALLOT_OUT:
                setActivityTitle(getString(R.string.allot_outstock_tip));
                tvStockoutTip.setText(R.string.stock_out_pick_tip);
                tvQueryTitle.setText(R.string.allot_outstock_tip);
                break;

        }
        /**
         * 设置输入框的监听
         */
        setEdittextListener(etStockoutInput, EDITTEXT_ORDERNO, R.string.please_input_orderno_or_scan, R.string.input_orderno_more_four, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                requestManagerMethod(result);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public StockOutSearchPresenter createPresenter() {
        return new StockOutSearchPresenter(this);
    }

    @Override
    public StockOutSearchView createView() {
        return this;
    }


    @OnClick(R.id.iv_sotckout_scan)
    public void onViewClicked() {
        scan(Constant.REQUEST_SCAN_MODE_ALL_MODE, new ScanQRCodeResultListener() {
            @Override
            public void scanSuccess(int requestCode, String result) {
                etStockoutInput.setText(result);
                requestManagerMethod(result);
            }
        });
    }

    /**
     * 根据 intentcode 发起不同的请求
     *
     * @param orderNum
     */
    public void requestManagerMethod(String orderNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("BillNo", orderNum);
        /**
         * 不同的intentcode  请求不同
         */
        switch (intentCode) {
            case Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料
                getPresenter().queryOutSourceFeedByInput(params);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_AUDIT://委外发料-审核
                params.put("DestBillType", 20);
                getPresenter().queryOutSourcePickByInput(params);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_BILL://委外发料-生单
                params.put("DestBillType", 20);
                getPresenter().queryWWPickDataByOutSource(params);
                break;
            case Constants.STOCK_OUT_OUTSOURCE_ALLOT://委外调拨
                params.put("DestBillType", 50);
                getPresenter().queryWWPickDataByOutSource(params);
                break;
            case Constants.STOCK_OUT_PRODUCTION_FEEDING://生产补料
                getPresenter().queryPrdFeedByInput(params);
                break;
            case Constants.STOCK_OUT_PRODUCTION_AUDIT://生产领料-审核
                getPresenter().queryProductPickByInput(params);
                break;
            case Constants.STOCK_OUT_PRODUCTION_BILL://生产领料-生单
                params.put("DestBillType", 23);
                getPresenter().queryPrdPickDataByMO(params);
                break;
            case Constants.STOCK_OUT_PRODUCTION_ALLOT://生产调拨
                params.put("DestBillType", 50);
                getPresenter().queryPrdPickDataByMO(params);
                break;
            case Constants.STOCK_OUT_SELL_OUT_AUDIT://销售领料-审核
                getPresenter().queryDNByInputForOutStock(params);
                break;
            case Constants.STOCK_OUT_SELL_OUT_BILL://销售领料-生单
                getPresenter().querySalesOutSotckByInputForOutStock(params);
                break;
            case Constants.STOCK_OUT_OTHER_OUT_AUDIT://其他出库-审核
                getPresenter().searchOtherAuditSelectOrderno(params);
                break;
            case Constants.STOCK_OUT_OTHER_OUT_BILL://其他出库-生单
                getPresenter().queryOtherOutStockByInput(params);
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL:// 申请审核
                getPresenter().queryPrdPickApplyByInput(params);
                break;
            case STOCK_OUT_FINISH_GOODS_PICK:// 成品拣货
                getPresenter().queryDNByInputForPick(params);
                break;
            case STOCK_OUT_SEND_OUT_PICK://发货拣货
                getPresenter().queryDNByInputForPick(params);
                break;
            case STOCK_OUT_SALE_OUT_PICK://销售拣货
                getPresenter().querySalesOutSotckByInputForPick(params);
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨拣货
                getPresenter().queryTransferByInputForPick(params);
                break;
            case  Constants.STOCK_OUT_ALLOT_OUT://调拨调出
                getPresenter().queryTransferByInputForOutStock(params);
                break;
        }
    }

    /**
     * 委外补料的请求 回调
     * 跳转到清点的界面
     *
     * @param bean
     */
    @Override
    public void queryOutSourceFeedByInput(QueryOutSourceFeedByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 委外发料的请求 回调
     * 跳转到清点的界面
     *
     * @param bean
     */
    @Override
    public void queryOutSourcePickByInput(QueryOutSourcePickByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        /**
         * 是否是批次拣货 ，如果是则跳转到 批次拣货的清单的界面 否则跳转到普通出库的界面
         */
        if (bean.getSummaryResults().isIsLotPick()) {
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        startActivity(intent);
    }

    @Override
    public void queryWWPickDataByOutSource(QueryWWPickDataByOutSourceResult bean) {
        QueryWWPickDataByOutSourceResult.SummaryResultsBean summaryResults = bean.getSummaryResults();
        /**
         * 一共分4 中情况
         * 1、不合并  非批次  拣货
         * 2、不合并   批次   拣货
         * 3、 合并   非批次  拣货
         * 4   合并    批次   拣货
         */
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (summaryResults.isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 生产生单
     *
     * @param bean
     */
    @Override
    public void queryPrdPickDataByMO(QueryWWPickDataByOutSourceResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 生产审核
     *
     * @param bean
     */
    @Override
    public void queryProductPickByInput(QueryProductPickByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 生产领料单末尾号查询（审核流程）的返回
     *
     * @param bean
     */
    @Override
    public void queryPrdPickApplyByInput(QueryProductPickByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 生产补料
     *
     * @param bean
     */
    @Override
    public void queryPrdFeedByInput(QueryPrdFeedByInputResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }



    /**
     * 其他审核  返回结果
     *
     * @param bean
     */
    @Override
    public void searchOtherAuditSelectOrderno(OtherOutAndInStockBean bean) {
        Intent intent = new Intent();
        if (bean.getSummaryResults().getRob() == 0) {//蓝单　　入库
            intent.setClass(this, OtherAuditActivity.class);
            intent.putExtra(Constants.CODE_STR, intentCode);
            intent.putExtra(IN_STOCK_FINISH_OTHER_BEAN, new Gson().toJson(bean));

        } else {//
            intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
            intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
            if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
                intent.setClass(this, BatchPointListActivity.class);
            } else {
                intent.setClass(this, NormalOutStockActivity.class);
            }
        }
        startActivity(intent);
    }

    @Override
    public void queryDNByInputForOutStock(QueryDNByInputForOutStockResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void querySalesOutSotckByInputForOutStock(QuerySalesOutSotckByInputForOutStockResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 其他出库
     * @param bean
     */
    @Override
    public void queryOtherOutStockByInput(OtherOutAndInStockBean bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 销售拣货
     * @param bean
     */
    @Override
    public void querySalesOutSotckByInputForPick(QuerySalesOutSotckByInputForPickResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 调拨拣货
     * @param bean
     */
    @Override
    public void queryTransferByInputForPick(QueryTransferByInputForPickResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }
    /**
     * 成品拣货 发货拣货
     *
     * @param bean
     */
    @Override
    public void queryDNByInputForPick(QueryDNByInputForPickResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 调拨调出
     * @param bean
     */
    @Override
    public void queryTransferByInputForOutStock(QueryAllotOutResult bean) {
        Intent intent = new Intent();
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        if (bean.getSummaryResults().isIsLotPick()) {//批次拣货
            intent.setClass(this, BatchPointListActivity.class);
        } else {
            intent.setClass(this, NormalOutStockActivity.class);
        }
        startActivity(intent);
    }

}
