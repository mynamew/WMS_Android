package com.timi.sz.wms_android.http.api;


import com.timi.sz.wms_android.bean.TestBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * api service
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("query?key=7c2d1da3b8634a2b9fe8848c3a9edcba")
    Observable<ApiResponse<TestBean>> getDatas(@Field("pno") int pno, @Field("ps") int ps, @Field("dtype") String dtype);

    @POST("query?key=7c2d1da3b8634a2b9fe8848c3a9edcba")
    Observable<ApiResponse<TestBean>> getUserList( @QueryMap Map<String,Object> params);
//    @FormUrlEncoded
//    @POST("CheckLogin")
//    Call<ResponseBody> login(@Field("usercode") String usercode,@Field("password")String password);
    @FormUrlEncoded
    @POST("CheckLogin")
    Observable<ResponseBody> login(@Field("usercode") String usercode,@Field("password")String password);
}
