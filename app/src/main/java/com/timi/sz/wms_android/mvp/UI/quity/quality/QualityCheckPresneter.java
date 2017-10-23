package com.timi.sz.wms_android.mvp.UI.quity.quality;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.GetAQLList;
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
    private HttpSubscriber<Object> setAQLValueHttpSubscriber;
    private HttpSubscriber<GetAQLList> getAQLListHttpSubscriber;

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

    /**
     * 获取 aql列表
     *
     * @param params
     * @param position
     */
    public void getAQLList(Map<String, Object> params, final int position) {
        if (null == getAQLListHttpSubscriber) {
            getAQLListHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetAQLList>() {
                @Override
                public void onSuccess(GetAQLList o) {
                    getView().getAQLList(o,position);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getAQLList(params, getAQLListHttpSubscriber);
    }

    /**
     * 设置aql参数
     *
     * @param params
     * @param position
     */
    public void setAQLValue(Map<String, Object> params, final int position) {
        if (null == setAQLValueHttpSubscriber) {
            setAQLValueHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().setAQLValue(position);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.setAQLValue(params, setAQLValueHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != qualityListBeanHttpSubscriber) {
            qualityListBeanHttpSubscriber.unSubscribe();
            qualityListBeanHttpSubscriber = null;
        }
        if (null != setAQLValueHttpSubscriber) {
            setAQLValueHttpSubscriber.unSubscribe();
            setAQLValueHttpSubscriber = null;
        }
        if (null != getAQLListHttpSubscriber) {
            getAQLListHttpSubscriber.unSubscribe();
            getAQLListHttpSubscriber = null;
        }
        if (null != dontNeedQualityHttpSubscriber) {
            dontNeedQualityHttpSubscriber.unSubscribe();
            dontNeedQualityHttpSubscriber = null;
        }
    }
}
