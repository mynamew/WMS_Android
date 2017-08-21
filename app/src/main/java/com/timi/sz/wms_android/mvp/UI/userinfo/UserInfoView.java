package com.timi.sz.wms_android.mvp.UI.userinfo;

import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * author: timi
 * create at: 2017-08-21 09:18
 */
public interface UserInfoView extends MvpBaseView {

    /**
     * 从sp 获取用户信息
     *
     * @param bean
     */
    public void setSpUserInfo(UserInfoBean bean);
}
