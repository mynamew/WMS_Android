package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-27 14:49
 */

public class MrpEvent extends BaseEvent {
    public MrpEvent(String event) {
        super(event);
    }
    public static final String MRP_REVIEW_SUCCESS="MRP_REVIEW_SUCCESS";
}
