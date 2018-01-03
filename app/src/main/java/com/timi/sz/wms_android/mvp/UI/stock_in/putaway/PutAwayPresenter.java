package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 10:57
 */

public class PutAwayPresenter extends MvpBasePresenter<PutAwayView> {
    private PutAwayModel model = null;
    private HttpSubscriber<MaterialScanPutAwayBean> subscriber = null;
    private HttpSubscriber<VertifyLocationCodeBean> vertifyLocationCodeBeanHttpSubscriber = null;
    private HttpSubscriber<Object> createInStockOrdernoBeanHttpSubscriber = null;
    private HttpSubscriber<ReceiveOrdernoBean> receiveOrdernoBeanHttpSubscriber = null;

    public PutAwayPresenter(Context context) {
        super(context);
        model = new PutAwayModel();
    }

    /**
     * 扫物料码并上架的方法
     *
     * @param params
     * @param materialCode
     */
    public void materialScanNetWork(final Map<String, Object> params, final String materialCode) {
        getView().showProgressDialog();
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialScanPutAwayBean>() {
                @Override
                public void onSuccess(MaterialScanPutAwayBean materialBean) {
                    getView().materialScanResult(materialBean);
                    getView().setMaterialEdittextSelect();
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().setMaterialEdittextSelect();
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
    public void vertifyLocationCode(final Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == vertifyLocationCodeBeanHttpSubscriber) {
            vertifyLocationCodeBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<VertifyLocationCodeBean>() {
                @Override
                public void onSuccess(VertifyLocationCodeBean bean) {
                    getView().vertifyLocationCode(bean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().setLocationSelect();
                }
            });
        }
        model.vertifyLocationCode(params, vertifyLocationCodeBeanHttpSubscriber);
    }

    /**
     * 创建入库单
     *
     * @param params
     */
    public void createInSockOrderno(final Map<String, Object> params) {
        if (null == createInStockOrdernoBeanHttpSubscriber) {
            createInStockOrdernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object bean) {
                    getView().createInStockOrderno();
                }

                @Override
                public void onError(String errorMsg) {

//                    ToastUtils.showShort(errorMsg);
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
        if (null != receiveOrdernoBeanHttpSubscriber) {
            receiveOrdernoBeanHttpSubscriber.unSubscribe();
            receiveOrdernoBeanHttpSubscriber = null;
        }
    }
}
