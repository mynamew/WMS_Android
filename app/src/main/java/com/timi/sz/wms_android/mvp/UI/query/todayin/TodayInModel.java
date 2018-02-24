package com.timi.sz.wms_android.mvp.UI.query.todayin;

import com.timi.sz.wms_android.bean.query.request.RequestBean;
import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
import com.timi.sz.wms_android.bean.query.response.TodayInStockDetailResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-25 11:01
 */

public class TodayInModel extends MvpBaseModel {
    /**
     * 获取每日入库的总揽
     * @param params
     * @param observer
     */
    public void getTodayInSummeryTotal(final RequestBean params, Observer<StockSummeryResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<StockSummeryResult>() {
            @Override
            public Observable<CommonResult<StockSummeryResult>> createObservable(ApiService apiService) {
                return apiService.getStockSummary(params);
            }
        });
    }
    /**
     * 获取每日入库的明细
     * @param params
     * @param observer
     */
    public void getTodayInSummeryDetail(final RequestBean params, Observer<List<TodayInStockDetailResult>> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<TodayInStockDetailResult>>() {
            @Override
            public Observable<CommonResult<List<TodayInStockDetailResult>>> createObservable(ApiService apiService) {
                return apiService.getInStockDetailSummary(params);
            }
        });
    }
}
