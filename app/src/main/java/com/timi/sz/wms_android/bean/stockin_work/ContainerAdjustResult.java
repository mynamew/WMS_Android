package com.timi.sz.wms_android.bean.stockin_work;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-23 17:18
 */

public class ContainerAdjustResult {

    /**
     * srcBarcode : CN201708010001
     * destContainer : CN201708010002
     * materialId : 3912
     * materialCode : 20301010017
     * materialName : 750前壳(带音频)
     * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
     * materialAttribute : null
     * qty : 4
     */

    private String srcBarcode;
    private String destContainer;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private Object materialAttribute;
    private int qty;

    public String getSrcBarcode() {
        return srcBarcode;
    }

    public void setSrcBarcode(String srcBarcode) {
        this.srcBarcode = srcBarcode;
    }

    public String getDestContainer() {
        return destContainer;
    }

    public void setDestContainer(String destContainer) {
        this.destContainer = destContainer;
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
