package com.timi.sz.wms_android.bean.stockin_work.check;

/**
 * $dsc 盘点提交的返回
 * author: timi
 * create at: 2017-12-10 16:10
 */

public class SubmitCheckDataResult {

    /**
     * materialId : 207
     * materialCode : 10101010207
     * materialName : 贴片电阻
     * materialStandard : 37.4R/06
     * materialAttribute :
     * qty : 1960
     * waitQty : 1950
     * checkQty : 10
     */

    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;
    private int qty;
    private int waitQty;
    private int checkQty;

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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getWaitQty() {
        return waitQty;
    }

    public void setWaitQty(int waitQty) {
        this.waitQty = waitQty;
    }

    public int getCheckQty() {
        return checkQty;
    }

    public void setCheckQty(int checkQty) {
        this.checkQty = checkQty;
    }
}
