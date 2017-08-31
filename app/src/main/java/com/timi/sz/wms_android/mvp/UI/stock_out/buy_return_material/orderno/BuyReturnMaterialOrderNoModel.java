package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import com.timi.sz.wms_android.bean.outstock.MaterialBean;
import com.timi.sz.wms_android.bean.outstock.OrderNoAddMaterial;
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
 * create at: 2017-08-29 14:43
 */

public class BuyReturnMaterialOrderNoModel extends MvpBaseModel {
    /**
     * 物料扫码的网络请求
     * @param scanStr
     * @param observer
     */
    public void materialScanNetWork(final String scanStr, final Observer<MaterialBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<MaterialBean>() {
            @Override
            public Observable<CommonResult<MaterialBean>> createObservable(ApiService apiService) {
                return apiService.materialScan(scanStr);
            }
        });
    }
    /**
     * 退料单 提交物料信息
     * @param scanStr
     * @param observer
     */
    public void returnMaterialCommitResultNetWork(final String scanStr, final Observer<OrderNoAddMaterial> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<OrderNoAddMaterial>() {
            @Override
            public Observable<CommonResult<OrderNoAddMaterial>> createObservable(ApiService apiService) {
                return apiService.returnMaterialOrderNoAddMaterial(scanStr);
            }
        });
    }
}
