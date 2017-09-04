package com.timi.sz.wms_android.mvp.UI.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.HomeEvent;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.receiver.ExampleUtil;
import com.timi.sz.wms_android.view.MyDialog;
import com.timi.sz.wms_android.view.MyProgressDialog;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * 登录界面
 * author: timi
 * create at: 2017/8/16 8:57
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.iv_login_eye)
    ImageView ivLoginEye;
    @BindView(R.id.tv_login_name)
    TextView tvLoginName;
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
    @BindView(R.id.btn_set)
    Button btnSet;
    @BindView(R.id.iv_login_clear_username)
    ImageView ivLoginClearUsername;

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
            ivLoginClearUsername.setVisibility(View.VISIBLE);
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
        etLoginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if (!TextUtils.isEmpty(username)) {
                    ivLoginClearUsername.setVisibility(View.VISIBLE);
                } else {
                    ivLoginClearUsername.setVisibility(View.GONE);
                }
            }
        });
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
        //如果  url或语言设置为空 则弹出填写框
        if (TextUtils.isEmpty(SpUtils.getInstance().getBaseUrl())
                || TextUtils.isEmpty(SpUtils.getInstance().getLocaleLanguage())) {
            showServerSetDialogShow();
        } else {
            //如果界面传过来的参数需要弹出服务配置的弹出框
            boolean isNeedShowServerSet = getIntent().getBooleanExtra(Constants.IS_NEED_SHOW_SHOW_SERVER_SET, false);
            if (isNeedShowServerSet) {
                showServerSetDialogShow();
            }
        }
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
//        //如果记录密码 存储用户名和密码
        LogUitls.d("是否存入密码-->" + cbLoginRempsw.isChecked());
        if (cbLoginRempsw.isChecked()) {
            //存储用户名和密码
            SpUtils.getInstance()
                    .putString(Constants.USER_NAME, username)
                    .putString(Constants.USER_PSW, password)
                    .putBoolean(Constants.REMENBER_PSW, true);
        } else {
            //清空用户名和密码
            SpUtils.getInstance()
                    .putString(Constants.USER_NAME, "")
                    .putString(Constants.USER_PSW, "")
                    .putBoolean(Constants.REMENBER_PSW, false);
        }
        //获取租户地址
        String tenancyName = SpUtils.getInstance().gettenancyName();
        //获取mac地址
//        String mac = PackageUtils.getLocalMacAddressFromBusybox();
        String mac = PackageUtils.getMac();
