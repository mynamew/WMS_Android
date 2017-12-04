package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-04 09:03
 */

public class StockQueryEvent extends BaseEvent {


    /**
     * 库存查询输入的内容
     */
    public String inputContent;

    public StockQueryEvent(String event) {
        super(event);
    }

    /**
     * 容器查询
     */
    public  static final String  STOCK_QUERY_PACK_REPERTORY="stock_query_pack_repertory";
    /**
     * 物料查询
     */
    public  static final String  STOCK_QUERY_MATERIAL_REPERTORY="stock_query_material_repertory";
}
