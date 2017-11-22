package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * $dsc 拆分打码的事件
 * author: timi
 * create at: 2017-11-22 15:20
 */

public class SubmitBarcodeLotPickOutSplitEvent extends BaseEvent {
    private SubmitBarcodeLotPickOutSplitResult result;

    public SubmitBarcodeLotPickOutSplitResult getResult() {
        return result;
    }

    public void setResult(SubmitBarcodeLotPickOutSplitResult result) {
        this.result = result;
    }

    public SubmitBarcodeLotPickOutSplitEvent(String event) {
        super(event);
    }

    public static final String SUBMIT_BARCODE_SPLIT_SUCCESS = "submit_barcode_split_success";
}
