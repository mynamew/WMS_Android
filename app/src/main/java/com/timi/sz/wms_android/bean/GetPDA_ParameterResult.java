package com.timi.sz.wms_android.bean;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 10:50
 */

public class GetPDA_ParameterResult {

    /**
     * isGiveGoods : true
     * isMaterialAttribute : true
     * isBillList : false
     */

    private boolean isGiveGoods;
    private boolean isMaterialAttribute;
    private boolean isBillList;

    public boolean isIsGiveGoods() {
        return isGiveGoods;
    }

    public void setIsGiveGoods(boolean isGiveGoods) {
        this.isGiveGoods = isGiveGoods;
    }

    public boolean isIsMaterialAttribute() {
        return isMaterialAttribute;
    }

    public void setIsMaterialAttribute(boolean isMaterialAttribute) {
        this.isMaterialAttribute = isMaterialAttribute;
    }

    public boolean isIsBillList() {
        return isBillList;
    }

    public void setIsBillList(boolean isBillList) {
        this.isBillList = isBillList;
    }
}
