package com.timi.sz.wms_android.mvp.UI.query.orderno_barcode;

import com.timi.sz.wms_android.bean.query.request.QueryBillBarcodeBean;
import com.timi.sz.wms_android.bean.query.response.QueryBarcodeTracesResult;
import com.timi.sz.wms_android.bean.query.response.QueryBillBarcodeResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 09:23
 */

public class OrdernoBarcodeModel extends MvpBaseModel {
    /**
     * 单据条码查询
     * @param params
     * @param observer
     */
    public void queryBillBarcode(final QueryBillBarcodeBean params, Observer<QueryBillBarcodeResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryBillBarcodeResult>() {
            @Override
            public Observable<CommonResult<QueryBillBarcodeResult>> createObservable(ApiService apiService) {
                return apiService.queryBillBarcode(params);
            }
        });
    }
}
