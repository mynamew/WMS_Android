package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 08:31
 */

public class FormChangeOutstockPresenter extends MvpBasePresenter<FormChangeOutstockView> {
    private FormChangeOutstockModel model;
    private HttpSubscriber<MaterialScanPutAwayBean> subscriber = null;

    public FormChangeOutstockPresenter(Context context) {
        super(context);
        model = new FormChangeOutstockModel();
    }

    /**
     * 转出查询
     *
     * @param params
     */
    public void materialScanPutAawy(Map<String, Object> params) {
        if (null == subscriber) {
            subscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialScanPutAwayBean>() {
                @Override
                public void onSuccess(MaterialScanPutAwayBean o) {
                    getView().materialScanPutAawy(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.materialScanPutAawy(params, subscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null == subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
    }
}
