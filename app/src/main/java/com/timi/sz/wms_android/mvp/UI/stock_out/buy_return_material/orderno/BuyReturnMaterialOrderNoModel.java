package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoAddMaterial;
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
 * create at: 2017-08-29 14:43
 */

public class BuyReturnMaterialOrderNoModel extends MvpBaseModel {
    /**
     * 物料扫码 获取采购退货单
     * @param params
     * @param observer
     */
    public void materialScan(final Map<String,Object> params, Observer<SubmitBarcodeOutAuditData> observer){
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
}
