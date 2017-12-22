package com.timi.sz.wms_android.mvp.UI.stock_in_work.query;

import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeInResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.bean.stockin_work.query.StockQueryResult;
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
 * create at: 2017-09-22 13:18
 */

public class StockInWorkQueryModel extends MvpBaseModel {
    /**
     * 查询-扫描调入
     *
     * @param params
     * @param observer
     */
    public void queryAllotScan(final Map<String, Object> params, Observer<AllotScanResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<AllotScanResult>() {
            @Override
            public Observable<CommonResult<AllotScanResult>> createObservable(ApiService apiService) {
                return apiService.queryAllotScan(params);
            }
        });
    }

    /**
     * 查询-一步调入
     *
     * @param params
     * @param observer
     */
    public void queryAllotOneStep(final Map<String, Object> params, Observer<AllotOneSetpResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<AllotOneSetpResult>() {
            @Override
            public Observable<CommonResult<AllotOneSetpResult>> createObservable(ApiService apiService) {
                return apiService.queryAllotOneStep(params);
            }
        });
    }

    /**
     * 查询-形态转换 出库
     *
     * @param params
     * @param observer
     */
    public void queryFormChangeOut(final Map<String, Object> params, Observer<FormChangeOutResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<FormChangeOutResult>() {
            @Override
            public Observable<CommonResult<FormChangeOutResult>> createObservable(ApiService apiService) {
                return apiService.queryFormChangeOut(params);
            }
        });
    }

    /**
     * 查询-形态转换 入库
     *
     * @param params
     * @param observer
     */
    public void queryFormChangeIn(final Map<String, Object> params, Observer<FormChangeInResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<FormChangeInResult>() {
            @Override
            public Observable<CommonResult<FormChangeInResult>> createObservable(ApiService apiService) {
                return apiService.queryFormChangeIn(params);
            }
        });
    }

    /**
     * 查询-盘点
     *
     * @param params
     * @param observer
     */
    public void queryPoint(final Map<String, Object> params, Observer<PointResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<PointResult>() {
            @Override
            public Observable<CommonResult<PointResult>> createObservable(ApiService apiService) {
                return apiService.queryPoint(params);
            }
        });
    }


}
