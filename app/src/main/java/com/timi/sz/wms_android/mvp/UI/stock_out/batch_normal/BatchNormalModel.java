package com.timi.sz.wms_android.mvp.UI.stock_out.batch_normal;

import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
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
 * create at: 2017-12-19 15:10
 */

public class BatchNormalModel extends MvpBaseModel {
    /**
     * 提交条码出库(批次拣货)。
     *
     * @param observer
     */
    public void submitBarcodeLotPickOut(final Map<String, Object> params, Observer<SubmitBarcodeLotPickOutResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodeLotPickOutResult>() {
            @Override
            public Observable<CommonResult<SubmitBarcodeLotPickOutResult>> createObservable(ApiService apiService) {
                return apiService.submitBarcodeLotPickOut(params);
            }
        });
    }
}
