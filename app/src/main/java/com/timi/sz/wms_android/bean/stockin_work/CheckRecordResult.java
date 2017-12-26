package com.timi.sz.wms_android.bean.stockin_work;

/**
 * $dsc 盘点记录的返回
 * author: timi
 * create at: 2017-12-25 16:14
 */

public class CheckRecordResult {

    /**
     * materialId : 207
     * materialCode : 10101010207
     * materialName : 贴片电阻
     * materialStandard : 37.4R/06
     * materialAttribute :
     * checkQty : 2
     * checkTime : 2017-12-07
     */

    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;
    private int checkQty;
    private String checkTime;

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

    public int getCheckQty() {
        return checkQty;
    }

    public void setCheckQty(int checkQty) {
        this.checkQty = checkQty;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
}
