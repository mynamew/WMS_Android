package com.timi.sz.wms_android.bean.stockin_work.query;

/**
 * $dsc 盘点查询结果
 * author: timi
 * create at: 2017-10-10 10:18
 */

public class PointResult {

    /**
     * billId : 1
     * billCode : CS201712050005
     * billDate :
     * warehouseName : 原材料仓
     * checkTypeName : 按仓库盘点
     * checkNatureId : 0
     * createrName : ADMIN
     * items : 2946
     * qty : 15627683
     * waitQty : 15627683
     * scanQty : 0
     */

    private int billId;
    private String billCode;
    private String billDate;
    private String warehouseName;
    private String checkTypeName;
    private int checkNatureId;
    private String createrName;
    private int items;
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

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public int getCheckNatureId() {
        return checkNatureId;
    }

    public void setCheckNatureId(int checkNatureId) {
        this.checkNatureId = checkNatureId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
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
