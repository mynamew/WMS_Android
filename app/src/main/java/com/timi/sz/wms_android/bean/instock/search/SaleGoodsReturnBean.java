package com.timi.sz.wms_android.bean.instock.search;

/**
 * $dsc 销售退货
 * author: timi
 * create at: 2017-08-31 13:40
 */

public class SaleGoodsReturnBean {

    /**
     * billId : 7
     * billCode : CK150929025
     * billDate : 2015-09-29
     * customerName : Rostra Precision Controls
     * createrName : 方艳冬
     * scanId : 0
     * qty : 150
     * waitQty : 150
     * scanQty : 0
     */

    private int billId;
    private String billCode;
    private String billDate;
    private String customerName;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
