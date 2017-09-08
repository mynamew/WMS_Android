package com.timi.sz.wms_android.mvp.UI.stock_out.point_list;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-06 10:45
 */

public class PointListPresenter extends MvpBasePresenter<PointListView> {
    private PointListModel model;

    public PointListPresenter(Context context) {
        super(context);
        model = new PointListModel();
    }
}
