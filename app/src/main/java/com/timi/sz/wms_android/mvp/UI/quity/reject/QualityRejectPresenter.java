package com.timi.sz.wms_android.mvp.UI.quity.reject;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-13 08:50
 */

public class QualityRejectPresenter extends MvpBasePresenter<QualityRejectView> {
    private QualityRejectModel model;
    private HttpSubscriber<BarcodeData> barcodeDataHttpSubscriber;
    private HttpSubscriber<BarcodeData> setBarcodeDataHttpSubscriber;
    private HttpSubscriber<Object> submitFinishHttpSubscriber;

    public QualityRejectPresenter(Context context) {
        super(context);
        model = new QualityRejectModel();
    }

    /**
     * 通过条码获取质检条码的数据
     *
     * @param params
     * @param result
     */
    public void getBarcodeData(Map<String, Object> params, final String result) {
        getView().showProgressDialog();
        if (null == barcodeDataHttpSubscriber) {
            barcodeDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BarcodeData>() {
                @Override
                public void onSuccess(BarcodeData o) {
                    getView().getBarcodeData(o, result);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getBarcodeData(params, barcodeDataHttpSubscriber);
    }

    /**
     * 通过条码获取质检条码的数据
     *
     * @param params
     */
    public void setBarcodeData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == setBarcodeDataHttpSubscriber) {
            setBarcodeDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BarcodeData>() {
                @Override
                public void onSuccess(BarcodeData o) {
                    getView().setBarcodeData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.setBarcodeReject(params, setBarcodeDataHttpSubscriber);
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
        if (null != barcodeDataHttpSubscriber) {
            barcodeDataHttpSubscriber.unSubscribe();
            barcodeDataHttpSubscriber = null;
        }
        if (null != submitFinishHttpSubscriber) {
            submitFinishHttpSubscriber.unSubscribe();
            submitFinishHttpSubscriber = null;
        }
        if (null != setBarcodeDataHttpSubscriber) {
            setBarcodeDataHttpSubscriber.unSubscribe();
            setBarcodeDataHttpSubscriber = null;
        }
    }
}
