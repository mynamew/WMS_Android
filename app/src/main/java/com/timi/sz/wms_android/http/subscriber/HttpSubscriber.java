package com.timi.sz.wms_android.http.subscriber;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.HttpResultBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.mvp.base.BaseApplication;
import com.timi.sz.wms_android.view.MyProgressDialog;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import okhttp3.ResponseBody;


public class HttpSubscriber<T> implements Observer<T> {
    public static final String CONNECT_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_network_exception);
    public static final String SOCKET_TIMEOUT_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_network_timeout);
    public static final String MALFORMED_JSON_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_json_error);
    public static final String SERVER_TIMEOUT_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_server_timeout);

    private OnResultCallBack mOnResultListener;
    private Disposable mDisposable;

    public HttpSubscriber(OnResultCallBack listener) {
        this.mOnResultListener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        MyProgressDialog.showProgressDialog(BaseActivity.getCurrentActivty());
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        MyProgressDialog.hideProgressDialog();
        if (mOnResultListener != null) {
            mOnResultListener.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        MyProgressDialog.hideProgressDialog();
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
        } else if (e instanceof HttpException) {//服务器 错误 连接超时
            /**
             * 当 webapi 返回失败的  500 时 进行解析一场
             * 对异常返回数据 进行捕获 拿出message details 信息
             */
            String messageStr = "";//提示信息
            ResponseBody responseBody = ((HttpException) e).response().errorBody();
            try {
                assert responseBody != null;
                HttpResultBean httpResultBean = new Gson().fromJson(responseBody.string(), HttpResultBean.class);
                if (null == httpResultBean) {//为空则返回
                    mOnResultListener.onError(SERVER_TIMEOUT_EXCEPTION);
                    return;
                }
                if (!TextUtils.isEmpty(httpResultBean.getError().getMessage())) {
                    messageStr = httpResultBean.getError().getMessage();
                }
                if (!TextUtils.isEmpty(httpResultBean.getError().getDetails())) {
                    messageStr = httpResultBean.getError().getDetails();
                }
                ToastUtils.showShort(messageStr);
                mOnResultListener.onError(messageStr);
                LogUitls.e("打印输出错误代码httpResultBean---->", httpResultBean.toString());
                return;
            } catch (Exception e1) {
                mOnResultListener.onError(SERVER_TIMEOUT_EXCEPTION);
            }

        } else if (e instanceof UnknownHostException) {// 测试到时再没网的情况下
            mOnResultListener.onError(CONNECT_EXCEPTION);
        } else {//
            /**
             * 弹出提示 所有的后台返回的提示
             */
            mOnResultListener.onError(e.getMessage());
        }

    }

    @Override
    public void onComplete() {
        MyProgressDialog.hideProgressDialog();
    }

    public void unSubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            MyProgressDialog.hideProgressDialog();
        }
    }
}
