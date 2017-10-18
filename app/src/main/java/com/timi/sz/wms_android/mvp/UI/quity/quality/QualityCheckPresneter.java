package com.timi.sz.wms_android.mvp.UI.quity.quality;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-06 17:06
 */

public class QualityCheckPresneter extends MvpBasePresenter<QualityCheckView> {
    private QualityCheckModel model;
    private HttpSubscriber<List<QualityListBean>> qualityListBeanHttpSubscriber;
    private HttpSubscriber<Object> dontNeedQualityHttpSubscriber;

    public QualityCheckPresneter(Context context) {
        super(context);
        model = new QualityCheckModel();
    }

    /**
     * 获取质量检验的列表
     */
    public void getQualityList(Map<String, Object> params) {
        if (null == qualityListBeanHttpSubscriber) {
            qualityListBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<QualityListBean>>() {
                @Override
                public void onSuccess(List<QualityListBean> o) {
                    getView().getQualityList(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getQualityList(params, qualityListBeanHttpSubscriber);
    }

    /**
     * 免检
     */
    public void submitExemption(Map<String, Object> params, final int position) {
        getView().showProgressDialog();
        if (null == dontNeedQualityHttpSubscriber) {
            dontNeedQualityHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().queryReceiptForIQC(position);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().queryReceiptForIQC(position);
                }
            });
        }
        model.submitExemption(params, dontNeedQualityHttpSubscriber);
    }

    /**
     * 条件查询获取质量检验的列表
     */
    public void queryReceiptForIQC(Map<String, Object> params) {
        if (null == qualityListBeanHttpSubscriber) {
            qualityListBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<QualityListBean>>() {
                @Override
                public void onSuccess(List<QualityListBean> o) {
                    getView().getQualityList(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryReceiptForIQC(params, qualityListBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != qualityListBeanHttpSubscriber) {
            qualityListBeanHttpSubscriber.unSubscribe();
            qualityListBeanHttpSubscriber = null;
        }
    }
}
