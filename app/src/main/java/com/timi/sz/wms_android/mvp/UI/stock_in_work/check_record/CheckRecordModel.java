package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_record;

import com.timi.sz.wms_android.bean.stockin_work.CheckRecordResult;
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
 * create at: 2017-12-11 16:10
 */

public class CheckRecordModel extends MvpBaseModel {
    /**
     * 盘点记录
     * @param params
     * @param observer
     */
    public void getCheckStockPageRecord(final Map<String,Object> params, Observer<List<CheckRecordResult>> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<CheckRecordResult>>() {
            @Override
            public Observable<CommonResult<List<CheckRecordResult>>> createObservable(ApiService apiService) {
                return apiService.getCheckStockPageRecord(params);
            }
        });
    }
}
