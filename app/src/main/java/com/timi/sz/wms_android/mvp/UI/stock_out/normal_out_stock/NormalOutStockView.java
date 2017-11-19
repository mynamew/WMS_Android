package com.timi.sz.wms_android.mvp.UI.stock_out.normal_out_stock;

import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc  普通出库的view
 * author: timi
 * create at: 2017-11-19 08:57
 */

public interface NormalOutStockView extends MvpBaseView {
    void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data);

    void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data);

    void submitMakeOrAuditBill();
}
