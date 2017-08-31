package com.timi.sz.wms_android.bean.instock;

/**
 * $dsc 物料扫描入库上架的返回bean
 * author: timi
 * create at: 2017-08-31 10:02
 */

public class MaterialScanPutAwayBean {
    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
    }

    public String getMaterialModel() {
        return MaterialModel;
    }

    public void setMaterialModel(String materialModel) {
        MaterialModel = materialModel;
    }

    public String getMaterialBuyNum() {
        return MaterialBuyNum;
    }

    public void setMaterialBuyNum(String materialBuyNum) {
        MaterialBuyNum = materialBuyNum;
    }

    public MaterialScanPutAwayBean(String materialName, String materialCode, String materialModel, String materialBuyNum) {
        MaterialName = materialName;
        MaterialCode = materialCode;
        MaterialModel = materialModel;
        MaterialBuyNum = materialBuyNum;

    }

    public String MaterialName;
    public String MaterialCode;
    public String MaterialModel;
    public String MaterialBuyNum;
}
