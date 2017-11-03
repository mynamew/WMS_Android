package com.timi.sz.wms_android.mvp.UI.stock_in.detail;

import com.timi.sz.wms_android.bean.instock.OrderDetailData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 16:02
 */

public interface StockInDetailView extends MvpBaseView {
    void getReceiptDetail(List<OrderDetailData> datas);
}
