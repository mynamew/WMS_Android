package com.timi.sz.wms_android.mvp.UI.splash;

import com.timi.sz.wms_android.bean.GetPDA_ParameterResult;
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
 * create at: 2017-12-06 10:54
 */

public class SplashModel extends MvpBaseModel {
    /**
     * 获取PDA参数
     * @param params
     * @param observer
     */
    public void  getPDAParams(final Map<String,Object> params, Observer<GetPDA_ParameterResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<GetPDA_ParameterResult>() {
            @Override
            public Observable<CommonResult<GetPDA_ParameterResult>> createObservable(ApiService apiService) {
                return apiService.getPDA_Parameter(params);
            }
        });
    }
}
