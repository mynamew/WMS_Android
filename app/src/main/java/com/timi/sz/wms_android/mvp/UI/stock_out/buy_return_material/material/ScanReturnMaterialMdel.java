package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
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
 * create at: 2017-08-29 08:42
 */

public class ScanReturnMaterialMdel extends MvpBaseModel {

    /**
     * 提交物料条码
     * @param params
     * @param observer
     */
    public void submitBarcodeOutAudit(final Map<String,Object> params, Observer<SubmitBarcodeOutAuditData> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodeOutAuditData>() {
            @Override
            public Observable<CommonResult<SubmitBarcodeOutAuditData>> createObservable(ApiService apiService) {
                return apiService.submitBarcodeOutAudit(params);
            }
        });

    }
    /**
     * 提交退料条码（制单）
     * @param params
     * @param observer
     */
    public void submitBarcodePurReturn(final  Map<String,Object> params, Observer<SubmitBarcodePurReturnData> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitBarcodePurReturnData>() {
            @Override
            public Observable<CommonResult<SubmitBarcodePurReturnData>> createObservable(ApiService apiService) {
                return apiService.submitBarcodePurReturn(params);
            }
        });
    }
    /**
     * 提交制单
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
