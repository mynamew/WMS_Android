package com.timi.sz.wms_android.mvp.UI.quity.advance_quality;

import android.content.Context;

import com.google.gson.Gson;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-12 14:54
 */

public class AdvanceQualityPresenter extends MvpBasePresenter<AdvanceQualityView> {
    private AdvanceQualityModel model;
    private HttpSubscriber<GetAdvance2Data> getAdvance2DataHttpSubscriber;

    public AdvanceQualityPresenter(Context context) {
        super(context);
        model = new AdvanceQualityModel();
    }

    /**
     * 获取普通质检的数据
     *
     * @param params
     */
    public void getAdvance2Data(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getAdvance2DataHttpSubscriber) {
            getAdvance2DataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetAdvance2Data>() {
                @Override
                public void onSuccess(GetAdvance2Data o) {
                    getView().getAdvance2Data(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getAdvance2Data(params, getAdvance2DataHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != getAdvance2DataHttpSubscriber) {
            getAdvance2DataHttpSubscriber.unSubscribe();
            getAdvance2DataHttpSubscriber = null;
        }
    }
}
