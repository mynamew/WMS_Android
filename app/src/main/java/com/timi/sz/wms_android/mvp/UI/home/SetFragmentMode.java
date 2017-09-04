package com.timi.sz.wms_android.mvp.UI.home;

import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 15:56
 */

public class SetFragmentMode extends MvpBaseModel {
    /**
     * 下载APK
     *
     * @param url
     * @param observer
     */
    public void downLoadApk(final String url, Observer<File> observer) {
        HttpManager.getInstance().downLoadAPkRequest(observer, url);
    }

    /**
     * 获取app 版本
     *
     * @param tenancyName
     * @param usernameOrEmailAddress
     * @param password
     * @param mac
     * @param observer
     */
    public void getVersion(final String tenancyName, final String usernameOrEmailAddress, final String password, final String mac, Observer<VersionBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<VersionBean>() {
            @Override
            public Observable<CommonResult<VersionBean>> createObservable(ApiService apiService) {
                return apiService.getAppVersion(tenancyName, usernameOrEmailAddress, password, 8, mac);
            }
        });
    }
}
