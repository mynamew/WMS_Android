package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_detail;

import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-25 15:04
 */

public interface CheckDetailView extends MvpBaseView {
    void getCheckStockDetail(List<StockInWorkDetailResult> datas);
}
