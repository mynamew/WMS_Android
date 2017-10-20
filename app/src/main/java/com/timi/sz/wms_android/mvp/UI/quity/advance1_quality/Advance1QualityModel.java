package com.timi.sz.wms_android.mvp.UI.quity.advance1_quality;

import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
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
 * create at: 2017-10-18 16:57
 */

public class Advance1QualityModel extends MvpBaseModel {
    /**
     * 获取高级质检1 的数据
      * @param params
     * @param observer
     */
    public void getAdvacen1Data(final Map<String, Object> params, Observer<GetAdvanceData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<GetAdvanceData>() {
            @Override
            public Observable<CommonResult<GetAdvanceData>> createObservable(ApiService apiService) {
                return apiService.getAdvanceData(params);
            }
        });
    }
}
