package com.timi.sz.wms_android.mvp.UI.stock_in_work.goods_query;

import com.timi.sz.wms_android.bean.stockin_work.stock_query.MaterialQueryResult;
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
 * create at: 2017-11-30 16:33
 */

public class MaterialRepertoryModel extends MvpBaseModel {
    /**
     * 搜索物料的库存
     * @param params
     * @param observer
     */
    public void queryStockMaterial(final Map<String, Object> params, Observer<MaterialQueryResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<MaterialQueryResult>() {
            @Override
            public Observable<CommonResult<MaterialQueryResult>> createObservable(ApiService apiService) {
                return apiService.queryStockMaterial(params);
            }
        });
    }
}
