package com.timi.sz.wms_android.bean.outstock.buy;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-03 16:07
 */

public class BuyReturnMaterialByMaterialCodeData {


    /**
     * billType : 11
     * billId : 1208
     * billCode : CG140902021
     * billDate : 2014-09-02
     * supplierName : 东莞市建利线材有限公司
     * purEmployeeName : 陈曼维
     * scanId : 77
     * billDetailId : 2693
     * materialId : 2040
     * materialCode : 10202040007
     * materialName : (33#）
     * materialStandard : 母/米
     * materialAttribute :
     * poQty : 202
     * inStockQty : 202
     * returnQty : 0
     * usedQty : 180
     * scanQty : 22
     */

    private int billType;
    private int billId;
    private String billCode;
    private String billDate;
    private String supplierName;
    private String purEmployeeName;
    private int scanId;
    private int billDetailId;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;
    private int poQty;
    private int inStockQty;
    private int returnQty;
    private int usedQty;
    private int scanQty;

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPurEmployeeName() {
        return purEmployeeName;
    }

    public void setPurEmployeeName(String purEmployeeName) {
        this.purEmployeeName = purEmployeeName;
    }

    public int getScanId() {
        return scanId;
    }

    public void setScanId(int scanId) {
        this.scanId = scanId;
    }

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
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

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialStandard() {
        return materialStandard;
    }

    public void setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard;
    }

    public String getMaterialAttribute() {
        return materialAttribute;
    }

    public void setMaterialAttribute(String materialAttribute) {
        this.materialAttribute = materialAttribute;
    }

    public int getPoQty() {
        return poQty;
    }

    public void setPoQty(int poQty) {
        this.poQty = poQty;
    }

    public int getInStockQty() {
        return inStockQty;
    }

    public void setInStockQty(int inStockQty) {
        this.inStockQty = inStockQty;
    }

    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }

    public int getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(int usedQty) {
        this.usedQty = usedQty;
    }

    public int getScanQty() {
        return scanQty;
    }

    public void setScanQty(int scanQty) {
        this.scanQty = scanQty;
    }
}
