package com.timi.sz.wms_android.mvp.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.mvp.base.presenter.MvpPresenter;
import com.timi.sz.wms_android.mvp.base.view.MvpView;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;
import com.timi.sz.wms_android.view.SwipeBackLayout;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

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

    public String TAG = "";
    //当前Activity的实例
    public Activity currentActivity;
    //侧滑返回的布局
    private SwipeBackLayout swipeBackLayout;
    //侧滑返回的img
    private ImageView ivShadow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = setLayoutId();
        //设置布局id
        // 不需要侧滑的布局id  进行过滤
        if (layoutResID == R.layout.activity_main) {
           setContentView(layoutResID);
        }
        //添加 侧滑布局
        else {
            setContentView(getContainer());
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            view.setBackgroundColor(getResources().getColor(R.color.app_background));
            swipeBackLayout.addView(view);
        }
        //注入
        ButterKnife.bind(this);
        //绑定 presenter
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        //view
        if (this.view == null) {
            this.view = createView();
        }
        //attach
        if (this.presenter != null && this.view != null) {
            this.presenter.attachView(view);
        }
        //current activity
        currentActivity = this;
        //tag
        TAG = currentActivity.getClass().getSimpleName() + "_";
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
        StatusBarUtil.setColor(this, getResources().getColor(R.color.statuscolor));
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
            TextView tvRightText = (TextView) findViewById(R.id.tv_title_right);
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
            ImageView ivRightImg = (ImageView) findViewById(R.id.iv_title_right);
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

    /**
     * 侧滑 返回
     *
     * @return
     */
    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        //设置是 左滑
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.theme_black_7f));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        //滑动 透明度变化
        swipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                Logger.d("打印侧滑的fload--->"+fractionScreen);
                ivShadow.setAlpha(1 - fractionScreen);
            }
        });
        return container;
    }
}
