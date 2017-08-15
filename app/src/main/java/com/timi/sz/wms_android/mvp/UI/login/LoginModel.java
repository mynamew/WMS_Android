package com.timi.sz.wms_android.mvp.UI.login;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * Created by jzk on 2017/8/14.
 */

public class LoginModel extends MvpBaseModel {
    /**
     * 登录的方法
     * author: timi
     * create at: 2017/8/15 14:26
     */
    public void login(final String username, final String password, Observer<LoginBean> observer) {
        HttpManager.getInstance().HttpManagerRequestByWebservice(observer, LoginBean.class, new ApiServiceMethodCallBack() {
            @Override
            public Observable<ResponseBody> createObservable(ApiService apiService) {
                return apiService.login(username, password);
            }
        });
    }
}
