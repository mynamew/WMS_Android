package com.timi.sz.wms_android.mvp.UI.quity.mrp;

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
 * create at: 2017-10-17 15:58
 */

public class MRPReviewPresenter extends MvpBasePresenter<MRPReviewView> {
    private MRPReviewModel model;
    private HttpSubscriber<List<MrpReviewData>> mrpReviewDataHttpSubscriber;

    public MRPReviewPresenter(Context context) {
        super(context);
        model = new MRPReviewModel();
    }

    /**
     * 获取 mrp数据
     */
    public void getMRPReviewData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == mrpReviewDataHttpSubscriber) {
            mrpReviewDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<MrpReviewData>>() {
                @Override
                public void onSuccess(List<MrpReviewData> o) {
                    getView().getMrpReviewData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getMRPReviewData(params, mrpReviewDataHttpSubscriber);

    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != mrpReviewDataHttpSubscriber) {
            mrpReviewDataHttpSubscriber.unSubscribe();
            mrpReviewDataHttpSubscriber = null;
        }
    }
}
