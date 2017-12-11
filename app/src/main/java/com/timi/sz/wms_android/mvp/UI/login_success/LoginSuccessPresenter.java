package com.timi.sz.wms_android.mvp.UI.login_success;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;
import com.timi.sz.wms_android.view.MyProgressDialog;

/**
 * s
 * Created by jzk on 2017/8/14.
 */

public class LoginSuccessPresenter extends MvpBasePresenter<LoginSuccessView> {
    private LoginSuccessModel mLoginModel;
    private HttpSubscriber<UserInfoBean> mLoginBeanHttpSubscriber;

    public LoginSuccessPresenter(Context context) {
        super(context);
        this.mLoginModel = new LoginSuccessModel();
    }

    /**
     * 网络请求 获取用户信息
     *
     * @param userId
     * @param mac
     */
    public void getUserInfo(int userId, String mac) {
        /**
         * 显示进度条
         */
        getView().showProgressDialog();
        /**
         * 初始化观察者
         */
        if (null == mLoginBeanHttpSubscriber) {
            mLoginBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<UserInfoBean>() {
                @Override
                public void onSuccess(UserInfoBean loginBean) {
                    getView().getUserinfo(loginBean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
//                    getView().getUserinfo(new UserInfoBean("研发部", "啊实打实的", "邢力丰", "234321", "啊飞洒发生的", "男", "15995844889"));
                }
            });
        }
        /**
         * 调用请求的方法
         */
        mLoginModel.getUserInfo(userId, mac, mLoginBeanHttpSubscriber);
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
