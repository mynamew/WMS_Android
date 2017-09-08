package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
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
     * 委外补料 搜索请求

     * @param observer
     */
    public void searchOutsourceFeed(final Map<String,Object> params, Observer<OutSourceFeedBean> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OutSourceFeedBean>() {
            @Override
            public Observable<CommonResult<OutSourceFeedBean>> createObservable(ApiService apiService) {
                return apiService.searchOutsourceFeed(params);
            }
        });
    }
}
