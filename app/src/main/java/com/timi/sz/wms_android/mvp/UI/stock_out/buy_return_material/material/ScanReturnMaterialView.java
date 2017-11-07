package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodeOutAuditData;
import com.timi.sz.wms_android.bean.outstock.buy.SubmitBarcodePurReturnData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 08:42
 */

public interface ScanReturnMaterialView extends MvpBaseView {
    void submitBarcodeOutAudit(SubmitBarcodeOutAuditData bean);
    void submitBarcodePurReturn(SubmitBarcodePurReturnData bean);
}
