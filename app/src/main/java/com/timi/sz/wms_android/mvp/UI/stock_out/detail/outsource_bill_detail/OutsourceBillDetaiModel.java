package com.timi.sz.wms_android.mvp.UI.stock_out.detail.outsource_bill_detail;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.bean.outstock.detail.BillMaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetWWDetailPickDataResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-21 01:29
 */

public class OutsourceBillDetaiModel extends MvpBaseModel {
    /**
     * 委外发料(明旭) 搜索请求
     *
     * @param intentCode
     * @param observer
     */
    public void getDetailData(final Map<String, Object> params, final int intentCode, Observer<BillMaterialDetailResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BillMaterialDetailResult>() {
            @Override
            public Observable<CommonResult<BillMaterialDetailResult>> createObservable(ApiService apiService) {
                switch (intentCode) {
                    /**
                     * 委外  生单/调拨
                     */
                    case STOCK_OUT_OUTSOURCE_BILL:
                    case STOCK_OUT_OUTSOURCE_ALLOT:

                        return apiService.getWWDetailPickData(params);
                    /**
                     * 生产 生单/调拨
                     */
                    case Constants.STOCK_OUT_PRODUCTION_BILL:
                    case  Constants.STOCK_OUT_PRODUCTION_ALLOT:

                        return apiService.getPrdDetailPickData(params);

                    /**
                     * 默认
                     */
                    default:
                        return apiService.getPrdDetailPickData(params);
                }


            }
        });
    }
}
