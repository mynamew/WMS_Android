package com.timi.sz.wms_android.bean.stockin_work.lib_adjust;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-30 14:16
 */

public class LibAdjustDetail {
    public int line;
    public String formLib;
    public String toLib;
    public String materailCode;
    public String materialName;
    public String materialModel;
    public String materialAttr;
    public int waitQty;
    public LibAdjustDetail(int line, String formLib, String toLib, String materailCode, String materialName, String materialModel, String materialAttr) {
        this.line = line;
        this.formLib = formLib;
        this.toLib = toLib;
        this.materailCode = materailCode;
        this.materialName = materialName;
        this.materialModel = materialModel;
        this.materialAttr = materialAttr;
    }

    public int getWaitQty() {
        return waitQty;
    }
}
