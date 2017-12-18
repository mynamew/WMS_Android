package com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality;

import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.IQCGetAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.UpdateCheckItemResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-12 14:53
 */

public interface AdvanceQualityView extends MvpBaseView {
    //获取高级质检2 数据
    void getAdvance2Data(GetAdvance2Data data);
    //设置高级质检2 数据
    void setAdvance2Data();
    //质检确认
    void submitFinish();
    //修改高级质检2 的项目
    void updateCheckItemData(UpdateCheckItemResult result);
    //获取高级质检2 的项目
    void getAdvanceCheckItemData(IQCGetAdvanceData o);
}
