package com.timi.sz.wms_android.mvp.UI.list;

import android.content.Context;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.list.RequestBuyInStockListBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.other.QueryOtherOutStockByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.GetSalesOutSotckByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryTransferByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeInResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.Map;

import static com.timi.sz.wms_android.base.uils.Constants.BUY_ORDE_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.CREATE_PRO_CHECK_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.CREATE_PRO_CREATE_ORDER_NUM;
import static com.timi.sz.wms_android.base.uils.Constants.CREATE_RETURN_MATERAIL;
import static com.timi.sz.wms_android.base.uils.Constants.OTHER_IN_STOCK_SELECT_ORDERNO;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_RETURN_MATERAIL;
import static com.timi.sz.wms_android.base.uils.Constants.OUT_SOURCE;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_ALLOT_ONE_STEP;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_ALLOT_SCAN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_FORM_CHANGE_IN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_IN_WORK_FORM_CHANGE_OUT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_ALLOT_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OTHER_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_OUTSOURCE_FEED_SUPLLIEMENT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_ALLOT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_APPLY_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PRODUCTION_FEEDING;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_PURCHASE_MATERIAL_RETURN;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SALE_OUT_PICK;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_AUDIT;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SELL_OUT_BILL;
import static com.timi.sz.wms_android.base.uils.Constants.STOCK_OUT_SEND_OUT_PICK;

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
    //生产补料
    private HttpSubscriber<QueryPrdFeedByInputResult> produtionFeedOrderno;
    //成品入库审核
    private HttpSubscriber<QueryPrdInstockByInputResult> finishGoodInstock;
    //成品入库生单
    private HttpSubscriber<FinishGoodsCreateBillBean> finishGoodInstockBill;
    //其他入库
    private HttpSubscriber<OtherAuditSelectOrdernoBean> otherInStock;
    //其他出库
    private HttpSubscriber<QueryOtherOutStockByInputResult> otherOutStock;
    //发货拣货
    private HttpSubscriber<QueryDNByInputForPickResult> sendMaterailPick;
    //销售拣货
    private HttpSubscriber<GetSalesOutSotckByInputForPickResult> salePick;
    //调拨拣货
    private HttpSubscriber<QueryTransferByInputForPickResult> allotPick;
    //扫描调入
    private HttpSubscriber<AllotScanResult> scanAllot;
    //一步调入
    private HttpSubscriber<AllotOneSetpResult> oneStepAllot;
    //调拨调出
    private HttpSubscriber<QueryAllotOutResult> allotallotout;
    //形态转换入
    private HttpSubscriber<FormChangeInResult> formInChange;
    //形态转换出
    private HttpSubscriber<FormChangeOutResult> formOutChange;
    //盘点
    private HttpSubscriber<PointResult> check;

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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
            case STOCK_OUT_PRODUCTION_ALLOT://生产领料列表（生单）--生产工单列表
                model.queryPrdList(params, httpSubscriber);
                break;
            case STOCK_OUT_PRODUCTION_AUDIT://生产领料列表（审核）--生产领料单列表
                model.queryPrdPickList(params, httpSubscriber);
                break;
            case STOCK_OUT_PRODUCTION_FEEDING://生产补单列表
                model.queryPrdFeedList(params, httpSubscriber);
                break;
            case STOCK_OUT_PRODUCTION_APPLY_BILL://查询生产领料单列表
                model.getPickApplyList(params, httpSubscriber);
                break;
            case CREATE_RETURN_MATERAIL://生产退料
                model.queryPrdReturnList(params, httpSubscriber);
                break;
            case CREATE_PRO_CHECK_NUM://查询成品入库单(审核)列表
                model.queryPrdInstockByInput(params, httpSubscriber);
                break;
            case CREATE_PRO_CREATE_ORDER_NUM://查询成品入库单(生单)列表
                model.queryWOInstockList(params, httpSubscriber);
                break;
            case OTHER_IN_STOCK_SELECT_ORDERNO://查询其他入库单 列表
                model.queryOtherInstockList(params, httpSubscriber);
                break;
            case STOCK_OUT_OTHER_OUT_AUDIT://查询其他出库单 列表
                model.queryOtherOutstockList(params, httpSubscriber);
                break;
            case STOCK_OUT_SALE_OUT_PICK://销售拣货
            case STOCK_OUT_SELL_OUT_AUDIT://销售审核
                model.querySalesOutStockList(params, httpSubscriber);
                break;
            case STOCK_OUT_SEND_OUT_PICK://发货拣货
            case STOCK_OUT_SELL_OUT_BILL://销售生单
                model.queryDNOutList(params, httpSubscriber);
                break;
            case STOCK_OUT_ALLOT_OUT_PICK://调拨拣货
                model.QueryTransferListForOutStock(params, httpSubscriber);
                break;
            case STOCK_OUT_ALLOT_OUT://调拨调出
                model.queryTransferListForOutStock(params, httpSubscriber);
                break;
            case STOCK_IN_WORK_ALLOT_SCAN://扫描调入
            case STOCK_IN_WORK_ALLOT_ONE_STEP://一步调入
                model.queryTransferListForOutStock(params, httpSubscriber);
                break;
            case STOCK_IN_WORK_FORM_CHANGE_OUT://形态转换出库
            case STOCK_IN_WORK_FORM_CHANGE_IN://形态转换入库
                model.queryConvertList(params, httpSubscriber);
                break;
            case Constants.STOCK_IN_WORK_POINT://盘点
                model.queryCheckStockList(params, httpSubscriber);
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getPrdPickData(params, produtionWorkOrderno);
    }

    /**
     * 生产领料单数据
     *
     * @param params
     */
    public void getProductPickData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == produtionGetMaterial) {
            produtionGetMaterial = new HttpSubscriber<>(new OnResultCallBack<QueryProductPickByInputResult>() {
                @Override
                public void onSuccess(QueryProductPickByInputResult bean) {
                    getView().getProductPickData(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getProductPickData(params, produtionGetMaterial);
    }

    /**
     * 产成品审核数据
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getPrdInstockData(params, finishGoodInstock);
    }

    /**
     * 产成品生单数据
     *
     * @param params
     */
    public void getWOInstockData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == finishGoodInstockBill) {
            finishGoodInstockBill = new HttpSubscriber<>(new OnResultCallBack<FinishGoodsCreateBillBean>() {
                @Override
                public void onSuccess(FinishGoodsCreateBillBean bean) {
                    getView().getWOInstockData(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getWOInstockData(params, finishGoodInstockBill);
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
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getPrdReturnData(params, produtionReturnOrderno);
    }

    /**
     * 生产补料单数据
     *
     * @param params
     */
    public void getProductPickDataByFeed(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == produtionFeedOrderno) {
            produtionFeedOrderno = new HttpSubscriber<>(new OnResultCallBack<QueryPrdFeedByInputResult>() {
                @Override
                public void onSuccess(QueryPrdFeedByInputResult bean) {
                    getView().getProductPickDataByFeed(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getProductPickDataByFeed(params, produtionFeedOrderno);
    }

    /**
     * 其他入库单数据
     *
     * @param params
     */
    public void getOtherInstockData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == otherInStock) {
            otherInStock = new HttpSubscriber<>(new OnResultCallBack<OtherAuditSelectOrdernoBean>() {
                @Override
                public void onSuccess(OtherAuditSelectOrdernoBean bean) {
                    getView().getOtherInstockData(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getOtherInstockData(params, otherInStock);
    }

    /**
     * 其他出库单数据
     *
     * @param params
     */
    public void getOtherOutstockData(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == otherOutStock) {
            otherOutStock = new HttpSubscriber<>(new OnResultCallBack<QueryOtherOutStockByInputResult>() {
                @Override
                public void onSuccess(QueryOtherOutStockByInputResult bean) {
                    getView().getOtherOutstockData(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getOtherOutstockData(params, otherOutStock);
    }

    /**
     * 根据发货通知单号获得发货通知单拣货数据。
     *
     * @param params
     */
    public void getDNDataForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == sendMaterailPick) {
            sendMaterailPick = new HttpSubscriber<>(new OnResultCallBack<QueryDNByInputForPickResult>() {
                @Override
                public void onSuccess(QueryDNByInputForPickResult bean) {
                    getView().setOrdernoSelect();
                    getView().getDNDataForPick(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getDNDataForPick(params, sendMaterailPick);
    }

    /**
     * 根据调拨单号获得调拨单拣货数据。
     *
     * @param params
     */
    public void getTransferByInputForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == allotPick) {
            allotPick = new HttpSubscriber<>(new OnResultCallBack<QueryTransferByInputForPickResult>() {
                @Override
                public void onSuccess(QueryTransferByInputForPickResult bean) {
                    getView().getTransferByInputForPick(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getTransferByInputForPick(params, allotPick);
    }

    /**
     * 根据销售单号获得调拨单拣货数据。
     *
     * @param params
     */
    public void getSalesOutSotckByInputForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == salePick) {
            salePick = new HttpSubscriber<>(new OnResultCallBack<GetSalesOutSotckByInputForPickResult>() {
                @Override
                public void onSuccess(GetSalesOutSotckByInputForPickResult bean) {
                    getView().getSalesOutSotckByInputForPick(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getSalesOutSotckByInputForPick(params, salePick);
    }

    /**
     * 根据扫描调入单获取扫描调入数据
     *
     * @param params
     */
    public void getTransferForStepBy(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == scanAllot) {
            scanAllot = new HttpSubscriber<>(new OnResultCallBack<AllotScanResult>() {
                @Override
                public void onSuccess(AllotScanResult bean) {
                    getView().getTransferForStepBy(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getTransferForStepBy(params, scanAllot);
    }

    /**
     * 根据一步调入单获取一步调入数据
     *
     * @param params
     */
    public void getTransferForOneStep(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == oneStepAllot) {
            oneStepAllot = new HttpSubscriber<>(new OnResultCallBack<AllotOneSetpResult>() {
                @Override
                public void onSuccess(AllotOneSetpResult bean) {
                    getView().getTransferForOneStep(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getTransferForOneStep(params, oneStepAllot);
    }

    /**
     * 根据调拨调出单获取一步调入数据
     *
     * @param params
     */
    public void getTransferDNDataForPick(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == allotallotout) {
            allotallotout = new HttpSubscriber<>(new OnResultCallBack<QueryAllotOutResult>() {
                @Override
                public void onSuccess(QueryAllotOutResult bean) {
                    getView().getTransferDNDataForPick(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getTransferDNDataForPick(params, allotallotout);
    }

    /**
     * 根据形态转换入获取形态转换数据
     *
     * @param params
     */
    public void getConvertInByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == formInChange) {
            formInChange = new HttpSubscriber<>(new OnResultCallBack<FormChangeInResult>() {
                @Override
                public void onSuccess(FormChangeInResult bean) {
                    getView().getConvertInByInput(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getConvertInByInput(params, formInChange);
    }

    /**
     * 根据形态转换出单获取形态转换数据
     *
     * @param params
     */
    public void getConvertOutByInput(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == formOutChange) {
            formOutChange = new HttpSubscriber<>(new OnResultCallBack<FormChangeInResult>() {
                @Override
                public void onSuccess(FormChangeInResult bean) {
                    getView().getConvertOutByInput(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getConvertOutByInput(params, formOutChange);
    }

    /**
     * 根据盘点单获取盘点数据
     *
     * @param params
     */
    public void getCheckStockByCode(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == check) {
            check = new HttpSubscriber<>(new OnResultCallBack<PointResult>() {
                @Override
                public void onSuccess(PointResult bean) {
                    getView().getCheckStockByCode(bean);
                    getView().setOrdernoSelect();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    getView().setOrdernoSelect();
                }
            });
        }
        model.getCheckStockByCode(params, check);
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
        if (null != otherInStock) {
            otherInStock.unSubscribe();
            otherInStock = null;
        }
        if (null != otherOutStock) {
            otherOutStock.unSubscribe();
            otherOutStock = null;
        }
        if (null != sendMaterailPick) {
            sendMaterailPick.unSubscribe();
            sendMaterailPick = null;
        }
        if (null != salePick) {
            salePick.unSubscribe();
            salePick = null;
        }
        if (null != allotPick) {
            allotPick.unSubscribe();
            allotPick = null;
        }
        if (null != check) {
            check.unSubscribe();
            check = null;
        }
        if (null != scanAllot) {
            scanAllot.unSubscribe();
            scanAllot = null;
        }
        if (null != formOutChange) {
            formOutChange.unSubscribe();
            formOutChange = null;
        }
        if (null != formInChange) {
            formInChange.unSubscribe();
            formInChange = null;
        }
        if (null != produtionFeedOrderno) {
            produtionFeedOrderno.unSubscribe();
            produtionFeedOrderno = null;
        }
        if (null != oneStepAllot) {
            oneStepAllot.unSubscribe();
            oneStepAllot = null;
        }
        if (null != allotallotout) {
            allotallotout.unSubscribe();
            allotallotout = null;
        }
        if (null != finishGoodInstockBill) {
            finishGoodInstockBill.unSubscribe();
            finishGoodInstockBill = null;
        }
    }
}
