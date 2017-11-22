package com.timi.sz.wms_android.bean.instock.search;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-31 13:37
 */

public class QueryPrdReturnByInputResult {

    /**
     * billId : 1554
     * billCode : 0000016561
     * billDate : 2016-06-02
     * deptName :
     * createrName : 方艳冬
     * scanId : 0
     * qty : 9
     * waitQty : 9
     * scanQty : 0
     */

    private int billId;
    private String billCode;
    private String billDate;
    private String deptName;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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
