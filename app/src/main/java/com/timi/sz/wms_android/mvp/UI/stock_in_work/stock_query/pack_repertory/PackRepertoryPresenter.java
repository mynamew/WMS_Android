package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-30 16:30
 */

public class PackRepertoryPresenter extends MvpBasePresenter<PackRepertoryView> {
    private PackRepertoryModel model;
    public PackRepertoryPresenter(Context context) {
        super(context);
        model=new PackRepertoryModel();
    }
}
