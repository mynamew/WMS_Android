package com.timi.sz.wms_android.mvp.UI.query.orderno_barcode;

import android.content.Context;

import com.timi.sz.wms_android.bean.query.request.QueryBillBarcodeBean;
import com.timi.sz.wms_android.bean.query.request.SNRequsetBean;
import com.timi.sz.wms_android.bean.query.response.QueryBarcodeTracesResult;
import com.timi.sz.wms_android.bean.query.response.QueryBillBarcodeResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 09:23
 */

public class OrdernoBarcodePresenter extends MvpBasePresenter<OrdernoBarcodeView> {
    private OrdernoBarcodeModel model;
    private HttpSubscriber<QueryBillBarcodeResult> queryBillBarcodeResultHttpSubscriber;
    public OrdernoBarcodePresenter(Context context) {
        super(context);
        model = new OrdernoBarcodeModel();
    }
    /**
     * SN追溯
     *
     * @param params
     */
    public void queryBillBarcode(QueryBillBarcodeBean params) {
        getView().showProgressDialog();
        if (null == queryBillBarcodeResultHttpSubscriber) {
            queryBillBarcodeResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryBillBarcodeResult>() {
                @Override
                public void onSuccess(QueryBillBarcodeResult o) {
                    getView().queryBillBarcode(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.queryBillBarcode(params, queryBillBarcodeResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if(null!=queryBillBarcodeResultHttpSubscriber){
            queryBillBarcodeResultHttpSubscriber.unSubscribe();
            queryBillBarcodeResultHttpSubscriber=null;
        }
    }
}
