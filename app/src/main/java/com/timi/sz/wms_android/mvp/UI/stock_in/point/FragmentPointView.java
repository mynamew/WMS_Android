package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:16
 */

public interface FragmentPointView extends MvpBaseView {
     /**
      * 保存采购单物料清点
      * @param result
      */
     void savePointMaterial(Integer result);
     /**
      * 保存送货单物料清点
      * @param result
      */
     void saveSendPointMaterial(Integer result);
     /**
      * 提交物料清点
      */
     void commitPointMaterial();
     /**
      * 获取采购单清点的表体
      */
     void getPODetailsByCode(BuyOrdernoBean bean);
     /**
      * 获取送货单清点的表体
      */
     void getSendPODetailsByCode(SendOrdernoBean bean);
}
