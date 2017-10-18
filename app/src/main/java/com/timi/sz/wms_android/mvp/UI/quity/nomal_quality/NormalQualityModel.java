package com.timi.sz.wms_android.mvp.UI.quity.nomal_quality;

import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
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
 * create at: 2017-09-06 17:22
 */

public class NormalQualityModel extends MvpBaseModel {
    /**
     * 获取普通质检的数据
     *
     * @param params
     * @param observer
     */
    public void getNormalQualityData(final Map<String, Object> params, Observer<NormalQualityData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<NormalQualityData>() {
            @Override
            public Observable<CommonResult<NormalQualityData>> createObservable(ApiService apiService) {
                return apiService.getNormalQualityData(params);
            }
        });
    }
    /**
     * 提交普通质检的数据
     *
     * @param params
     * @param observer
     */
    public void setNormalQualityData(final Map<String, Object> params, Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.setNormalQualityData(params);
            }
        });
    }
    /**
     * 质检确认
     *
     * @param params
     * @param observer
     */
    public void submitFinish(final Map<String, Object> params, Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.submitFinish(params);
            }
        });
    }
}
