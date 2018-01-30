package com.timi.sz.wms_android.mvp.UI.query.todayin;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-25 11:01
 */

public class TodayInPresenter extends MvpBasePresenter<TodayInView> {
    private TodayInModel model;
    public TodayInPresenter(Context context) {
        super(context);
        model=new TodayInModel();
    }
}
