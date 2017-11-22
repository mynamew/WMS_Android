package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
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
 * create at: 2017-09-04 08:55
 */

public class StockOutSearchModel extends MvpBaseModel {
    /**
     * 委外发料(审核) 搜索请求
     *
     * @param observer
     */
    public void queryOutSourcePickByInput(final Map<String, Object> params, Observer<QueryOutSourcePickByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOutSourcePickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOutSourcePickByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryOutSourcePickByInput(params);
            }
        });
    }

    /**
     * 委外发料(生单) 搜索请求
     *
     * @param observer
     */
    public void queryWWPickDataByOutSource(final Map<String, Object> params, Observer<QueryWWPickDataByOutSourceResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryWWPickDataByOutSourceResult>() {
            @Override
            public Observable<CommonResult<QueryWWPickDataByOutSourceResult>> createObservable(ApiService apiService) {
                return apiService.queryWWPickDataByOutSource(params);
            }
        });
    }

    /**
     * 委外补料 搜索请求
     *
     * @param observer
     */
    public void queryOutSourceFeedByInput(final Map<String, Object> params, Observer<QueryOutSourceFeedByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOutSourceFeedByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOutSourceFeedByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryOutSourceFeedByInput(params);
            }
        });
    }

    /**====== 生产领料 ======**/
    /**
     * 生产领料单末尾号查询（生单流程）
     *
     * @param observer
     */
    public void queryPrdPickDataByMO(final Map<String, Object> params, Observer<QueryWWPickDataByOutSourceResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryWWPickDataByOutSourceResult>() {
            @Override
            public Observable<CommonResult<QueryWWPickDataByOutSourceResult>> createObservable(ApiService apiService) {
                return apiService.queryPrdPickDataByMO(params);
            }
        });
    }
    /**
     * 生产领料单末尾号查询（审核流程）
     *
     * @param observer
     */
    public void queryProductPickByInput(final Map<String, Object> params, Observer<QueryProductPickByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryProductPickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryProductPickByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryProductPickByInput(params);
            }
        });
    }

    /**
     * 生产补料单末尾号查询
     *
     * @param observer
     */
    public void queryPrdFeedByInput(final Map<String, Object> params, Observer<QueryPrdFeedByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryPrdFeedByInputResult>() {
            @Override
            public Observable<CommonResult<QueryPrdFeedByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryPrdFeedByInput(params);
            }
        });
    }

}
