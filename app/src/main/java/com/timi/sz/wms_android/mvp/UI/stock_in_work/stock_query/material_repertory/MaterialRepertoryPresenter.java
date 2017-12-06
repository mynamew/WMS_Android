package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.stock_query.MaterialQueryResult;
import com.timi.sz.wms_android.bean.stockin_work.stock_query.QueryStockContainerResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-30 16:33
 */

public class MaterialRepertoryPresenter extends MvpBasePresenter<MaterialRepertoryView> {
    private MaterialRepertoryModel model;

    public MaterialRepertoryPresenter(Context context) {
        super(context);
        model = new MaterialRepertoryModel();
    }
    private HttpSubscriber<MaterialQueryResult> queryResultHttpSubscriber;

    /**
     * 查询库存
     *
     * @param params
     */
    public void queryStockMaterial(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryResultHttpSubscriber) {
            queryResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialQueryResult>() {
                @Override
                public void onSuccess(MaterialQueryResult o) {
                    getView().queryStockMaterial(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryStockMaterial(params, queryResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != queryResultHttpSubscriber) {
            queryResultHttpSubscriber.unSubscribe();
            queryResultHttpSubscriber = null;
        }
    }
}
