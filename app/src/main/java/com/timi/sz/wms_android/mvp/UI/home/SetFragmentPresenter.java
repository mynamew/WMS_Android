package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Context;

import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.io.File;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 15:57
 */

public class SetFragmentPresenter extends MvpBasePresenter<SetFragmentView> {
    private SetFragmentMode setFragmentMode;
    private HttpSubscriber<File> downLoadHttpSubscriber;
    private HttpSubscriber<VersionBean> versionBeanHttpSubscriber;

    public SetFragmentPresenter(Context context) {
        super(context);
        setFragmentMode=new SetFragmentMode();
    }

    /**
     * 下载 apk文件
     */
    public void downLoadApk(String url) {
        if (null == downLoadHttpSubscriber) {
            //下载
            downLoadHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack() {
                @Override
                public void onSuccess(Object o) {
                    getView().installApk();
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        setFragmentMode.downLoadApk(url, downLoadHttpSubscriber);
    }

    /**
     * 获取app 版本
     *
     * @param tenancyName            租户
     * @param usernameOrEmailAddress 用户名
     * @param password               密码
     * @param mac                    mac地址
     */
    public void getVersion(String tenancyName, String usernameOrEmailAddress, String password, String mac) {
        if (null == versionBeanHttpSubscriber) {
            versionBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<VersionBean>() {
                @Override
                public void onSuccess(VersionBean versionBean) {
                    getView().getVersion(versionBean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().getVersion(new VersionBean(new VersionBean.ObjectReturnBean("1.0", "http://7xk9dj.com1.z0.glb.clouddn.com/BGAUpdateDemo_v1.0.1_debug.apk"), true, ""));
                }
            });
        }
        setFragmentMode.getVersion(tenancyName, usernameOrEmailAddress, password, mac, versionBeanHttpSubscriber);
    }

    /**
     * 解除 观察者
     */
    public void unRegistHttpSubscriber() {
        if (null != downLoadHttpSubscriber) {
            downLoadHttpSubscriber.unSubscribe();
            downLoadHttpSubscriber = null;
        }
    }
}
