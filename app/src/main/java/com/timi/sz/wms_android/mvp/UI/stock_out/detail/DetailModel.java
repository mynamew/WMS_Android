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

import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_FINISH_GOODS_PICK;

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

                    case Constants.STOCK_OUT_PRODUCTION_APPLY_BILL://领料申请

                        return apiService.getPrdPickApplyDetail(params);

                    case Constants.STOCK_OUT_SELL_OUT_AUDIT://销售领料-审核

                        return apiService.getDNDetail(params);

                    case Constants.STOCK_OUT_SELL_OUT_BILL://销售领料-生单

                        return apiService.getSalesOutStockDetailForOutStock(params);

                    case Constants.STOCK_OUT_OTHER_OUT_AUDIT://其他出库-审核

                        return apiService.getOtherOutStockDetail(params);

                    case Constants.STOCK_OUT_OTHER_OUT_BILL://其他出库-生单
                    case Constants.OTHER_IN_STOCK_SCAN:// 其他扫描 （扫码 不进行单据的查询）
                        return apiService.getMakeOtherInStockDetail(params);
                    case STOCK_OUT_FINISH_GOODS_PICK://成品拣货

                        return apiService.getDNDetail(params);

                    case STOCK_OUT_ALLOT_OUT_PICK://调拨调出

                        return apiService.getTransferDetailForOutStock(params);

                    case Constants.CREATE_PRO_CHECK_NUM://产成品审核

                        return apiService.getPrdInstockDetail(params);

                    case Constants.CREATE_PRO_CREATE_ORDER_NUM://产成品生单

                        return apiService.getWorkOrderDetail(params);

                    case Constants.OTHER_IN_STOCK_SELECT_ORDERNO://其他入库-审核

                        return apiService.getOtherInstockDetail(params);

                    default:

                        return apiService.getOutSourceFeedDetail(params);

                }
            }
        });
    }
}
