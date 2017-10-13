package com.timi.sz.wms_android.mvp.UI.stock_in_work.query;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeInResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.bean.stockin_work.query.StockQueryResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-22 13:18
 */

public class StockInWorkQueryPresenter extends MvpBasePresenter<StockInWorkQueryView> {
    private HttpSubscriber<AllotScanResult> allotScanResultHttpSubscriber = null;
    private HttpSubscriber<AllotOneSetpResult> allotOneSetpResultHttpSubscriber = null;
    private HttpSubscriber<FormChangeInResult> formChangeInResultHttpSubscriber = null;
    private HttpSubscriber<FormChangeOutResult> formChangeOutResultHttpSubscriber = null;
    private HttpSubscriber<StockQueryResult> stockQueryResultHttpSubscriber = null;
    private HttpSubscriber<PointResult> pointResultHttpSubscriber = null;

    private StockInWorkQueryModel model;

    public StockInWorkQueryPresenter(Context context) {
        super(context);
        model = new StockInWorkQueryModel();
    }

    /**
     * 查询-扫描调入
     * @param params
     */
    public void queryAllotScan(Map<String, Object> params) {
         getView().showProgressDialog();
        if (null == allotScanResultHttpSubscriber) {
            allotScanResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<AllotScanResult>() {
                @Override
                public void onSuccess(AllotScanResult o) {
                    getView().queryAllotScan(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().queryAllotScan(new AllotScanResult());
                }
            });
        }
        model.queryAllotScan(params, allotScanResultHttpSubscriber);
    }

    /**
     * 查询-扫描调入
     * @param params
     */
    public void queryAllotOneStep(Map<String, Object> params) {

        if (null == allotOneSetpResultHttpSubscriber) {
            allotOneSetpResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<AllotOneSetpResult>() {
                @Override
                public void onSuccess(AllotOneSetpResult o) {
                    getView().queryAllotOneStep(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryAllotOneStep(params, allotOneSetpResultHttpSubscriber);
    }
    /**
     * 查询-形态转换 出库
     * @param params
     */
    public void queryFormChangeOut(Map<String, Object> params) {

        if (null == formChangeOutResultHttpSubscriber) {
            formChangeOutResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<FormChangeOutResult>() {
                @Override
                public void onSuccess(FormChangeOutResult o) {
                    getView().queryFormChangeOut(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryFormChangeOut(params, formChangeOutResultHttpSubscriber);
    }
    /**
     * 查询-形态转换 入库
     * @param params
     */
    public void queryFormChangeIn(Map<String, Object> params) {

        if (null == formChangeInResultHttpSubscriber) {
            formChangeInResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<FormChangeInResult>() {
                @Override
                public void onSuccess(FormChangeInResult o) {
                    getView().queryFormChangeIn(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryFormChangeIn(params, formChangeInResultHttpSubscriber);
    }
    /**
     * 查询-盘点
     * @param params
     */
    public void queryPoint(Map<String, Object> params) {

        if (null == pointResultHttpSubscriber) {
            pointResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<PointResult>() {
                @Override
                public void onSuccess(PointResult o) {
                    getView().queryPoint(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryPoint(params, pointResultHttpSubscriber);
    }
    @Override
    public void dettachView() {
        super.dettachView();
        if (null != allotScanResultHttpSubscriber) {
            allotScanResultHttpSubscriber.unSubscribe();
            allotScanResultHttpSubscriber = null;
        }
        if (null != allotOneSetpResultHttpSubscriber) {
            allotOneSetpResultHttpSubscriber.unSubscribe();
            allotOneSetpResultHttpSubscriber = null;
        }
        if (null != formChangeInResultHttpSubscriber) {
            formChangeInResultHttpSubscriber.unSubscribe();
            formChangeInResultHttpSubscriber = null;
        }
        if (null != formChangeOutResultHttpSubscriber) {
            formChangeOutResultHttpSubscriber.unSubscribe();
            formChangeOutResultHttpSubscriber = null;
        }
        if (null != stockQueryResultHttpSubscriber) {
            stockQueryResultHttpSubscriber.unSubscribe();
            stockQueryResultHttpSubscriber = null;
        }
        if (null != pointResultHttpSubscriber) {
            pointResultHttpSubscriber.unSubscribe();
            pointResultHttpSubscriber = null;
        }
    }
}
