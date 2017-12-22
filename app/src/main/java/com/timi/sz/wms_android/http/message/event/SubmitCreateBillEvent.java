package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-21 14:35
 */

public class SubmitCreateBillEvent extends BaseEvent {
    public SubmitCreateBillEvent(String event) {
        super(event);
    }
    public static  final String SUBMIT_CREATE_BILL_SUCCESS="submit_create_bill_success";//提交至但成功
}
