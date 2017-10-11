package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OutReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ProductionReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;
import com.timi.sz.wms_android.view.MyProgressDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索采购单的presenter
 * author: timi
 * create at: 2017-08-18 17:41
 */
public class SearchBuyOrderPresenter extends MvpBasePresenter<SearchBuyOrderView> {
    private SearchBuyModel model;

    private HttpSubscriber<BuyOrdernoBean> mBuyHttpSubscriber = null;//采货单
    private HttpSubscriber<SendOrdernoBean> mSendHttpSubscriber = null;//送货单
    private HttpSubscriber<ReceiveOrdernoBean> mReceiveSubscriber = null;//收货上架
    private HttpSubscriber<FinishGoodsOrdernoBean> mFinishSubscriber = null;//产成品-审核
    private HttpSubscriber<FinishGoodsCreateBillBean> mFinishCreateSubscriber = null;//产成品-生单
    private HttpSubscriber<OtherAuditSelectOrdernoBean> mOtherSubscriber = null;//其他入库-选单
    private HttpSubscriber<OutReturnMaterialBean> mOutSubscriber = null;//委外退料-选单
    private HttpSubscriber<ProductionReturnMaterialBean> mProductionSubscriber = null;//生产退料-选单
    private HttpSubscriber<SaleGoodsReturnBean> mSaleSubscriber = null;//销售退货-选单

    public SearchBuyOrderPresenter(Context context) {
        super(context);
        model = new SearchBuyModel();
    }

    /**
     * 采购单查询的方法
     */
    public void buyOrdernoQuery(Map<String,Object> params) {
        getView().showProgressDialog();
        if (null == mBuyHttpSubscriber) {
            mBuyHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyOrdernoBean>() {
                @Override
                public void onSuccess(BuyOrdernoBean bean) {
                    getView().buyOrdernoQuery(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    BuyOrdernoBean sendOrdernoBean = new BuyOrdernoBean();
                    List<BuyOrdernoBean.DetailResultsBean> datas=new ArrayList<>();
                    for (int i = 0; i <10 ; i++) {
                        BuyOrdernoBean.DetailResultsBean bean=new BuyOrdernoBean.DetailResultsBean();
                        bean.setId(i);
                        bean.setPoLine(i);
                        bean.setArrivalQty(100);
                        bean.setCountQty(20);
                        bean.setGiveQty(40);
                        bean.setInStockQty(50);
                        bean.setMaterialId(i);
                        bean.setMaterialName("和呵呵无聊");
                        bean.setMaterialStandard("实打实");
                        bean.setPoQty(30);
                        bean.setMaterialCode("P2345433");
                        datas.add(bean);
                    }
                    sendOrdernoBean.setDetailResults(datas);
                    BuyOrdernoBean.SummaryResultsBean summaryResultsBean = new BuyOrdernoBean.SummaryResultsBean();
                    summaryResultsBean.setId(1);
                    summaryResultsBean.setReceiveId(1);
                    summaryResultsBean.setBizType(1);
                    summaryResultsBean.setPoCode("P2343232");
                    summaryResultsBean.setPoDate("2017-9-12");
                    summaryResultsBean.setPurEmployeeName("实打实");
                    summaryResultsBean.setSupplierName("雷");
                    sendOrdernoBean.setSummaryResults(summaryResultsBean);
                    getView().buyOrdernoQuery(sendOrdernoBean);
                }
            });
        }
        model.buyOrdernoQuery(params, mBuyHttpSubscriber);
    }

