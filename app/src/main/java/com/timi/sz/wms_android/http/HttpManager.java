package com.timi.sz.wms_android.http;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.timi.sz.wms_android.base.uils.Xml2JsonUtils;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.TestBean;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.http.api.ApiResponse;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.exception.ApiException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

public class HttpManager<T> {
    public static final String TAG = HttpManager.class.getSimpleName();
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private ApiService mApiService;
    private static Context mContext;
    private volatile static HttpManager instance;
    /**************************************************************************************************************/
    /**
     * 初始化
     */
    private HttpManager() {
        //设置拦截器
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("HttpManager", message);
            }
        });
        loggingInterceptor.setLevel(level);
        //拦截请求和响应日志并输出，其实有很多封装好的日志拦截插件，大家也可以根据个人喜好选择。
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor);

        OkHttpClient okHttpClient = builder.build();
        //初始化retrofit
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build();

        //获得 apiservice
        mApiService = mRetrofit.create(ApiService.class);
    }
    /**************************************************************************************************************/
    /**
     * 单例
     *
     * @return
     */
    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    /**
     * 保存 上下文
     *
     * @param context
     */
    public static void init(Context context) {
        mContext = context;
    }
    /**************************************************************************************************************/
    /**p
     * 普通的网络请求注册
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscribe(Observable<ApiResponse<T>> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .map(new Function<ApiResponse<T>, T>() {
                    @Override
                    public T apply(@NonNull ApiResponse<T> response) throws Exception {
                        int code = response.getCode();
                        if (code != Constants.SUCCESS_CODE) {
                            throw new ApiException(code, response.getMsg());
                        } else {
                            return response.getDatas();
                        }
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 测试普通的api接口的方法
     *
     * @param subscriber
     * @param pno
     * @param ps
     * @param dtype
     */
    public void getDatasUser(Observer<TestBean> subscriber, int pno, int ps, String dtype) {
        Map<String, Object> params = new HashMap<>();
        params.put("pno", pno);
        params.put("ps", ps);
        params.put("dtype", dtype);
        toSubscribe(mApiService.getUserList(params), subscriber);
    }
    /**************************************************************************************************************/
    /**
     * 为 webservice服务的注册观察者的方法
     *
     * @param o
     * @param s
     * @param clazz
     * @param <T>
     */
    private <T> void toSubscribe1(Observable<ResponseBody> o, Observer<T> s, final Class<T> clazz) {
        o.subscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(@NonNull ResponseBody response) throws Exception {
                        T t = new Xml2JsonUtils<T>().toJson(response, clazz);
                        return t;
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
    /**************************************************************************************************************/
    /**
     * 登录的返回
     *
     * @param usercode
     * @param password
     * @param subscriber
     */
    public void postLoginResult(String usercode, String password, Observer<LoginBean> subscriber) {
        toSubscribe1(mApiService.login(usercode, password), subscriber, LoginBean.class);
    }
    /**************************************************************************************************************/

}
