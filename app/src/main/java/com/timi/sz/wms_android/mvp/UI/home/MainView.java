package com.timi.sz.wms_android.mvp.UI.home;


import com.timi.sz.wms_android.bean.TestBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
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
    public void setSpUserInfo(UserInfoBean bean);
    /**
     * apk 下载完成 提示安装
     */
    public void installApk();
}
