package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Context;

import com.timi.sz.wms_android.bean.TestBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * 主页的Presenter
 */

public class MainPresenter extends MvpBasePresenter<MainView> {
    MainModel mainModel;
    HttpSubscriber<TestBean> testBeanHttpSubscriber;

    /**
     * 初始化 观察者和mainmode
     * @param context
     */
    public MainPresenter(Context context) {
        super(context);
        this.mainModel = new MainModel();
    }

    /**
     * 获取测试信息
     * @param pno
     * @param ps
     * @param dtype
     */
    public void getMainMsg(int pno, int ps, String dtype) {
        if(null==testBeanHttpSubscriber){
            testBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<TestBean>() {
                @Override
                public void onSuccess(TestBean bean) {
                    getView().onGetMainMsg(bean);
                }

                @Override
                public void onError(String errorMsg) {

                }

            });
        }
        mainModel.getMainMsg(pno, ps, dtype, testBeanHttpSubscriber);
    }

    /**
     * 解除 观察者
     */
    public void unRegistHttpSubscriber() {
        if(null!=testBeanHttpSubscriber){
            testBeanHttpSubscriber.unSubscribe();
            testBeanHttpSubscriber=null;
        }
    }
}
