package com.timi.sz.wms_android.mvp.UI.login_success;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * 登录
 */

public interface LoginSuccessView extends MvpBaseView {
    void getUserinfo(UserInfoBean bean);
}
