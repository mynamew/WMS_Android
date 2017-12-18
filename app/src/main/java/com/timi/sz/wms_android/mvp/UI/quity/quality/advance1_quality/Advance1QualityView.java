package com.timi.sz.wms_android.mvp.UI.quity.quality.advance1_quality;

import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-18 16:57
 */

public interface Advance1QualityView extends MvpBaseView {
    /**
     * 获取高级质检1 的数据
     * @param data
     */
    void getAdvance1Data(GetAdvanceData data);

    /**
     * 设置高级质检1
     */
    void setAdvance1Data();
    /**
     * 质检确认
     */
    void submitFinish();
}
