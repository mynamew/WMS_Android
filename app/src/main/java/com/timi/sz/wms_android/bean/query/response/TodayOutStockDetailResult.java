package com.timi.sz.wms_android.bean.query.response;

/**
 * $dsc    今日出库明细的实体
 * author: timi
 * create at: 2018-02-24 10:02
 */

public class TodayOutStockDetailResult {
    private int OutstockCode;
    private int Line;
    private int DetailId;
    private int MaterialId;
    private String MatetialCode;
    private String MaterialName;
    private String MaterialStandard;
    private String MaterialAttribute;
    private int Qty;

    public int getOutstockCode() {
        return OutstockCode;
    }

    public void setOutstockCode(int outstockCode) {
        OutstockCode = outstockCode;
    }

    public int getLine() {
        return Line;
    }

    public void setLine(int line) {
        Line = line;
    }

    public int getDetailId() {
        return DetailId;
    }

    public void setDetailId(int detailId) {
        DetailId = detailId;
    }

    public int getMaterialId() {
        return MaterialId;
    }

    public void setMaterialId(int materialId) {
        MaterialId = materialId;
    }

    public String getMatetialCode() {
        return MatetialCode;
    }

    public void setMatetialCode(String matetialCode) {
        MatetialCode = matetialCode;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getMaterialStandard() {
        return MaterialStandard;
    }

    public void setMaterialStandard(String materialStandard) {
        MaterialStandard = materialStandard;
    }

    public String getMaterialAttribute() {
        return MaterialAttribute;
    }

    public void setMaterialAttribute(String materialAttribute) {
        MaterialAttribute = materialAttribute;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }
}
