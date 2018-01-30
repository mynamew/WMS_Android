package com.timi.sz.wms_android.mvp.UI.query.todayout;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-25 14:07
 */

public class TodayOutPresenter extends MvpBasePresenter<TodayOutView> {
    private TodayOutModel model;
    public TodayOutPresenter(Context context) {
        super(context);
        model=new TodayOutModel();
    }
}
