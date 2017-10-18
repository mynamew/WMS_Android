package com.timi.sz.wms_android.bean.quality;

/**
 * $dsc  质检条码信息
 * author: timi
 * create at: 2017-10-17 13:22
 */

public class BarcodeData {

    /**
     * initialQty : 5
     * currentQty : 4
     * rejectQty : 0
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
