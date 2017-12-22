package com.timi.sz.wms_android.mvp.UI.list;

import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.FinishGoodsCreateBillBean;
import com.timi.sz.wms_android.bean.instock.search.OtherAuditSelectOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdInstockByInputResult;
import com.timi.sz.wms_android.bean.instock.search.QueryPrdReturnByInputResult;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
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
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 15:20
 */

public interface BuyInStockListView extends MvpBaseView {
    /**
     * 获取列表
     *
     * @param datas
     */
    void queryPOList(List<QueryPoListBean> datas);

    /**
     * 获取采购单的表头 和表体
     *
     * @param bean
     */
    void BuyOrdernoBean(BuyOrdernoBean bean);

    /**
     * 获取采购退料单数据
     *
     * @param bean
     */
    void getPurReturnData(BuyReturnMaterialByOrdernoData bean);

    /**
     * 委外生单
     *
     * @param bean
     */
    void getWWPickDataByOutSource(QueryWWPickDataByOutSourceResult bean);

    /**
     * 委外审核
     *
     * @param bean
     */
    void getOutSourcePickData(QueryOutSourcePickByInputResult bean);

    /**
     * 委外补料
     *
     * @param bean
     */
    void getOutSourceFeedData(QueryOutSourceFeedByInputResult bean);

    /**
     * 委外退料
     *
     * @param bean
     */
    void getOutSourceReturnData(QueryOutSourceReturnByInputResult bean);

    /**
     * 领料申请
     *
     * @param bean
     */
    void getPrdPickApplyData(QueryProductPickByInputResult bean);

    /**
     * 生产领料
     *
     * @param bean
     */
    void getProductPickData(QueryProductPickByInputResult bean);

    /**
     * 生产工单
     *
     * @param bean
     */
    void getPrdPickData(QueryWWPickDataByOutSourceResult bean);

    /**
     * 成品入库
     *
     * @param bean
     */
    void getPrdInstockData(QueryPrdInstockByInputResult bean);

    /**
     * 产成品 生单
     * @param bean
     */
    void getWOInstockData(FinishGoodsCreateBillBean bean);
    /**
     * 生产退料
     *
     * @param bean
     */
    void getPrdReturnData(QueryPrdReturnByInputResult bean);

    /**
     * 获取其他入库单数据
     *
     * @param bean
     */
    void getOtherInstockData(OtherAuditSelectOrdernoBean bean);

    /**
     * 获取其他出库单数据
     *
     * @param bean
     */
    void getOtherOutstockData(QueryOtherOutStockByInputResult bean);

    /**
     * 调拨拣货
     *
     * @param bean
     */
    void getTransferByInputForPick(QueryTransferByInputForPickResult bean);

    /**
     * 发货拣货
     *
     * @param bean
     */
    void getDNDataForPick(QueryDNByInputForPickResult bean);

    void setOrdernoSelect();

    /**
     * 销售拣货
     *
     * @param bean
     */
    void getSalesOutSotckByInputForPick(GetSalesOutSotckByInputForPickResult bean);

    /**
     * 盘点数据
     *
     * @param bean
     */
    void getCheckStockByCode(PointResult bean);

    /**
     * 形态转换
     *
     * @param bean
     */
    void getConvertInByInput(FormChangeInResult bean);

    /**
     * 扫描调入/一步调入
     *
     * @param bean
     */
    void getTransferForStepBy(AllotScanResult bean);

    /**
     * 生产补料返回
     *
     * @param bean
     */
    void getProductPickDataByFeed(QueryPrdFeedByInputResult bean);

    /**
     * 一步调入
     *
     * @param bean
     */
    void getTransferForOneStep(AllotOneSetpResult bean);

    /**
     * 调拨调出数据
     * @param bean
     */
    void getTransferDNDataForPick(QueryAllotOutResult bean);

    /**
     * 形态转换出库
     * @param bean
     */
    void getConvertOutByInput(FormChangeInResult bean);

}
