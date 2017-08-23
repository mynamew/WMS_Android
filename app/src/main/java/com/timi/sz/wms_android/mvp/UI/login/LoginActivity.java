package com.timi.sz.wms_android.mvp.UI.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LanguageUtils;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.receiver.ExampleUtil;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.MyPopWindow;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 登录界面
 * author: timi
 * create at: 2017/8/16 8:57
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.tv_login_version)
    TextView tvLoginVersion;
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.cb_login_rempsw)
    CheckBox cbLoginRempsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_login_eye)
    ImageView ivLoginEye;

    //flag
    private boolean isCanSeePsw = false;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        /**
         * 获取用户名
         */
        String userName = SpUtils.getInstance().getString(Constants.USER_NAME);
        if (!TextUtils.isEmpty(userName)) {
            etLoginUsername.setText(userName);
        }
        /**
         * 获取 存储的密码
         */
        String password = SpUtils.getInstance().getString(Constants.USER_PSW);
        if (!TextUtils.isEmpty(password)) {
            etLoginPassword.setText(password);
        }
        /**
         * 设置是否记录密码
         */
        boolean isRememberPsw = SpUtils.getInstance().getBoolean(Constants.REMENBER_PSW);
        cbLoginRempsw.setChecked(isRememberPsw);
//        LogUitls.d("是否记录密码-->" + isRememberPsw);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        tvLoginVersion.setText(String.format(getString(R.string.login_version), PackageUtils.getAppVersionName(this)));
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        showServerSetDialogShow();
    }

    /**
     * 登录的请求
     * author: timi
     * create at: 2017/8/15 18:24
     */
    @OnClick(R.id.btn_login)
    public void submit() {
        String baseUrl = SpUtils.getInstance().getBaseUrl();
        if (TextUtils.isEmpty(baseUrl)) {
            ToastUtils.showShort(this, "请先配置服务地址");
            return;
        }
        String username = etLoginUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort(this, "请输入用户名");
            return;
        }
        String password = etLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort(this, "请输入密码");
            return;
        }
        //如果记录密码 存储用户名和密码
//        LogUitls.d("是否存入密码-->" + cbLoginRempsw.isChecked());
        if (cbLoginRempsw.isChecked()) {
            //存储用户名和密码
            SpUtils.getInstance()
                    .putString(Constants.USER_TEL, username)
                    .putString(Constants.USER_PSW, password)
                    .putBoolean(Constants.REMENBER_PSW, true);
        } else {
            //清空用户名和密码
            SpUtils.getInstance()
                    .putString(Constants.USER_TEL, "")
                    .putString(Constants.USER_PSW, "")
                    .putBoolean(Constants.REMENBER_PSW, false);
        }
        //登录请求
        getPresenter().getLoginResult(username, password);

    }

    /**
     * 是否显示密码
     */
    @OnClick(R.id.iv_login_eye)
    public void showPassword() {
        isCanSeePsw = !isCanSeePsw;
        //selector 切换
        ivLoginEye.setSelected(isCanSeePsw);
        //设置是否显示密码
        if (!isCanSeePsw) {
            etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());    //将文本框的内容以密文形式显示
        } else {
            etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // 以明文显示
        }
    }

    private LoginBean bean = null;

    @Override
    public void getLoginResult(LoginBean bean) {
        //存储 登录的返回
        this.bean = bean;
//        LogUitls.d("登录的返回的Code--->" + bean.getCode());
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        //登录成功  存储 id
        SpUtils.getInstance().putUserid(bean.getData().get(0).getCuserid());
        SpUtils.getInstance().putString(Constants.USER_NAME, "邢力丰");
        SpUtils.getInstance().putString(Constants.USER_SEX, "男");
        SpUtils.getInstance().putString(Constants.USER_NUM, "123456789");
        SpUtils.getInstance().putString(Constants.USER_DEPART, "研发部");
        SpUtils.getInstance().putString(Constants.USER_FROM, "玖坤深圳总部");
        SpUtils.getInstance().putString(Constants.USER_ROOT, "玖坤苏州分公司");
        //第一次登录以及设置别名
        jumpToMainActivity();
    }

    /**
     * 跳转到主页的方法
     */
    private void jumpToMainActivity() {
        //是否第一次登录
        boolean isFirstLog = SpUtils.getInstance().getBoolean(Constants.IS_FIRST_LOG);
        //判断跳转到不同界面
        if (!isFirstLog) {
            setAlias();
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            onBackPressed();

        }
        //设置 第一次登录为 false
        SpUtils.getInstance().putBoolean(Constants.IS_FIRST_LOG, true);
    }

    /*********************
     * 设置别名
     **************************************************************************************/
    private void setAlias() {
        String alias = bean.getData().get(0).getUser_code();
        LogUitls.d("alias--->" + alias);
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            ToastUtils.showShort(LoginActivity.this, "别名设置格式不正确");
            return;
        }

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0://第一次登录 和设置别名一起操作 （设置别名也只设置一次）
                    Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    if (null != bean) {
                        intent.putParcelableArrayListExtra("userinfo", (ArrayList<? extends Parcelable>) bean.getData());
                    }
                    startActivity(intent);
                    onBackPressed();
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
//                    LogUitls.i(TAG + logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
        }
    };


    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
