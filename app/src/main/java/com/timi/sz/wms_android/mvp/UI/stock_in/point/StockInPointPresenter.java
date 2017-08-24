package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * author: timi
 * create at: 2017-08-24 14:41
 */
public class StockInPointPresenter extends MvpBasePresenter<StockInPointView> {
    StockInPointMode mode;
    public StockInPointPresenter(Context context) {
        super(context);
        mode=new StockInPointMode();
    }
}
