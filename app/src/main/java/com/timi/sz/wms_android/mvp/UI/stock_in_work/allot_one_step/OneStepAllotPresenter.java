package com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_one_step;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 15:03
 */

public class OneStepAllotPresenter extends MvpBasePresenter<OneStepAllotView> {
    private OneStepAllotModel model;

    public OneStepAllotPresenter(Context context) {
        super(context);
        model = new OneStepAllotModel();
    }
    private HttpSubscriber<VertifyLocationCodeBean> vertifyLocationCodeBeanHttpSubscriber = null;
    private HttpSubscriber<Object> submitTransferOneStepHttpSubscriber = null;
    /**
     * 提交一步调入
     *
     * @param params
     */
    public void submitTransferOneStep(final Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitTransferOneStepHttpSubscriber) {
            submitTransferOneStepHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().submitTransferOneStep(o);
                    getView().setLocationSelect();
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().setLocationSelect();
                }
            });
        }
        model.submitTransferOneStep(params, submitTransferOneStepHttpSubscriber);
    }


    @Override
    public void dettachView() {
        super.dettachView();
        if (null != vertifyLocationCodeBeanHttpSubscriber) {
            vertifyLocationCodeBeanHttpSubscriber.unSubscribe();
            vertifyLocationCodeBeanHttpSubscriber = null;
        }
        if (null != submitTransferOneStepHttpSubscriber) {
            submitTransferOneStepHttpSubscriber.unSubscribe();
            submitTransferOneStepHttpSubscriber = null;
        }
    }
}
