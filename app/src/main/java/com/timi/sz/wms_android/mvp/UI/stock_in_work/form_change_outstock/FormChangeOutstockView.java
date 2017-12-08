package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 08:30
 */

public interface FormChangeOutstockView extends MvpBaseView {
    void submitBarcodeOutAudit(SubmitBarcodeOutAuditData data);

    void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data);

    void submitMakeOrAuditBill();
}
