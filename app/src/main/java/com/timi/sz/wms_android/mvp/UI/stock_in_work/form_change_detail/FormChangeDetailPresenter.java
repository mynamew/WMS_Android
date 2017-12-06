package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 09:48
 */

public class FormChangeDetailPresenter extends MvpBasePresenter<FormChangeDetailView> {
    private FormChangeDetailModel model;
    private HttpSubscriber<List<StockInWorkDetailResult>> httpSubscriber;

    public FormChangeDetailPresenter(Context context) {
        super(context);
        model = new FormChangeDetailModel();
    }


    @Override
    public void dettachView() {
        super.dettachView();
        if (null != httpSubscriber) {
            httpSubscriber.unSubscribe();
            httpSubscriber = null;
        }
    }
}
