package com.timi.sz.wms_android.mvp.UI.login;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;
import com.timi.sz.wms_android.view.MyProgressDialog;

/**
 * s
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
     * @param tenancyName
     * @param password
     */
    public void getLoginResult(String tenancyName,String usernameOrEmailAddress, String password,String mac) {
        /**
         * 显示进度条
         */
          getView().showProgressDialog();
            /**
         * 初始化观察者
         */
        if (null == mLoginBeanHttpSubscriber) {
            mLoginBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<LoginBean>() {
                @Override
                public void onSuccess(LoginBean loginBean) {
                    getView().getLoginResult(loginBean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        /**
         * 调用请求的方法
         */
        mLoginModel.login(tenancyName,usernameOrEmailAddress, password,mac,mLoginBeanHttpSubscriber);
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
