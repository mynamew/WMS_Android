package com.timi.sz.wms_android.bean.outstock;

/**
 * $dsc
 * author: timi
 * create at: 2018-01-17 10:12
 */

public class SetBatchExpRequstBean {

    /**
     * UserId : 2
     * MAC : 00-50-56-C0-00-01
     * OrgId : 1
     * WarehouseId : 1
     * MaterialId : 198
     * MaterialAttribute :
     * DateCode : 2017-10-30
     */

    private int UserId;
    private String MAC;
    private int OrgId;
    private int WarehouseId;
    private int MaterialId;
    private String MaterialAttribute;
    private String DateCode;

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

    public int getOrgId() {
        return OrgId;
    }

    public void setOrgId(int OrgId) {
        this.OrgId = OrgId;
    }

    public int getWarehouseId() {
        return WarehouseId;
    }

    public void setWarehouseId(int WarehouseId) {
        this.WarehouseId = WarehouseId;
    }

    public int getMaterialId() {
        return MaterialId;
    }

    public void setMaterialId(int MaterialId) {
        this.MaterialId = MaterialId;
    }

    public String getMaterialAttribute() {
        return MaterialAttribute;
    }

    public void setMaterialAttribute(String MaterialAttribute) {
        this.MaterialAttribute = MaterialAttribute;
    }

    public String getDateCode() {
        return DateCode;
    }

    public void setDateCode(String DateCode) {
        this.DateCode = DateCode;
    }
}
