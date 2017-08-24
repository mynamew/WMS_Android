package com.timi.sz.wms_android.mvp.UI.deviceinfo;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * author: timi
 * create at: 2017-08-24 16:35
 */
public class DeviceInfoPresenter extends MvpBasePresenter<DeviceInfoView> {
    DeviceInfoModel model=null;
    public DeviceInfoPresenter(Context context) {
        super(context);
        model=new DeviceInfoModel();
    }
}
