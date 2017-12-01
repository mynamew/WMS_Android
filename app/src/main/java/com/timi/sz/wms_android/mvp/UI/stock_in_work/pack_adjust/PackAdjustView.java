package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust;

import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.LibraryAdjustResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanLocationResult;
import com.timi.sz.wms_android.bean.stockin_work.ScanMaterialResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-22 10:22
 */

public interface PackAdjustView extends MvpBaseView {
    /**
     * 扫 库位码
     * @param bean
     */
    void vertifyLocationCode(VertifyLocationCodeBean bean);

    /**
     * 扫物料码
     * @param result
     */
    void scanMaterialCode(ScanMaterialResult result);

    /**
     * 库位调整
     * @param result
     */
    void libraryAdjust(LibraryAdjustResult result);
}
