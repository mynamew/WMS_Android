package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-09 09:15
 */

public class OursourcingAuditGoodsListPresenter extends MvpBasePresenter<OursourcingAuditGoodsListView> {
    private OursourcingAuditGoodsListModel model;

    public OursourcingAuditGoodsListPresenter(Context context) {
        super(context);
        model = new OursourcingAuditGoodsListModel();
    }
}
