package com.timi.sz.wms_android.mvp.UI.login;

import android.content.Context;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;

/**
 * Created by jzk on 2017/8/14.
 */

public class LoginPresenter extends MvpBasePresenter<LoginView> {
    private LoginModel mLoginModel;
    private HttpSubscriber<LoginBean> mLoginBeanHttpSubscriber;

    public LoginPresenter(Context context) {
        super(context);
        this.mLoginModel = new LoginModel();
    }

    /**
     * 网络请求 获取登录结果
     *
     * @param username
     * @param password
     */
    public void getLoginResult(String username, String password) {
        if (null == mLoginBeanHttpSubscriber) {
            mLoginBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<LoginBean>() {
                @Override
                public void onSuccess(LoginBean loginBean) {
                    getView().getLoginResult(loginBean);
                }

                @Override
                public void onError(int code, String errorMsg) {

                }
            });
        }
        mLoginModel.getLoginResult(mLoginBeanHttpSubscriber, username, password);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        //反注册
        if (null != mLoginBeanHttpSubscriber) {
            mLoginBeanHttpSubscriber.unSubscribe();
            mLoginBeanHttpSubscriber = null;
        }
    }
}
