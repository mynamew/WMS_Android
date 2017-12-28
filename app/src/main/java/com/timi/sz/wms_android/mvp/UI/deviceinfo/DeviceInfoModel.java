package com.timi.sz.wms_android.mvp.UI.deviceinfo;

import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * author: timi
 * create at: 2017-08-24 16:35
 */
public class DeviceInfoModel extends MvpBaseModel {
    /**
     * 设置PDA编号
     * author: timi
     * create at: 2017/8/15 14:26
     */
    public void setPDACode(final Map<String, Object> params, final Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.setPDACode(params);

            }
        });
    }
}
