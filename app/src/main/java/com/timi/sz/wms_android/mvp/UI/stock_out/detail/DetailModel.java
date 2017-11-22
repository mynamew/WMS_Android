package com.timi.sz.wms_android.mvp.UI.stock_out.detail;

import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.bean.outstock.detail.MaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetOutSourcePickDetailResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetWWDetailPickDataResult;
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
 * create at: 2017-09-18 19:57
 */

public class DetailModel extends MvpBaseModel {

    /**
     * 明细 搜索请求
     *
     * @param intentCode
     * @param observer
     */
    public void getDetailPickData(final Map<String, Object> params, final int intentCode, Observer<List<MaterialDetailResult>> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<List<MaterialDetailResult>>() {
            @Override
            public Observable<CommonResult<List<MaterialDetailResult>>> createObservable(ApiService apiService) {

                /**
                 * 不同的intentcode  请求不同
                 */
                switch (intentCode) {
                    case Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料

                        return apiService.getOutSourceFeedDetail(params);

                    case Constants.STOCK_OUT_OUTSOURCE_AUDIT://委外发料-审核

                        return apiService.getOutSourcePickDetail(params);


                    case Constants.STOCK_OUT_PRODUCTION_FEEDING://生产补料

                        return apiService.getPrdFeedDetail(params);

                    case Constants.STOCK_OUT_PRODUCTION_AUDIT://生产领料-审核

                        return apiService.getProductPickDetail(params);

                    case Constants.STOCK_OUT_SELL_OUT_AUDIT://销售领料-审核
                        return apiService.getOutSourceFeedDetail(params);
                    case Constants.STOCK_OUT_SELL_OUT_BILL://销售领料-生单
                        return apiService.getOutSourceFeedDetail(params);
                    case Constants.STOCK_OUT_OTHER_OUT_AUDIT://其他出库-审核
                        return apiService.getOutSourceFeedDetail(params);
                    case Constants.STOCK_OUT_OTHER_OUT_BILL://其他出库-生单
                        return apiService.getOutSourceFeedDetail(params);
                    default:
                        return apiService.getOutSourceFeedDetail(params);
                }
            }
        });
    }
}
