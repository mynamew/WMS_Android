package com.timi.sz.wms_android.http;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.http.uitls.Xml2JsonUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
         * 初始化拦截器
         */
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.d("网络请求----->", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * 初始化 okhttp client
         */
        OkHttpClient mClient = new OkHttpClient.Builder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request mRequest = chain.request();
//                        Logger.d("网络请求----->", mRequest.toString());
//                        okhttp3.Response mProceed = chain.proceed(mRequest);
//                        return mProceed;
//                    }
//                })
                .addInterceptor(httpLoggingInterceptor)
                .build();
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
                //okhttp client
                .client(mClient)
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
    private <T> void toSubscribe(Observable<ResponseBody> o, Observer<T> s, final Class<T> clazz)throws Exception {
        o.subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(@NonNull ResponseBody response) throws Exception {
                        //转换 ResponseBody 成 包装的对象
                        T t = new Gson().fromJson(response.string(), clazz);
                        return t;
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
    /**
     * 来自webservice的网络请求注册
     *
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscribeByWebService(Observable<ResponseBody> o, Observer<T> s, final Class<T> clazz)throws Exception {
        o.subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(@NonNull ResponseBody response) throws Exception {
                        //转换 ResponseBody 成 包装的对象
                        T t = new Xml2JsonUtils<T>().toJson(response, clazz);
                        return t;
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
     * @param clazz      类
     * @param callBack   回调
     * @param <T>
     */
    public <T> void HttpManagerRequest(Observer<T> subscriber, Class<T> clazz, ApiServiceMethodCallBack callBack) {
        try {
            toSubscribe(callBack.createObservable(mApiService), subscriber, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 公共的外部调用请求的方法  （来自webservice）
     *
     * @param subscriber 观察者
     * @param clazz      类
     * @param callBack   回调
     * @param <T>
     */
    public <T> void HttpManagerRequestByWebservice(Observer<T> subscriber, Class<T> clazz, ApiServiceMethodCallBack callBack) {
        try {
            toSubscribeByWebService(callBack.createObservable(mApiService), subscriber, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}