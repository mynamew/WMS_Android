package com.timi.sz.wms_android.mvp.UI.stock_in.list;

import com.timi.sz.wms_android.bean.instock.list.QueryPoListBean;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 15:20
 */

public interface BuyInStockListView extends MvpBaseView {
    /**
     * 采购列表
     * @param datas
     */
    void queryPOList(List<QueryPoListBean> datas);

    /**
     * 获取采购单的表头 和表体
     * @param bean
     */
    void BuyOrdernoBean(BuyOrdernoBean bean);
}
