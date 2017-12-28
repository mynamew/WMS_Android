package com.timi.sz.wms_android.mvp.UI.deviceinfo;

import android.content.Context;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * author: timi
 * create at: 2017-08-24 16:35
 */
public class DeviceInfoPresenter extends MvpBasePresenter<DeviceInfoView> {
    DeviceInfoModel model = null;
    private HttpSubscriber<Object> subscriber;

    public DeviceInfoPresenter(Context context) {
        super(context);
        model = new DeviceInfoModel();
    }

    /**
     * 设置PDA参数
     *
     * @param params
     */
    public void setPDACode(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == subscriber) {
            subscriber = new HttpSubscriber<Object>(new OnResultCallBack() {
                @Override
                public void onSuccess(Object o) {
                    getView().setPDACode();
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.setPDACode(params, subscriber);
    }
}
