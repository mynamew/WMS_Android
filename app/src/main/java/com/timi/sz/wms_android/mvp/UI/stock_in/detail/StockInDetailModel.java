package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

import com.timi.sz.wms_android.bean.instock.OrderDetailData;
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
 * $dsc  单据详情的返回数据
 * author: timi
 * create at: 2017-08-28 16:03
 */

public class StockInDetailModel extends MvpBaseModel {
    /**
     * 获取 单据详情的数据
     * @param params
     * @param observer
     */
    public void getReceiptDetail(final Map<String, Object> params, Observer<List<OrderDetailData>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<OrderDetailData>>() {
            @Override
            public Observable<CommonResult<List<OrderDetailData>>> createObservable(ApiService apiService) {
                return apiService.getReceiptDetail(params);
            }
        });

    }

}
