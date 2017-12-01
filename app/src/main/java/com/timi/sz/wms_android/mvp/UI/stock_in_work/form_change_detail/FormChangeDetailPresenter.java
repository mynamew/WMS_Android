package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 09:48
 */

public class FormChangeDetailPresenter extends MvpBasePresenter<FormChangeDetailView> {
    private FormChangeDetailModel model;

    public FormChangeDetailPresenter(Context context) {
        super(context);
        model = new FormChangeDetailModel();
    }
}
