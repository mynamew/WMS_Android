package com.timi.sz.wms_android.mvp.UI.query.todayout;

import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
import com.timi.sz.wms_android.bean.query.response.TodayOutStockDetailResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-25 14:06
 */

public interface TodayOutView extends MvpBaseView {

    void getTodayOutSumeryTotal(StockSummeryResult o);

    void getTodayOutSummeryDetail(List<TodayOutStockDetailResult> o);
}
