package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

public class StockInDetailActivity extends BaseActivity<StockInDetailView, StockInPresenter> {
    private int intentCode= Constants.COME_MATERAIL_NUM;//来料单
    @Override
    public int setLayoutId() {
        return R.layout.activity_stock_in_detail;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("详情");
        intentCode=getIntent().getIntExtra(Constants.CODE_STR,Constants.COME_MATERAIL_NUM);
    }

    @Override
    public void initView() {
        switch (intentCode) {
            case Constants.BUY_ORDE_NUM://采购单
                break;
            case Constants.BUY_SEND_NUM://送货单
                break;
            case Constants.COME_MATERAIL_NUM://来料单
                break;
            case Constants.CREATE_PRO_CHECK_NUM://产成品 审核
                break;
            case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品 生单
                break;
            case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单
                break;
            case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                break;
            case Constants.OUT_RETURN_MATERAIL://委外退料
                break;
            case Constants.CREATE_RETURN_MATERAIL://生产退料
                break;
            case Constants.SALE_RETURN_MATERAIL://销售 退料
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public StockInPresenter createPresenter() {
        return null;
    }

    @Override
    public StockInDetailView createView() {
        return null;
    }
}