    /**
     * 送货单查询的方法
     */
    public void sendOrdernoQuery(final Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == mSendHttpSubscriber) {
            mSendHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SendOrdernoBean>() {
                @Override
                public void onSuccess(SendOrdernoBean sendOrdernoBean) {
                    getView().sendOrdernoQuery(sendOrdernoBean);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    SendOrdernoBean sendOrdernoBean = new SendOrdernoBean();
                    List<SendOrdernoBean.DetailResultsBean> datas=new ArrayList<>();
                    for (int i = 0; i <10 ; i++) {
                        SendOrdernoBean.DetailResultsBean bean=new SendOrdernoBean.DetailResultsBean();
                        bean.setId(i);
                        bean.setPoLine(i);
                        bean.setArrivalQty(100);
                        bean.setCountQty(20);
                        bean.setDnQty(30);
                        bean.setGiveQty(40);
                        bean.setInStockQty(50);
                        bean.setMaterialId(i);
                        bean.setMaterialName("和呵呵无聊");
                        bean.setMaterialStandard("实打实");
                        bean.setRecvQty(20);
                        bean.setPoQty(30);
                        bean.setMaterialCode("P2345433");
                        datas.add(bean);
                    }
                    sendOrdernoBean.setDetailResults(datas);
                    SendOrdernoBean.SummaryResultsBean summaryResultsBean = new SendOrdernoBean.SummaryResultsBean();
                    summaryResultsBean.setAsnCode(1+"");
                    summaryResultsBean.setAsnDate(1+"");
                    summaryResultsBean.setBizType(1);
                    summaryResultsBean.setCreaterName("2017-9-12");
                    summaryResultsBean.setId(1);
                    summaryResultsBean.setReceiveId(1);
                    sendOrdernoBean.setSummaryResults(summaryResultsBean);
                    getView().sendOrdernoQuery(sendOrdernoBean);
                }
            });
        }
        model.sendOrdernoQuery(params, mSendHttpSubscriber);
    }

    /**
     * 搜索收货的单号
     *
     * @param orderno
     */
    public void searchReceiveGoodOrderno(final String orderno) {
        if (null == mReceiveSubscriber) {
            mReceiveSubscriber = new HttpSubscriber<>(new OnResultCallBack<ReceiveOrdernoBean>() {
                @Override
                public void onSuccess(ReceiveOrdernoBean bean) {
                    getView().searchReceiveGoodOrderno(bean);

                }

                @Override
                public void onError(String errorMsg) {
                    getView().searchReceiveGoodOrderno(new ReceiveOrdernoBean("CD7000101", 200, 100, 50, 50, 50, 50, "2017-8-29"));
                }
            });
        }
        model.searchReceiveGoodOrderno(orderno, mReceiveSubscriber);
    }

    /**
     * 搜索产成品-审核的单号
     *
     * @param orderno
     */
    public void searchFinishGoodsOrderno(final String orderno) {
        if (null == mFinishSubscriber) {
            mFinishSubscriber = new HttpSubscriber<>(new OnResultCallBack<FinishGoodsOrdernoBean>() {
                @Override
                public void onSuccess(FinishGoodsOrdernoBean bean) {
                    getView().searchFinishGoodsOrderno(bean);

                }

                @Override
                public void onError(String errorMsg) {
                    getView().searchFinishGoodsOrderno(new FinishGoodsOrdernoBean("CD7000101", 200, 100, "2017-8-29", 50));
                }
            });
        }
        model.searchFinishGoodsOrderno(orderno, mFinishSubscriber);
    }

    /**
     * 搜索产成品-生单的单号
     *
     * @param orderno
     */
    public void searchFinishGoodsCreateBillOrderno(final String orderno) {
        if (null == mFinishCreateSubscriber) {
            mFinishCreateSubscriber = new HttpSubscriber<>(new OnResultCallBack<FinishGoodsCreateBillBean>() {
                @Override
                public void onSuccess(FinishGoodsCreateBillBean bean) {
                    getView().searchFinishGoodsCreateBillOrderno(bean);

                }

                @Override
                public void onError(String errorMsg) {
                    getView().searchFinishGoodsCreateBillOrderno(new FinishGoodsCreateBillBean("CD7000101", "2017-8-29", 200, 100, 50, 211, 22));
                }
            });
        }
        model.searchFinishGoodsCreateBillOrderno(orderno, mFinishCreateSubscriber);
    }

    /**
     * 搜索其他-生单的单号
     *
     * @param orderno
     */
    public void searchOtherAuditSelectOrderno(final String orderno) {
        if (null == mOtherSubscriber) {
            mOtherSubscriber = new HttpSubscriber<>(new OnResultCallBack<OtherAuditSelectOrdernoBean>() {
                @Override
                public void onSuccess(OtherAuditSelectOrdernoBean bean) {
                    getView().searchOtherAuditSelectOrderno(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().searchOtherAuditSelectOrderno(new OtherAuditSelectOrdernoBean("A21312", 100, 100, "2017-8-29", 100));
                }
            });
        }
        model.searchOtherAuditSelectOrderno(orderno, mOtherSubscriber);
    }

    /**
     * 搜索委外退料—选单的单号
     *
     * @param orderno
     */
    public void searchOutReturnMaterialOrderno(final String orderno) {
        if (null == mOutSubscriber) {
            mOutSubscriber = new HttpSubscriber<>(new OnResultCallBack<OutReturnMaterialBean>() {
                @Override
                public void onSuccess(OutReturnMaterialBean bean) {
                    getView().searchOutReturnMaterialOrderno(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().searchOutReturnMaterialOrderno(new OutReturnMaterialBean("A21312", 100, 100, "2017-8-29", 100));
                }
            });
        }
        model.searchOutReturnMaterialOrderno(orderno, mOutSubscriber);
    }

    /**
     * 搜索生产退料—选单的单号
     *
     * @param orderno
     */
    public void searchProductionReturnMaterialOrderno(final String orderno) {
        if (null == mProductionSubscriber) {
            mProductionSubscriber = new HttpSubscriber<>(new OnResultCallBack<ProductionReturnMaterialBean>() {
                @Override
                public void onSuccess(ProductionReturnMaterialBean bean) {
                    getView().searchProductionReturnMaterialOrderno(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().searchProductionReturnMaterialOrderno(new ProductionReturnMaterialBean("A21312", 100, 100, "2017-8-29", 100));
                }
            });
        }
        model.searchProductionReturnMaterialOrderno(orderno, mProductionSubscriber);
    }

    /**
     * 搜索销售退货—选单的单号
     *
     * @param orderno
     */
    public void searchSaleGoodsReturnOrderno(final String orderno) {
        if (null == mSaleSubscriber) {
            mSaleSubscriber = new HttpSubscriber<>(new OnResultCallBack<SaleGoodsReturnBean>() {
                @Override
                public void onSuccess(SaleGoodsReturnBean bean) {
                    getView().searchSaleGoodsReturnOrderno(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().searchSaleGoodsReturnOrderno(new SaleGoodsReturnBean("A21312", 100, 100, "2017-8-29", 100));
                }
            });
        }
        model.searchSaleGoodsReturnOrderno(orderno, mSaleSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        //采购
        if (null != mBuyHttpSubscriber) {
            mBuyHttpSubscriber.unSubscribe();
            mBuyHttpSubscriber = null;
        }
        //送货
        if (null != mSendHttpSubscriber) {
            mSendHttpSubscriber.unSubscribe();
            mSendHttpSubscriber = null;
        }
        //收货 上架
        if (null != mReceiveSubscriber) {
            mReceiveSubscriber.unSubscribe();
            mReceiveSubscriber = null;
        }
        //产成品
        if (null != mFinishSubscriber) {
            mFinishSubscriber.unSubscribe();
            mFinishSubscriber = null;
        }
        //产成品生单
        if (null != mFinishCreateSubscriber) {
            mFinishCreateSubscriber.unSubscribe();
            mFinishCreateSubscriber = null;
        }
        //其他
        if (null != mOtherSubscriber) {
            mOtherSubscriber.unSubscribe();
            mOtherSubscriber = null;
        }
        //委外
        if (null != mOutSubscriber) {
            mOutSubscriber.unSubscribe();
            mOutSubscriber = null;
        }
        //生产退料
        if (null != mProductionSubscriber) {
            mProductionSubscriber.unSubscribe();
            mProductionSubscriber = null;
        }
        //销售
        if (null != mSaleSubscriber) {
            mSaleSubscriber.unSubscribe();
            mSaleSubscriber = null;
        }
    }
}
