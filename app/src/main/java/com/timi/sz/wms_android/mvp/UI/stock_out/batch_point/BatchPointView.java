package com.timi.sz.wms_android.mvp.UI.stock_out.batch_point;

import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-19 10:08
 */

public interface BatchPointView extends MvpBaseView {
    void getMaterialLotDataHttpSubscriber(GetMaterialLotData data);

    void submitBarcodeLotPickOutSplit(SubmitBarcodeLotPickOutSplitResult result);

    void submitBarcodeLotPickOut(SubmitBarcodeLotPickOutResult result);
    void setMaterialLotData();
    void setBarcodeSelect();

}
