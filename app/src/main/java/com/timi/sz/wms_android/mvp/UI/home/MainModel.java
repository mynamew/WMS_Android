package com.timi.sz.wms_android.mvp.UI.home;

import com.timi.sz.wms_android.bean.TestBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;

/**
 * main  model
 */

public class MainModel extends MvpBaseModel {

    public void getMainMsg(final int pno, final int ps, final String dtype, Observer<TestBean> subscriber) {
        HttpManager.getInstance().HttpManagerRequest(subscriber, TestBean.class, new ApiServiceMethodCallBack() {
            @Override
            public Observable<ResponseBody> createObservable(ApiService apiService) {
                return apiService.getDatas(pno, ps, dtype);
            }
        });
    }
}
