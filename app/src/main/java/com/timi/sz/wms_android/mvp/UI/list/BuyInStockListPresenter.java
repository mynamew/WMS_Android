package com.timi.sz.wms_android.mvp.UI.list;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.CREATE_PRO_CHECK_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.CREATE_RETURN_MATERAIL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_RETURN_MATERAIL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_SOURCE;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 15:21
 */

public class BuyInStockListPresenter extends MvpBasePresenter<BuyInStockListView> {
    private HttpSubscriber<List<QueryPoListBean>> httpSubscriber;
    //采购单/委外单
    private HttpSubscriber<BuyOrdernoBean> ordernoBeanHttpSubscriber;
    //采购退料
    private HttpSubscriber<BuyReturnMaterialByOrdernoData> purReturnDataHttpSubscriber;
    //委外审核
    private HttpSubscriber<QueryOutSourcePickByInputResult> outsourceAudit;
    //委外制单
    private HttpSubscriber<QueryWWPickDataByOutSourceResult> outsourceBill;
    //委外补料
    private HttpSubscriber<QueryOutSourceFeedByInputResult> outsourceFeed;
    //委外退料
    private HttpSubscriber<QueryOutSourceReturnByInputResult> outsourceReturn;
    //领料申请
    private HttpSubscriber<QueryProductPickByInputResult> getMaterialApply;
    //生产领料
    private HttpSubscriber<QueryProductPickByInputResult> produtionGetMaterial;
    //生产工单
    private HttpSubscriber<QueryWWPickDataByOutSourceResult> produtionWorkOrderno;
    //生产退料单
    private HttpSubscriber<QueryPrdReturnByInputResult> produtionReturnOrderno;
    //成品入库
    private HttpSubscriber<QueryPrdInstockByInputResult> finishGoodInstock;

    private BuyInStockListModel model;

    public BuyInStockListPresenter(Context context) {
        super(context);
        model = new BuyInStockListModel();
    }

