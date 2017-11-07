package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 08:43
 */

public class ScanReturnMaterialPresenter extends MvpBasePresenter<ScanReturnMaterialView> {
    ScanReturnMaterialMdel model = null;
    HttpSubscriber<SubmitBarcodeOutAuditData> subscriber = null;
    HttpSubscriber<SubmitBarcodePurReturnData> commitMaterialScanToOredernoBeanHttpSubscriber;

    public ScanReturnMaterialPresenter(Context context) {
        super(context);
        model = new ScanReturnMaterialMdel();
    }

    /**
     * 物料扫码的请求
     *
     * @param params
     */
    public void submitBarcodeOutAudit(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeOutAuditData>() {
                @Override
                public void onSuccess(SubmitBarcodeOutAuditData bean) {
                    getView().submitBarcodeOutAudit(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitBarcodeOutAudit(params, subscriber);
    }

    /**
     * 提交退料条码（制单）
     *
     * @param params
     */
    public void submitBarcodePurReturn(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == commitMaterialScanToOredernoBeanHttpSubscriber) {
            commitMaterialScanToOredernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodePurReturnData>() {
                @Override
                public void onSuccess(SubmitBarcodePurReturnData bean) {
                    getView().submitBarcodePurReturn(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitBarcodePurReturn(params, commitMaterialScanToOredernoBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
        if (null != commitMaterialScanToOredernoBeanHttpSubscriber) {
            commitMaterialScanToOredernoBeanHttpSubscriber.unSubscribe();
            commitMaterialScanToOredernoBeanHttpSubscriber = null;
        }
    }
}
