package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-14 14:37
 */

public class OutsourceAuditEvent extends BaseEvent {
    public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    private int scanId;

    public OutsourceAuditEvent(String event, int scanId) {
        super(event);
        this.scanId = scanId;
    }

    public OutsourceAuditEvent(String event) {
        super(event);
    }
    //物料扫码成功
    public static final String OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS="OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS";

}
