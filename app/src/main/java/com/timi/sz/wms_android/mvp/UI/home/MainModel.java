package com.timi.sz.wms_android.mvp.UI.home;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.TestBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * main  model
 */

public class MainModel extends MvpBaseModel {

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
        bean.userDepart = SpUtils.getInstance().getString(Constants.USER_DEPART);
        bean.userFrom = SpUtils.getInstance().getString(Constants.USER_FROM);
        bean.userRoot = SpUtils.getInstance().getString(Constants.USER_ROOT);
        bean.userNum = SpUtils.getInstance().getString(Constants.USER_NUM);
        return bean;
    }

    /**
     * 下载APK
     * @param url
     * @param observer
     */
    public void downLoadApk(final String url, Observer<File> observer){
        HttpManager.getInstance().downLoadAPkRequest(observer,url);
    }
}
