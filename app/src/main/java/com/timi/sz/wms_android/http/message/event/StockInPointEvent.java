package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc 清点的时间
 * author: timi
 * create at: 2017-09-06 09:40
 */

public class StockInPointEvent extends BaseEvent {
    public int receiveId;
    public StockInPointEvent(String event) {
        super(event);
    }
    //物料清点改变
    public static final String MATERIAL_POINT_UPDATE = "MATERIAL_POINT_UPDATE";
    //物料清点记录改变
    public static final String MATERIAL_POINT_RECORD_UPDATE = "MATERIAL_POINT_RECORD_UPDATE";

}
