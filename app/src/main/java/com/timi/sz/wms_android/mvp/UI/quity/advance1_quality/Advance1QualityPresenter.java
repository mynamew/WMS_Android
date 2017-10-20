package com.timi.sz.wms_android.mvp.UI.quity.advance1_quality;

import android.content.Context;

import com.google.gson.Gson;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-18 16:57
 */

public class Advance1QualityPresenter extends MvpBasePresenter<Advance1QualityView> {
    private Advance1QualityModel model;
    private  HttpSubscriber<GetAdvanceData> getAdvanceDataHttpSubscriber;
    public Advance1QualityPresenter(Context context) {
        super(context);
        model = new Advance1QualityModel();
    }
    /**
     * 获取普通质检的数据
     *
     * @param params
     */
    public void getAdvance1Data(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getAdvanceDataHttpSubscriber) {
            getAdvanceDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetAdvanceData>() {
                @Override
                public void onSuccess(GetAdvanceData o) {
                    getView().getAdvance1Data(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    /**
                     * 测试数据
                     */
                    GetAdvanceData getAdvanceData = new Gson().fromJson("", GetAdvanceData.class);
                    getView().getAdvance1Data(getAdvanceData);
                }
            });
        }
        model.getAdvacen1Data(params, getAdvanceDataHttpSubscriber);
    }
}
