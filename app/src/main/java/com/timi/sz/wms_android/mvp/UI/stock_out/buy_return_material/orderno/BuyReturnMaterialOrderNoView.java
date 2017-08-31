package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.orderno;

import com.timi.sz.wms_android.bean.outstock.MaterialBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 14:42
 */

public interface BuyReturnMaterialOrderNoView extends MvpBaseView {
    public void materialScanResult(MaterialBean bean);
    public void orderNoAddMaterial();
}
