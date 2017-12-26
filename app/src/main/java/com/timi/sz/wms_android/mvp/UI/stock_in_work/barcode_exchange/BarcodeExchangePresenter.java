package com.timi.sz.wms_android.mvp.UI.stock_in_work.barcode_exchange;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.ContainerAdjustResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-25 08:52
 */

public class BarcodeExchangePresenter extends MvpBasePresenter<BarcodeExchangeView> {
    private BarcodeExchangeModel model;
    private HttpSubscriber<ContainerAdjustResult> scanMaterialResultHttpSubscriber = null;

    public BarcodeExchangePresenter(Context context) {
        super(context);
        model = new BarcodeExchangeModel();
    }
    /**
     * 单一条码调整
     * @param params
     */
    public void  barcodeAdjust(Map<String, Object> params){
        getView().showProgressDialog();
        if (null == scanMaterialResultHttpSubscriber) {
            scanMaterialResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<ContainerAdjustResult>() {
                @Override
                public void onSuccess(ContainerAdjustResult o) {
                    getView().barcodeAdjust(o);
                    getView().setOldPackSelect();
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().setOldPackSelect();

                }
            });
        }
        model.barcodeAdjust(params, scanMaterialResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != scanMaterialResultHttpSubscriber) {
            scanMaterialResultHttpSubscriber.unSubscribe();
            scanMaterialResultHttpSubscriber = null;
        }
    }
}
