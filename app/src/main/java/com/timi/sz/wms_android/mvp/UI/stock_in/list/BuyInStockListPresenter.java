package com.timi.sz.wms_android.mvp.UI.stock_in.list;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 15:21
 */

public class BuyInStockListPresenter extends MvpBasePresenter<BuyInStockListView> {
    private HttpSubscriber<List<QueryPoListBean>> httpSubscriber;
    private HttpSubscriber<BuyOrdernoBean> ordernoBeanHttpSubscriber;
    private BuyInStockListModel model;

    public BuyInStockListPresenter(Context context) {
        super(context);
        model = new BuyInStockListModel();
    }

    /**
     * 采购单列表
     *
     * @param params
     */
    public void queryPOList(RequestBuyInStockListBean params) {
        if (null == httpSubscriber) {
            httpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<QueryPoListBean>>() {
                @Override
                public void onSuccess(List<QueryPoListBean> datas) {
                    getView().queryPOList(datas);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.queryPOList(params, httpSubscriber);
    }

    /**
     * 采购单表头 表体
     *
     * @param params
     */
    public void getPODataByCode(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == ordernoBeanHttpSubscriber) {
            ordernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyOrdernoBean>() {
                @Override
                public void onSuccess(BuyOrdernoBean bean) {
                    getView().BuyOrdernoBean(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPODataByCode(params, ordernoBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != httpSubscriber) {
            httpSubscriber.unSubscribe();
            httpSubscriber = null;
        }
    }
}
