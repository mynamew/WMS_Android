package com.timi.sz.wms_android.mvp.UI.quity.quality;

import com.timi.sz.wms_android.bean.quality.GetAQLList;
import com.timi.sz.wms_android.bean.quality.QualityListBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-06 17:06
 */

public interface QualityCheckView extends MvpBaseView {
    void getQualityList(List<QualityListBean> datas);

    void queryReceiptForIQC(int position);

    void getAQLList(GetAQLList datas, int position);

    void setAQLValue(int position);
}
