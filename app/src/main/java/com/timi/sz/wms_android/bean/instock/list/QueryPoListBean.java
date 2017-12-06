package com.timi.sz.wms_android.bean.instock.list;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 15:24
 */

public class QueryPoListBean {

    /**
     * billId : 7810
     * billCode : CG161128202
     * billDate : 2016-11-28
     * supplierName : 艾创特电子贸易（上海）有限公司
     */

    private int billId;
    private String billCode;
    private String billDate;
    private String supplierName;

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
}
