package com.timi.sz.wms_android.mvp.UI.stock_out.outsourcing_audit;

import com.timi.sz.wms_android.bean.outstock.outsource.GetMaterialLotData;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutResult;
import com.timi.sz.wms_android.bean.outstock.outsource.SubmitBarcodeLotPickOutSplitResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-14 08:59
 */

public interface OutsourcingAuditPointView extends MvpBaseView {
    void getMaterialLotDataHttpSubscriber(GetMaterialLotData data);

    void submitBarcodeLotPickOutSplit(SubmitBarcodeLotPickOutSplitResult result);

    void submitBarcodeLotPickOut(SubmitBarcodeLotPickOutResult result);
}
