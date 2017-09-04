package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import android.content.Context;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.OutReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ProductionReturnMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.MaterialBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.http.api.CommonResult;
import com.timi.sz.wms_android.http.callback.ApiServiceMethodCallBack;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;

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
     *
     * @param scanStr
     */
    public void buyOrdernoQuery(final String scanStr) {
        if (null == mBuyHttpSubscriber) {
            mBuyHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyOrdernoBean>() {
                @Override
                public void onSuccess(BuyOrdernoBean bean) {
                    getView().buyOrdernoQuery(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    //请求失败 加入假数据
                    List<BuyOrdernoBean.MarterialBean> datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        datas.add(new BuyOrdernoBean.MarterialBean(i + "", "M42324232" + i, "50", "50", "100", "10", "20"));
                    }
                    BuyOrdernoBean buyOrdernoBean = new BuyOrdernoBean("B789678", "邢力丰", "深圳超然科技股份有限公司", "2017-8-29", datas);
                    getView().buyOrdernoQuery(buyOrdernoBean);
                }
            });
        }
        model.buyOrdernoQuery(scanStr, mBuyHttpSubscriber);
    }

    /**
     * 送货单查询的方法
     *
     * @param scanStr
     */
    public void sendOrdernoQuery(final String scanStr) {
        if (null == mSendHttpSubscriber) {
            mSendHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SendOrdernoBean>() {
                @Override
                public void onSuccess(SendOrdernoBean sendOrdernoBean) {

                }

                @Override
                public void onError(String errorMsg) {
                    //请求失败 加入假数据
                    List<SendOrdernoBean.MarterialBean> datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        datas.add(new SendOrdernoBean.MarterialBean(i + "", "M42324232" + i, "200", 200, 50, "50", "100", "10", "20", "20"));
                    }
                    SendOrdernoBean sendOrdernoBean = new SendOrdernoBean("B789678", "邢力丰", "深圳超然科技股份有限公司", "2017-8-29", datas);
                    getView().sendOrdernoQuery(sendOrdernoBean);
                }
            });
        }
        model.sendOrdernoQuery(scanStr, mSendHttpSubscriber);
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
