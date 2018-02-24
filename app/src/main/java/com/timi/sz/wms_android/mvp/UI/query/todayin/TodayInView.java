package com.timi.sz.wms_android.mvp.UI.query.todayin;

import com.timi.sz.wms_android.bean.query.response.StockSummeryResult;
import com.timi.sz.wms_android.bean.query.response.TodayInStockDetailResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-25 11:00
 */

public interface TodayInView extends MvpBaseView {
      void getTodayInSummeryTotal(StockSummeryResult o);

    void getTodayInSummeryDetail(List<TodayInStockDetailResult> o);
}
