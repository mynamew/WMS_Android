package com.timi.sz.wms_android.mvp.UI.stock_out.detail.return_detial;

import com.timi.sz.wms_android.bean.stockin_work.ReturnDetailResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-15 15:12
 */

public interface ReturnDetailView extends MvpBaseView {
    void getMakePurReturnScannedDetail(List<ReturnDetailResult> o);
}
