package com.timi.sz.wms_android.mvp.UI.stock_out.divide_print;

import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutSplitAuditData;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-18 13:45
 */

public interface SplitPrintView extends MvpBaseView {
    void submitBarcodeLotPickOutSplit(SubmitBarcodeLotPickOutSplitResult result);

    void submitBarcodeOutSplitAudit(SubmitBarcodeOutSplitAuditData data);
}
