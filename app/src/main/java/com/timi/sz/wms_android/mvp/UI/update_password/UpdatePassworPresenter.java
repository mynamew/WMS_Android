package com.timi.sz.wms_android.mvp.UI.update_password;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-24 17:17
 */

public class UpdatePassworPresenter extends MvpBasePresenter<UpdatePassworView> {
    UpdatePassworModel model=null;
    public UpdatePassworPresenter(Context context) {
        super(context);
        model=new UpdatePassworModel();
    }
}
