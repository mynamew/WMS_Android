package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import com.timi.sz.wms_android.bean.outstock.BuyReturnMaterialOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.OrderNoBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 17:29
 */

public class BuyReturnMaterialModel extends MvpBaseModel {
    /**
     * 物料扫码 获取采购退货单
     * @param materialCode
     * @param observer
     */
    public void materialScanGetBuyRetrurnOrderNo(final String materialCode, Observer<BuyReturnMaterialOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyReturnMaterialOrdernoBean>() {
            @Override
            public Observable<CommonResult<BuyReturnMaterialOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.materialScanGetBuyRetrurnOrderNo(materialCode);
            }
        });

    }
    /**
     * 退料单号的扫码请求
     * @param oderNo
     * @param observer
     */
      public void returnMaterialOrderNoScanNetWork(final String oderNo, final Observer<OrderNoBean> observer){
          HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OrderNoBean>() {
              @Override
              public Observable<CommonResult<OrderNoBean>> createObservable(ApiService apiService) {
                  return apiService.returnMaterialOrderNoScan(oderNo);
              }
          });
      }

}
