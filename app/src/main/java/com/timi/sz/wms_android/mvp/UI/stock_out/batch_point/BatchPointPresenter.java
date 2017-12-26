package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-19 10:08
 */

public class BatchPointPresenter extends MvpBasePresenter<BatchPointView> {
    private HttpSubscriber<GetMaterialLotData> getMaterialLotDataHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeLotPickOutResult> submitBarcodeLotPickOutResultHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeLotPickOutSplitResult> submitBarcodeLotPickOutSplitResultHttpSubscriber;
    private BatchPointModel model;

    public BatchPointPresenter(Context context) {
        super(context);
        model = new BatchPointModel();
    }

    /**
     * 获取批次信息
     *
     * @param params
     */
    public void getMaterialLotData(RequestGetMaterialLotBean params) {
        if (null == getMaterialLotDataHttpSubscriber) {
            getMaterialLotDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetMaterialLotData>() {
                @Override
                public void onSuccess(GetMaterialLotData bean) {
                    getView().getMaterialLotDataHttpSubscriber(bean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getMaterialLotData(params, getMaterialLotDataHttpSubscriber);
    }

    /**
     * 提交条码拆分出库(批次拣货)
     *
     * @param params
     */
    public void submitBarcodeLotPickOutSplit(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getMaterialLotDataHttpSubscriber) {
            getMaterialLotDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeLotPickOutSplitResult>() {
                @Override
                public void onSuccess(SubmitBarcodeLotPickOutSplitResult bean) {
                    getView().submitBarcodeLotPickOutSplit(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitBarcodeLotPickOutSplit(params, submitBarcodeLotPickOutSplitResultHttpSubscriber);
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
        if(intentCode== Constants.STOCK_OUT_ALLOT_OUT_PICK||intentCode== Constants.STOCK_OUT_SALE_OUT_PICK||intentCode== Constants.STOCK_OUT_SEND_OUT_PICK){
            model.submitBarcodePick(params, submitBarcodeLotPickOutResultHttpSubscriber);
        }else {
            model.submitBarcodeLotPickOut(params, submitBarcodeLotPickOutResultHttpSubscriber);
        }
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != getMaterialLotDataHttpSubscriber) {
            getMaterialLotDataHttpSubscriber.unSubscribe();
            getMaterialLotDataHttpSubscriber = null;
        }
        if (null != submitBarcodeLotPickOutResultHttpSubscriber) {
            submitBarcodeLotPickOutResultHttpSubscriber.unSubscribe();
            submitBarcodeLotPickOutResultHttpSubscriber = null;
        }
        if (null != submitBarcodeLotPickOutSplitResultHttpSubscriber) {
            submitBarcodeLotPickOutSplitResultHttpSubscriber.unSubscribe();
            submitBarcodeLotPickOutSplitResultHttpSubscriber = null;
        }
    }
}
