package com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-12 14:54
 */

public class AdvanceQualityPresenter extends MvpBasePresenter<AdvanceQualityView> {
    private AdvanceQualityModel model;
    private HttpSubscriber<GetAdvance2Data> getAdvance2DataHttpSubscriber;
    private HttpSubscriber<Object> setAdvance2DataHttpSubscriber;
    private HttpSubscriber<Object> submitFinishHttpSubscriber;

    public AdvanceQualityPresenter(Context context) {
        super(context);
        model = new AdvanceQualityModel();
    }

    /**
     * 获取高级质检2的数据
     *
     * @param params
     */
    public void getAdvance2Data(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getAdvance2DataHttpSubscriber) {
            getAdvance2DataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetAdvance2Data>() {
                @Override
                public void onSuccess(GetAdvance2Data o) {
                    getView().getAdvance2Data(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getAdvance2Data(params, getAdvance2DataHttpSubscriber);
    }

    /**
     * 获取普通质检的数据
     *
     * @param data
     */
    public void setAdvance2Data(CommitAdvanceData data) {
        getView().showProgressDialog();
        if (null == setAdvance2DataHttpSubscriber) {
            setAdvance2DataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().setAdvance2Data();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.setAdvance2Data(data, setAdvance2DataHttpSubscriber);
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
        if (null != getAdvance2DataHttpSubscriber) {
            getAdvance2DataHttpSubscriber.unSubscribe();
            getAdvance2DataHttpSubscriber = null;
        }
        if (null != submitFinishHttpSubscriber) {
            submitFinishHttpSubscriber.unSubscribe();
            submitFinishHttpSubscriber = null;
        }
        if (null != setAdvance2DataHttpSubscriber) {
            setAdvance2DataHttpSubscriber.unSubscribe();
            setAdvance2DataHttpSubscriber = null;
        }
    }
}
