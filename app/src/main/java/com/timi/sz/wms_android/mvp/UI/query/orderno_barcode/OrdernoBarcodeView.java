package com.timi.sz.wms_android.mvp.UI.query.orderno_barcode;

import com.timi.sz.wms_android.bean.query.response.QueryBillBarcodeResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 09:23
 */

public interface OrdernoBarcodeView extends MvpBaseView {

    void queryBillBarcode(QueryBillBarcodeResult o);
}
