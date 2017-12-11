package com.timi.sz.wms_android.bean.stockin_work.check;

/**
 * $dsc  提交盘点的bean
 * author: timi
 * create at: 2017-12-10 16:11
 */

public class RequestSubmitCheckDataBean {

    /**
     * OrgId : 1
     * UserId : 55
     * MAC : 00-50-56-C0-00-01
     * BillId : 1
     * MaterialCode : 10101010207
     * MaterialAttribute :
     * CheckQty : 2
     */

    private int OrgId;
    private int UserId;
    private String MAC;
    private int BillId;
    private String MaterialCode;
    private String MaterialAttribute;
    private int CheckQty;

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

    public int getBillId() {
        return BillId;
    }

    public void setBillId(int BillId) {
        this.BillId = BillId;
    }

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String MaterialCode) {
        this.MaterialCode = MaterialCode;
    }

    public String getMaterialAttribute() {
        return MaterialAttribute;
    }

    public void setMaterialAttribute(String MaterialAttribute) {
        this.MaterialAttribute = MaterialAttribute;
    }

    public int getCheckQty() {
        return CheckQty;
    }

    public void setCheckQty(int CheckQty) {
        this.CheckQty = CheckQty;
    }
}
