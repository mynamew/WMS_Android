package com.timi.sz.wms_android.bean.query.request;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-27 14:13
 */

public class SNRequsetBean {

    /**
     * OrgId : 1
     * UserId : 2
     * BarCode : PR201712020002
     * MAC : 00-50-56-C0-00-01
     */

    private int OrgId;
    private int UserId;
    private String BarCode;
    private String MAC;

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

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
