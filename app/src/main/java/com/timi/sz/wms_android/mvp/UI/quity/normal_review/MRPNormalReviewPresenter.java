package com.timi.sz.wms_android.mvp.UI.quity.normal_review;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-17 16:55
 */

public class MRPNormalReviewPresenter extends MvpBasePresenter<MRPNormalReviewView> {
    private MRPNormalReviewModel model;

    public MRPNormalReviewPresenter(Context context) {
        super(context);
        model = new MRPNormalReviewModel();
    }
}
