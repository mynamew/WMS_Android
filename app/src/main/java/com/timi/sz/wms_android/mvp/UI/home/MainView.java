package com.timi.sz.wms_android.mvp.UI.home;


import com.timi.sz.wms_android.bean.GetPDA_ParameterResult;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * View接口
 * @author Dream
 * 
 */
public interface MainView extends MvpBaseView{
    /**
     * 从sp 获取用户信息
     *
     * @param bean
     */
     void setSpUserInfo(UserInfoBean bean);
    /**
     * apk 下载完成 提示安装
     */
     void installApk();
    /**
     * 获取app 版本
     */
    void getVersion(VersionBean bean);
    /**
     * 获取PDA参数
     * @param o
     */
    void getPDAParams(GetPDA_ParameterResult o);
}
