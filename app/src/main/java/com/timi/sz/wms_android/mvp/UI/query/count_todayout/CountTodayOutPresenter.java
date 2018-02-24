package com.timi.sz.wms_android.mvp.UI.query.count_todayout;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-01 16:04
 */

public class CountTodayOutPresenter extends MvpBasePresenter<CountTodayOutView> {
    private CountTodayOutModel model;
    public CountTodayOutPresenter(Context context) {
        super(context);
        model=new CountTodayOutModel();
    }
}
