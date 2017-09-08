package com.timi.sz.wms_android.mvp.UI.stock_out.point_detail;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-06 13:43
 */

public class PointDetailPresenter extends MvpBasePresenter<PointDetailView> {
    private PointDetailModel model;

    public PointDetailPresenter(Context context) {
        super(context);
        model = new PointDetailModel();
    }
}
