package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
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
 * $dsc 物料清点的model
 * author: timi
 * create at: 2017-08-25 16:17
 */

public class FragmentPointModel extends MvpBaseModel {
    /**
     * 采购单查询的方法
     */
    public void buyOrdernoQuery(final int orgId, final int userId, final String mac, final String billNo , Observer<BuyOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<BuyOrdernoBean>() {
            @Override
            public Observable<CommonResult<BuyOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.buyOrderNoQuery(orgId,userId,mac,billNo);
            }
        });
    }
    /**
     * 送货单查询的方法
     * @param scanStr
     * @param observer
     */
    public void sendOrdernoQuery(final String scanStr , Observer<SendOrdernoBean> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<SendOrdernoBean>() {
            @Override
            public Observable<CommonResult<SendOrdernoBean>> createObservable(ApiService apiService) {
                return apiService.sendOrdernoQuery(scanStr);
            }
        });
    }

    /**
     * 保存物料清点的方法
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
}
