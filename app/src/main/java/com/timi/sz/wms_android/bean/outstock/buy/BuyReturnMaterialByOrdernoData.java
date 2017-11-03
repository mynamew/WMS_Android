package com.timi.sz.wms_android.bean.outstock.buy;

/**
 * $dsc 采购退料单号搜索返回
 * author: timi
 * create at: 2017-11-02 15:08
 */

public class BuyReturnMaterialByOrdernoData {

    /**
     * purReturnId : 740
     * purReturnCode : DH161111006
     * purReturnDate : 2016-11-11
     * supplierName : 深圳市日科实业有限公司
     * createrName :
     * scanId : 0
     * purReturnQty : 132000
     * waitQty : 132000
     * scanQty : 0
     */

    private int purReturnId;
    private String purReturnCode;
    private String purReturnDate;
    private String supplierName;
    private String createrName;
    private int scanId;
    private int purReturnQty;
    private int waitQty;
    private int scanQty;

    public int getPurReturnId() {
        return purReturnId;
    }

    public void setPurReturnId(int purReturnId) {
        this.purReturnId = purReturnId;
    }

    public String getPurReturnCode() {
        return purReturnCode;
    }

    public void setPurReturnCode(String purReturnCode) {
        this.purReturnCode = purReturnCode;
    }

    public String getPurReturnDate() {
        return purReturnDate;
    }

    public void setPurReturnDate(String purReturnDate) {
        this.purReturnDate = purReturnDate;
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

    public int getPurReturnQty() {
        return purReturnQty;
    }

    public void setPurReturnQty(int purReturnQty) {
        this.purReturnQty = purReturnQty;
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
