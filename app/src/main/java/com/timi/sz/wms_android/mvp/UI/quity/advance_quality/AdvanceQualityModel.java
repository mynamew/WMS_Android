package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
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
 * create at: 2017-10-12 14:54
 */

public class AdvanceQualityModel extends MvpBaseModel {
    /**
     * 获取高级质检1 的数据
     * @param params
     * @param observer
     */
    public void getAdvance2Data(final Map<String, Object> params, Observer<GetAdvance2Data> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<GetAdvance2Data>() {
            @Override
            public Observable<CommonResult<GetAdvance2Data>> createObservable(ApiService apiService) {
                return apiService.getAdvance2Data(params);
            }
        });
    }
}
