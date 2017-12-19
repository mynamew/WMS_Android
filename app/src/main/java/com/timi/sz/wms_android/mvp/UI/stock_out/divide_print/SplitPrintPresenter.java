package com.timi.sz.wms_android.mvp.UI.stock_out.divide_print;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-18 13:45
 */

public class SplitPrintPresenter extends MvpBasePresenter<SplitPrintView> {
    private HttpSubscriber<SubmitBarcodeLotPickOutSplitResult> submitBarcodeLotPickOutSplitResultHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeOutSplitAuditData> submitBarcodeOutSplitAuditDataHttpSubscriber;
    private SplitPrintModel model;

    public SplitPrintPresenter(Context context) {
        super(context);
        model = new SplitPrintModel();
    }

    /**
     * 提交条码拆分出库(批次拣货)
     *
     * @param params
     */
    public void submitBarcodeLotPickOutSplit(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitBarcodeLotPickOutSplitResultHttpSubscriber) {
            submitBarcodeLotPickOutSplitResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeLotPickOutSplitResult>() {
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
    @Override
    public void dettachView() {
        super.dettachView();
        if (null != submitBarcodeLotPickOutSplitResultHttpSubscriber) {
            submitBarcodeLotPickOutSplitResultHttpSubscriber.unSubscribe();
            submitBarcodeLotPickOutSplitResultHttpSubscriber = null;
        }if (null != submitBarcodeOutSplitAuditDataHttpSubscriber) {
            submitBarcodeOutSplitAuditDataHttpSubscriber.unSubscribe();
            submitBarcodeOutSplitAuditDataHttpSubscriber = null;
        }
    }
}
