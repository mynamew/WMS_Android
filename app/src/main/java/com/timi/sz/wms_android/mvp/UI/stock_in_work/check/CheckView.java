package com.timi.sz.wms_android.mvp.UI.stock_in_work.check;

import com.timi.sz.wms_android.bean.stockin_work.MaterialDataBean;
import com.timi.sz.wms_android.bean.stockin_work.check.SubmitCheckDataResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-10 14:27
 */

public interface CheckView extends MvpBaseView {
    /**
     * 获取物料信息
     * @param bean
     */
    void getMaterialData(MaterialDataBean bean);

    /**
     * 提交盘点
     * @param o
     */
    void submitCheckData(SubmitCheckDataResult o);

    /**
     * 设置物料条码选中
     */
    void setMaterialEdittextSelect();

    /**
     * 设置盘点数量选中
     */
    void setCheckqQtySelect();
}
