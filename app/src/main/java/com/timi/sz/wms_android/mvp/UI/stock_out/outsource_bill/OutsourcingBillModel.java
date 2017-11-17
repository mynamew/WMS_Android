package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_bill;

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
 * create at: 2017-11-14 09:08
 */

public class OutsourcingBillModel extends MvpBaseModel {
    /**
     * 提交审核
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
