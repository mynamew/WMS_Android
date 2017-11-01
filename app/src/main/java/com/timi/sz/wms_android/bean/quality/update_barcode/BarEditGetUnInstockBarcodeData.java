package com.timi.sz.wms_android.bean.quality.update_barcode;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-30 14:42
 */

public class BarEditGetUnInstockBarcodeData {

    /**
     * initialQty : 5
     * currentQty : 5
     * packQty : 5
     * sourceBillType : 送货单
     * sourceBillCode : ASN0001
     * materialCode : 20301010017
     * materialName : 750前壳(带音频)
     * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
     * materialAttribute : null
     */

    private int initialQty;
    private int currentQty;
    private int packQty;
    private String sourceBillType;
    private String sourceBillCode;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private String materialAttribute;

    public int getInitialQty() {
        return initialQty;
    }

    public void setInitialQty(int initialQty) {
        this.initialQty = initialQty;
    }

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }

    public int getPackQty() {
        return packQty;
    }

    public void setPackQty(int packQty) {
        this.packQty = packQty;
    }

    public String getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(String sourceBillType) {
        this.sourceBillType = sourceBillType;
    }

    public String getSourceBillCode() {
        return sourceBillCode;
    }

    public void setSourceBillCode(String sourceBillCode) {
        this.sourceBillCode = sourceBillCode;
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
}
