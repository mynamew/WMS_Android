package com.timi.sz.wms_android.mvp.UI.quity.normal_review;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-17 16:55
 */

public class MRPNormalReviewPresenter extends MvpBasePresenter<MRPNormalReviewView> {
    private MRPNormalReviewModel model;
    private HttpSubscriber<Object> setMrpReviewHttpSubscriber;

    public MRPNormalReviewPresenter(Context context) {
        super(context);
        model = new MRPNormalReviewModel();
    }

    /**
     * 提交评审结果
     *
     * @param params
     */
    public void setMRPReviewData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == setMrpReviewHttpSubscriber) {
            setMrpReviewHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().setMrpReviewData();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.setMRPReviewData(params, setMrpReviewHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != setMrpReviewHttpSubscriber) {
            setMrpReviewHttpSubscriber.unSubscribe();
            setMrpReviewHttpSubscriber = null;
        }
    }
}
