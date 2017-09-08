package com.timi.sz.wms_android.mvp.UI.quity.nomal_quality;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-06 17:23
 */

public class NormalQualityPresenter extends MvpBasePresenter<NormalQualityView> {
    private NormalQualityModel model;

    public NormalQualityPresenter(Context context) {
        super(context);
        model = new NormalQualityModel();
    }
}
