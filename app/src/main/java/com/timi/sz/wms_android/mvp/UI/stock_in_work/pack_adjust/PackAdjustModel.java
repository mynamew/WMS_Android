package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust;

import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.ContainerAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.bean.stockin_work.lib_adjust.StockAdjustResult;
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
 * create at: 2017-09-22 10:23
 */

public class PackAdjustModel extends MvpBaseModel {


    /**
     * 容器调整
     *
     * @param params
     * @param observer
     */
    public void containnerAdjust(final Map<String, Object> params, Observer<ContainerAdjustResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<ContainerAdjustResult>() {
            @Override
            public Observable<CommonResult<ContainerAdjustResult>> createObservable(ApiService apiService) {
                return apiService.containnerAdjust(params);
            }
        });
    }

}
