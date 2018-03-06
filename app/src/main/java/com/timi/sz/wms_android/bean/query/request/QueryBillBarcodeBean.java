package com.timi.sz.wms_android.bean.query.request;

/**
 * $dsc
 * author: timi
 * create at: 2018-03-01 09:15
 */

public class QueryBillBarcodeBean {

    /**
     * BillCode : DH171214001
     * BillType : ”01”
     * OrgId : 1
     * UserId : 55
     * MAC : 00-50-56-C0-00-01
     */

    private String BillCode;
    private String BillType;
    private int OrgId;
    private int UserId;
    private String MAC;

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String BillCode) {
        this.BillCode = BillCode;
    }

    public String getBillType() {
        return BillType;
    }

    public void setBillType(String BillType) {
        this.BillType = BillType;
    }

    public int getOrgId() {
        return OrgId;
    }

    public void setOrgId(int OrgId) {
        this.OrgId = OrgId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
