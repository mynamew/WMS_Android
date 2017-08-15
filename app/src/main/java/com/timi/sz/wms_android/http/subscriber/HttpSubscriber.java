package com.timi.sz.wms_android.http.subscriber;

import com.google.gson.stream.MalformedJsonException;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;


public class HttpSubscriber<T> implements Observer<T> {
    public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    public static final String MALFORMED_JSON_EXCEPTION = "数据解析错误";

    private OnResultCallBack mOnResultListener;
    private Disposable mDisposable;

    public HttpSubscriber(OnResultCallBack listener) {
        this.mOnResultListener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        if (mOnResultListener != null) {
            mOnResultListener.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        //请求失败的异常
        if (e instanceof CompositeException) {
            CompositeException compositeE = (CompositeException) e;
            for (Throwable throwable : compositeE.getExceptions()) {
                //超时 异常
                if (throwable instanceof SocketTimeoutException) {
                    mOnResultListener.onError(SOCKET_TIMEOUT_EXCEPTION);
                }
                //连接异常
                else if (throwable instanceof ConnectException) {
                    mOnResultListener.onError(CONNECT_EXCEPTION);
                }
                //连接异常 位置的host
                else if (throwable instanceof UnknownHostException) {
                    mOnResultListener.onError(CONNECT_EXCEPTION);
                }
                //json  解析异常
                else if (throwable instanceof MalformedJsonException) {
                    mOnResultListener.onError(MALFORMED_JSON_EXCEPTION);
                }
            }
        } else {//其他异常
        }
    }

    @Override
    public void onComplete() {

    }

    public void unSubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
