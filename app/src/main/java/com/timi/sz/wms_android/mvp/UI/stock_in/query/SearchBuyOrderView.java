package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.ReceiveOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.SaleGoodsReturnBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * 搜索采购单的view
 * author: timi
 * create at: 2017-08-18 17:40
 */
public interface SearchBuyOrderView extends MvpBaseView {
    /**
     * 采购单
     *
     * @param bean
     */
    void buyOrdernoQuery(BuyOrdernoBean bean);

    /**
     * 送货单
     *
     * @param bean
     */
    void sendOrdernoQuery(SendOrdernoBean bean);

    /**
     * 搜索收货单
     *
     * @param bean
     */
    void searchReceiveGoodOrderno(ReceiveOrdernoBean bean);

    /**
     * 产成品-审核
     *
     * @param bean
     */
    void searchFinishGoodsOrderno(QueryPrdInstockByInputResult bean);

    /**
     * 产成品-生单
     *
     * @param bean
     */
    void searchFinishGoodsCreateBillOrderno(FinishGoodsCreateBillBean bean);

    /**
     * 其他入库—选单
     *
     * @param bean
     */
    void searchOtherAuditSelectOrderno(OtherAuditSelectOrdernoBean bean);

    /**
     * 搜索委外退料—选单的单号
     */
    void searchOutReturnMaterialOrderno(QueryOutSourceReturnByInputResult bean);

    /**
     * 搜索生产退料—选单的单号
     */
    void searchProductionReturnMaterialOrderno(QueryPrdReturnByInputResult bean);

    /**
     * 搜索销售退货—选单的单号
     */
    void searchSaleGoodsReturnOrderno(SaleGoodsReturnBean bean);

    /**
     * 设置
     */
    void errorSetEdittextSelect();
}
