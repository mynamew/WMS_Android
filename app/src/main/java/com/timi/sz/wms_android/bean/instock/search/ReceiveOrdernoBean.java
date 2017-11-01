package com.timi.sz.wms_android.bean.instock.search;

/**
 * $dsc  收货单
 * author: timi
 * create at: 2017-08-31 11:11
 */

public class ReceiveOrdernoBean  {


    /**
     * receipId : 10408
     * receiptCode : DH161018020
     * receipDate : 2017-10-18
     * supplierName : 深圳市宝安区大浪恒昶塑胶电子厂
     * createrName :
     * scanId : 0
     * receiptQty : 11
     * passQty : 10
     * instockQty : 0
     * waitQty : 10
     * scanQty : 0
     */

    private int receipId;
    private String receiptCode;
    private String receipDate;
    private String supplierName;
    private String createrName;
    private int scanId;
    private int receiptQty;
    private int passQty;
    private int instockQty;
    private int waitQty;
    private int scanQty;

    public int getReceipId() {
        return receipId;
    }

    public void setReceipId(int receipId) {
        this.receipId = receipId;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    public String getReceipDate() {
        return receipDate;
    }

    public void setReceipDate(String receipDate) {
        this.receipDate = receipDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    public int getReceiptQty() {
        return receiptQty;
    }

    public void setReceiptQty(int receiptQty) {
        this.receiptQty = receiptQty;
    }

    public int getPassQty() {
        return passQty;
    }

    public void setPassQty(int passQty) {
        this.passQty = passQty;
    }

    public int getInstockQty() {
        return instockQty;
    }

    public void setInstockQty(int instockQty) {
        this.instockQty = instockQty;
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
