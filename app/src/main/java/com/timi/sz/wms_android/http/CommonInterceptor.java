package com.timi.sz.wms_android.http;

/**
 * author: timi
 * create at: 2017-08-21 16:17
 */

import android.support.v4.os.IResultReceiver;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.SpUtils;

import java.io.IOException;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 封装公共参数（Key和密码）
 * <p>
 */
public class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        URL url = authorizedUrlBuilder.build().url();
        Request newRequest=null;
        /**
         * 拦截器  登录的请求的时候不进行header 的设置
         */
        if (!url.toString().contains("ClientLogin")) {
            // 普通请求
             newRequest = oldRequest.newBuilder().addHeader(Constants.AUTHORIZATION,SpUtils.getInstance().getAuthorization())
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .build();
        }else{
            // 登录请求
            newRequest = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .build();
        }
        LogUitls.e("处理后的url-->"+newRequest.url().toString());
        return chain.proceed(newRequest);
    }
}
