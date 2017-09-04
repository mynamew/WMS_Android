package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.material;

import com.timi.sz.wms_android.bean.outstock.buy.CommitMaterialScanToOredernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.MaterialBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 08:42
 */

public interface ScanReturnMaterialView extends MvpBaseView {
    void materialScan(MaterialBean bean);
    void commitMaterialScanToOrederno(CommitMaterialScanToOredernoBean bean);
}
