package com.timi.sz.wms_android.mvp.UI.list;

import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.outsource_return_material.QueryOutSourceReturnByInputResult;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByOrdernoData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
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
     * @param bean
     */
    void getOutSourceFeedData(QueryOutSourceFeedByInputResult bean);

    /**
     * 委外退料
     * @param bean
     */
    void getOutSourceReturnData(QueryOutSourceReturnByInputResult bean);
    /**
     * 领料申请
     * @param bean
     */
    void getPrdPickApplyData(QueryProductPickByInputResult bean);

    /**
     * 生产领料
     * @param bean
     */
    void getProductPickData(QueryProductPickByInputResult bean);

    /**
     * 生产工单
     * @param bean
     */
    void getPrdPickData(QueryWWPickDataByOutSourceResult bean);

}
