package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point;

import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
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
 * create at: 2017-11-19 10:08
 */

public class BatchPointModel extends MvpBaseModel {
    /**
     * 获取批次 拣货的信息
     *
     * @param observer
     */
    public void getMaterialLotData(final RequestGetMaterialLotBean params, Observer<GetMaterialLotData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<GetMaterialLotData>() {
            @Override
            public Observable<CommonResult<GetMaterialLotData>> createObservable(ApiService apiService) {
                return apiService.getMaterialLotData(params);
            }
        });
    }

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

    /**
     * 提交条码拆分出库(批次拣货)
     *
     * @param observer
     */
    public void submitBarcodeLotPickOutSplit(final Map<String, Object> params, Observer<SubmitBarcodeLotPickOutSplitResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodeLotPickOutSplitResult>() {
            @Override
            public Observable<CommonResult<SubmitBarcodeLotPickOutSplitResult>> createObservable(ApiService apiService) {
                return apiService.submitBarcodeLotPickOutSplit(params);
            }
        });
    }
    /**
     * 提交条码出库(批次拣货)。成品拣货
     *
     * @param observer
     */
    public void submitBarcodePick(final Map<String, Object> params, Observer<SubmitBarcodeLotPickOutResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodeLotPickOutResult>() {
            @Override
            public Observable<CommonResult<SubmitBarcodeLotPickOutResult>> createObservable(ApiService apiService) {
                return apiService.submitBarcodePick(params);
            }
        });
    }
}
