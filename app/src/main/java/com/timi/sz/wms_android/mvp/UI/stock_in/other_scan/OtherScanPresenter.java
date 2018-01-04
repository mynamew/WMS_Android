package com.timi.sz.wms_android.mvp.UI.stock_in.other_scan;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.GetMakeOtherStockTotalResult;
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
    private HttpSubscriber<GetMakeOtherStockTotalResult> getMakeOtherStockTotalResultHttpSubscriber = null;

    public OtherScanPresenter(Context context) {
        super(context);
        model = new OtherScanModel();
    }

    /**
     * 扫物料码并上架的方法
     *
     * @param params
     */
    public void materialScanNetWork(Map<String, Object> params) {
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
    public void vertifyLocationCode(Map<String, Object> params) {
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
     * 生成入库单
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

    /**
     * 获得其他入库单统计数据（制单）
     *
     * @param params
     */
    public void getMakeOtherStockTotal(final Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getMakeOtherStockTotalResultHttpSubscriber) {
            getMakeOtherStockTotalResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetMakeOtherStockTotalResult>() {
                @Override
                public void onSuccess(GetMakeOtherStockTotalResult bean) {
                    getView().getMakeOtherStockTotal(bean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getMakeOtherStockTotal(params, getMakeOtherStockTotalResultHttpSubscriber);
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
        if (null != getMakeOtherStockTotalResultHttpSubscriber) {
            getMakeOtherStockTotalResultHttpSubscriber.unSubscribe();
            getMakeOtherStockTotalResultHttpSubscriber = null;
        }
    }
}
