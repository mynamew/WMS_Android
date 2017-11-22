package com.timi.sz.wms_android.mvp.UI.stock_out.detail.outsource_bill_detail;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.detail.BillMaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetWWDetailPickDataResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-21 01:30
 */

public class OutsourceBillDetaiPresenter extends MvpBasePresenter<OutsourceBillDetaiView> {
    private OutsourceBillDetaiModel model;
    private HttpSubscriber<BillMaterialDetailResult> getWWDetailPickDataResultHttpSubscriber;
    public OutsourceBillDetaiPresenter(Context context) {
        super(context);
        model = new OutsourceBillDetaiModel();
    }
    /**
     * 获取明细的数据
     */
    public void getDetailData(Map<String, Object> params, int intentCode) {
        getView().showProgressDialog();
        if (null == getWWDetailPickDataResultHttpSubscriber) {
            getWWDetailPickDataResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BillMaterialDetailResult>() {
                @Override
                public void onSuccess(BillMaterialDetailResult o) {
                    getView().getDetailData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getDetailData(params,intentCode, getWWDetailPickDataResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if(null!=getWWDetailPickDataResultHttpSubscriber){
            getWWDetailPickDataResultHttpSubscriber.unSubscribe();
            getWWDetailPickDataResultHttpSubscriber=null;
        }
    }
}
