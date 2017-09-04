package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import android.content.Context;

import com.timi.sz.wms_android.bean.outstock.BuyReturnMaterialOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.OrderNoBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 17:30
 */

public class BuyReturnMaterialPresenter extends MvpBasePresenter<BuyReturnMaterialView> {
    BuyReturnMaterialModel model = null;
    HttpSubscriber<BuyReturnMaterialOrdernoBean> subscriber = null;
    HttpSubscriber<OrderNoBean> returnMaterialScanSubscriber = null;

    public BuyReturnMaterialPresenter(Context context) {
        super(context);
        model = new BuyReturnMaterialModel();


    }

    /**
     * 扫物料码的方法
     *
     * @param scanStr
     */
    public void materialScanNetWork(String scanStr) {
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<BuyReturnMaterialOrdernoBean>() {
                @Override
                public void onSuccess(BuyReturnMaterialOrdernoBean materialBean) {
                    getView().materialScanResult(materialBean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().materialScanResult(new BuyReturnMaterialOrdernoBean("CD20160001", "1017-8-29", "深圳超然科技股份有限公司", "邢立风", 100, 80, new MaterialBean("滑轨双孔梁496-蓝色", "CD7000101", "Slide Beam0824-496铝挤压加工", "50")));

                }
            });
        }
        model.materialScanGetBuyRetrurnOrderNo(scanStr, subscriber);
    }

    /**
     * 退料单号扫码/输入单号的网络请求
     *
     * @param scanStr
     */
    public void returnMaterialScanNetWork(String scanStr) {
        if (null == returnMaterialScanSubscriber) {
            returnMaterialScanSubscriber = new HttpSubscriber<>(new OnResultCallBack<OrderNoBean>() {
                @Override
                public void onSuccess(OrderNoBean materialBean) {
                    getView().ReturnMaterialOrderNoScanResult(materialBean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().ReturnMaterialOrderNoScanResult(new OrderNoBean("滑轨双孔梁496-蓝色", "CD7000101", "2017-8-24", "50", "22", "28"));

                }
            });
        }
        model.returnMaterialOrderNoScanNetWork(scanStr, returnMaterialScanSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        //反注册
        if (null != returnMaterialScanSubscriber) {
            returnMaterialScanSubscriber.unSubscribe();
            returnMaterialScanSubscriber = null;
        }
        if (null != subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
    }
}
