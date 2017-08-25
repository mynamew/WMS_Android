package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Context;

import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.io.File;

/**
 * 主页的Presenter
 */

public class MainPresenter extends MvpBasePresenter<MainView> {
    MainModel mainModel;
    HttpSubscriber<File> downLoadHttpSubscriber;

    /**
     * 初始化 观察者和mainmode
     *
     * @param context
     */
    public MainPresenter(Context context) {
        super(context);
        this.mainModel = new MainModel();
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

    /***
     * 获取用户信息
     *
     * @return
     */
    public void getUserInfoFromSp() {
        getView().setSpUserInfo(mainModel.getSpUserInfo());
    }

    /**
     * 下载 apk文件
     */
    public void downLoadApk(String url) {
        mainModel.downLoadApk(url, downLoadHttpSubscriber);
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
