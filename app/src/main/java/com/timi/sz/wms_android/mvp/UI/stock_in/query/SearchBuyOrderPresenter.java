package com.timi.sz.wms_android.mvp.UI.stock_in.query;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

/**
 * 搜索采购单的presenter
 * author: timi
 * create at: 2017-08-18 17:41
 */
public class SearchBuyOrderPresenter extends MvpBasePresenter<SearchBuyOrderView> {
    private SearchBuyModel model;

    public SearchBuyOrderPresenter(Context context) {
        super(context);
        model = new SearchBuyModel();
    }
}
