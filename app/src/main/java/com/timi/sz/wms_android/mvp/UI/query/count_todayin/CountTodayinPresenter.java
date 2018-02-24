package com.timi.sz.wms_android.mvp.UI.query.count_todayin;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-01 10:02
 */

public class CountTodayinPresenter extends MvpBasePresenter<CountTodayinView> {
    private CountTodayinModel model;

    public CountTodayinPresenter(Context context) {
        super(context);
        model = new CountTodayinModel();
    }
}
