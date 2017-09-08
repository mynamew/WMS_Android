package com.timi.sz.wms_android.mvp.UI.quity.quality;

import com.timi.sz.wms_android.bean.quality.QualityListBean;
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
 * create at: 2017-09-06 17:06
 */

public class QualityCheckModel extends MvpBaseModel {
    /**
     *  获取质检列表
      * @param params
     * @param observer
     */
    public void getQualityList(final Map<String, Object> params, Observer<QualityListBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QualityListBean>() {
            @Override
            public Observable<CommonResult<QualityListBean>> createObservable(ApiService apiService) {
                return apiService.getQualityList(params);
            }
        });
    }
}
