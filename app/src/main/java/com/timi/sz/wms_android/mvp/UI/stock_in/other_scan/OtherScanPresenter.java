package com.timi.sz.wms_android.mvp.UI.stock_in.other_scan;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-31 16:52
 */

public class OtherScanPresenter extends MvpBasePresenter<OtherScanView> {
    private OtherScanModel model;
    private HttpSubscriber<MaterialScanPutAwayBean> subscriber = null;
    private HttpSubscriber<VertifyLocationCodeBean> vertifyLocationCodeBeanHttpSubscriber = null;
    private HttpSubscriber<Object> createInStockOrdernoBeanHttpSubscriber = null;
    public OtherScanPresenter(Context context) {
        super(context);
        model = new OtherScanModel();
    }
    /**
     * 扫物料码并上架的方法
     *
     * @param params
     */
    public void materialScanNetWork(Map<String,Object> params) {
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialScanPutAwayBean>() {
                @Override
                public void onSuccess(MaterialScanPutAwayBean materialBean) {
                    getView().materialScanResult(materialBean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.materialScanPutAawy(params, subscriber);
    }

    /**
     * 验证库位码 是否有效
     *
     * @param params
     */
    public void vertifyLocationCode(Map<String,Object> params) {
        if (null == vertifyLocationCodeBeanHttpSubscriber) {
            vertifyLocationCodeBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<VertifyLocationCodeBean>() {
                @Override
                public void onSuccess(VertifyLocationCodeBean bean) {
                    getView().vertifyLocationCode(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().vertifyLocationCode(new VertifyLocationCodeBean(System.currentTimeMillis() % 2 == 0));

                }
            });
        }
        model.vertifyLocationCode(params, vertifyLocationCodeBeanHttpSubscriber);
    }

    /**
     * 生成入库单
     *
     * @param params
     */
    public void createInSockOrderno(final Map<String,Object> params) {
        if (null == createInStockOrdernoBeanHttpSubscriber) {
            createInStockOrdernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object bean) {
                    getView().createInStockOrderno();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.createInStockOrderno(params, createInStockOrdernoBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
        if (null != vertifyLocationCodeBeanHttpSubscriber) {
            vertifyLocationCodeBeanHttpSubscriber.unSubscribe();
            vertifyLocationCodeBeanHttpSubscriber = null;
        }
        if (null != createInStockOrdernoBeanHttpSubscriber) {
            createInStockOrdernoBeanHttpSubscriber.unSubscribe();
            createInStockOrdernoBeanHttpSubscriber = null;
        }
    }
}
