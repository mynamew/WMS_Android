package com.timi.sz.wms_android.mvp.UI.stock_out;

import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
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
 * create at: 2018-01-16 09:25
 */

public class StockOutModel extends MvpBaseModel {
    /**
     * 采购退料制单 获取上次扫描数据
     * @param params
     * @param observer
     */
    public void buyReturnMaterialByMaterialCodeData(final Map<String,Object> params, Observer<BuyReturnMaterialByMaterialCodeData> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyReturnMaterialByMaterialCodeData>() {
            @Override
            public Observable<CommonResult<BuyReturnMaterialByMaterialCodeData>> createObservable(ApiService apiService) {
                return apiService.buyReturnMaterialByMaterialCodeData(params);
            }
        });

    }
}
