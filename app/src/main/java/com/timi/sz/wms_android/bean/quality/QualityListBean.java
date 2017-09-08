package com.timi.sz.wms_android.bean.quality;

/**
 * $dsc 获取质检表/
 * author: timi
 * create at: 2017-09-07 08:59
 */

public class QualityListBean {
    public boolean isFinsishQuality;
    public String materialCode;
    public String supplier;
    public int haveReceveNum;
    public int sendQuaskityNum;
    public int qualitiedNum;
    public String qualityResult;

    public boolean isFinsishQuality() {
        return isFinsishQuality;
    }

    public void setFinsishQuality(boolean finsishQuality) {
        isFinsishQuality = finsishQuality;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getHaveReceveNum() {
        return haveReceveNum;
    }

    public void setHaveReceveNum(int haveReceveNum) {
        this.haveReceveNum = haveReceveNum;
    }

    public int getSendQuaskityNum() {
        return sendQuaskityNum;
    }

    public void setSendQuaskityNum(int sendQuaskityNum) {
        this.sendQuaskityNum = sendQuaskityNum;
    }

    public int getQualitiedNum() {
        return qualitiedNum;
    }

    public void setQualitiedNum(int qualitiedNum) {
        this.qualitiedNum = qualitiedNum;
    }

    public String getQualityResult() {
        return qualityResult;
    }

    public void setQualityResult(String qualityResult) {
        this.qualityResult = qualityResult;
    }

    public QualityListBean(boolean isFinsishQuality, String materialCode, String supplier, int haveReceveNum, int sendQuaskityNum, int qualitiedNum, String qualityResult) {
        this.isFinsishQuality = isFinsishQuality;
        this.materialCode = materialCode;
        this.supplier = supplier;
        this.haveReceveNum = haveReceveNum;
        this.sendQuaskityNum = sendQuaskityNum;
        this.qualitiedNum = qualitiedNum;
        this.qualityResult = qualityResult;
    }
}
