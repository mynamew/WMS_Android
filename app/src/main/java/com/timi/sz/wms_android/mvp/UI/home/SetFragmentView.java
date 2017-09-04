package com.timi.sz.wms_android.mvp.UI.home;

import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;

/**
 * $dsc
 * author: timi
 * create at: 2017-09-04 15:55
 */

public interface SetFragmentView extends MvpBaseView {
    /**
     * apk 下载完成 提示安装
     */
    void installApk();
    /**
     * 获取app 版本
     */
    void getVersion(VersionBean bean);
}
