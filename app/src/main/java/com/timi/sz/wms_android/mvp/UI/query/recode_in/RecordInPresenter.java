package com.timi.sz.wms_android.mvp.UI.query.recode_in;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 10:32
 */

public class RecordInPresenter extends MvpBasePresenter<RecordInView> {
    private RecordInModel model;
    public RecordInPresenter(Context context) {
        super(context);
        model=new RecordInModel();
    }
}
