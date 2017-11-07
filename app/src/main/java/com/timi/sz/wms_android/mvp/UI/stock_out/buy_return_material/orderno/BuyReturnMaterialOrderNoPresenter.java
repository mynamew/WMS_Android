package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoAddMaterial;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
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
    private HttpSubscriber<SubmitBarcodeOutAuditData> subscriber;
    private HttpSubscriber<SubmitBarcodePurReturnData> commitMaterialScanToOredernoBeanHttpSubscriber;
    BuyReturnMaterialOrderNoModel model=null;
    public BuyReturnMaterialOrderNoPresenter(Context context) {
        super(context);
        model=new BuyReturnMaterialOrderNoModel();
    }


    /**
     * 物料扫码的请求
     *
     * @param params
     */
    public void materialScan(Map<String, Object> params) {
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitBarcodeOutAuditData>() {
                @Override
                public void onSuccess(SubmitBarcodeOutAuditData bean) {
                    getView().materialScan(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.materialScan(params, subscriber);
    }

    /**
     * 提交退料条码（制单）
     *
     * @param params
     */
    public void submitBarcodePurReturn(Map<String, Object> params) {
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
        //反注册
        if (null != commitMaterialScanToOredernoBeanHttpSubscriber) {
            commitMaterialScanToOredernoBeanHttpSubscriber.unSubscribe();
            commitMaterialScanToOredernoBeanHttpSubscriber = null;
        }
        if (null != subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
    }
}
