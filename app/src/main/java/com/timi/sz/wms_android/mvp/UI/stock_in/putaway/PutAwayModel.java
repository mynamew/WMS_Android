package com.timi.sz.wms_android.mvp.UI.stock_in.putaway;

import com.timi.sz.wms_android.bean.instock.CreateInStockOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc 入库上架的model
 * author: timi
 * create at: 2017-08-28 10:56
 */

public class PutAwayModel extends MvpBaseModel {
    /**查看订单的所有方法***********************************************************************/
    /**
     * 搜索收货的单号
     * @param orderno
     * @param observer
     */
    public void searchReceiveGoodOrderno(final String orderno,
                                         final Observer<ReceiveOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<ReceiveOrdernoBean>() {
                    @Override
                    public Observable<CommonResult<ReceiveOrdernoBean>> createObservable(ApiService apiService) {
                        return apiService.searchReceiveGoodOrderno(orderno);
                    }
                });
    }
    /**
     * 搜索收货的单号
     * @param orderno
     * @param observer
     */
    public void searchFinishGoodsOrderno(final String orderno,
                                         final Observer<FinishGoodsOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<FinishGoodsOrdernoBean>() {
                    @Override
                    public Observable<CommonResult<FinishGoodsOrdernoBean>> createObservable(ApiService apiService) {
                        return apiService.searchFinishGoodsOrderno(orderno);
                    }
                });
    }

    /****其他操作*********************************************************************************/
    /**
     * 物料扫码上架的网络请求
     * @param locationCode
     * @param materialCode
     * @param userId
     * @param observer
     */
    public void materialScanPutAawy(final String locationCode, final String materialCode,
                                    final String userId,
                                    final Observer<MaterialScanPutAwayBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<MaterialScanPutAwayBean>() {
            @Override
            public Observable<CommonResult<MaterialScanPutAwayBean>> createObservable(ApiService apiService) {
                return apiService.materialScanPutAawy(locationCode,materialCode,userId);
            }
        });
    }
    /**
     * 验证库位码是否有效
     * @param scanStr
     * @param observer
     */
    public void vertifyLocationCode(final String scanStr,
                                    final Observer<VertifyLocationCodeBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<VertifyLocationCodeBean>() {
            @Override
            public Observable<CommonResult<VertifyLocationCodeBean>>
            createObservable(ApiService apiService) {
                return apiService.vertifyLocationCode(scanStr);
            }
        });
    }
    /**
     * 生成入库单
     * @param locationCode
     * @param observer
     */
    public void createInStockOrderno(final  String locationCode,
                                    final Observer<CreateInStockOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer,
                new ApiServiceMethodCallBack<CreateInStockOrdernoBean>() {
            @Override
            public Observable<CommonResult<CreateInStockOrdernoBean>>
            createObservable(ApiService apiService) {
                return apiService.createInStockOrderno(locationCode);
            }
        });
    }

}
