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
import com.timi.sz.wms_android.http.exception.ApiException;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.mvp.base.BaseApplication;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.MyProgressDialog;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import okhttp3.ResponseBody;

import static com.timi.sz.wms_android.http.exception.ApiException.CODE_REQUEST_SUCCESS_EXCEPTION;


public class HttpSubscriber<T> implements Observer<T> {
    private boolean isAutoDismiss = true;//是否自动隐藏加载框

    public static final String CONNECT_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_network_exception);
    public static final String SOCKET_TIMEOUT_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_network_timeout);
    public static final String MALFORMED_JSON_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_json_error);
    public static final String SERVER_TIMEOUT_EXCEPTION = BaseApplication.getMApplicationContext().getString(R.string.exp_server_timeout);

    private OnResultCallBack mOnResultListener;
    private Disposable mDisposable;

    public HttpSubscriber(OnResultCallBack listener) {
        this.mOnResultListener = listener;
    }

    public HttpSubscriber(boolean isAutoDismiss, OnResultCallBack listener) {
        this.isAutoDismiss = isAutoDismiss;
        this.mOnResultListener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        if (isAutoDismiss) {
            MyProgressDialog.hideProgressDialog();
        }
        if (mOnResultListener != null) {
            mOnResultListener.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (isAutoDismiss) {
            MyProgressDialog.hideProgressDialog();
        }
        String messageStr = "";//提示信息
        //请求失败的异常
        if (e instanceof CompositeException) {


            CompositeException compositeE = (CompositeException) e;
            for (Throwable throwable : compositeE.getExceptions()) {
                //超时 异常
                if (throwable instanceof SocketTimeoutException) {
                    mOnResultListener.onError(SOCKET_TIMEOUT_EXCEPTION);
                    //设置异常信息
                    messageStr = SOCKET_TIMEOUT_EXCEPTION;
                }
                //连接异常
                else if (throwable instanceof ConnectException) {
                    mOnResultListener.onError(CONNECT_EXCEPTION);
                    //设置异常信息
                    messageStr = CONNECT_EXCEPTION;
                }
                //连接异常 位置的host
                else if (throwable instanceof UnknownHostException) {
                    mOnResultListener.onError(CONNECT_EXCEPTION);
                    //设置异常信息
                    messageStr = CONNECT_EXCEPTION;
                }
                //json  解析异常
                else if (throwable instanceof MalformedJsonException) {
                    mOnResultListener.onError(MALFORMED_JSON_EXCEPTION);
                    //设置异常信息
                    messageStr = MALFORMED_JSON_EXCEPTION;
                }
            }
        } else if (e instanceof HttpException) {//服务器 错误 连接超时
            /**
             * 当 webapi 返回失败的  500 时 进行解析一场
             * 对异常返回数据 进行捕获 拿出message details 信息
             */

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
                /**
                 * 如果返回的tocken 失效 跳转到登录的界面
                 */
                if (httpResultBean.isUnAuthorizedRequest()) {
                    /**
                     * 显示 错误提示的对话框
                     */
                    new MyDialog(BaseActivity.getCurrentActivty(), R.layout.dialog_go_login_tip)
                            .setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            BaseActivity currentActivty = (BaseActivity) BaseActivity.getCurrentActivty();
                            currentActivty.jumpToLoginActivity();
                        }})
                            .setTextViewContent(R.id.tv_content, messageStr)
                            .setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }})
                            .show();
                    //登录提示 要直接return  防止弹出下面的dialog
                    return;
                }
                mOnResultListener.onError(messageStr);
                LogUitls.e("打印输出错误代码httpResultBean---->", httpResultBean.getError().getMessage().toString());
            } catch (Exception e1) {
                mOnResultListener.onError(SERVER_TIMEOUT_EXCEPTION);
                messageStr = SERVER_TIMEOUT_EXCEPTION;
            }

        } else if (e instanceof UnknownHostException) {// 测试到时再没网的情况下
            mOnResultListener.onError(CONNECT_EXCEPTION);
            messageStr = CONNECT_EXCEPTION;
        } else if (e instanceof ApiException) {
            /**
             * 为了解决当服务端返回的数据是空的情况，即map转换时返回值为null抛出异常
             */
            if (e.getMessage().equals(CODE_REQUEST_SUCCESS_EXCEPTION)) {
                mOnResultListener.onSuccess(null);
                return;
            }
            /**
             * 弹出提示 所有的后台返回的提示
             */
            mOnResultListener.onError(e.getMessage());
            messageStr = e.getMessage();
        } else if (e instanceof SocketTimeoutException) {
            mOnResultListener.onError(SOCKET_TIMEOUT_EXCEPTION);
            messageStr = SOCKET_TIMEOUT_EXCEPTION;
        } else if (e instanceof ConnectException) {
            mOnResultListener.onError(SOCKET_TIMEOUT_EXCEPTION);
            messageStr = SOCKET_TIMEOUT_EXCEPTION;
        }
        /**
         * 排除网络请求成功的情况
         */
        if (!messageStr.equals(CODE_REQUEST_SUCCESS_EXCEPTION)) {
            /**
             * 显示 错误提示的对话框
             */
            new MyDialog(BaseActivity.getCurrentActivty(), R.layout.dialog_error_tip).setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            }).setTextViewContent(R.id.tv_content, messageStr).setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                @Override
                public void dialogClick(MyDialog dialog) {
                    dialog.dismiss();
                }
            }).show();
        }
    }

    @Override
    public void onComplete() {
        //进度条消失
        if (isAutoDismiss) {
            MyProgressDialog.hideProgressDialog();
        }
    }

    public void unSubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            //进度条消失
            if (isAutoDismiss) {
                MyProgressDialog.hideProgressDialog();
            }
        }
    }
}
