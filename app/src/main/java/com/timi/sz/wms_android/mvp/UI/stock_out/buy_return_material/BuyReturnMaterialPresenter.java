package com.timi.sz.wms_android.mvp.UI.stock_out.buy_return_material;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-28 17:30
 */

public class BuyReturnMaterialPresenter extends MvpBasePresenter<BuyReturnMaterialView> {
    BuyReturnMaterialModel model = null;

    public BuyReturnMaterialPresenter(Context context) {
        super(context);
        model = new BuyReturnMaterialModel();
    }
}
