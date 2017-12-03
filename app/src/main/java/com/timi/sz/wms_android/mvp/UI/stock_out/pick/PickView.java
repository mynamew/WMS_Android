package com.timi.sz.wms_android.mvp.UI.stock_out.pick;

import com.timi.sz.wms_android.bean.outstock.outsource.QueryWWPickDataByOutSourceResult;
import com.timi.sz.wms_android.bean.outstock.pick.QueryDNByInputForPickResult;
import com.timi.sz.wms_android.bean.outstock.product.QueryProductPickByInputResult;
import com.timi.sz.wms_android.bean.stockin_work.allot_out.QueryAllotOutResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-18 15:07
 */

public interface PickView extends MvpBaseView {
    /**
     * 成品拣货
     * @param bean
     */
    void queryDNByInputForPick(QueryDNByInputForPickResult bean);
    /**
     * 调拨调出
     * @param bean
     */
    void queryTransferByInputForOutStock(QueryAllotOutResult bean);
}
