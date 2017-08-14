package com.timi.sz.wms_android.http.subscriber;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 自定义的观察者
  * @param <T>
 */
public class HttpSubscriber<T> implements Observer<T> {
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
        //统一处理
        Logger.e("打印异常-->"+e.toString());
        mOnResultListener.onError(-1,e.toString());
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
