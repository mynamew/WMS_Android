package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-14 14:37
 */

public class OutsourceAuditEvent extends BaseEvent {
    private int scanId;//扫描的id
    private String materialCode;//物料编码
    private int barcodeQty;//条码包含物料的数量
    public OutsourceAuditEvent(String event, int scanId) {
        super(event);
        this.scanId = scanId;
    }

    public OutsourceAuditEvent(String event) {
        super(event);
    }
    //物料扫码成功
    public static final String OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS="OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS";


    public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public int getBarcodeQty() {
        return barcodeQty;
    }

    public void setBarcodeQty(int barcodeQty) {
        this.barcodeQty = barcodeQty;
    }
}
