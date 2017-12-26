package com.timi.sz.wms_android.mvp.UI.stock_in_work.barcode_exchange;

import com.timi.sz.wms_android.bean.stockin_work.ContainerAdjustResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-25 08:52
 */

public interface BarcodeExchangeView extends MvpBaseView {
    /**
     * 设置源容器选中
     */
    void setOldPackSelect();

    /**
     * 单一调整
     * @param o
     */
    void barcodeAdjust(ContainerAdjustResult o);
}
