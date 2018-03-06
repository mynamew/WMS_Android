package com.timi.sz.wms_android.mvp.UI.query.snform;

import android.content.Context;

import com.timi.sz.wms_android.bean.query.request.RequestBean;
import com.timi.sz.wms_android.bean.query.request.SNRequsetBean;
import com.timi.sz.wms_android.bean.query.response.QueryBarcodeTracesResult;
import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 10:15
 */

public class SNFromPresenter extends MvpBasePresenter<SNFromView> {
    private SNFromModel model;
    private HttpSubscriber<QueryBarcodeTracesResult> queryBarcodeTracesResultHttpSubscriber;
    public SNFromPresenter(Context context) {
        super(context);
        model=new SNFromModel();
    }
    /**
     * SN追溯
     *
     * @param params
     */
    public void queryBarcodeTraces(SNRequsetBean params) {
        getView().showProgressDialog();
        if (null == queryBarcodeTracesResultHttpSubscriber) {
            queryBarcodeTracesResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryBarcodeTracesResult>() {
                @Override
                public void onSuccess(QueryBarcodeTracesResult o) {
                    getView().queryBarcodeTraces(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.queryBarcodeTraces(params, queryBarcodeTracesResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if(null!=queryBarcodeTracesResultHttpSubscriber){
            queryBarcodeTracesResultHttpSubscriber.unSubscribe();
            queryBarcodeTracesResultHttpSubscriber=null;
        }
    }
}
