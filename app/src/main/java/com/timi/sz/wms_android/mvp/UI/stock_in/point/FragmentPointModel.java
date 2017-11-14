package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * $dsc 物料清点的model
 * author: timi
 * create at: 2017-08-25 16:17
 */

public class FragmentPointModel extends MvpBaseModel {
    /**
     * 保存采购单的物料清点的方法
     * @param observer
     */
    public  void savePointMaterial(final Map<String,Object> params, Observer<Integer> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Integer>() {
            @Override
            public Observable<CommonResult<Integer>> createObservable(ApiService apiService) {
                return apiService.saveMaterialPoint(params);
            }
        });
    }
    /**
     * 保存采购单的物料清点的方法
     * @param observer
     */
    public  void saveSendMaterialPoint(final Map<String,Object> params, Observer<Integer> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Integer>() {
            @Override
            public Observable<CommonResult<Integer>> createObservable(ApiService apiService) {
                return apiService.saveSendMaterialPoint(params);
            }
        });
    }
    /**
     * 获取采购单物品清点的表体
     * @param observer
     */
    public  void getPODetailsByCode(final Map<String,Object> params, Observer<BuyOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyOrdernoBean>() {
            @Override
            public Observable<CommonResult<BuyOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.getPODetailsByCode(params);
            }
        });
    }
    /**
     * 获取送货单物品清点的表体
     * @param observer
     */
    public  void getASNDetailsByCode(final Map<String,Object> params, Observer<SendOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SendOrdernoBean>() {
            @Override
            public Observable<CommonResult<SendOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.getASNDetailsByCode(params);
            }
        });
    }
    /**
     * 提交物料清点的方法
     * @param observer
     */
    public  void commitMaterialPoint(final Map<String,Object> params, Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.submitMakeOrAuditBill(params);
            }
        });
    }
}
