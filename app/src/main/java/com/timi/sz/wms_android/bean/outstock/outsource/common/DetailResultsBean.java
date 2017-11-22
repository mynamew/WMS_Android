package com.timi.sz.wms_android.bean.outstock.outsource.common;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-19 15:47
 */

public class DetailResultsBean {


    /**
     * detailId : 1358
     * poLine : 3
     * materialId : 3796
     * materialCode : 20105010009
     * materialName : 770灯板
     * materialStandard : φ5，24颗灯，波长850，90度/φ54*1.2
     * materialAttribute :
     * poQty : 4000
     * qty : 504
     * waitQty : 504
     * scanQty : 0
     * wipQty : 0
     */

    private int detailId;
    private int poLine;
    private int line;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;
    private int poQty;
    private int qty;
    private int waitQty;
    private int scanQty;
    private int wipQty;
    private String warehouseName;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getPoLine() {
        return poLine;
    }

    public void setPoLine(int poLine) {
        this.poLine = poLine;
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

    public int getPoQty() {
        return poQty;
    }

    public void setPoQty(int poQty) {
        this.poQty = poQty;
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

    public int getWipQty() {
        return wipQty;
    }

    public void setWipQty(int wipQty) {
        this.wipQty = wipQty;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
