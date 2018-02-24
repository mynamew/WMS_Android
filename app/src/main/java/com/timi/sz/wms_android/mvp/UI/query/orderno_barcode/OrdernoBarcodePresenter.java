package com.timi.sz.wms_android.mvp.UI.query.orderno_barcode;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 09:23
 */

public class OrdernoBarcodePresenter extends MvpBasePresenter<OrdernoBarcodeView> {
    private OrdernoBarcodeModel model;

    public OrdernoBarcodePresenter(Context context) {
        super(context);
        model = new OrdernoBarcodeModel();
    }
}
