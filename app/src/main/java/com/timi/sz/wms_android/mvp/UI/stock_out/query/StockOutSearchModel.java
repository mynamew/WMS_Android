package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.other.OtherOutAndInStockBean;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QuerySalesOutSotckByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryTransferByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 08:55
 */

public class StockOutSearchModel extends MvpBaseModel {
    /**
     * 委外发料(审核) 搜索请求
     *
     * @param observer
     */
    public void queryOutSourcePickByInput(final Map<String, Object> params, Observer<QueryOutSourcePickByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOutSourcePickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOutSourcePickByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryOutSourcePickByInput(params);
            }
        });
    }

    /**
     * 委外调拨 搜索请求
     *
     * @param observer
     */
    public void queryWWPickDataByOutSource(final Map<String, Object> params, Observer<QueryWWPickDataByOutSourceResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryWWPickDataByOutSourceResult>() {
            @Override
            public Observable<CommonResult<QueryWWPickDataByOutSourceResult>> createObservable(ApiService apiService) {
                return apiService.queryWWPickDataByOutSource(params);
            }
        });
    }

    /**
     * 委外补料 搜索请求
     *
     * @param observer
     */
    public void queryOutSourceFeedByInput(final Map<String, Object> params, Observer<QueryOutSourceFeedByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryOutSourceFeedByInputResult>() {
            @Override
            public Observable<CommonResult<QueryOutSourceFeedByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryOutSourceFeedByInput(params);
            }
        });
    }

    /**====== 生产领料 ======**/
    /**
     * 生产领料单末尾号查询（生单流程）
     *
     * @param observer
     */
    public void queryPrdPickDataByMO(final Map<String, Object> params, Observer<QueryWWPickDataByOutSourceResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryWWPickDataByOutSourceResult>() {
            @Override
            public Observable<CommonResult<QueryWWPickDataByOutSourceResult>> createObservable(ApiService apiService) {
                return apiService.queryPrdPickDataByMO(params);
            }
        });
    }

    /**
     * 生产领料单末尾号查询（审核流程）
     *
     * @param observer
     */
    public void queryProductPickByInput(final Map<String, Object> params, Observer<QueryProductPickByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryProductPickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryProductPickByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryProductPickByInput(params);
            }
        });
    }

    /**
     * 领料申请单末尾号查询（审核流程）
     *
     * @param observer
     */
    public void queryPrdPickApplyByInput(final Map<String, Object> params, Observer<QueryProductPickByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryProductPickByInputResult>() {
            @Override
            public Observable<CommonResult<QueryProductPickByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryPrdPickApplyByInput(params);
            }
        });
    }

    /**
     * 生产补料单末尾号查询
     *
     * @param observer
     */
    public void queryPrdFeedByInput(final Map<String, Object> params, Observer<QueryPrdFeedByInputResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryPrdFeedByInputResult>() {
            @Override
            public Observable<CommonResult<QueryPrdFeedByInputResult>> createObservable(ApiService apiService) {
                return apiService.queryPrdFeedByInput(params);
            }
        });
    }


    /**
     * 销售出库 审核
     *
     * @param params
     * @param observer
     */
    public void queryDNByInputForOutStock(final Map<String, Object> params, Observer<QueryDNByInputForOutStockResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryDNByInputForOutStockResult>() {
            @Override
            public Observable<CommonResult<QueryDNByInputForOutStockResult>> createObservable(ApiService apiService) {
                return apiService.queryDNByInputForOutStock(params);
            }
        });
    }

    /**
     * 销售出库 生单
     *
     * @param params
     * @param observer
     */
    public void querySalesOutSotckByInputForOutStock(final Map<String, Object> params, Observer<QuerySalesOutSotckByInputForOutStockResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QuerySalesOutSotckByInputForOutStockResult>() {
            @Override
            public Observable<CommonResult<QuerySalesOutSotckByInputForOutStockResult>> createObservable(ApiService apiService) {
                return apiService.querySalesOutSotckByInputForOutStock(params);
            }
        });
    }

    /**
     * 其他出库-审核（红单）
     *
     * @param params
     * @param observer
     */
    public void searchOtherAuditSelectOrderno(final Map<String, Object> params, Observer<OtherOutAndInStockBean> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OtherOutAndInStockBean>() {
            @Override
            public Observable<CommonResult<OtherOutAndInStockBean>> createObservable(ApiService apiService) {
                return apiService.searchOtherAuditSelectOrderno(params);
            }
        });
    }

    /**
     * 其他出库-审核
     *
     * @param params
     * @param observer
     */
    public void queryOtherOutStockByInput(final Map<String, Object> params, Observer<OtherOutAndInStockBean> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OtherOutAndInStockBean>() {
            @Override
            public Observable<CommonResult<OtherOutAndInStockBean>> createObservable(ApiService apiService) {
                return apiService.queryOtherOutStockByInput(params);
            }
        });
    }

    /**
     * 发货通知单末尾号查询
     *
     * @param observer
     */
    public void queryDNByInputForPick(final Map<String, Object> params, Observer<QueryDNByInputForPickResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryDNByInputForPickResult>() {
            @Override
            public Observable<CommonResult<QueryDNByInputForPickResult>> createObservable(ApiService apiService) {
                return apiService.queryDNByInputForPick(params);
            }
        });
    }

    /**
     * 销售出库单末尾号查询
     *
     * @param observer
     */
    public void querySalesOutSotckByInputForPick(final Map<String, Object> params, Observer<QuerySalesOutSotckByInputForPickResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QuerySalesOutSotckByInputForPickResult>() {
            @Override
            public Observable<CommonResult<QuerySalesOutSotckByInputForPickResult>> createObservable(ApiService apiService) {
                return apiService.querySalesOutSotckByInputForPick(params);
            }
        });
    }

    /**
     * 调拨拣货出库单末尾号查询
     *
     * @param observer
     */
    public void queryTransferByInputForPick(final Map<String, Object> params, Observer<QueryTransferByInputForPickResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryTransferByInputForPickResult>() {
            @Override
            public Observable<CommonResult<QueryTransferByInputForPickResult>> createObservable(ApiService apiService) {
                return apiService.queryTransferByInputForPick(params);
            }
        });
    }
    /**
     * 调拨调出
     *
     * @param params
     * @param observer
     */
    public void queryTransferByInputForOutStock(final Map<String, Object> params, Observer<QueryAllotOutResult> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryAllotOutResult>() {
            @Override
            public Observable<CommonResult<QueryAllotOutResult>> createObservable(ApiService apiService) {
                return apiService.queryTransferByInputForOutStock(params);
            }
        });
    }
}
