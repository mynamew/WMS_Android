package com.timi.sz.wms_android.bean.list;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 16:56
 */

public class RequestBuyInStockListBean {

    /**
     * OrgId : 1
     * UserId : 2
     * MAC : 00-50-56-C0-00-01
     * BillNo :
     * SupplierName : 艾创特
     */

    private int OrgId;
    private int UserId;
    private String MAC;
    private String BillNo;
    private String SupplierName;

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

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String BillNo) {
        this.BillNo = BillNo;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String SupplierName) {
        this.SupplierName = SupplierName;
    }
}
