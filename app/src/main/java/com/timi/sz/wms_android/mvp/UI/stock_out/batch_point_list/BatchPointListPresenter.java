package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.RequestGetMaterialLotBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-19 15:04
 */

public class BatchPointListPresenter extends MvpBasePresenter<BatchPointListView> {
    private BatchPointListModel model;

    public BatchPointListPresenter(Context context) {
        super(context);
        model = new BatchPointListModel();
    }

    HttpSubscriber<Object> submitMakeOrAuditBillHttpSubscriber = null;
    private HttpSubscriber<GetMaterialLotData> getMaterialLotDataHttpSubscriber;


    /**
     * 提交制单
     *
     * @param params
     */
    public void submitMakeOrAuditBill(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitMakeOrAuditBillHttpSubscriber) {
            submitMakeOrAuditBillHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object bean) {
                    getView().submitMakeOrAuditBill();
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitMakeOrAuditBill(params, submitMakeOrAuditBillHttpSubscriber);
    }

    /**
     * 获取批次信息
     *
     * @param params
     */
    public void getMaterialLotData(RequestGetMaterialLotBean params) {
        if (null == getMaterialLotDataHttpSubscriber) {
            getMaterialLotDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetMaterialLotData>() {
                @Override
                public void onSuccess(GetMaterialLotData bean) {
                    getView().getMaterialLotData(bean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getMaterialLotData(params, getMaterialLotDataHttpSubscriber);
    }


    @Override
    public void dettachView() {
        super.dettachView();
        if (null != submitMakeOrAuditBillHttpSubscriber) {
            submitMakeOrAuditBillHttpSubscriber.unSubscribe();
            submitMakeOrAuditBillHttpSubscriber = null;
        }
        if (null != getMaterialLotDataHttpSubscriber) {
            getMaterialLotDataHttpSubscriber.unSubscribe();
            getMaterialLotDataHttpSubscriber = null;
        }
    }
}
