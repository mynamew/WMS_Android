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
 * create at: 2017-08-25 16:19
 */

public class FragmentPointRecordPresenter extends MvpBasePresenter<FragmentPointRecordView> {
    FragmentPointRecordModel model = null;
    private HttpSubscriber<List<StockinMaterialBean>> materialBeanHttpSubscriber;
    private HttpSubscriber<Object> updateHttpSubscriber;
    private HttpSubscriber<Object> deleteHttpSubscriber;

    public FragmentPointRecordPresenter(Context context) {
        super(context);
        model = new FragmentPointRecordModel();
    }

    /**
     * 获取 清点记录
     *
     * @param params
     */
    public void getPointRecord(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == materialBeanHttpSubscriber) {
            materialBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<StockinMaterialBean>>() {
                @Override
                public void onSuccess(List<StockinMaterialBean> datas) {
                    getView().getPointRecord(datas);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.buyOrderNoPointRecord(params, materialBeanHttpSubscriber);

    }

    /**
     * 修改清点记录
     *
     * @param params
     */
    public void updateMaterialPoint(Map<String, Object> params,boolean isBuyOrder) {
        getView().showProgressDialog();
        if (null == updateHttpSubscriber) {
            updateHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object datas) {
                    getView().updatePointRecord();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        if(isBuyOrder) {
            model.updateMaterialPoint(params, updateHttpSubscriber);
        }else {
            model.updateSendMaterialPoint(params, updateHttpSubscriber);
        }
    }

    /**
     * 删除清点记录
     *
     * @param params
     */
    public void deleteMaterialPoint(Map<String, Object> params,boolean isBuyOrder) {
        getView().showProgressDialog();
        if (null == deleteHttpSubscriber) {
            deleteHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object datas) {
                    getView().deletePointRecord();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        if(isBuyOrder) {
            model.deleteMaterialPoint(params, deleteHttpSubscriber);
        }else {
            model.deleteSendMaterialPoint(params, deleteHttpSubscriber);
        }
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != materialBeanHttpSubscriber) {
            materialBeanHttpSubscriber.unSubscribe();
            materialBeanHttpSubscriber = null;
        }
        if (null != updateHttpSubscriber) {
            updateHttpSubscriber.unSubscribe();
            updateHttpSubscriber = null;
        }
        if (null != deleteHttpSubscriber) {
            deleteHttpSubscriber.unSubscribe();
            deleteHttpSubscriber = null;
        }
    }
}
