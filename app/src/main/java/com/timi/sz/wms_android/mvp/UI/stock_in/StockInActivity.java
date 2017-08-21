package com.timi.sz.wms_android.mvp.UI.stock_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.UI.stock_in.query.SearchBuyOrderActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 入库作业的界面
 * author: timi
 * create at: 2017/8/17 10:11
 */
public class StockInActivity extends BaseNoMvpActivity {


    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.home_in_lib));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_stockin_buy_order, R.id.tv_stock_in_send_order, R.id.tv_stockin_inlib, R.id.tv_stock_in_check, R.id.tv_stock_in_create_order, R.id.tv_stockin_other_inlib_check, R.id.tv_stockin_other_inlib_create_order, R.id.tv_stockin_out_return, R.id.tv_stock_in_produce_return, R.id.tv_stock_in_sale_return})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_stockin_buy_order:
                startActivity(new Intent(StockInActivity.this, SearchBuyOrderActivity.class));
                break;
            case R.id.tv_stock_in_send_order:
                break;
            case R.id.tv_stockin_inlib:
                break;
            case R.id.tv_stock_in_check:
                break;
            case R.id.tv_stock_in_create_order:
                break;
            case R.id.tv_stockin_other_inlib_check:
                break;
            case R.id.tv_stockin_other_inlib_create_order:
                break;
            case R.id.tv_stockin_out_return:
                break;
            case R.id.tv_stock_in_produce_return:
                break;
            case R.id.tv_stock_in_sale_return:
                break;
        }
    }
}
