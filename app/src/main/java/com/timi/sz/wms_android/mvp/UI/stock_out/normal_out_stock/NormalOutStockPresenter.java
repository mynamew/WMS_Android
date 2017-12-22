package com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-19 08:57
 */

public class NormalOutStockPresenter extends MvpBasePresenter<NormalOutStockView> {
    public NormalOutStockPresenter(Context context) {
        super(context);
        model = new NormalOutStockModel();
    }

    private HttpSubscriber<SubmitBarcodeOutAuditData> submitBarcodeOutAuditDataHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeOutSplitAuditData> submitBarcodeOutSplitAuditDataHttpSubscriber;
    private HttpSubscriber<Object> submitMakeOrAuditBillHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeLotPickOutResult> submitBarcodeLotPickOutResultHttpSubscriber;
    private NormalOutStockModel model;

    /**
     * 提交退料条码（制单）
     *
     * @param params
     */
    public void submitBarcodeOutAudit(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitBarcodeOutAuditDataHttpSubscriber) {
            submitBarcodeOutAuditDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeOutAuditData>() {
                @Override
                public void onSuccess(SubmitBarcodeOutAuditData bean) {
                    getView().submitBarcodeOutAudit(bean);
                    getView().setBarcodeSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setBarcodeSelect();
                }
            });
        }
        model.submitBarcodeOutAudit(params, submitBarcodeOutAuditDataHttpSubscriber);
    }

    /**
     * 提交条码拆分出库（普通）
     *
     * @param params
     */
    public void submitBarcodeOutSplitAudit(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitBarcodeOutSplitAuditDataHttpSubscriber) {
            submitBarcodeOutSplitAuditDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeOutSplitAuditData>() {
                @Override
                public void onSuccess(SubmitBarcodeOutSplitAuditData bean) {
                    getView().submitBarcodeOutSplitAudit(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitBarcodeOutSplitAudit(params, submitBarcodeOutSplitAuditDataHttpSubscriber);
    }

    /**
     * 提交制单和审核
     *
     * @param params
     */
    public void submitMakeOrAuditBill(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitMakeOrAuditBillHttpSubscriber) {
            submitMakeOrAuditBillHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object bean) {
                    getView().submitMakeOrAuditBill();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitMakeOrAuditBill(params, submitMakeOrAuditBillHttpSubscriber);
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
                    getView().setBarcodeSelect();
                    getView().submitBarcodeLotPickOut(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setBarcodeSelect();
                }
            });
        }
        model.submitBarcodeLotPickOut(params, submitBarcodeLotPickOutResultHttpSubscriber);
    }


    @Override
    public void dettachView() {
        super.dettachView();
        if (null != submitMakeOrAuditBillHttpSubscriber) {
            submitMakeOrAuditBillHttpSubscriber.unSubscribe();
            submitMakeOrAuditBillHttpSubscriber = null;
        }
        if (null != submitBarcodeLotPickOutResultHttpSubscriber) {
            submitBarcodeLotPickOutResultHttpSubscriber.unSubscribe();
            submitBarcodeLotPickOutResultHttpSubscriber = null;
        }
    }
}