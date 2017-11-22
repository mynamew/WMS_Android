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
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list.BatchPointListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock.NormalOutStockActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_BEAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_CODE_STR;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;

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
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvQueryTitle.setText(R.string.stock_in_sale_return);
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_SELL_OUT_BILL://销售 生单
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvQueryTitle.setText(R.string.stock_in_sale_return);
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT:// 其他 审核
                setActivityTitle(getString(R.string.stock_out_production_feed_title));
                tvQueryTitle.setText(R.string.stock_out_create_add_materail);
                tvStockoutTip.setText(R.string.stock_out_production_feed_tip);
                break;
        }
        /**
         *  输入框
         */
        etStockoutInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodUtils.hide(StockOutSearchActivity.this);
                    String orderNum = etStockoutInput.getText().toString().trim();
                    if (TextUtils.isEmpty(orderNum)) {
                        ToastUtils.showShort(R.string.query_allot_scan_hint);
                    }
                    if (orderNum.length() < 4) {
                        ToastUtils.showShort(R.string.input_orderno_more_four);
                    } else {
                        /**
                         * 发起请求
                         */
                        requestManagerMethod(orderNum);
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
                break;
            case Constants.STOCK_OUT_SELL_OUT_BILL://销售领料-生单
                break;
            case Constants.STOCK_OUT_OTHER_OUT_AUDIT://其他出库-审核
                break;
            case Constants.STOCK_OUT_OTHER_OUT_BILL://其他出库-生单
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
        Intent intent = new Intent(this, BatchPointListActivity.class);
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
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
        Intent intent = new Intent(this, BatchPointListActivity.class);
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        startActivity(intent);
    }

    /**
     * 生产补料
     *
     * @param bean
     */
    @Override
    public void queryPrdFeedByInput(QueryPrdFeedByInputResult bean) {
        Intent intent = new Intent(this, BatchPointListActivity.class);
        intent.putExtra(STOCK_OUT_CODE_STR, intentCode);
        intent.putExtra(STOCK_OUT_BEAN, new Gson().toJson(bean));
        startActivity(intent);
    }

}
