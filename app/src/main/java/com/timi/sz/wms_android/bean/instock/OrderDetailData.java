package com.timi.sz.wms_android.bean.instock;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-03 11:35
 */

public class OrderDetailData {

    /**
     * receiptDetailId : 25164
     * receiptLine : 1
     * materialId : 3912
     * materialCode : 20301010017
     * materialName : 750前壳(带音频)
     * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
     * materialAttribute :
     * qty : 4
     * passQty : 4
     * inStockQty : 0
     * waitQty : 0
     * scanQty : 4
     */

    private int receiptDetailId;
    private int receiptLine;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;
    private int qty;
    private int passQty;
    private int inStockQty;
    private int waitQty;
    private int scanQty;

    public int getReceiptDetailId() {
        return receiptDetailId;
    }

    public void setReceiptDetailId(int receiptDetailId) {
        this.receiptDetailId = receiptDetailId;
    }

    public int getReceiptLine() {
        return receiptLine;
    }

    public void setReceiptLine(int receiptLine) {
        this.receiptLine = receiptLine;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPassQty() {
        return passQty;
    }

    public void setPassQty(int passQty) {
        this.passQty = passQty;
    }

    public int getInStockQty() {
        return inStockQty;
    }

    public void setInStockQty(int inStockQty) {
        this.inStockQty = inStockQty;
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
