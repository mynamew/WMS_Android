package com.timi.sz.wms_android.mvp.UI.stock_in.other_scan;

import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-31 16:51
 */

public interface OtherScanView extends MvpBaseView {

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
     * @param isCreateSuccess
     */
    void createInStockOrderno(boolean isCreateSuccess);
}
