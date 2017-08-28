package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 16:03
 */

public class StockInPresenter extends MvpBasePresenter<StockInDetailView> {
    StockInDetailModel model=null;
    public StockInPresenter(Context context) {
        super(context);
        model=new StockInDetailModel();
    }
}
