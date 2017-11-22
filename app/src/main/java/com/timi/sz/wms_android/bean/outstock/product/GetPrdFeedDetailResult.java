package com.timi.sz.wms_android.bean.outstock.product;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-20 01:13
 */

public class GetPrdFeedDetailResult {

    /**
     * detailId : 85
     * line : 1
     * materialId : 3626
     * materialCode : 20102010001
     * materialName : 750主板
     * materialStandard : N制，带音频,1/4CCD，sharp/32*32*1.6
     * materialAttribute :
     * warehouseId : 2
     * warehouseName : 半成品
     * qty : 7
     * waitQty : 7
     * scanQty : 0
     */

    private int detailId;
    private int line;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;
    private int warehouseId;
    private String warehouseName;
    private int qty;
    private int waitQty;
    private int scanQty;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialStandard() {
        return materialStandard;
    }

    public void setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard;
    }

    public String getMaterialAttribute() {
        return materialAttribute;
    }

    public void setMaterialAttribute(String materialAttribute) {
        this.materialAttribute = materialAttribute;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getWaitQty() {
        return waitQty;
    }

    public void setWaitQty(int waitQty) {
        this.waitQty = waitQty;
    }

    public int getScanQty() {
        return scanQty;
    }

    public void setScanQty(int scanQty) {
        this.scanQty = scanQty;
    }
}
