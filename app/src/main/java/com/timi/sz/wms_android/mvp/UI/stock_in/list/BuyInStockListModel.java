package com.timi.sz.wms_android.mvp.UI.stock_in.list;

import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 15:21
 */

public class BuyInStockListModel extends MvpBaseModel {
    /**
     * 获取采购单列表
     * @param params
     * @param observer
     */
    public void queryPOList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryPOList(params);
            }
        });
    }
    /**
     * 获取委外单列表
     * @param params
     * @param observer
     */
    public void queryWWPOList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryWWPOList(params);
            }
        });
    }
    /**
     * 获取采购单表头表体数据
     * @param params
     * @param observer
     */
    public void getPODataByCode(final Map<String,Object> params, Observer<BuyOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyOrdernoBean>() {
            @Override
            public Observable<CommonResult<BuyOrdernoBean>> createObservable(ApiService apiService) {

                return apiService.getPODataByCode(params);
            }
        });
    }
}
