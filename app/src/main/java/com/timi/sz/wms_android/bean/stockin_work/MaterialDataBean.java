package com.timi.sz.wms_android.bean.stockin_work;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-10 16:01
 */

public class MaterialDataBean {

    /**
     * materialId : 209
     * materialCode : 10101010209
     * materialName : 贴片电阻
     * materialStandard : 390R/0603/±5%
     * materialAttribute :
     * qty : 4000
     * waitQty : 4000
     * checkQty : 0
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
