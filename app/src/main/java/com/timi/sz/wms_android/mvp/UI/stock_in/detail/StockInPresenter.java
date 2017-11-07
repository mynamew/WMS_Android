package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.OrderDetailData;
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
     */
    public void getReceiptDetail(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == orderDetailDataHttpSubscriber) {
            orderDetailDataHttpSubscriber = new HttpSubscriber<List<OrderDetailData>>(new OnResultCallBack<List<OrderDetailData>>() {
                @Override
                public void onSuccess(List<OrderDetailData> orderDetailDatas) {
                    getView().getReceiptDetail(orderDetailDatas);
//                    List<OrderDetailData> datas=new ArrayList<>();
//                    for (int i = 0; i < 100; i++) {
//                        OrderDetailData orderDetailData = new OrderDetailData();
//                        orderDetailData.setReceiptLine(i);
//                        orderDetailData.setScanQty(100);
//                        orderDetailData.setInStockQty(100);
//                        orderDetailData.setMaterialAttribute("撒大大苏打撒旦");
//                        orderDetailData.setMaterialCode("CT234643234532");
//                        orderDetailData.setMaterialName("2让非官方推广人分的");
//                        orderDetailData.setMaterialStandard("士大夫士大夫随风倒");
//                        orderDetailData.setPassQty(200);
//                        orderDetailData.setQty(100);
//                        orderDetailData.setWaitQty(i*10);
//                        datas.add(orderDetailData);
//                    }
//                    getView().getReceiptDetail(datas);

                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
//                    List<OrderDetailData> datas=new ArrayList<>();
//                    for (int i = 0; i < 100; i++) {
//                        OrderDetailData orderDetailData = new OrderDetailData();
//                        orderDetailData.setReceiptLine(i);
//                        orderDetailData.setScanQty(100);
//                        orderDetailData.setInStockQty(100);
//                        orderDetailData.setMaterialAttribute("撒大大苏打撒旦");
//                        orderDetailData.setMaterialCode("CT234643234532");
//                        orderDetailData.setMaterialName("2让非官方推广人分的");
//                        orderDetailData.setMaterialStandard("士大夫士大夫随风倒");
//                        orderDetailData.setPassQty(200);
//                        orderDetailData.setQty(100);
//                        orderDetailData.setWaitQty(i*10);
//                        datas.add(orderDetailData);
//                    }
//                    getView().getReceiptDetail(datas);

                }
            });
        }
        model.getReceiptDetail(params, orderDetailDataHttpSubscriber);
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
