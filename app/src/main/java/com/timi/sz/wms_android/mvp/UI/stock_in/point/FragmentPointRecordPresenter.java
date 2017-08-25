package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:19
 */

public class FragmentPointRecordPresenter extends MvpBasePresenter<FragmentPointRecordView> {
    FragmentPointRecordModel model = null;

    public FragmentPointRecordPresenter(Context context) {
        super(context);
        model = new FragmentPointRecordModel();
    }
}
