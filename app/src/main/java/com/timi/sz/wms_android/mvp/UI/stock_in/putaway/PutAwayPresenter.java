package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 10:57
 */

public class PutAwayPresenter extends MvpBasePresenter<PutAwayView> {
    PutAwayModel model=null;
    public PutAwayPresenter(Context context) {
        super(context);
        model=new PutAwayModel();
    }
}
