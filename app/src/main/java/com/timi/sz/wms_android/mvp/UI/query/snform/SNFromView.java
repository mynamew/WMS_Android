package com.timi.sz.wms_android.mvp.UI.query.snform;

import com.timi.sz.wms_android.bean.query.response.QueryBarcodeTracesResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 10:15
 */

public interface SNFromView extends MvpBaseView {
    void queryBarcodeTraces(QueryBarcodeTracesResult o);
}
