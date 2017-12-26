package com.timi.sz.wms_android.bean.outstock.outsource;

/**
 * $dsc  提交条码拆分出库(批次拣货) 的返回
 * author: timi
 * create at: 2017-11-10 14:47
 */

public class SubmitBarcodeLotPickOutSplitResult {

    /**
     * isNotAllowPickOut : false
     * scanId : 88
     * materialId : 776
     * materialCode : 10104030003
     * materialName : 贴片场效应管
     * materialStandard : FDS4141/SOP8/(仙童)
     * materialAttribute :
     * barcodeQty : 200
     * lineMustQty : 2000
     * lineScanQty : 2000
     * totalScanQty : 2000
     * exceedQty : 0
     * newBarcode : CT20171100002
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

    public int getCartonNo() {
        return cartonNo;
    }

    public void setCartonNo(int cartonNo) {
        cartonNo = cartonNo;
    }
}
