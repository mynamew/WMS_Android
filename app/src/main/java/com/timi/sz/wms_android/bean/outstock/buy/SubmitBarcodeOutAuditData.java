package com.timi.sz.wms_android.bean.outstock.buy;

/**
 * $dsc  提交条码出库  普通的返回值
 * author: timi
 * create at: 2017-11-06 09:53
 */

public class SubmitBarcodeOutAuditData {


    /**
     * scanId : 4
     * materialId : 198
     * materialCode : 10101010198
     * materialName : 贴片电阻
     * materialStandard : 330R/1206/±5%
     * materialAttribute :
     * barcodeQty : 1
     * lineMustQty : 0
     * lineScanQty : 2
     * totalScanQty : 12
     * exceedQty : 0
     * newBarcode : null
     */

    private int scanId;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;
    private int barcodeQty;
    private int lineMustQty;
    private int lineScanQty;
    private int totalScanQty;
    private int exceedQty;
    private String newBarcode;

    public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
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

    public int getBarcodeQty() {
        return barcodeQty;
    }

    public void setBarcodeQty(int barcodeQty) {
        this.barcodeQty = barcodeQty;
    }

    public int getLineMustQty() {
        return lineMustQty;
    }

    public void setLineMustQty(int lineMustQty) {
        this.lineMustQty = lineMustQty;
    }

    public int getLineScanQty() {
        return lineScanQty;
    }

    public void setLineScanQty(int lineScanQty) {
        this.lineScanQty = lineScanQty;
    }

    public int getTotalScanQty() {
        return totalScanQty;
    }

    public void setTotalScanQty(int totalScanQty) {
        this.totalScanQty = totalScanQty;
    }

    public int getExceedQty() {
        return exceedQty;
    }

    public void setExceedQty(int exceedQty) {
        this.exceedQty = exceedQty;
    }

    public Object getNewBarcode() {
        return newBarcode;
    }

    public void setNewBarcode(String newBarcode) {
        this.newBarcode = newBarcode;
    }
}
