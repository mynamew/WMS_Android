package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust_detail;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-24 16:28
 */

public class LibAdjustDetailPresenter extends MvpBasePresenter<LibAdjustDetailView> {
    LibAdjustDetailModel model;

    public LibAdjustDetailPresenter(Context context) {
        super(context);
        model = new LibAdjustDetailModel();
    }
}
