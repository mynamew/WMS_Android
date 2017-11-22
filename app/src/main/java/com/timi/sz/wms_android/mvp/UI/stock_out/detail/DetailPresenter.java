package com.timi.sz.wms_android.mvp.UI.stock_out.detail;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.detail.MaterialDetailResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetOutSourcePickDetailResult;
import com.timi.sz.wms_android.bean.outstock.outsource.GetWWDetailPickDataResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-18 19:57
 */

public class DetailPresenter extends MvpBasePresenter<DetailView> {
    DetailModel model;
    private HttpSubscriber<List<MaterialDetailResult>> getOutSourcePickDetailHttpSubscriber;

    public DetailPresenter(Context context) {
        super(context);
        model = new DetailModel();
    }

    /**
     * 委外审核获取详情
     */
    public void getDetailPickData(Map<String, Object> params, int intentCode) {
        getView().showProgressDialog();
        if (null == getOutSourcePickDetailHttpSubscriber) {
            getOutSourcePickDetailHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<MaterialDetailResult>>() {
                @Override
                public void onSuccess(List<MaterialDetailResult> o) {
                    getView().getDetailPickData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getDetailPickData(params,intentCode, getOutSourcePickDetailHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != getOutSourcePickDetailHttpSubscriber) {
            getOutSourcePickDetailHttpSubscriber.unSubscribe();
            getOutSourcePickDetailHttpSubscriber = null;
        }
    }
}
