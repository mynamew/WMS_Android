package com.timi.sz.wms_android.http.api;


import com.timi.sz.wms_android.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * retrofit 的网络请求api
 * author: timi
 * create at: 2017-08-15 09:58
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("index.asmx/CheckLogin")
    Observable<CommonResult<LoginBean>> login(@Field("usercode") String usercode, @Field("password") String password);
    //下载更新
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String   fileUrl);
}
