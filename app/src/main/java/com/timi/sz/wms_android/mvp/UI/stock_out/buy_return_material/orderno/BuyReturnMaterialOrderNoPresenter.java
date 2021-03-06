package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import android.content.Context;

import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 14:44
 */

public class BuyReturnMaterialOrderNoPresenter extends MvpBasePresenter<BuyReturnMaterialOrderNoView> {
    BuyReturnMaterialOrderNoModel model = null;
    private HttpSubscriber<SubmitBarcodeOutAuditData> submitBarcodeOutAuditDataHttpSubscriber;
    private HttpSubscriber<SubmitBarcodeOutSplitAuditData> submitBarcodeOutSplitAuditDataHttpSubscriber;
    private HttpSubscriber<Object> submitMakeOrAuditBillHttpSubscriber;

    public BuyReturnMaterialOrderNoPresenter(Context context) {
        super(context);
        model = new BuyReturnMaterialOrderNoModel();
    }

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
//                    ToastUtils.showShort(errorMsg);
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
//                    ToastUtils.showShort(errorMsg);
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
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitMakeOrAuditBill(params, submitMakeOrAuditBillHttpSubscriber);
    }


    @Override
    public void dettachView() {
        super.dettachView();
        if (null != submitMakeOrAuditBillHttpSubscriber) {
            submitMakeOrAuditBillHttpSubscriber.unSubscribe();
            submitMakeOrAuditBillHttpSubscriber = null;
        }
        if (null != submitBarcodeOutAuditDataHttpSubscriber) {
            submitBarcodeOutAuditDataHttpSubscriber.unSubscribe();
            submitBarcodeOutAuditDataHttpSubscriber = null;
        }
        if (null != submitBarcodeOutSplitAuditDataHttpSubscriber) {
            submitBarcodeOutSplitAuditDataHttpSubscriber.unSubscribe();
            submitBarcodeOutSplitAuditDataHttpSubscriber = null;
        }
    }
}
