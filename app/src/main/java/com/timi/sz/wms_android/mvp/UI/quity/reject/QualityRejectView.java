package com.timi.sz.wms_android.mvp.UI.quity.reject;

import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-13 08:50
 */

public interface QualityRejectView extends MvpBaseView {
    void getBarcodeData(BarcodeData data, String result);
    void setBarcodeData(BarcodeData data);

    void submitFinish();
    //设置输入框 选中
    void setEdittextSelect();
}
