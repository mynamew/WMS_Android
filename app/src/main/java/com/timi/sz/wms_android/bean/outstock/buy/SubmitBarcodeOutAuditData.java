package com.timi.sz.wms_android.bean.outstock.buy;

/**
 * $dsc  采购退料物料扫码 提交条码出库 的返回结果
 * author: timi
 * create at: 2017-11-06 09:53
 */

public class SubmitBarcodeOutAuditData {

    /**
     * scanId : 77
     * barcodeQty : 4
     * inStockQty : 202
     * returnQty : 0
     * usedQty : 180
     */

    private int scanId;
    private int barcodeQty;
    private int inStockQty;
    private int returnQty;
    private int usedQty;

    public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    public int getBarcodeQty() {
        return barcodeQty;
    }

    public void setBarcodeQty(int barcodeQty) {
        this.barcodeQty = barcodeQty;
    }

    public int getInStockQty() {
        return inStockQty;
    }

    public void setInStockQty(int inStockQty) {
        this.inStockQty = inStockQty;
    }

    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }

    public int getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(int usedQty) {
        this.usedQty = usedQty;
    }
}
