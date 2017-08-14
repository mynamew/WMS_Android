package com.timi.sz.wms_android.mvp.UI.login;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * 登录
 */

public interface LoginView extends MvpBaseView {
    public void getLoginResult(LoginBean bean);
}
