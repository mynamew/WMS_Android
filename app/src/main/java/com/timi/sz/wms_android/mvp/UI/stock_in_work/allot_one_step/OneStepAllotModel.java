package com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_one_step;

import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
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
 * create at: 2017-12-01 15:03
 */

public class OneStepAllotModel extends MvpBaseModel {
    /**
     * 验证库位码是否有效
     * @param params
     * @param observer
     */
    public void vertifyLocationCode(final Map<String,Object> params,
                                    final Observer<VertifyLocationCodeBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<VertifyLocationCodeBean>() {
                    @Override
                    public Observable<CommonResult<VertifyLocationCodeBean>>
                    createObservable(ApiService apiService) {
                        return apiService.vertifyLocationCode(params);
                    }
                });
    }
    /**
     * 一部调出  提交
     * @param params
     * @param observer
     */
    public void submitTransferOneStep(final Map<String,Object> params,
                                      final Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<Object>() {
                    @Override
                    public Observable<CommonResult<Object>>
                    createObservable(ApiService apiService) {
                        return apiService.submitTransferOneStep(params);
                    }
                });
    }
}
