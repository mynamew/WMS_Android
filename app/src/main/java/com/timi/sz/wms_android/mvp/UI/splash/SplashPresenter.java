package com.timi.sz.wms_android.mvp.UI.splash;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.GetPDA_ParameterResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 10:54
 */

public class SplashPresenter extends MvpBasePresenter<SplashView> {
    private SplashModel model;
    private HttpSubscriber<GetPDA_ParameterResult> getPDA_parameterResultHttpSubscriber;

    public SplashPresenter(Context context) {
        super(context);
        model = new SplashModel();
    }

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
        model.getPDAParams(params, getPDA_parameterResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != getPDA_parameterResultHttpSubscriber) {
            getPDA_parameterResultHttpSubscriber.unSubscribe();
            getPDA_parameterResultHttpSubscriber = null;
        }
    }
}
