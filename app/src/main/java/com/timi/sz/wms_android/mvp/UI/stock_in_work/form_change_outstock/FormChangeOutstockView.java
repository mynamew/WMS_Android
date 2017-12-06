package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_outstock;

import com.timi.sz.wms_android.bean.instock.MaterialScanPutAwayBean;
import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 08:30
 */

public interface FormChangeOutstockView extends MvpBaseView {
    /**
     * 物料扫码
     *
     * @param bean
     */
    void materialScanPutAawy(MaterialScanPutAwayBean bean);

    /**
     * 创建入库单
     */
    void createInStockOrderno();
}
