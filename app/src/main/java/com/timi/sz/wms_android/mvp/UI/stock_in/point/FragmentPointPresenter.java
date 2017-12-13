package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:18
 */

public class FragmentPointPresenter extends MvpBasePresenter<FragmentPointView> {
    FragmentPointModel model = null;
    private HttpSubscriber<Integer> savePointMaterialHttpSubscriber;
    private HttpSubscriber<Integer> saveSendMaterialPointHttpSubscriber;
    private HttpSubscriber<Object> commitPointMaterialHttpSubscriber;
    private HttpSubscriber<BuyOrdernoBean> getPointMaterialHttpSubscriber;
    private HttpSubscriber<SendOrdernoBean> getASNDetailsByCodeHttpSubscriber;

    public FragmentPointPresenter(Context context) {
        super(context);
        model = new FragmentPointModel();
    }

    /**
     * 保存采购单物料清点的方法
     */
    public void savePointMaterial(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == savePointMaterialHttpSubscriber) {
            savePointMaterialHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    getView().savePointMaterial(result);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().dismisProgressDialog();
                }
            });
        }
        model.savePointMaterial(params, savePointMaterialHttpSubscriber);
    }

    /**
     * 保存送货单物料清点的方法
     */
    public void saveSendMaterialPoint(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == savePointMaterialHttpSubscriber) {
            saveSendMaterialPointHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    getView().saveSendPointMaterial(result);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().dismisProgressDialog();
                }
            });
        }
        model.saveSendMaterialPoint(params, saveSendMaterialPointHttpSubscriber);
    }

    /**
     * 提交采购单物料清点的方法
     */
    public void commitMaterialPoint(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == commitPointMaterialHttpSubscriber) {
            commitPointMaterialHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object result) {
                    getView().commitPointMaterial();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.commitMaterialPoint(params, commitPointMaterialHttpSubscriber);
    }

    /**
     * 获取采购单物料清点的表体
     */
    public void getPODetailsByCode(Map<String, Object> params) {
        if (null == getPointMaterialHttpSubscriber) {
            getPointMaterialHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<BuyOrdernoBean>() {
                @Override
                public void onSuccess(BuyOrdernoBean result) {
                    getView().getPODetailsByCode(result);
                    getView().dismisProgressDialog();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getPODetailsByCode(params, getPointMaterialHttpSubscriber);
    }

    /**
     * 获取送货单物料清点的表体
     */
    public void getASNDetailsByCode(Map<String, Object> params) {
        if (null == getPointMaterialHttpSubscriber) {
            getASNDetailsByCodeHttpSubscriber = new HttpSubscriber<>(false,new OnResultCallBack<SendOrdernoBean>() {
                @Override
                public void onSuccess(SendOrdernoBean result) {
                    getView().getSendPODetailsByCode(result);
                    getView().dismisProgressDialog();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().dismisProgressDialog();
                }
            });
        }
        model.getASNDetailsByCode(params, getASNDetailsByCodeHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != savePointMaterialHttpSubscriber) {
            savePointMaterialHttpSubscriber.unSubscribe();
            savePointMaterialHttpSubscriber = null;
        }
        if (null != saveSendMaterialPointHttpSubscriber) {
            saveSendMaterialPointHttpSubscriber.unSubscribe();
            saveSendMaterialPointHttpSubscriber = null;
        }
        if (null != commitPointMaterialHttpSubscriber) {
            commitPointMaterialHttpSubscriber.unSubscribe();
            commitPointMaterialHttpSubscriber = null;
        }
        if (null != getASNDetailsByCodeHttpSubscriber) {
            getASNDetailsByCodeHttpSubscriber.unSubscribe();
            getASNDetailsByCodeHttpSubscriber = null;
        }
    }
}
