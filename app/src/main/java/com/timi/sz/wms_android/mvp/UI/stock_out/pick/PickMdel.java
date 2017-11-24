package com.timi.sz.wms_android.mvp.UI.stock_out.pick;

import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
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
 * create at: 2017-09-18 15:07
 */

public class PickMdel extends MvpBaseModel {

    /**
     * 成品拣货
     *
     * @param observer
     */
    public void queryDNByInputForPick(final Map<String, Object> params, Observer<QueryDNByInputForPickResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryDNByInputForPickResult>() {
            @Override
            public Observable<CommonResult<QueryDNByInputForPickResult>> createObservable(ApiService apiService) {
                return apiService.queryDNByInputForPick(params);
            }
        });
    }
}
