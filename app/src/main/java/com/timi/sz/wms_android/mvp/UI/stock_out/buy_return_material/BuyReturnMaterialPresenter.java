package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 17:30
 */

public class BuyReturnMaterialPresenter extends MvpBasePresenter<BuyReturnMaterialView> {
    BuyReturnMaterialModel model = null;
    HttpSubscriber<BuyReturnMaterialByMaterialCodeData> subscriber = null;
    HttpSubscriber<BuyReturnMaterialByOrdernoData> returnMaterialScanSubscriber = null;

    public BuyReturnMaterialPresenter(Context context) {
        super(context);
        model = new BuyReturnMaterialModel();


    }

    /**
     * 扫物料码的方法
     *
     * @param params
     */
    public void materialScanNetWork( Map<String,Object> params) {
        getView().showProgressDialog();
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<BuyReturnMaterialByMaterialCodeData>() {
                @Override
                public void onSuccess(BuyReturnMaterialByMaterialCodeData materialBean) {
                    getView().materialScanResult(materialBean);
                }

                @Override
                public void onError(String errorMsg) {
//                     ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.materialScanGetBuyRetrurnOrderNo(params, subscriber);
    }

    /**
     * 退料单号扫码/输入单号的网络请求
     *
     * @param params
     */
    public void returnMaterialScanNetWork(Map<String,Object> params) {
        getView().showProgressDialog();
        if (null == returnMaterialScanSubscriber) {
            returnMaterialScanSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyReturnMaterialByOrdernoData>() {
                @Override
                public void onSuccess(BuyReturnMaterialByOrdernoData materialBean) {
                    getView().ReturnMaterialOrderNoScanResult(materialBean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.returnMaterialOrderNoScanNetWork(params, returnMaterialScanSubscriber);
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
