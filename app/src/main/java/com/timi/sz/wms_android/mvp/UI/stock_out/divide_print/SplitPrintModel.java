package com.timi.sz.wms_android.mvp.UI.stock_out.divide_print;

import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
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
 * create at: 2017-09-18 13:45
 */

public class SplitPrintModel extends MvpBaseModel {
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
     * 提交条码拆分出库(批次拣货) --- 成品拣货
     *
     * @param observer
     */
    public void submitBarcodePickSplit(final Map<String, Object> params, Observer<SubmitBarcodeLotPickOutSplitResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodeLotPickOutSplitResult>() {
            @Override
            public Observable<CommonResult<SubmitBarcodeLotPickOutSplitResult>> createObservable(ApiService apiService) {
                return apiService.submitBarcodePickSplit(params);
            }
        });
    }
    /**
     * 提交条码拆分出库（普通）
     * @param params
     * @param observer
     */
    public void submitBarcodeOutSplitAudit(final Map<String, Object> params, Observer<SubmitBarcodeOutSplitAuditData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodeOutSplitAuditData>() {
            @Override
            public Observable<CommonResult<SubmitBarcodeOutSplitAuditData>> createObservable(ApiService apiService) {
                return apiService.submitBarcodeOutSplitAudit(params);
            }
        });
    }
}
