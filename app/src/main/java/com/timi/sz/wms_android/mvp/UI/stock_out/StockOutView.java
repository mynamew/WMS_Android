package com.timi.sz.wms_android.mvp.UI.stock_out;

import com.timi.sz.wms_android.bean.outstock.buy.BuyReturnMaterialByMaterialCodeData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-16 09:24
 */

public interface StockOutView extends MvpBaseView {
    /**
     *获取正在扫描的制单状态的采购退料信息（制单流程）
     * @param materialBean
     */
    void buyReturnMaterialByMaterialCodeData(BuyReturnMaterialByMaterialCodeData materialBean);
}
