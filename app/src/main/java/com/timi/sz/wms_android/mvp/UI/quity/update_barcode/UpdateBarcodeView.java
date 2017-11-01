package com.timi.sz.wms_android.mvp.UI.quity.update_barcode;

import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.bean.quality.update_barcode.BarEditGetUnInstockBarcodeData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-13 08:50
 */

public interface UpdateBarcodeView extends MvpBaseView {
    void barEditGetUnInstockBarcodeData(BarEditGetUnInstockBarcodeData data, String result);
    void barEditSetUnInstockBarcodeData(int packQty, int rejectNum);
}
