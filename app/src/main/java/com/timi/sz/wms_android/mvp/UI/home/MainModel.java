package com.timi.sz.wms_android.mvp.UI.home;

import com.timi.sz.wms_android.bean.TestBean;
import com.timi.sz.wms_android.http.HttpManager;
import com.timi.sz.wms_android.mvp.base.model.impl.MvpBaseModel;

import io.reactivex.Observer;

/**
 * main  model
 */

public class MainModel extends MvpBaseModel {

    public void getMainMsg(int pno, int ps, String dtype, Observer<TestBean> subscriber) {
        HttpManager.getInstance().getDatasUser(subscriber, pno, ps, dtype);
    }
}
