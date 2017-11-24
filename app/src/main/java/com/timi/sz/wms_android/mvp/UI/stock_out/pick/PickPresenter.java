package com.timi.sz.wms_android.mvp.UI.stock_out.pick;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-18 15:13
 */

public class PickPresenter extends MvpBasePresenter<PickView> {


    private HttpSubscriber<QueryWWPickDataByOutSourceResult> queryWWPickDataByOutSourceResultHttpSubscriber;
    private HttpSubscriber<QueryProductPickByInputResult> queryPrdPickApplyByInputHttpSubscriber;
    private HttpSubscriber<QueryDNByInputForPickResult> queryDNByInputForPickResultHttpSubscriber;
    private PickMdel  model;
    public PickPresenter(Context context) {
        super(context);
        model=new PickMdel();
    }
    /**
     * 成品拣货
     */
    public void  queryDNByInputForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryDNByInputForPickResultHttpSubscriber) {
            queryDNByInputForPickResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryDNByInputForPickResult>() {
                @Override
                public void onSuccess(QueryDNByInputForPickResult o) {
                    getView().queryDNByInputForPick(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model. queryDNByInputForPick(params, queryDNByInputForPickResultHttpSubscriber);
    }

}