    /**
     * 采购单列表
     *
     * @param params
     */
    public void queryPOList(RequestBuyInStockListBean params, int intentCode) {
        getView().showProgressDialog();
        if (null == httpSubscriber) {
            httpSubscriber = new HttpSubscriber<>(new OnResultCallBack<List<QueryPoListBean>>() {
                @Override
                public void onSuccess(List<QueryPoListBean> datas) {
                    getView().queryPOList(datas);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        switch (intentCode) {
            case BUY_ORDE_NUM://采购
                model.queryPOList(params, httpSubscriber);
                break;
            case OUT_SOURCE://委外
                model.queryWWPOList(params, httpSubscriber);
                break;
            case STOCK_OUT_PURCHASE_MATERIAL_RETURN://采购退料
                model.queryPurReturnList(params, httpSubscriber);
                break;
            case STOCK_OUT_OUTSOURCE_BILL://委外列表(制单)
                model.queryWWPOListForPick(params, httpSubscriber);
                break;
            case STOCK_OUT_OUTSOURCE_AUDIT://委外列表(审核)
                model.queryWWPickList(params, httpSubscriber);
                break;
            case STOCK_OUT_OUTSOURCE_ALLOT://委外调拨列表
                model.queryWWPOListForPick(params, httpSubscriber);
                break;
            case STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT://委外补料列表
                model.queryWWFeedList(params, httpSubscriber);
                break;
            case OUT_RETURN_MATERAIL://委外退料列表
                model.queryWWReturnList(params, httpSubscriber);
                break;
            case STOCK_OUT_PRODUCTION_BILL://生产领料列表（生单）--生产工单列表
                model.queryPrdList(params, httpSubscriber);
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产领料列表（审核）--生产领料单列表
                model.queryPrdPickList(params, httpSubscriber);
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL://查询生产领料单列表
                model.getPickApplyList(params, httpSubscriber);
                break;
            case CREATE_RETURN_MATERAIL://生产退料
                model.queryPrdReturnList(params, httpSubscriber);
                break;
            case CREATE_PRO_CHECK_NUM://查询成品入库单列表
                model.queryPrdInstockByInput(params, httpSubscriber);
                break;
        }

    }

    /**
     * 采购单表头 表体
     *
     * @param params
     */
    public void getPODataByCode(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == ordernoBeanHttpSubscriber) {
            ordernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyOrdernoBean>() {
                @Override
                public void onSuccess(BuyOrdernoBean bean) {
                    getView().BuyOrdernoBean(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPODataByCode(params, ordernoBeanHttpSubscriber);
    }

    /**
     * 采购退料数据
     *
     * @param params
     */
    public void getPurReturnData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == purReturnDataHttpSubscriber) {
            purReturnDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyReturnMaterialByOrdernoData>() {
                @Override
                public void onSuccess(BuyReturnMaterialByOrdernoData bean) {
                    getView().getPurReturnData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPurReturnData(params, purReturnDataHttpSubscriber);
    }

    /**
     * 委外审核数据
     *
     * @param params
     */
    public void getOutSourcePickData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == outsourceAudit) {
            outsourceAudit = new HttpSubscriber<>(new OnResultCallBack<QueryOutSourcePickByInputResult>() {
                @Override
                public void onSuccess(QueryOutSourcePickByInputResult bean) {
                    getView().getOutSourcePickData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getOutSourcePickData(params, outsourceAudit);
    }

    /**
     * 委外生单数据
     *
     * @param params
     */
    public void getWWPickDataByOutSource(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == outsourceBill) {
            outsourceBill = new HttpSubscriber<>(new OnResultCallBack<QueryWWPickDataByOutSourceResult>() {
                @Override
                public void onSuccess(QueryWWPickDataByOutSourceResult bean) {
                    getView().getWWPickDataByOutSource(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getWWPickDataByOutSource(params, outsourceBill);
    }

    /**
     * 委外补料数据
     *
     * @param params
     */
    public void getOutSourceReturnData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == outsourceFeed) {
            outsourceReturn = new HttpSubscriber<>(new OnResultCallBack<QueryOutSourceReturnByInputResult>() {
                @Override
                public void onSuccess(QueryOutSourceReturnByInputResult bean) {
                    getView().getOutSourceReturnData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getOutSourceReturnData(params, outsourceReturn);
    }

    /**
     * 委外补料数据
     *
     * @param params
     */
    public void getOutSourceFeedData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == outsourceFeed) {
            outsourceFeed = new HttpSubscriber<>(new OnResultCallBack<QueryOutSourceFeedByInputResult>() {
                @Override
                public void onSuccess(QueryOutSourceFeedByInputResult bean) {
                    getView().getOutSourceFeedData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getOutSourceFeedData(params, outsourceFeed);
    }

    /**
     * 领料申请数据
     *
     * @param params
     */
    public void getPrdPickApplyData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getMaterialApply) {
            getMaterialApply = new HttpSubscriber<>(new OnResultCallBack<QueryProductPickByInputResult>() {
                @Override
                public void onSuccess(QueryProductPickByInputResult bean) {
                    getView().getPrdPickApplyData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPrdPickApplyData(params, getMaterialApply);
    }

    /**
     * 生产工单数据
     *
     * @param params
     */
    public void getPrdPickData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == produtionWorkOrderno) {
            produtionWorkOrderno = new HttpSubscriber<>(new OnResultCallBack<QueryWWPickDataByOutSourceResult>() {
                @Override
                public void onSuccess(QueryWWPickDataByOutSourceResult bean) {
                    getView().getPrdPickData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPrdPickData(params, produtionWorkOrderno);
    }

    /**
     * 成品入库单数据
     *
     * @param params
     */
    public void getPrdInstockData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == produtionGetMaterial) {
            produtionGetMaterial = new HttpSubscriber<>(new OnResultCallBack<QueryPrdInstockByInputResult>() {
                @Override
                public void onSuccess(QueryPrdInstockByInputResult bean) {
                    getView().getPrdInstockData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPrdInstockData(params, finishGoodInstock);
    }

    /**
     * 生产领料单数据
     *
     * @param params
     */
    public void finishGoodInstock(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == finishGoodInstock) {
            finishGoodInstock = new HttpSubscriber<>(new OnResultCallBack<QueryPrdInstockByInputResult>() {
                @Override
                public void onSuccess(QueryPrdInstockByInputResult bean) {
                    getView().getPrdInstockData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPrdInstockData(params, finishGoodInstock);
    }

    /**
     * 生产退料单数据
     *
     * @param params
     */
    public void getPrdReturnData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == produtionReturnOrderno) {
            produtionReturnOrderno = new HttpSubscriber<>(new OnResultCallBack<QueryPrdReturnByInputResult>() {
                @Override
                public void onSuccess(QueryPrdReturnByInputResult bean) {
                    getView().getPrdReturnData(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.getPrdReturnData(params, produtionReturnOrderno);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != httpSubscriber) {
            httpSubscriber.unSubscribe();
            httpSubscriber = null;
        }
        if (null != purReturnDataHttpSubscriber) {
            purReturnDataHttpSubscriber.unSubscribe();
            purReturnDataHttpSubscriber = null;
        }
        if (null != ordernoBeanHttpSubscriber) {
            ordernoBeanHttpSubscriber.unSubscribe();
            ordernoBeanHttpSubscriber = null;
        }
        if (null != outsourceFeed) {
            outsourceFeed.unSubscribe();
            outsourceFeed = null;
        }
        if (null != outsourceBill) {
            outsourceBill.unSubscribe();
            outsourceBill = null;
        }
        if (null != outsourceAudit) {
            outsourceAudit.unSubscribe();
            outsourceAudit = null;
        }
        if (null != getMaterialApply) {
            getMaterialApply.unSubscribe();
            getMaterialApply = null;
        }
        if (null != produtionGetMaterial) {
            produtionGetMaterial.unSubscribe();
            produtionGetMaterial = null;
        }
        if (null != produtionWorkOrderno) {
            produtionWorkOrderno.unSubscribe();
            produtionWorkOrderno = null;
        }
        if (null != produtionReturnOrderno) {
            produtionReturnOrderno.unSubscribe();
            produtionReturnOrderno = null;
        }
        if (null != finishGoodInstock) {
            finishGoodInstock.unSubscribe();
            finishGoodInstock = null;
        }
    }
}
