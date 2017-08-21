package com.timi.sz.wms_android.http.callback;


import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * api setvice 的回调
 * author: timi
 * create at: 2017-08-15 10:56
 */
public interface ApiServiceMethodCallBack<T> {
    Observable<CommonResult<T>> createObservable(ApiService apiService);
}
