package com.timi.sz.wms_android.mvp.UI.userinfo;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

/**
 * author: timi
 * create at: 2017-08-21 09:20
 */
public class UserInfoModel extends MvpBaseModel {
    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoBean getSpUserInfo() {
        UserInfoBean bean = new UserInfoBean();
        //获取各个存储过的信息
        bean.userName = SpUtils.getInstance().getString(Constants.USER_NAME);
        bean.userSex = SpUtils.getInstance().getString(Constants.USER_SEX);
        bean.userTel= SpUtils.getInstance().getString(Constants.USER_TEL);
        bean.userDepart = SpUtils.getInstance().getString(Constants.USER_DEPART);
        bean.userFrom = SpUtils.getInstance().getString(Constants.USER_FROM);
        bean.userRoot = SpUtils.getInstance().getString(Constants.USER_ROOT);
        bean.userNum = SpUtils.getInstance().getString(Constants.USER_NUM);
        return bean;
    }
}
