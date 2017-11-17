package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feed;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-10 09:12
 */

public class OutSourceFeedMaterialPointPresenter extends MvpBasePresenter<OutSourceFeedMaterialPointView> {
    private OutSourceFeedMaterialPointModel model;

    public OutSourceFeedMaterialPointPresenter(Context context) {
        super(context);
        model = new OutSourceFeedMaterialPointModel();
    }
}
