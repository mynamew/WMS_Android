package com.timi.sz.wms_android.mvp.UI.stock_in_work.detail;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.UI.stock_in.detail.StockInDetailModel;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-04 15:17
 */

public class StockInWorkDetailPresenter extends MvpBasePresenter<StockInWorkDetailView> {
    private HttpSubscriber<List<StockInWorkDetailResult>> httpSubscriber;
    private StockInWorkDetailModel model;
    public StockInWorkDetailPresenter(Context context) {
        super(context);
        model=new StockInWorkDetailModel();
    }

    /**
     * 扫描调入
     * @param params
     */
    public void queryAllotScanDetail(Map<String,Object> params){
        if(null==httpSubscriber){
            httpSubscriber=new HttpSubscriber<>(new OnResultCallBack<List<StockInWorkDetailResult>>() {
                @Override
                public void onSuccess(List<StockInWorkDetailResult> datas) {
                    getView().queryAllotScanDetail(datas);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryAllotScanDetail(params,httpSubscriber);
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
