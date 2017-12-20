package com.timi.sz.wms_android.mvp.UI.list;

import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
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
     *
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
     *
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
     *
     * @param params
     * @param observer
     */
    public void getPODataByCode(final Map<String, Object> params, Observer<BuyOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyOrdernoBean>() {
            @Override
            public Observable<CommonResult<BuyOrdernoBean>> createObservable(ApiService apiService) {

                return apiService.getPODataByCode(params);
            }
        });
    }

    /**
     * 获取采购退料单列表
     *
     * @param params
     * @param observer
     */
    public void queryPurReturnList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryPurReturnList(params);
            }
        });
    }

    /**
     * 获取采购退料单数据
     *
     * @param params
     * @param observer
     */
    public void getPurReturnData(final Map<String, Object> params, Observer<BuyReturnMaterialByOrdernoData> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyReturnMaterialByOrdernoData>() {
            @Override
            public Observable<CommonResult<BuyReturnMaterialByOrdernoData>> createObservable(ApiService apiService) {

                return apiService.getPurReturnData(params);
            }
        });
    }

    /**
     * 获取委外补料单列表
     *
     * @param params
     * @param observer
     */
    public void queryWWFeedList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryWWFeedList(params);
            }
        });
    }

    /**
     * 从委外补料单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getOutSourceFeedData(final Map<String, Object> params, Observer<QueryOutSourceFeedByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOutSourceFeedByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOutSourceFeedByInputResult>> createObservable(ApiService apiService) {

                return apiService.getOutSourceFeedData(params);
            }
        });
    }

    /**
     * 获取委外退料单
     *
     * @param params
     * @param observer
     */
    public void queryWWReturnList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryWWReturnList(params);
            }
        });
    }

    /**
     * 从委外补料单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getOutSourceReturnData(final Map<String, Object> params, Observer<QueryOutSourceReturnByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOutSourceReturnByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOutSourceReturnByInputResult>> createObservable(ApiService apiService) {

                return apiService.getOutSourceReturnData(params);
            }
        });
    }

    /**
     * 获取委外单列表
     *
     * @param params
     * @param observer
     */
    public void queryWWPOListForPick(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryWWPOListForPick(params);
            }
        });
    }

    /**
     * 从委外单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getWWPickDataByOutSource(final Map<String, Object> params, Observer<QueryWWPickDataByOutSourceResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryWWPickDataByOutSourceResult>() {
            @Override
            public Observable<CommonResult<QueryWWPickDataByOutSourceResult>> createObservable(ApiService apiService) {

                return apiService.getWWPickDataByOutSource(params);
            }
        });
    }

    /**
     * 获取委外单列表（审核）
     *
     * @param params
     * @param observer
     */
    public void queryWWPickList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryWWPickList(params);
            }
        });
    }

    /**
     * 从委外单列表获取数据（审核）
     *
     * @param params
     * @param observer
     */
    public void getOutSourcePickData(final Map<String, Object> params, Observer<QueryOutSourcePickByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOutSourcePickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOutSourcePickByInputResult>> createObservable(ApiService apiService) {

                return apiService.getOutSourcePickData(params);
            }
        });
    }

    /**
     * 获取领料申请单列表
     *
     * @param params
     * @param observer
     */
    public void getPickApplyList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.getPickApplyList(params);
            }
        });
    }

    /**
     * 从领料申请单单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getPrdPickApplyData(final Map<String, Object> params, Observer<QueryProductPickByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryProductPickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryProductPickByInputResult>> createObservable(ApiService apiService) {

                return apiService.getPrdPickApplyData(params);
            }
        });
    }

    /**
     * 获取生产工单列表
     *
     * @param params
     * @param observer
     */
    public void queryPrdList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryPrdList(params);
            }
        });
    }

    /**
     * 从生产领料单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getPrdPickData(final Map<String, Object> params, Observer<QueryWWPickDataByOutSourceResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryWWPickDataByOutSourceResult>() {
            @Override
            public Observable<CommonResult<QueryWWPickDataByOutSourceResult>> createObservable(ApiService apiService) {

                return apiService.getPrdPickData(params);
            }
        });
    }

    /**
     * 获取生产领料单列表
     *
     * @param params
     * @param observer
     */
    public void queryPrdPickList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryPrdPickList(params);
            }
        });
    }

    /**
     * 从生产领料单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getProductPickData(final Map<String, Object> params, Observer<QueryProductPickByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryProductPickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryProductPickByInputResult>> createObservable(ApiService apiService) {

                return apiService.getProductPickData(params);
            }
        });
    }
    /**
     * 查询成品入库单列表
     *
     * @param params
     * @param observer
     */
    public void queryPrdInstockByInput(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.qeryPrdInstockList(params);
            }
        });
    }
    /**
     * 从成品入库单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getPrdInstockData(final Map<String, Object> params, Observer<QueryPrdInstockByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryPrdInstockByInputResult>() {
            @Override
            public Observable<CommonResult<QueryPrdInstockByInputResult>> createObservable(ApiService apiService) {

                return apiService.getPrdInstockData(params);
            }
        });
    }
    /**
     * 查询生产退料单列表
     *
     * @param params
     * @param observer
     */
    public void queryPrdReturnList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryPrdReturnList(params);
            }
        });
    }
    /**
     * 从生产退料单获取数据
     *
     * @param params
     * @param observer
     */
    public void getPrdReturnData(final Map<String, Object> params, Observer<QueryPrdReturnByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryPrdReturnByInputResult>() {
            @Override
            public Observable<CommonResult<QueryPrdReturnByInputResult>> createObservable(ApiService apiService) {

                return apiService.getPrdReturnData(params);
            }
        });
    }
    /**
     * 查询其他入库单列表
     *
     * @param params
     * @param observer
     */
    public void queryOtherInstockList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryOtherInstockList(params);
            }
        });
    }
    /**
     * 从查询其他入库单数据
     *
     * @param params
     * @param observer
     */
    public void getOtherInstockData(final Map<String, Object> params, Observer<OtherAuditSelectOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OtherAuditSelectOrdernoBean>() {
            @Override
            public Observable<CommonResult<OtherAuditSelectOrdernoBean>> createObservable(ApiService apiService) {

                return apiService.getOtherInstockData(params);
            }
        });
    }
    /**
     * 查询其他出库单列表
     *
     * @param params
     * @param observer
     */
    public void queryOtherOutstockList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryOtherOutstockList(params);
            }
        });
    }
    /**
     * 查询其他出库列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getOtherOutstockData(final Map<String, Object> params, Observer<QueryOtherOutStockByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOtherOutStockByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOtherOutStockByInputResult>> createObservable(ApiService apiService) {

                return apiService.getOtherOutstockData(params);
            }
        });
    }
}
