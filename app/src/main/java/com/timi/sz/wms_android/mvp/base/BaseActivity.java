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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Selection;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.InputMethodUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.base.uils.TypefaceUtil;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.mvp.UI.login.LoginActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.query.SearchBuyOrderActivity;
import com.timi.sz.wms_android.mvp.base.presenter.MvpPresenter;
import com.timi.sz.wms_android.mvp.base.view.MvpView;
import com.timi.sz.wms_android.mvp.base.view.iml.MvpBaseView;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;
import com.timi.sz.wms_android.view.MyProgressDialog;
import com.timi.sz.wms_android.view.SwipeBackLayout;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.Iterator;

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
//        if (layoutResID == R.layout.activity_main) {
        setContentView(layoutResID);
//        } else {
//            setContentView(getContainer());
//            View view = LayoutInflater.from(this).inflate(layoutResID, null);
//            view.setBackgroundColor(getResources().getColor(R.color.app_background));
//            swipeBackLayout.addView(view);
//        }
        /**
         * 更改字体
         */
//        TypefaceUtil.replaceSystemDefaultFont(this,"fonts/normal.otf");
        //添加 侧滑布局
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                hideKeyboard(ev, view);//调用方法判断是否需要隐藏键盘
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 隐藏软键盘
     *
     * @param event
     * @param view
     */
    public void hideKeyboard(MotionEvent event, View view) {
        try {
            if (view != null && view instanceof EditText
                    ) {
                int[] location = {0, 0};
                view.getLocationInWindow(location);
                int left = location[0], top = location[1], right = left
                        + view.getWidth(), bootom = top + view.getHeight();
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.getRawX() < left || event.getRawX() > right
                        || event.getY() < top || event.getRawY() > bootom) {
                    // 隐藏键盘
                    InputMethodUtils.hidSoftInput(event, BaseActivity.this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * 设置 title
     *
     * @param titleId
     */
    public void setActivityTitle(@StringRes int titleId) {
        if (null != findViewById(R.id.tv_title)) {
            //设置标题  如果titlebar存在的话
            TextView tvActivityTitle = (TextView) findViewById(R.id.tv_title);
            tvActivityTitle.setText(getString(titleId));
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
        it.putExtra("unAuthorizedRequest", true);
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
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                if (null != mListener) {
                    mListener.scanSuccess(requestCode, bundle.getString("result"));
                }
            }
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
     * 设置 文本
     *
     * @param tv
     */
    public void setTextViewContent(TextView tv, Object object) {
        String content = "";
//        if (object instanceof String) {
//            content = TextUtils.isEmpty(String.valueOf(object)) ? getString(R.string.none) : String.valueOf(object);
//        } else {
            content = String.valueOf(object);
//        }
        tv.setText(content);
    }

    /**
     * 设置备品的状态 （是否显示备品）
     *
     * @param view
     */
    public void setSpareGoodsStatus(View view) {
        if (!SpUtils.getInstance().getIsGiveGoods())
            view.setVisibility(View.GONE);
    }

    /**
     * 设置附加属性的状态 （是否显示附加属性）
     *
     * @param view
     */
    public void setMaterialAttrStatus(View view) {
        if (!SpUtils.getInstance().getIsMaterialAttribute())
            view.setVisibility(View.GONE);
    }

    /**
     * zxing 扫码的回调接口
     */
    public interface ScanQRCodeResultListener {
        void scanSuccess(int requestCode, String result);
    }

    private SparseArray<EditText> edits = new SparseArray<>();

    /**
     * 设置 输入框  将输入框传进来
     *
     * @param editText
     * @param editCode         输入框标识的code
     * @param tipPleaseInputId 输入框输入内容为空 的提示
     * @param tipLengthId      输入位数小于4 的提示
     * @param listener         监听事件
     */
    public void setEdittextListener(final EditText editText, int editCode, @StringRes final int tipPleaseInputId, @StringRes final int tipLengthId, final EdittextInputListener listener) {
        //存储输入框
        edits.put(editCode, editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 6 || actionId == 0) {
                    InputMethodUtils.hide(BaseActivity.this);
                    String content = editText.getText().toString().trim();

                    if (TextUtils.isEmpty(content)) {
                        ToastUtils.showShort(getString(tipPleaseInputId));
                        Selection.selectAll(editText.getText());
                        return true;
                    }
                    /**
                     * 长度的判定
                     */
                    if (content.length() < 4 && tipLengthId != 0) {
                        ToastUtils.showShort(getString(tipLengthId));
                        Selection.selectAll(editText.getText());
                        return true;
                    }
                    /**
                     * 验证通过，进行下一步
                     */
                    if (null != listener) {
                        listener.verticalSuccess(content);
                    }

                }
                return false;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        InputMethodUtils.hide(currentActivity);
        /**
         * 按下扫描件
         */
        if (keyCode == 0) {
            setEdittextSelect();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置 输入框内容选中的方法
     */
    public void setEdittextSelect() {
        /**
         * 遍历sparsearray  找到正在获取焦点的edittext 设置全部选中
         */
        for (int i = 0; i < edits.size(); i++) {
            EditText et = edits.valueAt(i);
            if (et.isFocused()) {
                Selection.selectAll(et.getText());
                break;
            }
        }
    }

    /**
     * 所有的输入框的输入监听方法  验证全部通过则调用 verticalSuccess方法
     * 1、为了统一处理 输入错误设置内容选中
     * 2、为了监听回车键
     * 3、为了监听扫描键
     */
    public interface EdittextInputListener {
        void verticalSuccess(String result);
    }

    /**
     * 设置recycleview  滑动更流畅
     * @param rlv
     * @param layoutManager
     */
    public void setRecycleViewScrollSmooth(RecyclerView rlv,LinearLayoutManager layoutManager){
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        rlv.setLayoutManager(layoutManager);
        rlv.setNestedScrollingEnabled(false);
        rlv.setHasFixedSize(true);
    }
}
