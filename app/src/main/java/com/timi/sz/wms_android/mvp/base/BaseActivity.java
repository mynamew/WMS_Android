package com.timi.sz.wms_android.mvp.base;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.mvp.UI.login.LoginActivity;
import com.timi.sz.wms_android.mvp.base.presenter.MvpPresenter;
import com.timi.sz.wms_android.mvp.base.view.MvpView;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;
import com.timi.sz.wms_android.view.MyProgressDialog;
import com.timi.sz.wms_android.view.SwipeBackLayout;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;

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
    static private BaseActivity currentActivity;
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
        StatusBarUtil.setColor(this, getResources().getColor(R.color.statuscolor), 80);
        //初始化各种数据
        initBundle(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodUtils.hidSoftInput(event, this);
        return super.onTouchEvent(event);
    }

    @Override
    public void showProgressDialog() {
        MyProgressDialog.showProgressDialog(currentActivity);
    }

    @Override
    public void dismisProgressDialog() {
        MyProgressDialog.hideProgressDialog();
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
            ivRightImg.setVisibility(View.VISIBLE);
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
    protected void onResume() {
        super.onResume();
        //current activity  为了解决progress  dialog 弹出 context改变的问题
        currentActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
        dismisProgressDialog();
    }

    /********
     * 页面跳转相关的方法
     **********************************************************************************************/
    public enum Interlude {

        DEFAULT,
        POP_FROM_BOTTOM
    }

    final private String keyInterlude = "keyInterlude";
    private Interlude curInterlude = Interlude.DEFAULT;

    @Override
    public void startActivity(Intent intent) {
        startActivity(intent, Interlude.DEFAULT);
    }

    public void startActivity(Intent intent, Interlude interlude) {
        intent.putExtra(keyInterlude, interlude.ordinal());
        super.startActivity(intent);

        if (interlude == Interlude.DEFAULT) {
            overridePendingTransition(R.transition.slide_in_right, R.anim.none);
        } else if (interlude == Interlude.POP_FROM_BOTTOM) {
            //从下方弹出
            overridePendingTransition(R.transition.pop_in_bottom, R.anim.none);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (curInterlude == Interlude.DEFAULT) {
            //默认情况，什么都不做，已经在style文件中配置
            overridePendingTransition(R.anim.none, R.transition.slide_out_right);
        } else if (curInterlude == Interlude.POP_FROM_BOTTOM) {
            //从下方弹出
            overridePendingTransition(R.anim.none, R.transition.pop_out_bottom);
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
                Logger.d("打印侧滑的fload--->" + fractionScreen);
                ivShadow.setAlpha(1 - fractionScreen);
            }
        });
        return container;
    }

    /**
     * 跳转到登录的公共方法
     */
    public void jumpToLoginActivity() {
        //做清除数据的操作
        // 做跳转的操作
        Intent it = new Intent(currentActivity, LoginActivity.class);
        startActivity(it, Interlude.POP_FROM_BOTTOM);
    }

    /**
     * 跳转到登录的公共方法
     */
    public void jumpToLoginActivity(boolean isNeedShowServerSet) {
        //做清除数据的操作
        // 做跳转的操作
        Intent it = new Intent(currentActivity, LoginActivity.class);
        it.putExtra(Constants.IS_NEED_SHOW_SHOW_SERVER_SET, isNeedShowServerSet);
        startActivity(it, Interlude.POP_FROM_BOTTOM);
    }

    /**
     * 扫码的返回 监听器
      */
    private ScanQRCodeResultListener mListener = null;
    /**
     * 调用相机扫描二维码的方法
     *
     * @param requestCode
     */
    public void scan(int requestCode, ScanQRCodeResultListener listener) {
        if (null != listener) {
            mListener = listener;
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            Intent intent = new Intent(this, CommonScanActivity.class);

            String pointMsg = getResources().getString(R.string.scan_point_title);
            Bundle bundle = new Bundle();
            bundle.putString("pointMsg", pointMsg);
            intent.putExtras(bundle);

            intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        if(null!=mListener){
                            mListener.scanSuccess(REQUEST_CODE,bundle.getString("result"));
                        }
                    }
                }
                break;
        }
    }

    /**
     * 获取当前Activity的实例
     *
     * @return
     */
    public static Activity getCurrentActivty() {
        return currentActivity;
    }

    /**
     * 设置 文本
     *
     * @param tv
     * @param fomat
     * @param content
     */
    public void setTextViewText(TextView tv, @StringRes int fomat, String content) {
        tv.setText(String.format(getString(fomat), content));
    }

    /**
     * 设置 文本
     *
     * @param tv
     * @param fomat
     * @param content
     */
    public void setTextViewText(TextView tv, @StringRes int fomat, int content) {
        tv.setText(String.format(getString(fomat), String.valueOf(content)));
    }

    /**
     * zxing 扫码的回调接口
     */
    public interface ScanQRCodeResultListener {
        void scanSuccess(int requestCode,String result);
    }
}
