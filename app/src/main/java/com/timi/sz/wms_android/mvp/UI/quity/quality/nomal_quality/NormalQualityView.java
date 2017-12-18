package com.timi.sz.wms_android.mvp.UI.quity.quality.nomal_quality;

import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-06 17:22
 */

public interface NormalQualityView extends MvpBaseView {
    /**
     * 获取普通质检的数据
     *
     * @param result
     */
    void getNormalQualityData(NormalQualityData result);

    /**
     * 提交普通质检的结果
     */
    void setNormalQualityData();

    /**
     * 质检确认
     */
    void submitFinish();
}
