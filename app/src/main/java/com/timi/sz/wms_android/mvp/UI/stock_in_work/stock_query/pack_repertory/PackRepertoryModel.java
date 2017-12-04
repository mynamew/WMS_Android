package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory;

import com.timi.sz.wms_android.bean.stockin_work.stock_query.QueryStockContainerResult;
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
 * create at: 2017-11-30 16:30
 */

public class PackRepertoryModel extends MvpBaseModel {
    /**
     * 库存查询
     * @param params
     * @param observer
     */
    public void queryStockContainer(final Map<String, Object> params, Observer<QueryStockContainerResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryStockContainerResult>() {
            @Override
            public Observable<CommonResult<QueryStockContainerResult>> createObservable(ApiService apiService) {
                return apiService.queryStockContainer(params);
            }
        });
    }

}
