package com.timi.sz.wms_android.mvp.UI.stock_out.outsource_bill;

import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-16 11:19
 */

public interface OutsourcingBillNormalView extends MvpBaseView {
    void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data);

    void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data);

    void submitMakeOrAuditBill();
}
