package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feed.OutSourceFeedMaterialPointModel;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-14 08:59
 */

public class OutsourcingAuditPointPresenter extends MvpBasePresenter<OutsourcingAuditPointView> {
    private HttpSubscriber<GetMaterialLotData> getMaterialLotDataHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeLotPickOutResult> submitBarcodeLotPickOutResultHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeLotPickOutSplitResult> submitBarcodeLotPickOutSplitResultHttpSubscriber;
    private OutsourcingAuditPointModel model;

    public OutsourcingAuditPointPresenter(Context context) {
        super(context);
        model = new OutsourcingAuditPointModel();
    }

    /**
     * 提交制单
     *
     * @param params
     */
    public void getMaterialLotData(RequestGetMaterialLotBean params) {
        getView().showProgressDialog();
        if (null == getMaterialLotDataHttpSubscriber) {
            getMaterialLotDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetMaterialLotData>() {
                @Override
                public void onSuccess(GetMaterialLotData bean) {
                    getView().getMaterialLotDataHttpSubscriber(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
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
     */
    public void submitBarcodeLotPickOut(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitBarcodeLotPickOutResultHttpSubscriber) {
            submitBarcodeLotPickOutResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeLotPickOutResult>() {
                @Override
                public void onSuccess(SubmitBarcodeLotPickOutResult bean) {
                    getView().submitBarcodeLotPickOut(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitBarcodeLotPickOut(params, submitBarcodeLotPickOutResultHttpSubscriber);
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
