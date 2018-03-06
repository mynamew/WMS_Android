package com.timi.sz.wms_android.bean.query.response;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 15:24
 */

public class TodayInStockDetailResult {


    /**
     * instockCode : CP140711008
     * detailId : 0
     * line : 1
     * materialId : 5360
     * materialCode : 30201010006
     * materialName : MS-643RSD无线后视系统
     * materialStandard : 客户：M43WRLDG/643D后视镜+610发射盒+309摄像头+6*配件
     * materialAttribute : null
     * qty : 20
     */

    private String instockCode;
    private int detailId;
    private int line;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private Object materialAttribute;
    private int qty;

    public String getInstockCode() {
        return instockCode;
    }

    public void setInstockCode(String instockCode) {
        this.instockCode = instockCode;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
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

    public Object getMaterialAttribute() {
        return materialAttribute;
    }

    public void setMaterialAttribute(Object materialAttribute) {
        this.materialAttribute = materialAttribute;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
