package com.timi.sz.wms_android.mvp.UI.stock_in_work.detail;

import com.timi.sz.wms_android.bean.stockin_work.FormChangeDetailResult;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-04 15:17
 */

public class StockInWorkDetailModel extends MvpBaseModel {
    /**
     * 查询扫描调入详情
      * @param params
     * @param observer
     */
    public void queryAllotScanDetail(final Map<String,Object> params, Observer<List<StockInWorkDetailResult>>observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<StockInWorkDetailResult>>() {
            @Override
            public Observable<CommonResult<List<StockInWorkDetailResult>>> createObservable(ApiService apiService) {
                return apiService.queryAllotScanDetail(params);
            }
        });
    }
    /**
     * 形态转换 出库 明细
     * @param params
     * @param observer
     */
    public void getConvertOutDetail(final Map<String,Object> params, Observer<List<StockInWorkDetailResult>> observer){

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<StockInWorkDetailResult>>() {
            @Override
            public Observable<CommonResult<List<StockInWorkDetailResult>>> createObservable(ApiService apiService) {
                return apiService.getConvertOutDetail(params);
            }
        });
    }
    /**
     * 形态转换 入库 明细
     * @param params
     * @param observer
     */
    public void getConvertInDetail(final Map<String,Object> params, Observer<List<StockInWorkDetailResult>> observer){

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<StockInWorkDetailResult>>() {
            @Override
            public Observable<CommonResult<List<StockInWorkDetailResult>>> createObservable(ApiService apiService) {
                return apiService.getConvertInDetail(params);
            }
        });
    }
    /**
     * 获取盘点单明细
     * @param params
     * @param observer
     */
    public void getCheckStockDetail(final Map<String,Object> params, Observer<List<StockInWorkDetailResult>> observer){

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<StockInWorkDetailResult>>() {
            @Override
            public Observable<CommonResult<List<StockInWorkDetailResult>>> createObservable(ApiService apiService) {
                return apiService.getCheckStockDetail(params);
            }
        });
    }
}
