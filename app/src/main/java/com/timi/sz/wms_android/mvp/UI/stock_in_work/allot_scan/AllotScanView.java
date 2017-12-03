package com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_scan;

import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 14:58
 */

public interface AllotScanView extends MvpBaseView {

    /**
     * 物料扫码
     * @param bean
     */
    void materialScanResult(MaterialScanPutAwayBean bean);

    /**
     * 库位码是否有效
     * @param bean
     */
    void vertifyLocationCode(VertifyLocationCodeBean bean);

    /**
     * 创建入库单
     */
    void createInStockOrderno();
}
