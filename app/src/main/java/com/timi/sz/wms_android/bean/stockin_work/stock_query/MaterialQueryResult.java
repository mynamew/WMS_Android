package com.timi.sz.wms_android.bean.stockin_work.stock_query;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-04 01:17
 */

public class MaterialQueryResult {

    /**
     * OrgId : 1
     * UserId : 2
     * MAC : 00-50-56-C0-00-01
     * MaterialBarcode : SN2017080100003
     */

    private int OrgId;
    private int UserId;
    private String MAC;
    private String MaterialBarcode;

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

    public String getMaterialBarcode() {
        return MaterialBarcode;
    }

    public void setMaterialBarcode(String MaterialBarcode) {
        this.MaterialBarcode = MaterialBarcode;
    }
}
