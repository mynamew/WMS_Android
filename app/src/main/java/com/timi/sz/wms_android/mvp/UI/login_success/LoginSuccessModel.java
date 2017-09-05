package com.timi.sz.wms_android.mvp.UI.login_success;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
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

public class LoginSuccessModel extends MvpBaseModel {
    /**
     * 获取用户信息
     *
     * @param userId
     * @param mac
     * @param observer
     */
    public void getUserInfo(final int userId, final String mac, final Observer<UserInfoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<UserInfoBean>() {
            @Override
            public Observable<CommonResult<UserInfoBean>> createObservable(ApiService apiService) {
                return apiService.getUserInfo(userId, 8, mac);
            }
        });
    }
}
