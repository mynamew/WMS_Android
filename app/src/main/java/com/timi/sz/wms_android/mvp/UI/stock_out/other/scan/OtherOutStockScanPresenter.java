package com.timi.sz.wms_android.mvp.UI.stock_out.other.scan;

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
 * create at: 2017-08-29 16:33
 */

public class OtherOutStockScanPresenter extends MvpBasePresenter<OtherOutStockScanView> {
    OtherOutStockScanModel model = null;
    private HttpSubscriber<Object> submitMakeOrAuditBillHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeOutAuditData> submitBarcodeLotPickOutResultHttpSubscriber;

    public OtherOutStockScanPresenter(Context context) {
        super(context);
        model = new OtherOutStockScanModel();
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
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitMakeOrAuditBill(params, submitMakeOrAuditBillHttpSubscriber);
    }

    /**
     * 提交条码出库(普通出库)。
     *
     * @param params
     */
    public void submitBarcodeLotPickOut(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitBarcodeLotPickOutResultHttpSubscriber) {
            submitBarcodeLotPickOutResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeOutAuditData>() {
                @Override
                public void onSuccess(SubmitBarcodeOutAuditData bean) {
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
        model.submitBarcodeOutAudit(params, submitBarcodeLotPickOutResultHttpSubscriber);
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
