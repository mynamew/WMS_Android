package com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality;

import com.timi.sz.wms_android.bean.quality.RequestUpdateCheckitemBean;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.IQCGetAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.UpdateCheckItemResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-12 14:54
 */

public class AdvanceQualityModel extends MvpBaseModel {
    /**
     * 获取高级质检2 的数据
     *
     * @param params
     * @param observer
     */
    public void getAdvance2Data(final Map<String, Object> params, Observer<GetAdvance2Data> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<GetAdvance2Data>() {
            @Override
            public Observable<CommonResult<GetAdvance2Data>> createObservable(ApiService apiService) {
                return apiService.getAdvance2Data(params);
            }
        });
    }

    /**
     * 设置高级质检2 的数据
     *
     * @param data
     * @param observer
     */
    public void setAdvance2Data(final CommitAdvanceData data , Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.setAdvance2Data(data);
            }
        });
    }
    /**
     * 质检确认
     *
     * @param params
     * @param observer
     */
    public void submitFinish(final Map<String, Object> params, Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.submitFinish(params);
            }
        });
    }
    /**
     * 修改高检二质检项目
     *
     * @param params
     * @param observer
     */
    public void IQCUpdateAdvance2Item(final RequestUpdateCheckitemBean params, Observer<UpdateCheckItemResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<UpdateCheckItemResult>() {
            @Override
            public Observable<CommonResult<UpdateCheckItemResult>> createObservable(ApiService apiService) {
                return apiService.IQCUpdateAdvance2Item(params);
            }
        });
    }
    /**
     * 获取高级质检2 质检项目
     *
     * @param params
     * @param observer
     */
    public void IQCGetAdvanceData(final Map<String, Object> params, Observer<IQCGetAdvanceData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IQCGetAdvanceData>() {
            @Override
            public Observable<CommonResult<IQCGetAdvanceData>> createObservable(ApiService apiService) {
                return apiService.IQCGetAdvanceData(params);
            }
        });
    }
}
