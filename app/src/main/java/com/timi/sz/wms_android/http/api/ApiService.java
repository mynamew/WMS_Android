package com.timi.sz.wms_android.http.api;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * retrofit 的网络请求api
 * author: timi
 * create at: 2017-08-15 09:58
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("query?key=7c2d1da3b8634a2b9fe8848c3a9edcba")
    Observable<ResponseBody> getDatas(@Field("pno") int pno, @Field("ps") int ps, @Field("dtype") String dtype);
    @FormUrlEncoded
    @POST("CheckLogin")
    Observable<ResponseBody> login(@Field("usercode") String usercode, @Field("password") String password);
}
