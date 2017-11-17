package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-14 15:22
 */

public interface OutSourceAuditNormalView extends MvpBaseView {

    void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data);

    void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data);

    void submitMakeOrAuditBill();
}
