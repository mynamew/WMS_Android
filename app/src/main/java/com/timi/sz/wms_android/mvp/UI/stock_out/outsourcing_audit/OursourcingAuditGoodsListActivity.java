package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

/**
 * 委外审核 的 物品清单界面
 */
public class OursourcingAuditGoodsListActivity extends BaseActivity<OursourcingAuditGoodsListView, OursourcingAuditGoodsListPresenter> implements OursourcingAuditGoodsListView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_oursourcing_audit_goods_list;
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
    public OursourcingAuditGoodsListPresenter createPresenter() {
        return new OursourcingAuditGoodsListPresenter(this);
    }

    @Override
    public OursourcingAuditGoodsListView createView() {
        return this;
    }
}
