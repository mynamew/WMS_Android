package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.ContainerAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.bean.stockin_work.lib_adjust.StockAdjustResult;
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
    private HttpSubscriber<ContainerAdjustResult> scanMaterialResultHttpSubscriber = null;

    public PackAdjustPresenter(Context context) {
        super(context);
        model = new PackAdjustModel();
    }



    /**
     * 扫描物料码
     * @param params
     */
    public void  containnerAdjust(Map<String, Object> params){
        getView().showProgressDialog();
        if (null == scanMaterialResultHttpSubscriber) {
            scanMaterialResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<ContainerAdjustResult>() {
                @Override
                public void onSuccess(ContainerAdjustResult o) {
                  getView().containnerAdjust(o);
                    getView().setOldPackSelect();
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                    getView().setOldPackSelect();

                }
            });
        }
        model.containnerAdjust(params, scanMaterialResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != scanMaterialResultHttpSubscriber) {
            scanMaterialResultHttpSubscriber.unSubscribe();
            scanMaterialResultHttpSubscriber = null;
        }
    }
}
