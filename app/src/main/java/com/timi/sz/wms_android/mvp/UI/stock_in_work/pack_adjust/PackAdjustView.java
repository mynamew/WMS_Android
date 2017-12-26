package com.timi.sz.wms_android.mvp.UI.stock_in_work.pack_adjust;

import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.bean.stockin_work.ContainerAdjustResult;
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
     * 容器调整
     * @param o
     */
    void containnerAdjust(ContainerAdjustResult o);
    /**
     * 设置源容器选中
     */
    void setOldPackSelect();
}
