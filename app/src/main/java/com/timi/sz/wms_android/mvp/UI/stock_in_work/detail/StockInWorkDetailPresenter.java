package com.timi.sz.wms_android.mvp.UI.stock_in_work.detail;

import android.content.Context;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
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
     * @param intentCode
     */
    public void queryAllotScanDetail(Map<String, Object> params, int intentCode){
        getView().showProgressDialog();
        if(null==httpSubscriber){
            httpSubscriber=new HttpSubscriber<>(new OnResultCallBack<List<StockInWorkDetailResult>>() {
                @Override
                public void onSuccess(List<StockInWorkDetailResult> datas) {
                    getView().queryAllotScanDetail(datas);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }

        switch (intentCode){

            case Constants.STOCK_IN_WORK_FORM_CHANGE_IN://形态转换入库
                model.getConvertInDetail(params,httpSubscriber);
                break;
            case Constants.STOCK_IN_WORK_FORM_CHANGE_OUT://形态转换出库
                model.getConvertOutDetail(params,httpSubscriber);
                break;
            case Constants.STOCK_IN_WORK_POINT://盘点
                model.getCheckStockDetail(params,httpSubscriber);
                break;
            case Constants.STOCK_IN_WORK_ALLOT_SCAN://扫描调入
                model.queryAllotScanDetail(params,httpSubscriber);
                break;
        }
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
