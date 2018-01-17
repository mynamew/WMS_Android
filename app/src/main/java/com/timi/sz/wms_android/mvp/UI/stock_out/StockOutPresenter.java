package com.timi.sz.wms_android.mvp.UI.stock_out;

import android.content.Context;

import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-16 09:24
 */

public class StockOutPresenter extends MvpBasePresenter<StockOutView> {
    private StockOutModel model;
    HttpSubscriber<BuyReturnMaterialByMaterialCodeData> buyReturnMaterialByMaterialCodeDataHttpSubscriber;
    public StockOutPresenter(Context context) {
        super(context);
        model=new StockOutModel();
    }
    /**
     * 扫物料码的方法
     *
     * @param params
     */
    public void buyReturnMaterialByMaterialCodeData( Map<String,Object> params) {
        getView().showProgressDialog();
        if (null == buyReturnMaterialByMaterialCodeDataHttpSubscriber) {
            buyReturnMaterialByMaterialCodeDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyReturnMaterialByMaterialCodeData>() {
                @Override
                public void onSuccess(BuyReturnMaterialByMaterialCodeData materialBean) {
                    getView().buyReturnMaterialByMaterialCodeData(materialBean);
                }

                @Override
                public void onError(String errorMsg) {
//                     ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.buyReturnMaterialByMaterialCodeData(params, buyReturnMaterialByMaterialCodeDataHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if(null!=buyReturnMaterialByMaterialCodeDataHttpSubscriber){
            buyReturnMaterialByMaterialCodeDataHttpSubscriber.unSubscribe();
            buyReturnMaterialByMaterialCodeDataHttpSubscriber=null;
        }
    }
}
