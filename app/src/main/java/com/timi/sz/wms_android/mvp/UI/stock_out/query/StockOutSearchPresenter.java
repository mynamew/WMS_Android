package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 08:56
 */

public class StockOutSearchPresenter extends MvpBasePresenter<StockOutSearchView> {
    private StockOutSearchModel model;
    private HttpSubscriber<QueryOutSourceFeedByInputResult> outSourceFeedBeanHttpSubscriber;
    private HttpSubscriber<QueryOutSourcePickByInputResult> queryOutSourcePickByInputResultHttpSubscriber;


    private HttpSubscriber<QueryWWPickDataByOutSourceResult> queryWWPickDataByOutSourceResultHttpSubscriber;
    private HttpSubscriber<QueryProductPickByInputResult> queryProductPickByInputResultHttpSubscriber;
    private HttpSubscriber<QueryProductPickByInputResult> queryPrdPickApplyByInputHttpSubscriber;
    private HttpSubscriber<QueryPrdFeedByInputResult> queryPrdFeedByInputResultHttpSubscriber;

    public StockOutSearchPresenter(Context context) {
        super(context);
        model = new StockOutSearchModel();
    }

    /**
     * 委外退料搜索
     */
    public void queryOutSourceFeedByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == outSourceFeedBeanHttpSubscriber) {
            outSourceFeedBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryOutSourceFeedByInputResult>() {
                @Override
                public void onSuccess(QueryOutSourceFeedByInputResult o) {
                    getView().queryOutSourceFeedByInput(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryOutSourceFeedByInput(params, outSourceFeedBeanHttpSubscriber);
    }

    /**
     * 委外发料 （审核）搜索
     */
    public void queryOutSourcePickByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryOutSourcePickByInputResultHttpSubscriber) {
            queryOutSourcePickByInputResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryOutSourcePickByInputResult>() {
                @Override
                public void onSuccess(QueryOutSourcePickByInputResult o) {
                    getView().queryOutSourcePickByInput(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryOutSourcePickByInput(params, queryOutSourcePickByInputResultHttpSubscriber);
    }

    /**
     * 委外发料 （生单）搜索
     */
    public void queryWWPickDataByOutSource(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryWWPickDataByOutSourceResultHttpSubscriber) {
            queryWWPickDataByOutSourceResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryWWPickDataByOutSourceResult>() {
                @Override
                public void onSuccess(QueryWWPickDataByOutSourceResult o) {
                    getView().queryWWPickDataByOutSource(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryWWPickDataByOutSource(params, queryWWPickDataByOutSourceResultHttpSubscriber);
    }
    /**====== 生产 ======**/
    /**
     * 生产领料 （生单）搜索
     */
    public void queryPrdPickDataByMO(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryWWPickDataByOutSourceResultHttpSubscriber) {
            queryWWPickDataByOutSourceResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryWWPickDataByOutSourceResult>() {
                @Override
                public void onSuccess(QueryWWPickDataByOutSourceResult o) {
                    getView().queryPrdPickDataByMO(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryPrdPickDataByMO(params, queryWWPickDataByOutSourceResultHttpSubscriber);
    }

    /**
     * 生产领料 （审核）搜索
     */
    public void queryProductPickByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryProductPickByInputResultHttpSubscriber) {
            queryProductPickByInputResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryProductPickByInputResult>() {
                @Override
                public void onSuccess(QueryProductPickByInputResult o) {
                    getView().queryProductPickByInput(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryProductPickByInput(params, queryProductPickByInputResultHttpSubscriber);
    }   /**
     * 生产领料 （审核）搜索
     */
    public void queryPrdPickApplyByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryPrdPickApplyByInputHttpSubscriber) {
            queryPrdPickApplyByInputHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryProductPickByInputResult>() {
                @Override
                public void onSuccess(QueryProductPickByInputResult o) {
                    getView().queryPrdPickApplyByInput(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryPrdPickApplyByInput(params, queryPrdPickApplyByInputHttpSubscriber);
    }

    /**
     * 生产补料搜索
     */
    public void queryPrdFeedByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryPrdFeedByInputResultHttpSubscriber) {
            queryPrdFeedByInputResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryPrdFeedByInputResult>() {
                @Override
                public void onSuccess(QueryPrdFeedByInputResult o) {
                    getView().queryPrdFeedByInput(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryPrdFeedByInput(params, queryPrdFeedByInputResultHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != outSourceFeedBeanHttpSubscriber) {
            outSourceFeedBeanHttpSubscriber.unSubscribe();
            outSourceFeedBeanHttpSubscriber = null;
        }
        if (null != queryWWPickDataByOutSourceResultHttpSubscriber) {
            queryWWPickDataByOutSourceResultHttpSubscriber.unSubscribe();
            queryWWPickDataByOutSourceResultHttpSubscriber = null;
        }
        if (null != queryOutSourcePickByInputResultHttpSubscriber) {
            queryOutSourcePickByInputResultHttpSubscriber.unSubscribe();
            queryOutSourcePickByInputResultHttpSubscriber = null;
        }
        if (null != queryProductPickByInputResultHttpSubscriber) {
            queryProductPickByInputResultHttpSubscriber.unSubscribe();
            queryProductPickByInputResultHttpSubscriber = null;
        }
        if (null != queryWWPickDataByOutSourceResultHttpSubscriber) {
            queryWWPickDataByOutSourceResultHttpSubscriber.unSubscribe();
            queryWWPickDataByOutSourceResultHttpSubscriber = null;
        }
        if (null != queryPrdFeedByInputResultHttpSubscriber) {
            queryPrdFeedByInputResultHttpSubscriber.unSubscribe();
            queryPrdFeedByInputResultHttpSubscriber = null;
        }
    }
}
