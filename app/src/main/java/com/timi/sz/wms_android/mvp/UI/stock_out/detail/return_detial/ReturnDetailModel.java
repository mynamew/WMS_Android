package com.timi.sz.wms_android.mvp.UI.stock_out.detail.return_detial;

import com.timi.sz.wms_android.bean.stockin_work.ReturnDetailResult;
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
 * create at: 2018-01-15 15:11
 */

public class ReturnDetailModel extends MvpBaseModel {

    /**
     * 明细 搜索请求
     *
     * @param observer
     */
    public void getMakePurReturnScannedDetail(final Map<String, Object> params, Observer<List<ReturnDetailResult>> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<ReturnDetailResult>>() {
            @Override
            public Observable<CommonResult<List<ReturnDetailResult>>> createObservable(ApiService apiService) {
                return apiService.getMakePurReturnScannedDetail(params);
            }
        });
    }
}
