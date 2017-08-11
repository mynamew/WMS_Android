package com.timi.sz.wms_android.mvp.base.view.iml;

import com.timi.sz.wms_android.mvp.base.view.MvpView;

/**
 * mvp 的base  实现类
 */

public interface MvpBaseView extends MvpView {
    void showProgressDialog();

    void dismisProgressDialog();
}
