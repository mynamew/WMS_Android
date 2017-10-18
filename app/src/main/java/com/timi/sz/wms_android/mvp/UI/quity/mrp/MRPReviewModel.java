package com.timi.sz.wms_android.mvp.UI.quity.mrp;

import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
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
 * create at: 2017-10-17 15:58
 */

public class MRPReviewModel extends MvpBaseModel {
    /**
     * 获取Mrp数据
     *
     * @param params
     * @param observer
     */
    public void getMRPReviewData(final Map<String, Object> params, Observer<List<MrpReviewData>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<MrpReviewData>>() {
            @Override
            public Observable<CommonResult<List<MrpReviewData>>> createObservable(ApiService apiService) {
                return apiService.getMRPReviewData(params);
            }
        });
    }

}
