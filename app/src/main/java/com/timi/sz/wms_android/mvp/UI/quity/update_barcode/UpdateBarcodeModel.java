package com.timi.sz.wms_android.mvp.UI.quity.update_barcode;

import com.timi.sz.wms_android.bean.quality.update_barcode.BarEditGetUnInstockBarcodeData;
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
 * create at: 2017-10-13 08:50
 */

public class UpdateBarcodeModel extends MvpBaseModel {

    /**
     * 获取未入库的条码的信息
     *
     * @param params
     * @param observer
     */
    public void barEditGetUnInstockBarcodeData(final Map<String, Object> params, Observer<BarEditGetUnInstockBarcodeData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BarEditGetUnInstockBarcodeData>() {
            @Override
            public Observable<CommonResult<BarEditGetUnInstockBarcodeData>> createObservable(ApiService apiService) {
                return apiService.barEditGetUnInstockBarcodeData(params);
            }
        });
    }
    /**
     * 保存未入库的条码的信息
     *
     * @param params
     * @param observer
     */
    public void barEditSetUnInstockBarcodeData(final Map<String, Object> params, Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.barEditSetBarcodeQty(params);
            }
        });
    }


}
