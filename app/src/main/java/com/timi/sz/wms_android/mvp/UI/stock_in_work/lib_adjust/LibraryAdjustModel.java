package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust;

import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
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

public class LibraryAdjustModel extends MvpBaseModel {
    /**
     * 验证库位码是否有效
     *
     * @param params
     * @param observer
     */
    public void vertifyLocationCode(final Map<String, Object> params, Observer<VertifyLocationCodeBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<VertifyLocationCodeBean>() {
            @Override
            public Observable<CommonResult<VertifyLocationCodeBean>> createObservable(ApiService apiService) {
                return apiService.vertifyLocationCode(params);
            }
        });
    }

    /**
     * 扫描库位码
     *
     * @param params
     * @param observer
     */
    public void scanMaterialCode(final Map<String, Object> params, Observer<StockAdjustResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<StockAdjustResult>() {
            @Override
            public Observable<CommonResult<StockAdjustResult>> createObservable(ApiService apiService) {
                return apiService.scanMaterialCode(params);
            }
        });
    }

    /**
     * 库位调整的请求方法
     *
     * @param params
     * @param observer
     */
    public void libraryAdjustResult(final Map<String, Object> params, Observer<LibraryAdjustResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<LibraryAdjustResult>() {
            @Override
            public Observable<CommonResult<LibraryAdjustResult>> createObservable(ApiService apiService) {
                return apiService.libraryAdjustResult(params);
            }
        });
    }

}
