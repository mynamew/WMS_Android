package com.timi.sz.wms_android.bean.outstock.outsource;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-15 14:23
 */

public class RequestGetMaterialLotBean {

    /**
     * UserId : 2
     * MAC : 00-50-56-C0-00-01
     * OrgId : 1
     * BillId : 620
     * SrcBillType : 12
     * DestBillType : 20
     * WarehouseId : 1
     * materialId : 3609
     * materialCode : 20101010056
     * MaterialAttribute :
     */

    private int UserId;
    private String MAC;
    private int OrgId;
    private int BillId;
    private int SrcBillType;
    private int DestBillType;
    private int WarehouseId;
    private int materialId;
    private String materialCode;
    private String MaterialAttribute;

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

    public int getBillId() {
        return BillId;
    }

    public void setBillId(int BillId) {
        this.BillId = BillId;
    }

    public int getSrcBillType() {
        return SrcBillType;
    }

    public void setSrcBillType(int SrcBillType) {
        this.SrcBillType = SrcBillType;
    }

    public int getDestBillType() {
        return DestBillType;
    }

    public void setDestBillType(int DestBillType) {
        this.DestBillType = DestBillType;
    }

    public int getWarehouseId() {
        return WarehouseId;
    }

    public void setWarehouseId(int WarehouseId) {
        this.WarehouseId = WarehouseId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialAttribute() {
        return MaterialAttribute;
    }

    public void setMaterialAttribute(String MaterialAttribute) {
        this.MaterialAttribute = MaterialAttribute;
    }
}
