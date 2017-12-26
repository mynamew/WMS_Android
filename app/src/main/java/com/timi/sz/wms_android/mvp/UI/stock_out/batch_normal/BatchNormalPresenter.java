package com.timi.sz.wms_android.mvp.UI.stock_out.batch_normal;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-19 15:10
 */

public class BatchNormalPresenter extends MvpBasePresenter<BatchNormalView> {
    private BatchNormalModel model;
    private HttpSubscriber<SubmitBarcodeLotPickOutResult> submitBarcodeLotPickOutResultHttpSubscriber;

    public BatchNormalPresenter(Context context) {
        super(context);
        model = new BatchNormalModel();
    }
    /**
     * 提交条码出库(批次拣货)。
     *
     * @param params
     * @param intentCode
     */
    public void submitBarcodeLotPickOut(Map<String, Object> params, int intentCode) {
        getView().showProgressDialog();
        if (null == submitBarcodeLotPickOutResultHttpSubscriber) {
            submitBarcodeLotPickOutResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeLotPickOutResult>() {
                @Override
                public void onSuccess(SubmitBarcodeLotPickOutResult bean) {
                    getView().setBarcodeSelect();
                    getView().submitBarcodeLotPickOut(bean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().setBarcodeSelect();
                }
            });
        }
        //成品拣货出库 调用成品拣货出库接口
        if(intentCode== Constants.STOCK_OUT_ALLOT_OUT_PICK||
                intentCode== Constants.STOCK_OUT_SALE_OUT_PICK||
                intentCode== Constants.STOCK_OUT_SEND_OUT_PICK){
            model.submitBarcodePick(params, submitBarcodeLotPickOutResultHttpSubscriber);
        }else {
            model.submitBarcodeLotPickOut(params, submitBarcodeLotPickOutResultHttpSubscriber);
        }
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != submitBarcodeLotPickOutResultHttpSubscriber) {
            submitBarcodeLotPickOutResultHttpSubscriber.unSubscribe();
            submitBarcodeLotPickOutResultHttpSubscriber = null;
        }
    }
}
