package com.timi.sz.wms_android.mvp.UI.stock_in_work.check_record;

import com.timi.sz.wms_android.bean.stockin_work.CheckRecordResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-11 16:10
 */

public interface CheckRecordView extends MvpBaseView {
    /**
     * 获取记录
     * @param datas
     */
    void getCheckStockPageRecord(List<CheckRecordResult> datas);
}
