package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialOrdernoBean;
import com.timi.sz.wms_android.bean.outstock.buy.OrderNoBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 17:29
 */

public interface BuyReturnMaterialView extends MvpBaseView {
    /**
     * 物料码扫描
     * @param bean
     */
     void materialScanResult(BuyReturnMaterialOrdernoBean bean);

    /**
     * 退单号/扫码
     * @param bean
     */
     void ReturnMaterialOrderNoScanResult(OrderNoBean bean);
}
