package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point_list;

import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-19 15:03
 */

public interface BatchPointListView extends MvpBaseView {
    void submitMakeOrAuditBill();

    void getMaterialLotData(GetMaterialLotData data);

}
