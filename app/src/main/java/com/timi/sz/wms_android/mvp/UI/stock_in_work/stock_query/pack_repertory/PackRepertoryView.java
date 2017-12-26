package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.pack_repertory;

import com.timi.sz.wms_android.bean.stockin_work.stock_query.QueryStockContainerResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-30 16:29
 */

public interface PackRepertoryView extends MvpBaseView {
    /**
     *  库存查询的返回
     * @param result
     */
    void queryStockContainer(QueryStockContainerResult result);

    /**
     * 设置物料条码选中
     */
    void setMaterialEdittextSelect();
}
