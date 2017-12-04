package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.stock_query.QueryStockContainerResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory.MaterialRepertoryModel;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-30 16:30
 */

public class PackRepertoryPresenter extends MvpBasePresenter<PackRepertoryView> {
    private PackRepertoryModel model;

    public PackRepertoryPresenter(Context context) {
        super(context);
        model = new PackRepertoryModel();
    }

    private HttpSubscriber<QueryStockContainerResult> queryStockContainerResultHttpSubscriber;

    /**
     * 查询库存
     *
     * @param params
     */
    public void queryStockContainer(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryStockContainerResultHttpSubscriber) {
            queryStockContainerResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryStockContainerResult>() {
                @Override
                public void onSuccess(QueryStockContainerResult o) {
                    getView().queryStockContainer(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
            model.queryStockContainer(params, queryStockContainerResultHttpSubscriber);
        }
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != queryStockContainerResultHttpSubscriber) {
            queryStockContainerResultHttpSubscriber.unSubscribe();
            queryStockContainerResultHttpSubscriber = null;
        }
    }
}
