package com.timi.sz.wms_android.bean.outstock.outsource;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-10 14:44
 */

public class SubmitBarcodeLotPickOutResult {

    /**
     * isNotAllowPickOut : false
     * scanId : 87
     * materialId : 1117
     * materialCode : 10106060003
     * materialName : 共模电感
     * materialStandard : 470UH/3A/TC1487-7T:7T
     * materialAttribute :
     * barcodeQty : 600
     * lineMustQty : 2000
     * lineScanQty : 600
     * totalScanQty : 600
     * exceedQty : 0
     * newBarcode : null
     */

    private boolean isNotAllowPickOut;
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
    private int cartonNo;
    public boolean isIsNotAllowPickOut() {
        return isNotAllowPickOut;
    }

    public void setIsNotAllowPickOut(boolean isNotAllowPickOut) {
        this.isNotAllowPickOut = isNotAllowPickOut;
    }

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

    public String getNewBarcode() {
        return newBarcode;
    }

    public void setNewBarcode(String newBarcode) {
        this.newBarcode = newBarcode;
    }

    public boolean isNotAllowPickOut() {
        return isNotAllowPickOut;
    }

    public void setNotAllowPickOut(boolean notAllowPickOut) {
        isNotAllowPickOut = notAllowPickOut;
    }

    public int getCartonNo() {
        return cartonNo;
    }

    public void setCartonNo(int cartonNo) {
        cartonNo = cartonNo;
    }
}
