package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feeed;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-09 10:29
 */

public class OutSourceFeedPresenter extends MvpBasePresenter<OutSourceFeedView> {
    private OutSourceFeedModel model;

    public OutSourceFeedPresenter(Context context) {
        super(context);
        model = new OutSourceFeedModel();
    }
}
