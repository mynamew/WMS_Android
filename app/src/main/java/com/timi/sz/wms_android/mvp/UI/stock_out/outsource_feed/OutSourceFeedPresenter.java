package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_feed;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit.OursourcingAuditGoodsListModel;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-09 10:29
 */

public class OutSourceFeedPresenter extends MvpBasePresenter<OutSourceFeedView> {
    private OutSourceFeedModel model;

    public OutSourceFeedPresenter(Context context) {
        super(context);
        model = new OutSourceFeedModel();
    }
    HttpSubscriber<Object> submitMakeOrAuditBillHttpSubscriber = null;
    /**
     * 提交制单
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
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitMakeOrAuditBill(params, submitMakeOrAuditBillHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != submitMakeOrAuditBillHttpSubscriber) {
            submitMakeOrAuditBillHttpSubscriber.unSubscribe();
            submitMakeOrAuditBillHttpSubscriber = null;
        }
    }
}
