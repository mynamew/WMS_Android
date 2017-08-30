package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import android.content.Context;

import com.timi.sz.wms_android.bean.buy_return.MaterialBean;
import com.timi.sz.wms_android.bean.buy_return.OrderNoAddMaterial;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 14:44
 */

public class BuyReturnMaterialOrderNoPresenter extends MvpBasePresenter<BuyReturnMaterialOrderNoView> {
    private HttpSubscriber<MaterialBean> subscriber;
    private HttpSubscriber<OrderNoAddMaterial> OrderNoAddMaterialSubscriber;
    BuyReturnMaterialOrderNoModel model=null;
    public BuyReturnMaterialOrderNoPresenter(Context context) {
        super(context);
        model=new BuyReturnMaterialOrderNoModel();
        subscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialBean>() {
            @Override
            public void onSuccess(MaterialBean materialBean) {
                getView().materialScanResult(materialBean);
            }

            @Override
            public void onError(String errorMsg) {
                getView().materialScanResult( new MaterialBean("滑轨双孔梁496-蓝色","CD7000101","Slide Beam0824-496铝挤压加工","50"));

            }
        });
        OrderNoAddMaterialSubscriber = new HttpSubscriber<>(new OnResultCallBack<OrderNoAddMaterial>() {
            @Override
            public void onSuccess(OrderNoAddMaterial materialBean) {
                getView().orderNoAddMaterial();
            }

            @Override
            public void onError(String errorMsg) {
                getView().orderNoAddMaterial();

            }
        });
    }

    /**
     * 扫物料码的方法
     * @param scanStr
     */
    public void materialScanNetWork(String scanStr) {
        model.materialScanNetWork(scanStr, subscriber);
    }/**
     * 退料单添加物料的方法
     * @param materialCode
     */
    public void orderNoAddmaterialNetWork(String materialCode) {
        model.returnMaterialCommitResultNetWork(materialCode, OrderNoAddMaterialSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        //反注册
        if (null != OrderNoAddMaterialSubscriber) {
            OrderNoAddMaterialSubscriber.unSubscribe();
            OrderNoAddMaterialSubscriber = null;
        }
        if (null != subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
    }
}
