package com.timi.sz.wms_android.mvp.UI.query.recode_out;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-26 14:20
 */

public class RecordOutPresenter extends MvpBasePresenter<RecordOutView> {
    RecordOutModel model;
    public RecordOutPresenter(Context context) {
        super(context);
        model=new RecordOutModel();
    }
}
