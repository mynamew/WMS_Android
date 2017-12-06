package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.GetPDA_ParameterResult;
import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.io.File;
import java.util.Map;

/**
 * 主页的Presenter
 */

public class MainPresenter extends MvpBasePresenter<MainView> {
    private MainModel mainModel;
    private HttpSubscriber<File> downLoadHttpSubscriber;
    private HttpSubscriber<VersionBean> versionBeanHttpSubscriber;
    private HttpSubscriber<GetPDA_ParameterResult> getPDA_parameterResultHttpSubscriber;

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
     * 获取app 版本
     * @param tenancyName 租户
     * @param usernameOrEmailAddress  用户名
     * @param password 密码
     * @param mac mac地址
     */
    public void getVersion( String tenancyName,  String usernameOrEmailAddress,  String password,String mac){
        if(null==versionBeanHttpSubscriber){
            versionBeanHttpSubscriber=new HttpSubscriber<>(new OnResultCallBack<VersionBean>() {
                @Override
                public void onSuccess(VersionBean versionBean) {
                   getView().getVersion(versionBean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().getVersion(new VersionBean(new VersionBean.ObjectReturnBean("1.0","http://7xk9dj.com1.z0.glb.clouddn.com/BGAUpdateDemo_v1.0.1_debug.apk"),true,""));
                }
            });
        }
        mainModel.getVersion(tenancyName,usernameOrEmailAddress,password,mac,versionBeanHttpSubscriber);
    }

    /**
     *   获取PDA数据
     * @param params
     */
    public void getPDAParams(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getPDA_parameterResultHttpSubscriber) {
            getPDA_parameterResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetPDA_ParameterResult>() {
                @Override
                public void onSuccess(GetPDA_ParameterResult o) {
                    getView().getPDAParams(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        mainModel.getPDAParams(params, getPDA_parameterResultHttpSubscriber);
    }
    /**
     * 解除 观察者
     */
    public void unRegistHttpSubscriber() {
        if (null != downLoadHttpSubscriber) {
            downLoadHttpSubscriber.unSubscribe();
            downLoadHttpSubscriber = null;
        }
        if (null != getPDA_parameterResultHttpSubscriber) {
            getPDA_parameterResultHttpSubscriber.unSubscribe();
            getPDA_parameterResultHttpSubscriber = null;
        }
    }
}
