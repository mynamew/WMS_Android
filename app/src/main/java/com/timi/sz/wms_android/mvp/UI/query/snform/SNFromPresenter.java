package com.timi.sz.wms_android.mvp.UI.query.snform;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 10:15
 */

public class SNFromPresenter extends MvpBasePresenter<SNFromView> {
    private SNFromModel model;
    public SNFromPresenter(Context context) {
        super(context);
        model=new SNFromModel();
    }
}
