package com.timi.sz.wms_android.mvp.UI.list;

import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.bean.other.OtherOutAndInStockBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.GetSalesOutSotckByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryTransferByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeInResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
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
     * 查询成品入库单（审核）列表
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
     * 查询成品入库单（生单）列表
     *
     * @param params
     * @param observer
     */
    public void queryWOInstockList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryWOInstockList(params);
            }
        });
    }

    /**
     * 从成品入库单(审核)列表获取数据
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
     * 从成品入库单(生单)列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getWOInstockData(final Map<String, Object> params, Observer<FinishGoodsCreateBillBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<FinishGoodsCreateBillBean>() {
            @Override
            public Observable<CommonResult<FinishGoodsCreateBillBean>> createObservable(ApiService apiService) {

                return apiService.getWOInstockData(params);
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
     * 查询生产补料单列表
     *
     * @param params
     * @param observer
     */
    public void queryPrdFeedList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryPrdFeedList(params);
            }
        });
    }

    /**
     * 从生产退料单获取数据
     *
     * @param params
     * @param observer
     */
    public void getProductPickDataByFeed(final Map<String, Object> params, Observer<QueryPrdFeedByInputResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryPrdFeedByInputResult>() {
            @Override
            public Observable<CommonResult<QueryPrdFeedByInputResult>> createObservable(ApiService apiService) {

                return apiService.getProductPickDataByFeed(params);
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
    public void getOtherInstockData(final Map<String, Object> params, Observer<OtherOutAndInStockBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OtherOutAndInStockBean>() {
            @Override
            public Observable<CommonResult<OtherOutAndInStockBean>> createObservable(ApiService apiService) {

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
    public void getOtherOutstockData(final Map<String, Object> params, Observer<OtherOutAndInStockBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OtherOutAndInStockBean>() {
            @Override
            public Observable<CommonResult<OtherOutAndInStockBean>> createObservable(ApiService apiService) {

                return apiService.getOtherOutstockData(params);
            }
        });
    }
    /***********成品拣货*******************************************************************************/
    /**
     * 发货通知单列表
     *
     * @param params
     * @param observer
     */
    public void queryDNOutList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryDNOutList(params);
            }
        });
    }

    /**
     * 根据发货通知单号获得发货通知单拣货数据。
     *
     * @param params
     * @param observer
     */
    public void getDNDataForPick(final Map<String, Object> params, Observer<QueryDNByInputForPickResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryDNByInputForPickResult>() {
            @Override
            public Observable<CommonResult<QueryDNByInputForPickResult>> createObservable(ApiService apiService) {

                return apiService.getDNDataForPick(params);
            }
        });
    }

    /**
     * 销售出库单列表
     *
     * @param params
     * @param observer
     */
    public void querySalesOutStockList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.querySalesOutStockList(params);
            }
        });
    }

    /**
     * 根据销售通知单号获得发货通知单拣货数据。
     *
     * @param params
     * @param observer
     */
    public void getSalesOutSotckByInputForPick(final Map<String, Object> params, Observer<GetSalesOutSotckByInputForPickResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<GetSalesOutSotckByInputForPickResult>() {
            @Override
            public Observable<CommonResult<GetSalesOutSotckByInputForPickResult>> createObservable(ApiService apiService) {

                return apiService.getSalesOutSotckByInputForPick(params);
            }
        });
    }

    /**
     * 调拨单列表
     *
     * @param params
     * @param observer
     */
    public void QueryTransferListForOutStock(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.QueryTransferListForOutStock(params);
            }
        });
    }

    /**
     * 销售退料单列表
     *
     * @param params
     * @param observer
     */
    public void querySalesReturnList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.querySalesReturnList(params);
            }
        });
    }

    /**
     * 销售退料单列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getSalesReturnData(final Map<String, Object> params, Observer<SaleGoodsReturnBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SaleGoodsReturnBean>() {
            @Override
            public Observable<CommonResult<SaleGoodsReturnBean>> createObservable(ApiService apiService) {

                return apiService.getSalesReturnData(params);
            }
        });
    }
 /**
     * 根据调拨单号获得调拨单拣货数据。
     *
     * @param params
     * @param observer
     */
    public void getTransferByInputForPick(final Map<String, Object> params, Observer<QueryTransferByInputForPickResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryTransferByInputForPickResult>() {
            @Override
            public Observable<CommonResult<QueryTransferByInputForPickResult>> createObservable(ApiService apiService) {

                return apiService.getTransferByInputForPick(params);
            }
        });
    }

    /**
     * 銷售审核列表－获取数据
     *
     * @param params
     * @param observer
     */
    public void getSalesOutSotckByInputForOutStock(final Map<String, Object> params, Observer<QuerySalesOutSotckByInputForOutStockResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QuerySalesOutSotckByInputForOutStockResult>() {
            @Override
            public Observable<CommonResult<QuerySalesOutSotckByInputForOutStockResult>> createObservable(ApiService apiService) {

                return apiService.getSalesOutSotckByInputForOutStock(params);
            }
        });
    }

    /**
     * 銷售聖誕列表－获取数据
     *
     * @param params
     * @param observer
     */
    public void getDNDataForOutStock(final Map<String, Object> params, Observer<QuerySalesOutSotckByInputForOutStockResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QuerySalesOutSotckByInputForOutStockResult>() {
            @Override
            public Observable<CommonResult<QuerySalesOutSotckByInputForOutStockResult>> createObservable(ApiService apiService) {

                return apiService.getDNDataForOutStock(params);
            }
        });
    }

    /**
     * 扫描调入/一步调入列表
     *
     * @param params
     * @param observer
     */
    public void queryTransferListForOutStock(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryTransferListForOutStock(params);
            }
        });
    }

    /**
     * 根据调扫描调入列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getTransferForStepBy(final Map<String, Object> params, Observer<AllotScanResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<AllotScanResult>() {
            @Override
            public Observable<CommonResult<AllotScanResult>> createObservable(ApiService apiService) {

                return apiService.getTransferForStepBy(params);
            }
        });
    }

    /**
     * 根据调一步调入列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getTransferForOneStep(final Map<String, Object> params, Observer<AllotOneSetpResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<AllotOneSetpResult>() {
            @Override
            public Observable<CommonResult<AllotOneSetpResult>> createObservable(ApiService apiService) {

                return apiService.getTransferForOneStep(params);
            }
        });
    }

    /**
     * 根据调拨调出列表获取数据
     *
     * @param params
     * @param observer
     */
    public void getTransferDNDataForPick(final Map<String, Object> params, Observer<QueryAllotOutResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<QueryAllotOutResult>() {
            @Override
            public Observable<CommonResult<QueryAllotOutResult>> createObservable(ApiService apiService) {

                return apiService.getTransferDNDataForPick(params);
            }
        });
    }

    /**
     * 形态转换单列表
     *
     * @param params
     * @param observer
     */
    public void queryConvertList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryConvertList(params);
            }
        });
    }

    /**
     * 根据调拨形态转入单获取形态转换数据。
     *
     * @param params
     * @param observer
     */
    public void getConvertInByInput(final Map<String, Object> params, Observer<FormChangeInResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<FormChangeInResult>() {
            @Override
            public Observable<CommonResult<FormChangeInResult>> createObservable(ApiService apiService) {

                return apiService.getConvertInByInput(params);
            }
        });
    }

    /**
     * 根据调拨形态转出单获取形态转换数据。
     *
     * @param params
     * @param observer
     */
    public void getConvertOutByInput(final Map<String, Object> params, Observer<FormChangeOutResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<FormChangeOutResult>() {
            @Override
            public Observable<CommonResult<FormChangeOutResult>> createObservable(ApiService apiService) {

                return apiService.getConvertOutByInput(params);
            }
        });
    }

    /**
     * 盘点单列表
     *
     * @param params
     * @param observer
     */
    public void queryCheckStockList(final RequestBuyInStockListBean params, Observer<List<QueryPoListBean>> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<QueryPoListBean>>() {
            @Override
            public Observable<CommonResult<List<QueryPoListBean>>> createObservable(ApiService apiService) {

                return apiService.queryCheckStockList(params);
            }
        });
    }

    /**
     * 根据盘点单单号获得调拨单拣货数据。
     *
     * @param params
     * @param observer
     */
    public void getCheckStockByCode(final Map<String, Object> params, Observer<PointResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<PointResult>() {
            @Override
            public Observable<CommonResult<PointResult>> createObservable(ApiService apiService) {

                return apiService.getCheckStockByCode(params);
            }
        });
    }
}
