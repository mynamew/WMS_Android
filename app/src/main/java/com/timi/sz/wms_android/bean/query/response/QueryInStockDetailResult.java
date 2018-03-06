package com.timi.sz.wms_android.bean.query.response;

/**
 * $dsc
 * author: timi
 * create at: 2018-03-01 09:40
 */

public class QueryInStockDetailResult {

    /**
     * InstockLine : 1
     * BillType : 14
     * BillCode : DH171214001
     * BillDate : 2014-04-02
     * MaterialCode : 20101010033
     * Qty : 232
     * WareHouseName : 原料仓
     * MaterialName : 压缩弹簧
     * MaterialAttribute :
     * MaterialStandard : 弹簧钢/黑色/φ3*φ0.3*14
     */

    private int InstockLine;
    private String BillType;
    private String BillCode;
    private String BillDate;
    private String MaterialCode;
    private int Qty;
    private String WareHouseName;
    private String MaterialName;
    private String MaterialAttribute;
    private String MaterialStandard;

    public int getInstockLine() {
        return InstockLine;
    }

    public void setInstockLine(int InstockLine) {
        this.InstockLine = InstockLine;
    }

    public String getBillType() {
        return BillType;
    }

    public void setBillType(String BillType) {
        this.BillType = BillType;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String BillCode) {
        this.BillCode = BillCode;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String BillDate) {
        this.BillDate = BillDate;
    }

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String MaterialCode) {
        this.MaterialCode = MaterialCode;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int Qty) {
        this.Qty = Qty;
    }

    public String getWareHouseName() {
        return WareHouseName;
    }

    public void setWareHouseName(String WareHouseName) {
        this.WareHouseName = WareHouseName;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String MaterialName) {
        this.MaterialName = MaterialName;
    }

    public String getMaterialAttribute() {
        return MaterialAttribute;
    }

    public void setMaterialAttribute(String MaterialAttribute) {
        this.MaterialAttribute = MaterialAttribute;
    }

    public String getMaterialStandard() {
        return MaterialStandard;
    }

    public void setMaterialStandard(String MaterialStandard) {
        this.MaterialStandard = MaterialStandard;
    }
}
