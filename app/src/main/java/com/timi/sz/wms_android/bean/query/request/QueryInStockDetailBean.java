package com.timi.sz.wms_android.bean.query.request;

import com.google.gson.annotations.SerializedName;

/**
 * $dsc 入库记录
 * author: timi
 * create at: 2018-03-01 09:57
 */

public class QueryInStockDetailBean {

    @SerializedName("“BillCode”")
    private String _$BillCode298; // FIXME check this code
    private String BillType;
    private int OrgId;
    private int UserId;
    private String MAC;

    public String get_$BillCode298() {
        return _$BillCode298;
    }

    public void set_$BillCode298(String _$BillCode298) {
        this._$BillCode298 = _$BillCode298;
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
