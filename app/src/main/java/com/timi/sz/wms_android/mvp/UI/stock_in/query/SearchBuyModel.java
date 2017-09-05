package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OutReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ProductionReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

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
    public void buyOrdernoQuery(final int orgId, final int userId, final String mac, final String billNo, Observer<BuyOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyOrdernoBean>() {
            @Override
            public Observable<CommonResult<BuyOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.buyOrderNoQuery(orgId,userId,mac,billNo);
            }
        });
    }

    /**
     * 送货单查询的方法
     *
     * @param scanStr
     * @param observer
     */
    public void sendOrdernoQuery(final String scanStr, Observer<SendOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SendOrdernoBean>() {
            @Override
            public Observable<CommonResult<SendOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.sendOrdernoQuery(scanStr);
            }
        });
    }

    /**
     * 搜索收货的单号
     *
     * @param orderno
     * @param observer
     */
    public void searchReceiveGoodOrderno(final String orderno,
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
     * @param orderno
     * @param observer
     */
    public void searchFinishGoodsOrderno(final String orderno,
                                         final Observer<FinishGoodsOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<FinishGoodsOrdernoBean>() {
                    @Override
                    public Observable<CommonResult<FinishGoodsOrdernoBean>> createObservable(ApiService apiService) {
                        return apiService.searchFinishGoodsOrderno(orderno);
                    }
                });
    }

     /**
     * 搜索产成品-生单的单号
     *
     * @param orderno
     * @param observer
     */
    public void searchFinishGoodsCreateBillOrderno(final String orderno,
                                         final Observer<FinishGoodsCreateBillBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<FinishGoodsCreateBillBean>() {
                    @Override
                    public Observable<CommonResult<FinishGoodsCreateBillBean>> createObservable(ApiService apiService) {
                        return apiService.searchFinishGoodsCreateBillOrderno(orderno);
                    }
                });
    }

    /**
     * 搜索其他-生单的单号
     *
     * @param orderno
     * @param observer
     */
    public void searchOtherAuditSelectOrderno(final String orderno,
                                                   final Observer<OtherAuditSelectOrdernoBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<OtherAuditSelectOrdernoBean>() {
                    @Override
                    public Observable<CommonResult<OtherAuditSelectOrdernoBean>> createObservable(ApiService apiService) {
                        return apiService.searchOtherAuditSelectOrderno(orderno);
                    }
                });
    }
  /**
     * 搜索委外退料—选单的单号
     *
     * @param orderno
     * @param observer
     */
    public void searchOutReturnMaterialOrderno(final String orderno,
                                                   final Observer<OutReturnMaterialBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<OutReturnMaterialBean>() {
                    @Override
                    public Observable<CommonResult<OutReturnMaterialBean>> createObservable(ApiService apiService) {
                        return apiService.searchOutReturnMaterialOrderno(orderno);
                    }
                });
    }
  /**
     * 搜索生产退料—选单的单号
     *
     * @param orderno
     * @param observer
     */
    public void searchProductionReturnMaterialOrderno(final String orderno,
                                                   final Observer<ProductionReturnMaterialBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<ProductionReturnMaterialBean>() {
                    @Override
                    public Observable<CommonResult<ProductionReturnMaterialBean>> createObservable(ApiService apiService) {
                        return apiService.searchProductionReturnMaterialOrderno(orderno);
                    }
                });
    }
  /**
     * 搜索销售退货—选单的单号
     *
     * @param orderno
     * @param observer
     */
    public void searchSaleGoodsReturnOrderno(final String orderno,
                                                   final Observer<SaleGoodsReturnBean> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<SaleGoodsReturnBean>() {
                    @Override
                    public Observable<CommonResult<SaleGoodsReturnBean>> createObservable(ApiService apiService) {
                        return apiService.searchSaleGoodsReturnOrderno(orderno);
                    }
                });
    }


}
