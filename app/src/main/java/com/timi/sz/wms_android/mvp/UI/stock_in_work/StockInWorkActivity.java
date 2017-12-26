package com.timi.sz.wms_android.mvp.UI.stock_in_work;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.mvp.UI.list.BuyInStockListActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_one_step.OneStepAllotActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_scan.AllotScanActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.barcode_exchange.BarcodeExchangeActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_instock.FormChangeInstockActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock.FormChangeOutstockActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust.LibraryAdjustActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust.PackAdjustActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.query.StockInWorkQueryActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.query.StockInWorkQueryActivity_ViewBinding;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.StockQueryActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.StockOutActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.query.StockOutSearchActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 库内作业
 * author: timi
 * create at: 2017/8/17 14:48
 */
public class StockInWorkActivity extends BaseNoMvpActivity {


    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_work;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.home_lib_in_deal));

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_stockin_work_barcode_change,R.id.tv_allot_outstock,R.id.tv_stockin_work_storage_location_change, R.id.tv_stockin_work_scan_in, R.id.tv_stockin_work_one_step_in, R.id.tv_stockin_work_group_change, R.id.tv_stockin_work_out_stock, R.id.tv_stockin_work_in_stock, R.id.tv_stockin_work_query_repertory, R.id.tv_stockin_work_count, R.id.tv_stock_in_sale_return})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_stockin_work_storage_location_change://库位调整
                intent.setClass(StockInWorkActivity.this, LibraryAdjustActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_LIBRARY_ADJUST);
                break;
            case R.id.tv_stockin_work_scan_in://扫描调入
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                }
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR, Constants.STOCK_IN_WORK_ALLOT_SCAN);
                break;
            case R.id.tv_stockin_work_one_step_in://一步调入
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                }
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR, Constants.STOCK_IN_WORK_ALLOT_ONE_STEP);
                break;
            case R.id.tv_stockin_work_group_change://容器调整
                intent.setClass(StockInWorkActivity.this, PackAdjustActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_PACK_ADJUST);
                break;
            case R.id.tv_stockin_work_out_stock://形态转换 出库
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                }
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_FORM_CHANGE_OUT);
                break;
            case R.id.tv_stockin_work_in_stock://形态转换入库
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                }
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_FORM_CHANGE_IN);
                break;
            case R.id.tv_stockin_work_query_repertory://库存查询
                intent.setClass(StockInWorkActivity.this, StockQueryActivity.class);
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_STOCK_QUERY);
                break;
            case R.id.tv_stockin_work_count://盘点
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(StockInWorkActivity.this, StockInWorkQueryActivity.class);
                }
                intent.putExtra(Constants.STOCK_IN_WORK_CODE_STR,Constants.STOCK_IN_WORK_POINT);//盘点
                break;
            case R.id.tv_stock_in_sale_return://
            case R.id.tv_allot_outstock://调拨调出
                /**
                 * 如果是无纸化作业
                 */
                if (SpUtils.getInstance().getIsBillList()) {
                    intent.setClass(this, BuyInStockListActivity.class);
                } else {
                    intent.setClass(this, StockOutSearchActivity.class);
                }
                intent.putExtra(Constants.STOCK_OUT_CODE_STR, Constants.STOCK_OUT_ALLOT_OUT);
                break;
            case R.id.tv_stockin_work_barcode_change://条码转移
                intent.setClass(this, BarcodeExchangeActivity.class);
                break;
        }
        startActivity(intent);
    }
}
