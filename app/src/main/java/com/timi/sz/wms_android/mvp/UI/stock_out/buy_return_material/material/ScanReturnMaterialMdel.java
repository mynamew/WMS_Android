package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
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
 * create at: 2017-08-29 08:42
 */

public class ScanReturnMaterialMdel extends MvpBaseModel {
    /**
     * 物料扫码 获取采购退货单
     * @param materialCode
     * @param observer
     */
    public void materialScan(final String materialCode, Observer<MaterialBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<MaterialBean>() {
            @Override
            public Observable<CommonResult<MaterialBean>> createObservable(ApiService apiService) {
                return apiService.materialScan(materialCode);
            }
        });

    }
    /**
     * 物料扫码 获取采购退货单
     * @param materialCode
     * @param observer
     */
    public void commitMaterialScanToOrederno(final String materialCode, Observer<CommitMaterialScanToOredernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<CommitMaterialScanToOredernoBean>() {
            @Override
            public Observable<CommonResult<CommitMaterialScanToOredernoBean>> createObservable(ApiService apiService) {
                return apiService.commitMaterialScanToOrederno(materialCode);
            }
        });

    }
}
