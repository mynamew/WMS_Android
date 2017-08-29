package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material.scan_return_material;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-29 08:43
 */

public class ScanReturnMaterialPresenter extends MvpBasePresenter<ScanReturnMaterialView> {
    ScanReturnMaterialMdel model = null;

    public ScanReturnMaterialPresenter(Context context) {
        super(context);
        model = new ScanReturnMaterialMdel();
    }
}
