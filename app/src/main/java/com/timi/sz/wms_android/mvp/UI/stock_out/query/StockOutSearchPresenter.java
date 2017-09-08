package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 08:56
 */

public class StockOutSearchPresenter extends MvpBasePresenter<StockOutSearchView> {
    private StockOutSearchModel model;
    private HttpSubscriber<OutSourceFeedBean> outSourceFeedBeanHttpSubscriber;

    public StockOutSearchPresenter(Context context) {
        super(context);
        model = new StockOutSearchModel();
    }

    /**
     * 委外退料搜索
     */
    public void searchOutsourceFeed(Map<String, Object> params) {

        if (null == outSourceFeedBeanHttpSubscriber) {
            outSourceFeedBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<OutSourceFeedBean>() {
                @Override
                public void onSuccess(OutSourceFeedBean o) {
                    getView().searchOutsourceFeed(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);

                    List<OutSourceFeedBean.MaterialBean> datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        datas.add(new OutSourceFeedBean.MaterialBean("a23das", "qwdwqdd", 100, 100, 100));
                    }
                    OutSourceFeedBean o = new OutSourceFeedBean("A1231321", "2017-8-25", "电子料仓", "IC检选区", 30, 30, 30, 0, 0, datas);
                    getView().searchOutsourceFeed(o);
                }
            });
        }
        model.searchOutsourceFeed(params, outSourceFeedBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != outSourceFeedBeanHttpSubscriber) {
            outSourceFeedBeanHttpSubscriber.unSubscribe();
            outSourceFeedBeanHttpSubscriber = null;
        }
    }
}
