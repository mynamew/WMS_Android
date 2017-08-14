package com.timi.sz.wms_android.mvp.UI.login;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by jzk on 2017/8/14.
 */

public class LoginModel extends MvpBaseModel {
    /**
     * 登录  获取登录的返回结果
     * @param observer
     * @param username
     * @param password
     */
    public void getLoginResult(Observer<LoginBean> observer, String username, String password){
        HttpManager.getInstance().postLoginResult(username,password,observer);
    }
}
