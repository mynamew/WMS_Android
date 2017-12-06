package com.timi.sz.wms_android.mvp.UI.splash;

import com.timi.sz.wms_android.bean.GetPDA_ParameterResult;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-06 10:53
 */

public interface SplashView extends MvpBaseView {
    /**
     * 获取PDA参数
     * @param o
     */
    void getPDAParams(GetPDA_ParameterResult o);
}
