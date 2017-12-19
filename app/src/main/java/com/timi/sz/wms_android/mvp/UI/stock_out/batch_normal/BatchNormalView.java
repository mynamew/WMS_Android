package com.timi.sz.wms_android.mvp.UI.stock_out.batch_normal;

import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-19 15:10
 */

public interface BatchNormalView extends MvpBaseView {
    void submitBarcodeLotPickOut(SubmitBarcodeLotPickOutResult result);

    void setBarcodeSelect();
}
