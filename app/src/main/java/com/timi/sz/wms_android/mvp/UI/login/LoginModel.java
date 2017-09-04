package com.timi.sz.wms_android.mvp.UI.login;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by jzk on 2017/8/14.
 */

public class LoginModel extends MvpBaseModel {
    /**
     * 登录的方法
     * author: timi
     * create at: 2017/8/15 14:26
     */
    public void login(final String tenancyName, final String usernameOrEmailAddress, final String password, final String mac, Observer<LoginBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<LoginBean>() {
            @Override
            public Observable<CommonResult<LoginBean>> createObservable(ApiService apiService) {
                return apiService.login(tenancyName,usernameOrEmailAddress,password, 8, "E8-2A-EA-D0-BB-D2");

            }
        });
    }
}
