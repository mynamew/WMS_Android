package com.timi.sz.wms_android.bean.outstock.buy;

/**
 * $dsc  提交条码出库  普通的返回值
 * author: timi
 * create at: 2017-11-06 09:53
 */

public class SubmitBarcodeOutAuditData {


    /**
     * scanId : 75
     * materialId : 3912
     * materialCode : 20301010017
     * materialName : 750前壳(带音频)
     * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
     * materialAttribute :
     * barcodeQty : 4
     * lineMustQty : 8
     * lineScanQty : 4
     * totalScanQty : 4
     * exceedQty : 0
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
    private int cartonNo;
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

    public int getCartonNo() {
        return cartonNo;
    }

    public void setCartonNo(int cartonNo) {
        this.cartonNo = cartonNo;
    }
}
