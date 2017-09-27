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
    private HttpSubscriber<QualityListBean> qualityListBeanHttpSubscriber;

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
                    List<QualityListBean> datas=new ArrayList<>();
                    for (int i = 0; i <20 ; i++) {
                        datas.add(new QualityListBean(i%2==0,"P2323","超然",i*10,i*5,i*5,"合格"));
                    }
                    getView().getQualityList(datas);
                }
            });
        }
        model.getQualityList(params, qualityListBeanHttpSubscriber);
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
