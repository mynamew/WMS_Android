package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:16
 */

public interface FragmentPointView extends MvpBaseView {
     void buyOrdernoQuery(BuyOrdernoBean bean);
     void sendOrdernoQuery(SendOrdernoBean bean);
     void savePointMaterial(PointMaterialBean bean);
}
