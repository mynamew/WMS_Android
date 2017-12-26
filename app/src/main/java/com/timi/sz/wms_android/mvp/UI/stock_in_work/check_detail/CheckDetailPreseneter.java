package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_detail;

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
 * create at: 2017-12-25 15:05
 */

public class CheckDetailPreseneter extends MvpBasePresenter<CheckDetailView> {
    private CheckDetailModel model;
    private HttpSubscriber<List<StockInWorkDetailResult>> httpSubscriber;

    public CheckDetailPreseneter(Context context) {
        super(context);
        model = new CheckDetailModel();
    }

    /**
     * 获取盘点的明细
     *
     * @param params
     */
    public void getCheckStockDetail(Map<String, Object> params) {
        if (null == httpSubscriber) {
            httpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<StockInWorkDetailResult>>() {
                @Override
                public void onSuccess(List<StockInWorkDetailResult> datas) {
                    getView().getCheckStockDetail(datas);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getCheckStockDetail(params, httpSubscriber);
    }
    @Override
    public void dettachView() {
        super.dettachView();
        if(null!=httpSubscriber){
            httpSubscriber.unSubscribe();
            httpSubscriber=null;
        }
    }
}
