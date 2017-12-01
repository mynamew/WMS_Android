package com.timi.sz.wms_android.mvp.UI.stock_in_work.stock_query.material_repertory;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-30 16:33
 */

public class MaterialRepertoryPresenter extends MvpBasePresenter<MaterialRepertoryView> {
    private MaterialRepertoryModel model;

    public MaterialRepertoryPresenter(Context context) {
        super(context);
        model = new MaterialRepertoryModel();
    }
}
