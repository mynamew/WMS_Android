package com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-22 10:23
 */

public class LibraryAdjustPresenter extends MvpBasePresenter<LibraryAdjustView> {
    private LibraryAdjustModel model;
    private HttpSubscriber<VertifyLocationCodeBean> vertifyLocationCodeBeanHttpSubscriber = null;
    private HttpSubscriber<ScanMaterialResult> scanMaterialResultHttpSubscriber = null;
    private HttpSubscriber<LibraryAdjustResult> libraryAdjustResultHttpSubscriber = null;

    public LibraryAdjustPresenter(Context context) {
        super(context);
        model = new LibraryAdjustModel();
    }

    /**
     * 验证目的库位码是否有效
     * @param params
     */
    public void vertifyLocationCode(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == vertifyLocationCodeBeanHttpSubscriber) {
            vertifyLocationCodeBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<VertifyLocationCodeBean>() {
                @Override
                public void onSuccess(VertifyLocationCodeBean o) {
                   getView().vertifyLocationCode(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.vertifyLocationCode(params, vertifyLocationCodeBeanHttpSubscriber);
    }

    /**
     * 扫描物料码
     * @param params
     */
    public void  scanMaterialCode(Map<String, Object> params){
        if (null == scanMaterialResultHttpSubscriber) {
            scanMaterialResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<ScanMaterialResult>() {
                @Override
                public void onSuccess(ScanMaterialResult o) {

                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.scanMaterialCode(params, scanMaterialResultHttpSubscriber);
    }
    /**
     * 扫描物料码
     * @param params
     */
    public void  libraryAdjustResult(Map<String, Object> params){
        if (null == libraryAdjustResultHttpSubscriber) {
            libraryAdjustResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<LibraryAdjustResult>() {
                @Override
                public void onSuccess(LibraryAdjustResult o) {

                }
                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.libraryAdjustResult(params, libraryAdjustResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != scanMaterialResultHttpSubscriber) {
            scanMaterialResultHttpSubscriber.unSubscribe();
            scanMaterialResultHttpSubscriber = null;
        }
        if (null != vertifyLocationCodeBeanHttpSubscriber) {
            vertifyLocationCodeBeanHttpSubscriber.unSubscribe();
            vertifyLocationCodeBeanHttpSubscriber = null;
        }
        if (null != libraryAdjustResultHttpSubscriber) {
            libraryAdjustResultHttpSubscriber.unSubscribe();
            libraryAdjustResultHttpSubscriber = null;
        }
    }
}
