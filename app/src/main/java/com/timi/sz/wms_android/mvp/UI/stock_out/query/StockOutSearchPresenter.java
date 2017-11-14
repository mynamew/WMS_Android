package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 08:56
 */

public class StockOutSearchPresenter extends MvpBasePresenter<StockOutSearchView> {
    private StockOutSearchModel model;
    private HttpSubscriber<QueryOutSourceFeedByInputResult> outSourceFeedBeanHttpSubscriber;

    public StockOutSearchPresenter(Context context) {
        super(context);
        model = new StockOutSearchModel();
    }

    /**
     * 委外退料搜索
     */
    public void queryOutSourceFeedByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == outSourceFeedBeanHttpSubscriber) {
            outSourceFeedBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryOutSourceFeedByInputResult>() {
                @Override
                public void onSuccess(QueryOutSourceFeedByInputResult o) {
                    getView().queryOutSourceFeedByInput(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    QueryOutSourceFeedByInputResult queryOutSourceFeedByInputResult = new QueryOutSourceFeedByInputResult();

                    QueryOutSourceFeedByInputResult.SummaryResultsBean summaryResultsBean = new QueryOutSourceFeedByInputResult.SummaryResultsBean();
                    summaryResultsBean.setScanQty(100);
                    summaryResultsBean.setWaitQty(100);
                    summaryResultsBean.setQty(200);
                    summaryResultsBean.setBillCode("DH48765456789876");
                    summaryResultsBean.setBillDate("2017-9-12");
                    summaryResultsBean.setBillId(22);
                    summaryResultsBean.setIsLotPick(true);
                    summaryResultsBean.setIsRegion(false);
                    summaryResultsBean.setCreaterName("2017-8-12");
                    summaryResultsBean.setRegionId(11);
                    summaryResultsBean.setRegionName("电子仓区");
                    summaryResultsBean.setWarehouseId(22);
                    summaryResultsBean.setWarehouseName("撒大苏打的");
                    queryOutSourceFeedByInputResult.setSummaryResults(summaryResultsBean);
                    List<QueryOutSourceFeedByInputResult.DetailResultsBean> datas=new ArrayList<>();
                    for (int i = 0; i <10 ; i++) {
                        QueryOutSourceFeedByInputResult.DetailResultsBean detailResultsBean = new QueryOutSourceFeedByInputResult.DetailResultsBean();
                        detailResultsBean.setMaterialName("说的是");
                        detailResultsBean.setWarehouseId(11);
                        detailResultsBean.setQty(11);
                        detailResultsBean.setLine(i);
                        detailResultsBean.setMaterialCode("CT54239876543");
                        detailResultsBean.setMaterialStandard("实打实打算");
                        detailResultsBean.setScanQty(11);
                        detailResultsBean.setWarehouseName("但是士大夫的");
                        datas.add(detailResultsBean);
                    }
                    queryOutSourceFeedByInputResult.setDetailResults(datas);
                    getView().queryOutSourceFeedByInput(queryOutSourceFeedByInputResult);
                }
            });
        }
        model.queryOutSourceFeedByInput(params, outSourceFeedBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != outSourceFeedBeanHttpSubscriber) {
            outSourceFeedBeanHttpSubscriber.unSubscribe();
            outSourceFeedBeanHttpSubscriber = null;
        }
    }
}
