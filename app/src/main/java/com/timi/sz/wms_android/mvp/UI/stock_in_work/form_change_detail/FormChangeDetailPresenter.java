package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail;

import android.content.Context;

import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.FormChangeDetailResult;
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
    private HttpSubscriber<List<FormChangeDetailResult>> httpSubscriberIn;
    private HttpSubscriber<List<FormChangeDetailResult>> httpSubscriberOut;
    private BaseRecyclerAdapter<FormChangeDetailResult> adapter;

    public FormChangeDetailPresenter(Context context) {
        super(context);
        model = new FormChangeDetailModel();
    }

    /**
     * 形态转换 出库  明细
     *
     * @param params
     */
    public void getConvertOutDetail(Map<String, Object> params) {
        if (null == httpSubscriberOut) {

            httpSubscriberOut = new HttpSubscriber<>(new OnResultCallBack<List<FormChangeDetailResult>>() {
                @Override
                public void onSuccess(List<FormChangeDetailResult> datas) {
                    getView().getConvertOutDetail(datas);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 形态转换 出库  明细
     *
     * @param params
     */
    public void getConvertInDetail(Map<String, Object> params) {
        if (null == httpSubscriberOut) {

            httpSubscriberOut = new HttpSubscriber<>(new OnResultCallBack<List<FormChangeDetailResult>>() {
                @Override
                public void onSuccess(List<FormChangeDetailResult> datas) {
                    getView().getStockInWorkDetail(datas);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });

        }
        model.getConvertInDetail(params, httpSubscriberOut);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != httpSubscriberIn) {
            httpSubscriberIn.unSubscribe();
            httpSubscriberIn = null;
        }
        if (null != httpSubscriberOut) {
            httpSubscriberOut.unSubscribe();
            httpSubscriberOut = null;
        }
    }
}
