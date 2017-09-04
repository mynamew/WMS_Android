package com.timi.sz.wms_android.mvp.UI.stock_in.other_scan;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.instock.CreateInStockOrdernoBean;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-31 16:52
 */

public class OtherScanPresenter extends MvpBasePresenter<OtherScanView> {
    private OtherScanModel model;
    private HttpSubscriber<MaterialScanPutAwayBean> subscriber = null;
    private HttpSubscriber<VertifyLocationCodeBean> vertifyLocationCodeBeanHttpSubscriber = null;
    private HttpSubscriber<CreateInStockOrdernoBean> createInStockOrdernoBeanHttpSubscriber = null;
    public OtherScanPresenter(Context context) {
        super(context);
        model = new OtherScanModel();
    }
    /**
     * 扫物料码并上架的方法
     *
     * @param locationCode
     * @param materialCode
     */
    public void materialScanNetWork(final String locationCode, final String materialCode) {
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialScanPutAwayBean>() {
                @Override
                public void onSuccess(MaterialScanPutAwayBean materialBean) {
                    getView().materialScanResult(materialBean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().materialScanResult(new MaterialScanPutAwayBean("滑轨双孔梁496-蓝色", "CD7000101", "Slide Beam0824-496铝挤压加工", "50"));

                }
            });
        }
        model.materialScanPutAawy(locationCode, materialCode, SpUtils.getInstance().getUserId(), subscriber);
    }

    /**
     * 验证库位码 是否有效
     *
     * @param locationCode
     */
    public void vertifyLocationCode(String locationCode) {
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
        model.vertifyLocationCode(locationCode, vertifyLocationCodeBeanHttpSubscriber);
    }

    /**
     * 生成入库单
     *
     * @param locationCode
     */
    public void createInSockOrderno(String locationCode) {
        if (null == createInStockOrdernoBeanHttpSubscriber) {
            createInStockOrdernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<CreateInStockOrdernoBean>() {
                @Override
                public void onSuccess(CreateInStockOrdernoBean bean) {
                    getView().createInStockOrderno(bean.isSuccess);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().createInStockOrderno(false);
                }
            });
        }
        model.createInStockOrderno(locationCode, createInStockOrdernoBeanHttpSubscriber);
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
