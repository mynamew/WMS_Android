package com.timi.sz.wms_android.bean.query.request;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 17:00
 */

public class RequestBean {

    /**
     * OrgId : 1
     * UserId : 55
     * MAC : 00-50-56-C0-00-01
     */

    private int OrgId;
    private int UserId;
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

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
}
