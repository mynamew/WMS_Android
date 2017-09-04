package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import android.content.Context;

import com.timi.sz.wms_android.bean.outstock.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.MaterialBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 08:43
 */

public class ScanReturnMaterialPresenter extends MvpBasePresenter<ScanReturnMaterialView> {
    ScanReturnMaterialMdel model = null;
    HttpSubscriber<MaterialBean> subscriber = null;
    HttpSubscriber<CommitMaterialScanToOredernoBean> commitMaterialScanToOredernoBeanHttpSubscriber;

    public ScanReturnMaterialPresenter(Context context) {
        super(context);
        model = new ScanReturnMaterialMdel();
    }

    /**
     * 物料扫码的请求
     *
     * @param materialCode
     */
    public void materialScan(String materialCode) {
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialBean>() {
                @Override
                public void onSuccess(MaterialBean bean) {
                    getView().materialScan(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().materialScan(new MaterialBean("滑轨双孔梁496-蓝色", "CD7000101", "Slide Beam0824-496铝挤压加工", "50"));
                }
            });
        }
        model.materialScan(materialCode, subscriber);
    }

    /**
     * 物料扫码的请求
     *
     * @param materialCode
     */
    public void commitMaterialScanToOrederno(String materialCode) {
        if (null == commitMaterialScanToOredernoBeanHttpSubscriber) {
            commitMaterialScanToOredernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<CommitMaterialScanToOredernoBean>() {
                @Override
                public void onSuccess(CommitMaterialScanToOredernoBean bean) {
                    getView().commitMaterialScanToOrederno(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().commitMaterialScanToOrederno(new CommitMaterialScanToOredernoBean(true));
                }
            });
        }
        model.commitMaterialScanToOrederno(materialCode, commitMaterialScanToOredernoBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
        if (null != commitMaterialScanToOredernoBeanHttpSubscriber) {
            commitMaterialScanToOredernoBeanHttpSubscriber.unSubscribe();
            commitMaterialScanToOredernoBeanHttpSubscriber = null;
        }
    }
}
