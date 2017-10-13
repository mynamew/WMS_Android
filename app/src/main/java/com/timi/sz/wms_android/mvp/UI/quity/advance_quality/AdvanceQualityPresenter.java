package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-12 14:54
 */

public class AdvanceQualityPresenter extends MvpBasePresenter<AdvanceQualityView> {
    private AdvanceQualityModel model;

    public AdvanceQualityPresenter(Context context) {
        super(context);
        model = new AdvanceQualityModel();
    }
}
