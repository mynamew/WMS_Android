package com.timi.sz.wms_android.mvp.UI.stock_in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.mvp.UI.stock_in.query.SearchBuyOrderActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.CODE_STR;

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
        Intent it=new Intent(StockInActivity.this, SearchBuyOrderActivity.class);
        switch (view.getId()) {
            case R.id.tv_stockin_buy_order://采购单
                it.putExtra(CODE_STR,Constants.BUY_ORDE_NUM);
                break;
            case R.id.tv_stock_in_send_order://送货单
                it.putExtra(CODE_STR,Constants.BUY_SEND_NUM);
                break;
            case R.id.tv_stockin_inlib://来料入库
                it.putExtra(CODE_STR,Constants.COME_MATERAIL_NUM);
                break;
            case R.id.tv_stock_in_check://产成品入库审核
                it.putExtra(CODE_STR,Constants.CREATE_PRO_CHECK_NUM);
                break;
            case R.id.tv_stock_in_create_order://产成品入库生单
                it.putExtra(CODE_STR,Constants.CREATE_PRO_CREATE_ORDER_NUM);
                break;
            case R.id.tv_stockin_other_inlib_check://其他入入库选单
                it.putExtra(CODE_STR,Constants.OTHER_IN_STOCK_SELECT_ORDERNO);
                break;
            case R.id.tv_stockin_other_inlib_create_order://其他入库扫描
                it.putExtra(CODE_STR,Constants.OTHER_IN_STOCK_SCAN);
                break;
            case R.id.tv_stockin_out_return://委外退料
                it.putExtra(CODE_STR,Constants.OUT_RETURN_MATERAIL);
                break;
            case R.id.tv_stock_in_produce_return://生产退料
                it.putExtra(CODE_STR,Constants.CREATE_RETURN_MATERAIL);
                break;
            case R.id.tv_stock_in_sale_return://销售退料
                it.putExtra(CODE_STR,Constants.SALE_RETURN_MATERAIL);
                break;
        }
        startActivity(it);
    }
}
