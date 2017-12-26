package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.other.OtherOutAndInStockBean;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QuerySalesOutSotckByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryTransferByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.outstock.sale.QueryDNByInputForOutStockResult;
import com.timi.sz.wms_android.bean.outstock.sale.QuerySalesOutSotckByInputForOutStockResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
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
    private HttpSubscriber<QueryDNByInputForOutStockResult> queryDNByInputForOutStockResultHttpSubscriber;
    private HttpSubscriber<QuerySalesOutSotckByInputForOutStockResult> querySalesOutSotckByInputForOutStockResultHttpSubscriber;
    private HttpSubscriber<OtherOutAndInStockBean> otherAuditSelectOrdernoBeanHttpSubscriber;
    private HttpSubscriber<OtherOutAndInStockBean> queryOtherOutStockByInputResultHttpSubscriber;
    //成品拣货
    private HttpSubscriber<QueryDNByInputForPickResult> queryDNByInputForPickResultHttpSubscriber;
    private HttpSubscriber<QuerySalesOutSotckByInputForPickResult> querySalesOutSotckByInputForPickResultHttpSubscriber;
    private HttpSubscriber<QueryTransferByInputForPickResult> queryTransferByInputForPickResultHttpSubscriber;
    //调拨调出
    private HttpSubscriber<QueryAllotOutResult> queryAllotOutResultHttpSubscriber = null;

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
//                    ToastUtils.showShort(errorMsg);
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
//                    ToastUtils.showShort(errorMsg);
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
//                    ToastUtils.showShort(errorMsg);
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
//                    ToastUtils.showShort(errorMsg);
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
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryProductPickByInput(params, queryProductPickByInputResultHttpSubscriber);
    }

    /**
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
//                    ToastUtils.showShort(errorMsg);
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
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryPrdFeedByInput(params, queryPrdFeedByInputResultHttpSubscriber);
    }

    /**
     * 销售出库 审核
     *
     * @param params
     */
    public void queryDNByInputForOutStock(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryDNByInputForOutStockResultHttpSubscriber) {
            queryDNByInputForOutStockResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryDNByInputForOutStockResult>() {
                @Override
                public void onSuccess(QueryDNByInputForOutStockResult o) {
                    getView().queryDNByInputForOutStock(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryDNByInputForOutStock(params, queryDNByInputForOutStockResultHttpSubscriber);
    }
    /**
     * 销售出库  生单
     *
     * @param params
     */
    public void querySalesOutSotckByInputForOutStock(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == querySalesOutSotckByInputForOutStockResultHttpSubscriber) {
            querySalesOutSotckByInputForOutStockResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QuerySalesOutSotckByInputForOutStockResult>() {
                @Override
                public void onSuccess(QuerySalesOutSotckByInputForOutStockResult o) {
                    getView().querySalesOutSotckByInputForOutStock(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.querySalesOutSotckByInputForOutStock(params, querySalesOutSotckByInputForOutStockResultHttpSubscriber);
    }
    /**
     * 其他出库  审核
     *
     * @param params
     */
    public void searchOtherAuditSelectOrderno(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == otherAuditSelectOrdernoBeanHttpSubscriber) {
            otherAuditSelectOrdernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<OtherOutAndInStockBean>() {
                @Override
                public void onSuccess(OtherOutAndInStockBean o) {
                    getView().searchOtherAuditSelectOrderno(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.searchOtherAuditSelectOrderno(params, otherAuditSelectOrdernoBeanHttpSubscriber);
    } /**
     * 其他出库  生单
     *
     * @param params
     */
    public void queryOtherOutStockByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryOtherOutStockByInputResultHttpSubscriber) {
            queryOtherOutStockByInputResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<OtherOutAndInStockBean>() {
                @Override
                public void onSuccess(OtherOutAndInStockBean o) {
                    getView().queryOtherOutStockByInput(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryOtherOutStockByInput(params, queryOtherOutStockByInputResultHttpSubscriber);
    }


    /**
     * 成品拣货  销售出库单末尾号查询
     */
    public void querySalesOutSotckByInputForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == querySalesOutSotckByInputForPickResultHttpSubscriber) {
            querySalesOutSotckByInputForPickResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QuerySalesOutSotckByInputForPickResult>() {
                @Override
                public void onSuccess(QuerySalesOutSotckByInputForPickResult o) {
                    getView().querySalesOutSotckByInputForPick(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.querySalesOutSotckByInputForPick(params, querySalesOutSotckByInputForPickResultHttpSubscriber);
    }
    /**
     * 成品拣货  调拨单末尾号查询
     */
    public void queryTransferByInputForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryTransferByInputForPickResultHttpSubscriber) {
            queryTransferByInputForPickResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryTransferByInputForPickResult>() {
                @Override
                public void onSuccess(QueryTransferByInputForPickResult o) {
                    getView().queryTransferByInputForPick(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryTransferByInputForPick(params, queryTransferByInputForPickResultHttpSubscriber);
    }
    /**
     * 成品拣货  发货通知单末尾号查询
     */
    public void queryDNByInputForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryDNByInputForPickResultHttpSubscriber) {
            queryDNByInputForPickResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryDNByInputForPickResult>() {
                @Override
                public void onSuccess(QueryDNByInputForPickResult o) {
                    getView().queryDNByInputForPick(o);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryDNByInputForPick(params, queryDNByInputForPickResultHttpSubscriber);
    }
    /**
     * 调拨调出
     * @param params
     */
    public void queryTransferByInputForOutStock(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == queryAllotOutResultHttpSubscriber) {
            queryAllotOutResultHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<QueryAllotOutResult>() {
                @Override
                public void onSuccess(QueryAllotOutResult bean) {
                    getView().queryTransferByInputForOutStock(bean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.queryTransferByInputForOutStock(params, queryAllotOutResultHttpSubscriber);
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
        if (null != queryDNByInputForOutStockResultHttpSubscriber) {
            queryDNByInputForOutStockResultHttpSubscriber.unSubscribe();
            queryDNByInputForOutStockResultHttpSubscriber = null;
        }
        if (null != querySalesOutSotckByInputForOutStockResultHttpSubscriber) {
            querySalesOutSotckByInputForOutStockResultHttpSubscriber.unSubscribe();
            querySalesOutSotckByInputForOutStockResultHttpSubscriber = null;
        }
        if (null != otherAuditSelectOrdernoBeanHttpSubscriber) {
            otherAuditSelectOrdernoBeanHttpSubscriber.unSubscribe();
            otherAuditSelectOrdernoBeanHttpSubscriber = null;
        }
        if (null != queryOtherOutStockByInputResultHttpSubscriber) {
            queryOtherOutStockByInputResultHttpSubscriber.unSubscribe();
            queryOtherOutStockByInputResultHttpSubscriber = null;
        }
    }
}
