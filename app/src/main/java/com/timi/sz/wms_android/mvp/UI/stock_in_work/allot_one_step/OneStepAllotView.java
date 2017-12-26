package com.timi.sz.wms_android.mvp.UI.stock_in_work.allot_one_step;

import com.timi.sz.wms_android.bean.instock.VertifyLocationCodeBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-01 15:02
 */

public interface OneStepAllotView extends MvpBaseView {
    /**
     * 一部调出提交
     *
     * @param bean
     */
    void submitTransferOneStep(Object bean);
    /**
     * 设置库位码选中
     */
    void setLocationSelect();
}
