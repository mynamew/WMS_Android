package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_bill;

import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
/**
  *  委外发料-制单   物料清点
  * author: timi
  * create at: 2017/11/14 9:12
  */
public class OutsourcingBillPointActivity extends BaseActivity<OutsourcingBillPointView,OutsourcingBillPointPresenter> implements OutsourcingBillPointView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_outsourcing_bill_point;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public OutsourcingBillPointPresenter createPresenter() {
        return new OutsourcingBillPointPresenter(this);
    }

    @Override
    public OutsourcingBillPointView createView() {
        return this;
    }
}
