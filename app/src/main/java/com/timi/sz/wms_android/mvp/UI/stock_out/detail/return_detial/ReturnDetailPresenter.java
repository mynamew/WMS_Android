package com.timi.sz.wms_android.mvp.UI.stock_out.detail.return_detial;

import android.content.Context;

import com.timi.sz.wms_android.bean.outstock.detail.MaterialDetailResult;
import com.timi.sz.wms_android.bean.stockin_work.ReturnDetailResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-15 15:12
 */

public class ReturnDetailPresenter extends MvpBasePresenter<ReturnDetailView> {
    private ReturnDetailModel  model;
    private HttpSubscriber<List<ReturnDetailResult>> getOutSourcePickDetailHttpSubscriber;

    public ReturnDetailPresenter(Context context) {
        super(context);
        model=new ReturnDetailModel();
    }
    /**
     * 委外审核获取详情
     */
    public void getMakePurReturnScannedDetail(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getOutSourcePickDetailHttpSubscriber) {
            getOutSourcePickDetailHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<ReturnDetailResult>>() {
                @Override
                public void onSuccess(List<ReturnDetailResult> o) {
                    getView().getMakePurReturnScannedDetail(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getMakePurReturnScannedDetail(params, getOutSourcePickDetailHttpSubscriber);
    }

}
