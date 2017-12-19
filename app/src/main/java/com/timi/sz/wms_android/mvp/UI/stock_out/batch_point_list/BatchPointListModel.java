package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list;

import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
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
 * create at: 2017-11-19 15:04
 */

public class BatchPointListModel extends MvpBaseModel {
    /**
     * 提交审核
     * @param observer
     */
    public  void submitMakeOrAuditBill(final Map<String,Object> params, Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.submitMakeOrAuditBill(params);
            }
        });
    }
    /**
     * 获取批次 拣货的信息
     *
     * @param observer
     */
    public void getMaterialLotData(final RequestGetMaterialLotBean params, Observer<GetMaterialLotData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<GetMaterialLotData>() {
            @Override
            public Observable<CommonResult<GetMaterialLotData>> createObservable(ApiService apiService) {
                return apiService.getMaterialLotData(params);
            }
        });
    }
}
