package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryPrdFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 08:55
 */

public interface StockOutSearchView extends MvpBaseView {
    /**
     * 委外补料
     *
     * @param bean
     */
    void queryOutSourceFeedByInput(QueryOutSourceFeedByInputResult bean);

    /**
     * 委外发料（审核）
     *
     * @param bean
     */
    void queryOutSourcePickByInput(QueryOutSourcePickByInputResult bean);

    /**
     * 委外调拨
     *
     * @param bean
     */
    void queryWWPickDataByOutSource(QueryWWPickDataByOutSourceResult bean);
    /**====== 生产 ======**/
    /**
     * 生产领料 （生单）搜索
     */
    void queryPrdPickDataByMO(QueryWWPickDataByOutSourceResult bean);

    /**
     * 生产领料 （审核）搜索
     */
    void queryProductPickByInput(QueryProductPickByInputResult bean);

    /**
     * 领料申请单 （审核）搜索
     */
    void queryPrdPickApplyByInput(QueryProductPickByInputResult bean);

    /**
     * 生产补料 （审核）搜索
     */
    void queryPrdFeedByInput(QueryPrdFeedByInputResult bean);

    /**
     * 成品拣货
     * @param bean
     */
    void queryDNByInputForPick(QueryDNByInputForPickResult bean);
}
