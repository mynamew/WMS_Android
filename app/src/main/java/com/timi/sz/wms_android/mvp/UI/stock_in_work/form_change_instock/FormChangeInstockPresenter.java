package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_instock;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 08:59
 */

public class FormChangeInstockPresenter extends MvpBasePresenter<FormChangeInstockView> {
    private FormChangeInstockModel model;

    public FormChangeInstockPresenter(Context context) {
        super(context);
        model = new FormChangeInstockModel();
    }
}