//        //登录请求
        getPresenter().getLoginResult(TextUtils.isEmpty(tenancyName) ? "" : tenancyName, username, password, TextUtils.isEmpty(mac) ? "" : mac);
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
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        //登录成功  存储 id
        SpUtils.getInstance().putUserid(bean.getUserId() + "");
        /**
         * 存储用户的所有信息 以字符串的形式
         */
        SpUtils.getInstance().putString(Constants.USER_INFO, bean.toString());
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
        String alias = bean.getUserId() + "";
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
     * 清楚用户名
     */
    @OnClick(R.id.iv_login_clear_username)
    public void clearUsername() {
        etLoginUsername.setText("");
        ivLoginClearUsername.setVisibility(View.GONE);
    }

    /**
     * 显示下拉框 选择语言
     *
     * @param view
     */
    private PopupWindow mPop = null;

    public void showSelectLanguagePopwindow(View view) {
        if (null == mPop) {
            mPop = new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View content = LayoutInflater.from(this).inflate(R.layout.popwindow_select_language, null);
            final TextView tvSimple = (TextView) content.findViewById(R.id.tv_language_simple);
            tvSimple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSimple.setText(getResources().getString(R.string.language_simple));
                    setCurrentActivityLanguage(0);
                    //发送事件 更新主界面的文字
                }
            });
            final TextView tvTrad = (TextView) content.findViewById(R.id.tv_language_trad);
            tvTrad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvTrad.setText(getResources().getString(R.string.language_tradtional));
                    setCurrentActivityLanguage(1);
                }
            });
            final TextView tvEnglish = (TextView) content.findViewById(R.id.tv_language_en);
            tvEnglish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvEnglish.setText(getResources().getString(R.string.language_tradtional));
                    setCurrentActivityLanguage(2);
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

    /**
     * 选择不同的语言 显示不同的语言文字以及Dialog的消失
     *
     * @param index
     */
    private void setCurrentActivityLanguage(int index) {
        final TextView tvLoginLanguage = myDialog.getTextView(R.id.tv_login_language);
        final TextView tvLoginServerSet = myDialog.getTextView(R.id.login_server_set);
        final TextView tvLoginZuhuTip = myDialog.getTextView(R.id.login_zuhu_tip);
        final TextView tvLoginUrlTip = myDialog.getTextView(R.id.login_url_tip);
        final TextView tvLoginLanguageTip = myDialog.getTextView(R.id.login_language_tip);
        final TextView btnLoginComfirmSet = (TextView) myDialog.getView(R.id.bt_login__confirm);
        EditText etLoginUrl = (EditText) myDialog.findViewById(R.id.et_login_server);
        EditText etLoginZuhu = (EditText) myDialog.findViewById(R.id.et_login_zuhu);
        switch (index) {
            case 0://简体
                SpUtils.getInstance().putLocaleLanguage("zh-CN");
                //存储选择的语言
                LanguageUtils.switchAppLanguage(LoginActivity.this);
                tvLoginLanguage.setText(getString(R.string.language_simple));
                break;
            case 1://繁体
                SpUtils.getInstance().putLocaleLanguage("zh-TW");
                LanguageUtils.switchAppLanguage(LoginActivity.this);
                tvLoginLanguage.setText(getString(R.string.language_tradtional));

                break;
            case 2://英文
                SpUtils.getInstance().putLocaleLanguage("en");
                LanguageUtils.switchAppLanguage(LoginActivity.this);
                tvLoginLanguage.setText(getString(R.string.language_english));

                break;
        }
        //切换界面的语言
        btnLogin.setText(getResources().getString(R.string.login_login));
        btnSet.setText(getResources().getString(R.string.login_set));
        tvLoginName.setText(getResources().getString(R.string.app_name));
        cbLoginRempsw.setText(getResources().getString(R.string.login_remember_psw));
        //Dialog 内部
        tvLoginServerSet.setText(getResources().getString(R.string.server_set));
        tvLoginUrlTip.setText(getResources().getString(R.string.login_url_tip));
        tvLoginZuhuTip.setText(getResources().getString(R.string.login_zuhu));
        tvLoginLanguageTip.setText(getResources().getString(R.string.login_language));
        btnLoginComfirmSet.setText(getResources().getString(R.string.login_confirm_set));

        etLoginUrl.setHint(getResources().getString(R.string.login_please_input_serverurl));
        etLoginZuhu.setHint(getResources().getString(R.string.login_please_input_zuhu));
        etLoginPassword.setHint(getResources().getString(R.string.login_inpiut_psw));
        etLoginUsername.setHint(getResources().getString(R.string.login_input_username));
        //发送事件 更新主界面的文字
        BaseMessage.post(new HomeEvent(HomeEvent.LANGUAGE_UPDATE));
        //Dialog消失
        mPop.dismiss();
    }

    private ImageView ivLoginDown = null;

    /**
     * 显示配置服务的dialog
     */
    private void showServerSetDialogShow() {
        if (null == myDialog) {
            String languageStr="";
            switch ( SpUtils.getInstance().getLocaleLanguage()){
                case "zh-CN":
                    languageStr=getString(R.string.language_simple);
                    break;
                case "en":
                    languageStr=getString(R.string.language_english);
                    break;
                case "zh-TW":
                    languageStr=getString(R.string.language_tradtional);
                    break;
                default:
                    languageStr=getString(R.string.language_simple);
                    break;
            }
            myDialog = new MyDialog(this, R.layout.dialog_login_server_set)
                    //设置url
                    .setTextViewContent(R.id.et_login_server, SpUtils.getInstance().getBaseUrl())
                    .setTextViewContent(R.id.et_login_zuhu, SpUtils.getInstance().gettenancyName())
                    .setTextViewContent(R.id.tv_login_language,TextUtils.isEmpty(languageStr)?"":languageStr)
                    //设置按钮
                    .setButtonListener(R.id.bt_login__confirm, getString(R.string.home_set), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            EditText etLoginServer = dialog.getEdittext(R.id.et_login_server);
                            String text = etLoginServer.getText().toString();
                            if (TextUtils.isEmpty(text)) {
                                ToastUtils.showShort(LoginActivity.this, R.string.login_please_input_serverurl);
                                return;
                            }
                            if (!text.contains("http")&&text.endsWith("/")){
                                ToastUtils.showShort(LoginActivity.this, R.string.login_please_input_right_serverurl);
                                //重置地址
                                etLoginServer.setText("");
                                return;
                            }
                            //存储url
                            SpUtils.getInstance().putBaseUrl(text);
                            //存储租户信息
                            String zuhuStr = dialog.getEdittext(R.id.et_login_zuhu).getText().toString();
                            if (!TextUtils.isEmpty(zuhuStr)) {
                                //存储url
                                SpUtils.getInstance().puttenancyName(zuhuStr);
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
            ivLoginDown = (ImageView) myDialog.getView(R.id.iv_login_down);
        }
        myDialog.show();
    }


}
