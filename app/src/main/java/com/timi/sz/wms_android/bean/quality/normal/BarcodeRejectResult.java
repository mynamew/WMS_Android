package com.timi.sz.wms_android.bean.quality.normal;

/**
 * $dsc  质检拒收的结果返回
 * author: timi
 * create at: 2017-10-17 13:24
 */

public class BarcodeRejectResult {

    /**
     * initialQty : 5
     * currentQty : 4
     * rejectQty : 1
     */

    private int initialQty;
    private int currentQty;
    private int rejectQty;

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

    public int getRejectQty() {
        return rejectQty;
    }

    public void setRejectQty(int rejectQty) {
        this.rejectQty = rejectQty;
    }
}
