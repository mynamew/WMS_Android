package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_record;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.CheckRecordResult;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-11 16:11
 */

public class CheckRecordPresenter extends MvpBasePresenter<CheckRecordView> {
    private CheckRecordModel model;
    private HttpSubscriber<List<CheckRecordResult>> httpSubscriber;

    public CheckRecordPresenter(Context context) {
        super(context);
        model = new CheckRecordModel();
    }
    /**
     * 获取盘点的明细
     *
     * @param params
     */
    public void getCheckStockRecord(Map<String, Object> params) {
        if (null == httpSubscriber) {
            httpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<CheckRecordResult>>() {
                @Override
                public void onSuccess(List<CheckRecordResult> datas) {
                    getView().getCheckStockPageRecord(datas);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getCheckStockPageRecord(params, httpSubscriber);
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
