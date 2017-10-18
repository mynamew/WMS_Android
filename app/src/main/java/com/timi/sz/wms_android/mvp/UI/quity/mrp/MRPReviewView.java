package com.timi.sz.wms_android.mvp.UI.quity.mrp;

import com.timi.sz.wms_android.bean.quality.mrp.MrpReviewData;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-17 15:58
 */

public interface MRPReviewView extends MvpBaseView {
    void getMrpReviewData(List<MrpReviewData> datas);
}
