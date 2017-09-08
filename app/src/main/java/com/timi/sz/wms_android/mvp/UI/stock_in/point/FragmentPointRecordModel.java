package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
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
 * create at: 2017-08-25 16:19
 */

public class FragmentPointRecordModel extends MvpBaseModel {
    /**
     * 获取 清点记录
     * @param params
     * @param observer
     */
      public void buyOrderNoPointRecord(final Map<String,Object> params, Observer< List<StockinMaterialBean>> observer){
          HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<StockinMaterialBean>>() {
              @Override
              public Observable<CommonResult<List<StockinMaterialBean>>> createObservable(ApiService apiService) {
                  return apiService.buyOrderNoPointRecord(params);
              }
          });
      }
    /**
     * 修改 清点记录
     * @param params
     * @param observer
     */
    public void updateMaterialPoint(final Map<String,Object> params, Observer< Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.updateMaterialPoint(params);
            }
        });
    }
    /**
     * 删除清点记录
     * @param params
     * @param observer
     */
    public void deleteMaterialPoint(final Map<String,Object> params, Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.deleteMaterialPoint(params);
            }
        });
    }
    /**
     * 修改 清点记录
     * @param params
     * @param observer
     */
    public void updateSendMaterialPoint(final Map<String,Object> params, Observer< Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.updateSendMaterialPoint(params);
            }
        });
    }
    /**
     * 删除清点记录
     * @param params
     * @param observer
     */
    public void deleteSendMaterialPoint(final Map<String,Object> params, Observer<Object> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<Object>() {
            @Override
            public Observable<CommonResult<Object>> createObservable(ApiService apiService) {
                return apiService.deleteSendMaterialPoint(params);
            }
        });
    }
}
