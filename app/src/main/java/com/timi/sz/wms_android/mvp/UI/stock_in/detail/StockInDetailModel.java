package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.bean.instock.OrderDetailData;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.GetOutSourceReturnDetailResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc  单据详情的返回数据
 * author: timi
 * create at: 2017-08-28 16:03
 */

public class StockInDetailModel extends MvpBaseModel {
    /**
     * 获取 单据详情的数据
     *
     * @param params
     * @param intentCode
     * @param observer
     */
    public void getReceiptDetail(final Map<String, Object> params, final int intentCode, Observer<List<OrderDetailData>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<OrderDetailData>>() {
            @Override
            public Observable<CommonResult<List<OrderDetailData>>> createObservable(ApiService apiService) {
                switch (intentCode) {
                    case Constants.BUY_ORDE_NUM://采购单
                    case Constants.BUY_SEND_NUM://送货单
                    case Constants.COME_MATERAIL_NUM://来料单

                        return apiService.getReceiptDetail(params);

                    case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他 选单

                        return apiService.getReceiptDetail(params);
                    case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）

                        return apiService.getReceiptDetail(params);
                    case Constants.OUT_RETURN_MATERAIL://委外退料

                        return apiService.getOutSourceReturnDetail(params);
                    case Constants.CREATE_RETURN_MATERAIL://生产退料

                        return apiService.getPrdReturnDetail(params);
                    case Constants.SALE_RETURN_MATERAIL://销售 退料

                        return apiService.getSalesReturnDetail(params);
                    case Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购 退料

                        return apiService.getPurReturnDetail(params);

                    default:

                        return apiService.getReceiptDetail(params);
                }
            }
        });

    }
}
