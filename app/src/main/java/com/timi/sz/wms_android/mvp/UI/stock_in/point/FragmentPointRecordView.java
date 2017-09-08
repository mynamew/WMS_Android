package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.bean.instock.search.StockinMaterialBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:19
 */

public interface FragmentPointRecordView extends MvpBaseView {
    /**
     * 获取采购单清点记录
     * @param datas
     */
    void getPointRecord(List<StockinMaterialBean> datas);
    /**
     * 修改采购单清点记录
     */
    void updatePointRecord();
    /**
     * 删除采购单清点记录
     */
    void deletePointRecord();
}
