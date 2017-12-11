package com.timi.sz.wms_android.mvp.UI.stock_in_work.check;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.stockin_work.MaterialDataBean;
import com.timi.sz.wms_android.bean.stockin_work.check.RequestSubmitCheckDataBean;
import com.timi.sz.wms_android.bean.stockin_work.check.SubmitCheckDataResult;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-10 14:28
 */

public class CheckPresenter extends MvpBasePresenter<CheckView> {
    private CheckModel model;
    private HttpSubscriber<MaterialDataBean> materialSubscriber;
    private HttpSubscriber<SubmitCheckDataResult> submitCheckDataResultHttpSubscriber;

    public CheckPresenter(Context context) {
        super(context);
        model = new CheckModel();
    }

    /**
     * 获取物料的信息
     *
     * @param params
     */
    public void getMaterialData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == materialSubscriber) {
            materialSubscriber = new HttpSubscriber<>(new OnResultCallBack<MaterialDataBean>() {
                @Override
                public void onSuccess(MaterialDataBean o) {
                    getView().getMaterialData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getMaterialData(params, materialSubscriber);
    }

    /**
     * 提交盘点
     *
     * @param params
     */
    public void submitCheckData(RequestSubmitCheckDataBean params) {
        getView().showProgressDialog();
        if (null == submitCheckDataResultHttpSubscriber) {
            submitCheckDataResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SubmitCheckDataResult>() {
                @Override
                public void onSuccess(SubmitCheckDataResult o) {
                    getView().submitCheckData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitCheckData(params, submitCheckDataResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != materialSubscriber) {
            materialSubscriber.unSubscribe();
            materialSubscriber = null;
        }
        if (null != submitCheckDataResultHttpSubscriber) {
            submitCheckDataResultHttpSubscriber.unSubscribe();
            submitCheckDataResultHttpSubscriber = null;
        }
    }
}
