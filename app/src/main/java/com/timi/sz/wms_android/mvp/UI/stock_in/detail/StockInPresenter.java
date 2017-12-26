package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.OrderDetailData;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.GetOutSourceReturnDetailResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 16:03
 */

public class StockInPresenter extends MvpBasePresenter<StockInDetailView> {
    StockInDetailModel model = null;
    private HttpSubscriber<List<OrderDetailData>> orderDetailDataHttpSubscriber;

    public StockInPresenter(Context context) {
        super(context);
        model = new StockInDetailModel();
    }

    /**
     * 获取单据详情
     *
     * @param params
     * @param intentCode
     */
    public void getReceiptDetail(Map<String, Object> params, int intentCode) {
        getView().showProgressDialog();
        if (null == orderDetailDataHttpSubscriber) {
            orderDetailDataHttpSubscriber = new HttpSubscriber<List<OrderDetailData>>(new OnResultCallBack<List<OrderDetailData>>() {
                @Override
                public void onSuccess(List<OrderDetailData> orderDetailDatas) {
                    getView().getReceiptDetail(orderDetailDatas);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getReceiptDetail(params,intentCode, orderDetailDataHttpSubscriber);
    }



    @Override
    public void dettachView() {
        super.dettachView();
        if (null != orderDetailDataHttpSubscriber) {
            orderDetailDataHttpSubscriber.unSubscribe();
            orderDetailDataHttpSubscriber = null;
        }

    }
}
