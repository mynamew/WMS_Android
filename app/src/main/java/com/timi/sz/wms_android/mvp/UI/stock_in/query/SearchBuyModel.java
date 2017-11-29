package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * 搜索采购单的model
 * author: timi
 * create at: 2017-08-18 17:39
 */
public class SearchBuyModel extends MvpBaseModel {
    /**查看订单的所有方法***********************************************************************/
    /**
     * 采购单查询的方法
     */
    public void buyOrdernoQuery(final Map<String,Object> params, Observer<BuyOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyOrdernoBean>() {
            @Override
            public Observable<CommonResult<BuyOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.buyOrderNoQuery(params);
            }
        });
    }

    /**
     * 送货单查询的方法
     */
    public void sendOrdernoQuery(final Map<String,Object> params, Observer<SendOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SendOrdernoBean>() {
            @Override
            public Observable<CommonResult<SendOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.sendOrdernoQuery(params);
            }
        });
    }

    /**
     * 搜索收货的单号
     *
     * @param orderno
     * @param observer
     */
    public void searchReceiveGoodOrderno(final Map<String,Object> orderno,
                                         final Observer<ReceiveOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<ReceiveOrdernoBean>() {
                    @Override
                    public Observable<CommonResult<ReceiveOrdernoBean>> createObservable(ApiService apiService) {
                        return apiService.searchReceiveGoodOrderno(orderno);
                    }
                });
    }

    /**
     * 搜索产成品-审核的单号
     *
     * @param params
     * @param observer
     */
    public void searchFinishGoodsOrderno(final Map<String, Object> params,
                                         final Observer<QueryPrdInstockByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<QueryPrdInstockByInputResult>() {
                    @Override
                    public Observable<CommonResult<QueryPrdInstockByInputResult>> createObservable(ApiService apiService) {
                        return apiService.searchFinishGoodsOrderno(params);
                    }
                });
    }

     /**
     * 搜索产成品-生单的单号
     *
     * @param params
     * @param observer
     */
    public void searchFinishGoodsCreateBillOrderno(final Map<String, Object> params,
                                         final Observer<FinishGoodsCreateBillBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<FinishGoodsCreateBillBean>() {
                    @Override
                    public Observable<CommonResult<FinishGoodsCreateBillBean>> createObservable(ApiService apiService) {
                        return apiService.searchFinishGoodsCreateBillOrderno(params);
                    }
                });
    }

    /**
     * 搜索其他-审核的单号
     *
     * @param params
     * @param observer
     */
    public void searchOtherAuditSelectOrderno(final Map<String, Object> params,
                                                   final Observer<OtherAuditSelectOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<OtherAuditSelectOrdernoBean>() {
                    @Override
                    public Observable<CommonResult<OtherAuditSelectOrdernoBean>> createObservable(ApiService apiService) {
                        return apiService.searchOtherAuditSelectOrderno(params);
                    }
                });
    }
  /**
     * 搜索委外退料—选单的单号
     *
     * @param params
     * @param observer
     */
    public void searchOutReturnMaterialOrderno(final Map<String, Object> params,
                                                   final Observer<QueryOutSourceReturnByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<QueryOutSourceReturnByInputResult>() {
                    @Override
                    public Observable<CommonResult<QueryOutSourceReturnByInputResult>> createObservable(ApiService apiService) {
                        return apiService.queryOutSourceReturnByInput(params);
                    }
                });
    }
  /**
     * 搜索生产退料—选单的单号
     *
     * @param params
     * @param observer
     */
    public void searchProductionReturnMaterialOrderno(final Map<String,Object> params,
                                                      final Observer<QueryPrdReturnByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<QueryPrdReturnByInputResult>() {
                    @Override
                    public Observable<CommonResult<QueryPrdReturnByInputResult>> createObservable(ApiService apiService) {
                        return apiService.queryPrdReturnByInput(params);
                    }
                });
    }
  /**
     * 搜索销售退货—选单的单号
     *  @param orderno
     * @param observer
   */
    public void searchSaleGoodsReturnOrderno(final Map<String, Object> orderno,
                                                   final Observer<SaleGoodsReturnBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<SaleGoodsReturnBean>() {
                    @Override
                    public Observable<CommonResult<SaleGoodsReturnBean>> createObservable(ApiService apiService) {
                        return apiService.querySalesReturnByInput(orderno);
                    }
                });
    }


}
