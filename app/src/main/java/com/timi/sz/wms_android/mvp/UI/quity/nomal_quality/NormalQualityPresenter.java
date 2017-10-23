package com.timi.sz.wms_android.mvp.UI.quity.nomal_quality;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-06 17:23
 */

public class NormalQualityPresenter extends MvpBasePresenter<NormalQualityView> {
    private NormalQualityModel model;
    private HttpSubscriber<NormalQualityData> normalQualityDataHttpSubscriber;
    private HttpSubscriber<Object> setNormalQualityDataHttpSubscriber;
    private HttpSubscriber<Object> submitFinishHttpSubscriber;

    public NormalQualityPresenter(Context context) {
        super(context);
        model = new NormalQualityModel();
    }

    /**
     * 获取普通质检的数据
     *
     * @param params
     */
    public void getNormalQualityData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == normalQualityDataHttpSubscriber) {
            normalQualityDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<NormalQualityData>() {
                @Override
                public void onSuccess(NormalQualityData o) {
                    getView().getNormalQualityData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getNormalQualityData(params, normalQualityDataHttpSubscriber);
    }

    /**
     * 设置普通质检的数据
     *
     * @param params
     * @param isQualified
     */
    public void setNormalQualityData(Map<String, Object> params, final boolean isQualified,final int rejectNum) {
        getView().showProgressDialog();
        if (null == setNormalQualityDataHttpSubscriber) {
            setNormalQualityDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().setNormalQualityData(isQualified,rejectNum);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.setNormalQualityData(params, setNormalQualityDataHttpSubscriber);
    }
    /**
     * 质检确认
     *
     * @param params
     */
    public void submitFinish(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitFinishHttpSubscriber) {
            submitFinishHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().submitFinish();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitFinish(params, submitFinishHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != normalQualityDataHttpSubscriber) {
            normalQualityDataHttpSubscriber.unSubscribe();
            normalQualityDataHttpSubscriber = null;
        }
        if (null != setNormalQualityDataHttpSubscriber) {
            setNormalQualityDataHttpSubscriber.unSubscribe();
            setNormalQualityDataHttpSubscriber = null;
        }   if (null != submitFinishHttpSubscriber) {
            submitFinishHttpSubscriber.unSubscribe();
            submitFinishHttpSubscriber = null;
        }
    }
}
