package com.timi.sz.wms_android.bean.query.response;

/**
 * $dsc
 * author: timi
 * create at: 2018-02-23 15:20
 */

public class StockSummeryResult {

    /**
     * instockCount : 0
     * outstockCount : 0
     */

    private int instockCount;
    private int outstockCount;

    public int getInstockCount() {
        return instockCount;
    }

    public void setInstockCount(int instockCount) {
        this.instockCount = instockCount;
    }

    public int getOutstockCount() {
        return outstockCount;
    }

    public void setOutstockCount(int outstockCount) {
        this.outstockCount = outstockCount;
    }
}
