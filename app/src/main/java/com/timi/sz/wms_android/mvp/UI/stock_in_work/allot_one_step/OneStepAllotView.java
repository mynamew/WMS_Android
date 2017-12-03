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
     * 库位码是否有效
     *
     * @param bean
     */
    void vertifyLocationCode(VertifyLocationCodeBean bean);

    /**
     * 一部调出提交
     *
     * @param bean
     */
    void submitTransferOneStep(Object bean);
}
