package com.timi.sz.wms_android.bean.instock.outsource_return_material;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-17 09:38
 */

public class QueryOutSourceReturnByInputResult {

    /**
     * billId : 1
     * billCode : 0000021733
     * billDate : 2016-11-30
     * supplierName : 深圳市引导电子有限公司
     * createrName : mes
     * scanId : 0
     * qty : 4010
     * waitQty : 4010
     * scanQty : 0
     */

    private int billId;
    private String billCode;
    private String billDate;
    private String supplierName;
    private String createrName;
    private int scanId;
    private int qty;
    private int waitQty;
    private int scanQty;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
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
