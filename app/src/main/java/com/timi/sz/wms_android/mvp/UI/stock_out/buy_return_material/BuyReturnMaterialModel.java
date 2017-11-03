package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
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
 * create at: 2017-08-28 17:29
 */

public class BuyReturnMaterialModel extends MvpBaseModel {
    /**
     * 物料扫码 获取采购退货单
     * @param params
     * @param observer
     */
    public void materialScanGetBuyRetrurnOrderNo(final Map<String,Object> params, Observer<BuyReturnMaterialByMaterialCodeData> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyReturnMaterialByMaterialCodeData>() {
            @Override
            public Observable<CommonResult<BuyReturnMaterialByMaterialCodeData>> createObservable(ApiService apiService) {
                return apiService.materialScanGetBuyRetrurnOrderNo(params);
            }
        });

    }
    /**
     * 退料单号的扫码请求
     * @param params
     * @param observer
     */
      public void returnMaterialOrderNoScanNetWork(final Map<String,Object> params, final Observer<BuyReturnMaterialByOrdernoData> observer){
          HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyReturnMaterialByOrdernoData>() {
              @Override
              public Observable<CommonResult<BuyReturnMaterialByOrdernoData>> createObservable(ApiService apiService) {
                  return apiService.returnMaterialOrderNoScan(params);
              }
          });
      }

}
