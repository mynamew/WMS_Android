package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 08:56
 */

public class StockOutSearchPresenter extends MvpBasePresenter<StockOutSearchView> {
    private StockOutSearchModel model;

    public StockOutSearchPresenter(Context context) {
        super(context);
        model = new StockOutSearchModel();
    }
}
