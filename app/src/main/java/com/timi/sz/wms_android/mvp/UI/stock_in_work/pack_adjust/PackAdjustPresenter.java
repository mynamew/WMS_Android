package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust;

import android.content.Context;

import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust.LibraryAdjustModel;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.lib_adjust.LibraryAdjustView;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc 容器调整的presenter
 * author: timi
 * create at: 2017-09-22 10:23
 */

public class PackAdjustPresenter extends MvpBasePresenter<PackAdjustView> {
    private PackAdjustModel model;
    private HttpSubscriber<ScanLocationResult> scanLocationResultHttpSubscriber = null;
    private HttpSubscriber<ScanMaterialResult> scanMaterialResultHttpSubscriber = null;
    private HttpSubscriber<LibraryAdjustResult> libraryAdjustResultHttpSubscriber = null;

    public PackAdjustPresenter(Context context) {
        super(context);
        model = new PackAdjustModel();
    }

    /**
     * 扫描库位码
     * @param params
     */
    public void scanLibLocationCode(Map<String, Object> params) {
        if (null == scanLocationResultHttpSubscriber) {
            scanLocationResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<ScanLocationResult>() {
                @Override
                public void onSuccess(ScanLocationResult o) {

                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.scanLibLocatonCode(params, scanLocationResultHttpSubscriber);
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
        if (null != scanLocationResultHttpSubscriber) {
            scanLocationResultHttpSubscriber.unSubscribe();
            scanLocationResultHttpSubscriber = null;
        }
        if (null != libraryAdjustResultHttpSubscriber) {
            libraryAdjustResultHttpSubscriber.unSubscribe();
            libraryAdjustResultHttpSubscriber = null;
        }
    }
}
