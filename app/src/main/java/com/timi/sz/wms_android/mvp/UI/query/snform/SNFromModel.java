package com.timi.sz.wms_android.mvp.UI.query.snform;

import com.timi.sz.wms_android.bean.query.request.RequestBean;
import com.timi.sz.wms_android.bean.query.request.SNRequsetBean;
import com.timi.sz.wms_android.bean.query.response.QueryBarcodeTracesResult;
import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
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
 * create at: 2018-02-23 10:15
 */

public class SNFromModel extends MvpBaseModel {

    /**
     * SN追溯查询
     * @param params
     * @param observer
     */
    public void queryBarcodeTraces(final SNRequsetBean params, Observer<QueryBarcodeTracesResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryBarcodeTracesResult>() {
            @Override
            public Observable<CommonResult<QueryBarcodeTracesResult>> createObservable(ApiService apiService) {
                return apiService.queryBarcodeTraces(params);
            }
        });
    }
}
