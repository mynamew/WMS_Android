package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-14 14:37
 */

public class StockOutSubmitScanMaterialEvent extends BaseEvent {
    private SubmitBarcodeLotPickOutResult result;

    public StockOutSubmitScanMaterialEvent(String event) {
        super(event);
    }

    //物料扫码成功
    public static final String OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS = "OUT_SOURCE_AUDIT_SCAN_MATERIAL_SUCCESS";

    public SubmitBarcodeLotPickOutResult getResult() {
        return result;
    }

    public void setResult(SubmitBarcodeLotPickOutResult result) {
        this.result = result;
    }
}
