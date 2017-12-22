package com.timi.sz.wms_android.mvp.UI.stock_out.other.scan;

import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
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
 * create at: 2017-08-29 16:33
 */

public class OtherOutStockScanModel extends MvpBaseModel {
    /**
     * 提交普通条码出库
     *
     * @param params
     * @param observer
     */
    public void submitBarcodeOutAudit(final Map<String, Object> params, Observer<SubmitBarcodeOutAuditData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodeOutAuditData>() {
            @Override
            public Observable<CommonResult<SubmitBarcodeOutAuditData>> createObservable(ApiService apiService) {
                return apiService.submitBarcodeOutAudit(params);
            }
        });
    }
    /**
     * 提交制单和审核
     * @param observer
     */
    public  void submitMakeOrAuditBill(final Map<String,Object> params, Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.submitMakeOrAuditBill(params);
            }
        });
    }
}
