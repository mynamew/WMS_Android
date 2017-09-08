package com.timi.sz.wms_android.bean.instock.search;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-05 16:14
 */

public class StockinMaterialBean {

    /**
     * materialId : 3912
     * materialCode : 20301010017
     * materialName : 750前壳(带音频)
     * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
     * materialAttribute : null
     * countQty : 1
     * giveQty : 1
     * createDateTime : 2017-09-05 05:nn:19
     * id : 1
     */

    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private Object materialAttribute;
    private int countQty;
    private int giveQty;
    private String createDateTime;
    private int id;

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

    public int getCountQty() {
        return countQty;
    }

    public void setCountQty(int countQty) {
        this.countQty = countQty;
    }

    public int getGiveQty() {
        return giveQty;
    }

    public void setGiveQty(int giveQty) {
        this.giveQty = giveQty;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