//                    LogUitls.d(TAG + "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
//                    LogUitls.i(TAG + "Unhandled msg - " + msg.what);
            }
        }
    };
    private MyDialog myDialog = null;

    @OnClick(R.id.btn_set)
    public void onViewClicked() {
        showServerSetDialogShow();
    }

    /**
     * 显示下拉框 选择语言
     *
     * @param view
     */
    private PopupWindow mPop = null;

    public void showSelectLanguagePopwindow(View view) {
        final TextView tvLanguage=myDialog.getTextView(R.id.tv_login_language);
        //初始化
        if (null == mPop) {
            mPop = new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View content = LayoutInflater.from(this).inflate(R.layout.popwindow_select_language, null);
            content.findViewById(R.id.tv_language_simple).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvLanguage.setText(getString(R.string.language_simple));
                    SpUtils.getInstance().putLocaleLanguage("zh-CN");
                    LanguageUtils.switchAppLanguage(LoginActivity.this);
                    mPop.dismiss();
                }
            });
            content.findViewById(R.id.tv_language_trad).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvLanguage.setText(getString(R.string.language_tradtional));
                    SpUtils.getInstance().putLocaleLanguage("zh-TW");
                    LanguageUtils.switchAppLanguage(LoginActivity.this);
                    mPop.dismiss();
                }
            });
            content.findViewById(R.id.tv_language_en).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvLanguage.setText(getString(R.string.language_english));
                    SpUtils.getInstance().putLocaleLanguage("en");
                    LanguageUtils.switchAppLanguage(LoginActivity.this);
                    //窗体消失
                    mPop.dismiss();
                }
            });
            mPop.setContentView(content);
            mPop.setOutsideTouchable(false);
            mPop.setTouchable(true);
            mPop.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
            mPop.setAnimationStyle(R.style.popWindow_animation_push);
        }
        if (null != mPop) {
            //显示窗体
            mPop.showAsDropDown(view);
        }
    }
    private ImageView ivLoginDown=null;
    private void showServerSetDialogShow() {
        if (null == myDialog) {
            myDialog = new MyDialog(this, R.layout.dialog_login_server_set)
                    //设置url
                    .setEdittextContent(R.id.et_login_server, SpUtils.getInstance().getBaseUrl())
                    //设置按钮
                    .setButtonListener(R.id.bt_login__confirm, "设置", new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            EditText etLoginServer = dialog.getEdittext(R.id.et_login_server);
                            String text = etLoginServer.getText().toString();
                            if (TextUtils.isEmpty(text)) {
                                ToastUtils.showShort(LoginActivity.this, "请输入服务地址");
                                return;
                            }
                            // TODO: 2017/8/23 判断url是否正确 可能需要更改
                            if (!text.contains("http")) {
                                ToastUtils.showShort(LoginActivity.this, "请输入正确地址");
                                //重置地址
                                etLoginServer.setText("");
                                return;
                            }
                            //存储url
                            SpUtils.getInstance().putBaseUrl(text);
                            //存储租户信息
                            EditText etLoginZuhu = dialog.getEdittext(R.id.et_login_zuhu);
                            String zuhuStr = etLoginZuhu.getText().toString();
                            if (TextUtils.isEmpty(zuhuStr)) {
                                //存储url
                                SpUtils.getInstance().putString("zuhuStr", zuhuStr);
                            }
                            dialog.dismiss();
                        }
                    }).setLinearlayoutListener(R.id.ll_login_language, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {

                            //显示和隐藏选择语言的下拉框
                            if (null != mPop && mPop.isShowing()) {
                                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.rotation_up);
                                animation.setFillAfter(true);
                                ivLoginDown.startAnimation(animation);
                                mPop.dismiss();
                            } else {
                                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.rotation_down);
                                animation.setFillAfter(true);
                                ivLoginDown.startAnimation(animation);
                                showSelectLanguagePopwindow(myDialog.getTextView(R.id.tv_login_language));
                            }
                        }
                    });
            ivLoginDown= (ImageView) myDialog.getView(R.id.iv_login_down);
        }
        myDialog.show();
    }
}
