package com.timi.sz.wms_android.mvp.UI.stock_out.detail.outsource_bill_detail;

import com.timi.sz.wms_android.bean.outstock.detail.BillMaterialDetailResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-21 01:29
 */

public interface OutsourceBillDetaiView extends MvpBaseView {


    void getDetailData(BillMaterialDetailResult result);
}
