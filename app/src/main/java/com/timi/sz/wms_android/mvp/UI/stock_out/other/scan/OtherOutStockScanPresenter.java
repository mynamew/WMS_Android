package com.timi.sz.wms_android.mvp.UI.stock_out.other.scan;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 16:33
 */

public class OtherOutStockScanPresenter extends MvpBasePresenter<OtherOutStockScanView> {
    OtherOutStockScanModel model=null;
    public OtherOutStockScanPresenter(Context context) {
        super(context);
        model=new OtherOutStockScanModel();
    }
}
