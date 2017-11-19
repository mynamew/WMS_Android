package com.timi.sz.wms_android.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.SDCardUtils;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.http.exception.ApiException;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.mvp.base.BaseApplication;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.timi.sz.wms_android.http.exception.ApiException.CODE_REQUEST_SUCCESS_EXCEPTION;

/**
 * 用于网络请求的管理类$
 * author: timi
 * create at: 2017-08-15 09:53
 */
public class HttpManager {
    //实例
    private static volatile HttpManager instancce = null;
    //retrofit
    private Retrofit mRetrofit = null;
    //api service
    private ApiService mApiService = null;

    /**
     * 获取实例的方法
     * author: timi
     * create at: 2017/8/15 10:05
     *
     * @return 返回当前实例
     */
    public static HttpManager getInstance() {
        if (null == instancce) {
            synchronized (HttpManager.class) {
                if (null == instancce) {
                    instancce = new HttpManager();
                }
            }
        }
        return instancce;
    }

    /**
     * 实例化 instance
     * author: timi
     * create at: 2017/8/15 10:06
     */
    public HttpManager() {
        /**
         * 初始化 okhttp
         */
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new CommonInterceptor()).build();
        /**
         * 初始化 retrofit
         */
        mRetrofit = new Retrofit.Builder()
                //base url
                .baseUrl(Constants.BASE_URL)
                //gosn 转换器
                .addConverterFactory(GsonConverterFactory.create())
                //rxjava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //OkHttpClient
                .client(okHttpClient)
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    /**
     * 普通的网络请求注册
     *
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscribe(Observable<CommonResult<T>> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .map(new Function<CommonResult<T>, T>() {
                    @Override
                    public T apply(@NonNull CommonResult<T> t)   {
                        LogUitls.e("返回结果--->", t.toString());
                        if (t.isSuccess()) {//请求成功的返回
//                            if (t.isUnAuthorizedRequest()) {

                                if(null== t.getResult()){
                                    throw new ApiException(CODE_REQUEST_SUCCESS_EXCEPTION);
                                }
                                return t.getResult();
//                            } else {
//                                /**
//                                 * 如果返回的tocken 失效
//                                 */
////                                BaseActivity currentActivty = (BaseActivity) BaseActivity.getCurrentActivty();
////                                currentActivty.jumpToLoginActivity();
//                                return_material t.getResult();
//                            }
                        } else {
                            throw new ApiException(t.getError().getMessage());
                        }
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 公共的外部调用请求的方法
     *
     * @param subscriber 观察者
     * @param callBack   回调
     * @param <T>
     */
    public <T> void HttpManagerRequest(Observer<T> subscriber, ApiServiceMethodCallBack<T> callBack) {
        try {
            toSubscribe(callBack.createObservable(mApiService), subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载的方法
     */
    public void downLoadAPkRequest(Observer<File> subscriber, String url) {
        LogUitls.e("存储APK的路径---->", SDCardUtils.getAPKPath(BaseApplication.getMApplicationContext()));
        Observable<ResponseBody> responseBodyObservable = mApiService.downloadFile(url);
        responseBodyObservable.subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody reponse) throws Exception {
                        return SDCardUtils.saveFile(reponse, SDCardUtils.getAPKPath(BaseApplication.getMApplicationContext()), Constants.APK_NAME);
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}