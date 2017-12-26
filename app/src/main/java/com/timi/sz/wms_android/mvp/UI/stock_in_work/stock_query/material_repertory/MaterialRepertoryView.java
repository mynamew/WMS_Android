package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory;

import com.timi.sz.wms_android.bean.stockin_work.stock_query.MaterialQueryResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-30 16:33
 */

public interface MaterialRepertoryView extends MvpBaseView{
    /**
     * 物料查询
     * @param o
     */
    void queryStockMaterial(MaterialQueryResult o);
    /**
     * 设置物料条码选中
     */
    void setMaterialEdittextSelect();
}
