package com.timi.sz.wms_android.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.mvp.base.presenter.MvpPresenter;
import com.timi.sz.wms_android.mvp.base.view.MvpView;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:05
 */

public abstract  class BaseFragment <V extends MvpView, P extends MvpPresenter<V>>extends Fragment implements MvpBaseView {
    private P presenter;
    private V view;
    private Unbinder unbinder;

    public P getPresenter() {
        return presenter;
    }

    public V getMvpView() {
        return view;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(setLayoutId(), null);
        unbinder = ButterKnife.bind(this, itemView);
        AutoUtils.autoSize(itemView);
        return itemView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismisProgressDialog() {

    }
    /**
     * 绑定P层
     *
     * @return
     */
    public abstract P createPresenter();

    /**
     * 创建View层
     *
     * @return
     */
    public abstract V createView();
    //初始化数据
    public abstract void initData();
    //设置布局id
    public abstract int setLayoutId();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
        unbinder.unbind();
    }

}
