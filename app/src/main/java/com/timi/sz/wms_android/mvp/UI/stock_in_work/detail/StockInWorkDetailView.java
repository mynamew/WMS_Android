package com.timi.sz.wms_android.mvp.UI.stock_in_work.detail;

import com.timi.sz.wms_android.bean.outstock.detail.MaterialDetailResult;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-04 15:17
 */

public interface StockInWorkDetailView extends MvpBaseView {


    void queryAllotScanDetail(List<StockInWorkDetailResult> datas);
}
