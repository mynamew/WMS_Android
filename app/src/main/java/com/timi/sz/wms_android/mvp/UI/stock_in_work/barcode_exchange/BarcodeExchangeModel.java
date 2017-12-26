package com.timi.sz.wms_android.mvp.UI.stock_in_work.barcode_exchange;

import com.timi.sz.wms_android.bean.stockin_work.ContainerAdjustResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-25 08:52
 */

public class BarcodeExchangeModel extends MvpBaseModel {

    /**
     * 单一转移调整
     *
     * @param params
     * @param observer
     */
    public void barcodeAdjust(final Map<String, Object> params, Observer<ContainerAdjustResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<ContainerAdjustResult>() {
            @Override
            public Observable<CommonResult<ContainerAdjustResult>> createObservable(ApiService apiService) {
                return apiService.barcodeAdujst(params);
            }
        });
    }
}
