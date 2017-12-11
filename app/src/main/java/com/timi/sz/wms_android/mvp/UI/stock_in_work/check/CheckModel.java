package com.timi.sz.wms_android.mvp.UI.stock_in_work.check;

import android.content.pm.ApplicationInfo;

import com.timi.sz.wms_android.bean.stockin_work.MaterialDataBean;
import com.timi.sz.wms_android.bean.stockin_work.check.RequestSubmitCheckDataBean;
import com.timi.sz.wms_android.bean.stockin_work.check.SubmitCheckDataResult;
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
 * create at: 2017-12-10 14:27
 */

public class CheckModel extends MvpBaseModel {
    /**
     * 获取物料的信息
      * @param params
     * @param observer
     */
    public void getMaterialData(final Map<String, Object> params, Observer<MaterialDataBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<MaterialDataBean>() {
            @Override
            public Observable<CommonResult<MaterialDataBean>> createObservable(ApiService apiService) {
                return apiService.getMaterialData(params);
            }
        });

    }
    /**
     * 获取物料的信息
      * @param params
     * @param observer
     */
    public void submitCheckData(final RequestSubmitCheckDataBean params, Observer<SubmitCheckDataResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SubmitCheckDataResult>() {
            @Override
            public Observable<CommonResult<SubmitCheckDataResult>> createObservable(ApiService apiService) {
                return apiService.submitCheckData(params);
            }
        });

    }
}
