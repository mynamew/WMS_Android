package com.timi.sz.wms_android.http.callback;

/**
 * 网络请求  回调接口
 * @param <T>
 */
public interface OnResultCallBack<T> {

    void onSuccess(T t);

    void onError(int code, String errorMsg);
}
