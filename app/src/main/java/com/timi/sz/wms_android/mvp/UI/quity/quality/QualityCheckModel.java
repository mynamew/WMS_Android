package com.timi.sz.wms_android.mvp.UI.quity.quality;

import com.timi.sz.wms_android.bean.quality.QualityListBean;
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
 * create at: 2017-09-06 17:06
 */

public class QualityCheckModel extends MvpBaseModel {
    /**
     *  获取质检列表
      * @param params
     * @param observer
     */
    public void getQualityList(final Map<String, Object> params, Observer<List<QualityListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QualityListBean>>() {
            @Override
            public Observable<CommonResult<List<QualityListBean>>> createObservable(ApiService apiService) {
                return apiService.getQualityList(params);
            }
        });
    }
    /**
     *  条件查询获取质检列表
      * @param params
     * @param observer
     */
    public void queryReceiptForIQC(final Map<String, Object> params, Observer<List<QualityListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QualityListBean>>() {
            @Override
            public Observable<CommonResult<List<QualityListBean>>> createObservable(ApiService apiService) {
                return apiService.queryReceiptForIQC(params);
            }
        });
    }
    /**
     *  免检的请求
      * @param params
     * @param observer
     */
    public void submitExemption(final Map<String, Object> params, Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.submitExemption(params);
            }
        });
    }
}
