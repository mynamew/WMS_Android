package com.timi.sz.wms_android.mvp.UI.quity.reject;

import com.timi.sz.wms_android.bean.quality.BarcodeData;
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
 * create at: 2017-10-13 08:50
 */

public class QualityRejectModel extends MvpBaseModel {

    /**
     * 获取质检拒收条码的信息
     *
     * @param params
     * @param observer
     */
    public void getBarcodeData(final Map<String, Object> params, Observer<BarcodeData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BarcodeData>() {
            @Override
            public Observable<CommonResult<BarcodeData>> createObservable(ApiService apiService) {
                return apiService.getBarcodeData(params);
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
