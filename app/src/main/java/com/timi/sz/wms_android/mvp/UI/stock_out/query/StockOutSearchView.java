package com.timi.sz.wms_android.mvp.UI.stock_out.query;

import com.timi.sz.wms_android.bean.outstock.outsource.OutSourceFeedBean;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourceFeedByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryOutSourcePickByInputResult;
import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 08:55
 */

public interface StockOutSearchView extends MvpBaseView {
    /**
     * 委外补料
     * @param bean
     */
    void queryOutSourceFeedByInput(QueryOutSourceFeedByInputResult bean);

    /**
     * 委外发料（审核）
     * @param bean
     */
    void queryOutSourcePickByInput(QueryOutSourcePickByInputResult bean);

    /**
     * 委外发料（生单）
     * @param bean
     */
    void queryWWPickDataByOutSource(QueryWWPickDataByOutSourceResult bean);
}
