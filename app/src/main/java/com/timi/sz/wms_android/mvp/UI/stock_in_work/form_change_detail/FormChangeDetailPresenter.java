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
    private HttpSubscriber<List<FormChangeDetailResult>> httpSubscriberCheck;

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
        getView().showProgressDialog();
        if (null == httpSubscriberOut) {

            httpSubscriberOut = new HttpSubscriber<>(new OnResultCallBack<List<FormChangeDetailResult>>() {
                @Override
                public void onSuccess(List<FormChangeDetailResult> datas) {
                    getView().getConvertOutDetail(datas);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getConvertOutDetail(params, httpSubscriberOut);
    }

    /**
     * 形态转换 出库  明细
     *
     * @param params
     */
    public void getConvertInDetail(Map<String, Object> params) {
        getView().showProgressDialog();
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

    /**
     * 形态转换 出库  明细
     *
     * @param params
     */
    public void getCheckStockDetail(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == httpSubscriberCheck) {

            httpSubscriberCheck = new HttpSubscriber<>(new OnResultCallBack<List<FormChangeDetailResult>>() {
                @Override
                public void onSuccess(List<FormChangeDetailResult> datas) {
                    getView().getCheckStockDetail(datas);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });

        }
        model.getCheckStockDetail(params, httpSubscriberCheck);
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
        if (null != httpSubscriberCheck) {
            httpSubscriberCheck.unSubscribe();
            httpSubscriberCheck = null;
        }
    }
}
