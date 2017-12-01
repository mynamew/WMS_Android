package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 08:31
 */

public class FormChangeOutstockPresenter extends MvpBasePresenter<FormChangeOutstockView> {
    private FormChangeOutstockModel model;

    public FormChangeOutstockPresenter(Context context) {
        super(context);
        model = new FormChangeOutstockModel();
    }
}
