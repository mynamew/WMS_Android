package com.timi.sz.wms_android.mvp.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.mvp.base.presenter.MvpPresenter;
import com.timi.sz.wms_android.mvp.base.view.MvpView;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 所有Acitity的基类  封装基类的方法
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends AutoLayoutActivity implements MvpBaseView {

    private P presenter;
    private V view;

    public P getPresenter() {
        return presenter;
    }

    public V getView() {
        return view;
    }
    //当前Activity的实例
    public Activity currentActivity;
    public String TAG = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        if (this.view == null) {
            this.view = createView();
        }
        if (this.presenter != null && this.view != null) {
            this.presenter.attachView(view);
        }
        //current activity
        currentActivity = this;
        //tag
        TAG = currentActivity.getClass().getSimpleName()+"_";
        //返回事件
        if (null != findViewById(R.id.iv_title_back)) {
            findViewById(R.id.iv_title_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
        //设置状态栏颜色 默认
        StatusBarUtil.setColor(this, Color.parseColor(Constants.StatusColorStr));
        //初始化各种数据
        initBundle(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismisProgressDialog() {

    }

    /**
     * 设置标题的title
     *
     * @param title
     */
    public void setActivityTitle(String title) {
        if (null != findViewById(R.id.tv_title) && null != title && !TextUtils.isEmpty(title)) {
            //设置标题  如果titlebar存在的话
            TextView tvActivityTitle = (TextView) findViewById(R.id.tv_title);
            tvActivityTitle.setText(title);
        }
    }
    /**
     * 设置右侧的文字
     *
     * @param rightText
     */
    public void setRightText(String rightText, final View.OnClickListener listener) {
        if (null != findViewById(R.id.tv_title_right) && null != rightText && !TextUtils.isEmpty(rightText)) {
            //设置标题  如果titlebar存在的话
            TextView tvRightText= (TextView) findViewById(R.id.tv_title_right);
            tvRightText.setText(rightText);
            tvRightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                }
            });
        }
    }
    /**
     * 设置右侧的图片及点击事件
     *
     * @param rightImgId 右侧图片的资源id
     */
    public void setRightImg(@DrawableRes int rightImgId, final View.OnClickListener listener) {
        if (null != findViewById(R.id.iv_title_right)) {
            //设置标题  如果titlebar存在的话
            ImageView ivRightImg= (ImageView) findViewById(R.id.iv_title_right);
            ivRightImg.setImageResource(rightImgId);
            ivRightImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view);
                }
            });
        }
    }
    //设置布局id
    public abstract int setLayoutId();
    //初始化bundle
    public abstract void initBundle(Bundle savedInstanceState);
    //初始化View
    public abstract void initView();
    //初始化数据
    public abstract void initData();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
    }
}
