package com.timi.sz.wms_android.mvp.UI.stock_in_work.form_change_detail;

import com.timi.sz.wms_android.bean.stockin_work.FormChangeDetailResult;
import com.timi.sz.wms_android.bean.stockin_work.StockInWorkDetailResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 09:47
 */

public interface FormChangeDetailView extends MvpBaseView {
    /**
     * 获取库内作业详情
     * @param datas
     */
    void getStockInWorkDetail(List<FormChangeDetailResult> datas);
    /**
     * 获取库内作业详情
     * @param datas
     */
    void getConvertOutDetail(List<FormChangeDetailResult> datas);
}
