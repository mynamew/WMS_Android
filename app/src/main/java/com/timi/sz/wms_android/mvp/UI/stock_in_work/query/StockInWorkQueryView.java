package com.timi.sz.wms_android.mvp.UI.stock_in_work.query;

import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotOneSetpResult;
import com.timi.sz.wms_android.bean.stockin_work.query.AllotScanResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeInResult;
import com.timi.sz.wms_android.bean.stockin_work.query.FormChangeOutResult;
import com.timi.sz.wms_android.bean.stockin_work.query.PointResult;
import com.timi.sz.wms_android.bean.stockin_work.query.StockQueryResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-22 13:17
 */

public interface StockInWorkQueryView extends MvpBaseView {
    /**
     * 扫描调入
     *
     * @param result
     */
    void queryAllotScan(AllotScanResult result);

    /**
     * 一步调入
     *
     * @param result
     */
    void queryAllotOneStep(AllotOneSetpResult result);

    /**
     * 形态转换出库
     *
     * @param result
     */
    void queryFormChangeOut(FormChangeOutResult result);

    /**
     * 形态转换 入库
     *
     * @param result
     */
    void queryFormChangeIn(FormChangeInResult result);
    /**
     * 盘点 查询
     *
     * @param result
     */
    void queryPoint(PointResult result);

}
