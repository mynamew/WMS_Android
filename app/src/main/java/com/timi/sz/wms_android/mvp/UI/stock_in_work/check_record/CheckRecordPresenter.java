package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_record;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-11 16:11
 */

public class CheckRecordPresenter extends MvpBasePresenter<CheckRecordView> {
    private CheckRecordModel model;

    public CheckRecordPresenter(Context context) {
        super(context);
        model = new CheckRecordModel();
    }
}
