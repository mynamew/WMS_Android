package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import com.timi.sz.wms_android.bean.buy_return.MaterialBean;
import com.timi.sz.wms_android.bean.buy_return.OrderNoBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 17:29
 */

public interface BuyReturnMaterialView extends MvpBaseView {
    public void materialScanResult(MaterialBean bean);
    public void ReturnMaterialOrderNoScanResult(OrderNoBean bean);
}
