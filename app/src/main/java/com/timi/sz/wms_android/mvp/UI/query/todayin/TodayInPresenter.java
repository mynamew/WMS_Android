package com.timi.sz.wms_android.mvp.UI.query.todayin;

import android.content.Context;

import com.timi.sz.wms_android.bean.query.request.RequestBean;
import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
import com.timi.sz.wms_android.bean.query.response.TodayInStockDetailResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-25 11:01
 */

public class TodayInPresenter extends MvpBasePresenter<TodayInView> {
    private TodayInModel model;
    private HttpSubscriber<StockSummeryResult> stockSummeryResultHttpSubscriber;
    private HttpSubscriber<List<TodayInStockDetailResult>> todayInStockDetailResultHttpSubscriber;

    public TodayInPresenter(Context context) {
        super(context);
        model = new TodayInModel();
    }

    /**
     * 获取今日入库总揽
     *
     * @param params
     */
    public void getTodayInSummeryTotal(RequestBean params) {
        getView().showProgressDialog();
        if (null == stockSummeryResultHttpSubscriber) {
            stockSummeryResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<StockSummeryResult>() {
                @Override
                public void onSuccess(StockSummeryResult o) {
                    getView().getTodayInSummeryTotal(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getTodayInSummeryTotal(params, stockSummeryResultHttpSubscriber);
    }

    /**
     * 获取今日入库明细
     *
     * @param params
     */
    public void getTodayInSummeryDetail(RequestBean params) {
        getView().showProgressDialog();
        if (null == todayInStockDetailResultHttpSubscriber) {
            todayInStockDetailResultHttpSubscriber = new HttpSubscriber<List<TodayInStockDetailResult>>(new OnResultCallBack<List<TodayInStockDetailResult>>() {
                @Override
                public void onSuccess(List<TodayInStockDetailResult> o) {
                    getView().getTodayInSummeryDetail(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.getTodayInSummeryDetail(params, todayInStockDetailResultHttpSubscriber);
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
