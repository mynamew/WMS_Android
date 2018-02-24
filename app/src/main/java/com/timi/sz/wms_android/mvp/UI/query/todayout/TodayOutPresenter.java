package com.timi.sz.wms_android.mvp.UI.query.todayout;

import android.content.Context;

import com.timi.sz.wms_android.bean.query.request.RequestBean;
import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
import com.timi.sz.wms_android.bean.query.response.TodayInStockDetailResult;
import com.timi.sz.wms_android.bean.query.response.TodayOutStockDetailResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-25 14:07
 */

public class TodayOutPresenter extends MvpBasePresenter<TodayOutView> {
    private TodayOutModel model;
    private HttpSubscriber<StockSummeryResult> stockSummeryResultHttpSubscriber;
    private HttpSubscriber<List<TodayOutStockDetailResult>> todayInStockDetailResultHttpSubscriber;
    public TodayOutPresenter(Context context) {
        super(context);
        model=new TodayOutModel();
    }
    /**
     * 获取今日入出总揽
     *
     * @param params
     */
    public void getTodayOutSummeryTotal(RequestBean params) {
        getView().showProgressDialog();
        if (null == stockSummeryResultHttpSubscriber) {
            stockSummeryResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<StockSummeryResult>() {
                @Override
                public void onSuccess(StockSummeryResult o) {
                    getView().getTodayOutSumeryTotal(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getTodayOutSummeryTotal(params, stockSummeryResultHttpSubscriber);
    }

    /**
     * 获取今日入库明细
     *
     * @param params
     */
    public void getTodayOutSummeryDetail(RequestBean params) {
        getView().showProgressDialog();
        if (null == todayInStockDetailResultHttpSubscriber) {
            todayInStockDetailResultHttpSubscriber = new HttpSubscriber<List<TodayOutStockDetailResult>>(new OnResultCallBack<List<TodayOutStockDetailResult>>() {
                @Override
                public void onSuccess(List<TodayOutStockDetailResult> o) {
                    getView().getTodayOutSummeryDetail(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getTodayOutSummeryDetail(params, todayInStockDetailResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != todayInStockDetailResultHttpSubscriber) {
            todayInStockDetailResultHttpSubscriber.unSubscribe();
            todayInStockDetailResultHttpSubscriber = null;
        }
        if (null != stockSummeryResultHttpSubscriber) {
            stockSummeryResultHttpSubscriber.unSubscribe();
            stockSummeryResultHttpSubscriber = null;
        }
    }
}
