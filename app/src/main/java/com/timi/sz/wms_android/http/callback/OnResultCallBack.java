package com.timi.sz.wms_android.http.callback;

/**
 * 网络请求的返回
 * author: timi
 * create at: 2017-08-15 11:26
 */
public interface OnResultCallBack<T> {
    //成功的返回
    void onSuccess(T t);
    //失败的返回
    void onError(String errorMsg);
}
