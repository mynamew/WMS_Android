package com.timi.sz.wms_android.mvp.UI.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.receiver.ExampleUtil;

import java.util.ArrayList;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
/** 
  * 登录界面
  * author: timi    
  * create at: 2017/8/16 8:57
  */  
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @Bind(R.id.tv_login_version)
    TextView tvLoginVersion;
    @Bind(R.id.tv_login_setvers_name)
    TextView tvLoginSetversName;
    @Bind(R.id.ll_servers)
    LinearLayout llServers;
    @Bind(R.id.et_login_username)
    EditText etLoginUsername;
    @Bind(R.id.et_login_password)
    EditText etLoginPassword;
    @Bind(R.id.cb_login_rempsw)
    CheckBox cbLoginRempsw;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.iv_login_eye)
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
        Logger.d("是否记录密码-->" + isRememberPsw);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginView createView() {
        return this;
    }

    /**
     * 登录的请求
     * author: timi
     * create at: 2017/8/15 18:24
     */
    @OnClick(R.id.btn_login)
    public void submit() {
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
        Logger.d("是否存入密码-->" + cbLoginRempsw.isChecked());
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
        //登录请求
        getPresenter().getLoginResult(username, password);

    }

    /**
     * 是否显示密码
     */
    @OnClick(R.id.iv_login_eye)
    public void showPassword() {
        isCanSeePsw = !isCanSeePsw;
        if (!isCanSeePsw) {
            etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());    //将文本框的内容以密文形式显示
        } else {
            etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance()); // 以明文显示
        }
    }
    private LoginBean bean=null;
    @Override
    public void getLoginResult(LoginBean bean) {
        //存储 登录的返回
        this.bean=bean;
//        Logger.d("登录的返回的Code--->" + bean.getCode());
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        //第一次登录以及设置别名
//        boolean isFirstLog = SpUtils.getInstance().getBoolean(Constants.IS_FIRST_LOG);
//        if (isFirstLog) {
            jumpToMainActivity();
//        } else {
//            设置别名
//            setAlias(isFirstLog);
//        }
    }
    /**
     * 跳转到主页的方法
     */
    private void jumpToMainActivity() {
        //是否第一次登录
        boolean isFirstLog = SpUtils.getInstance().getBoolean(Constants.IS_FIRST_LOG);
        //判断跳转到不同界面
        if (!isFirstLog) {
            Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
            if(null!=bean){
                intent.putParcelableArrayListExtra("userinfo", (ArrayList<? extends Parcelable>) bean.getData());
            }
            startActivity(intent);
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        //设置 第一次登录为 false
        SpUtils.getInstance().putBoolean(Constants.IS_FIRST_LOG, false);
        onBackPressed();
    }
    /*********************
     * 设置别名
     **************************************************************************************/
    private void setAlias(boolean isFirstLog) {
        String alias = "a123456";
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            Toast.makeText(LoginActivity.this, "别名设置格式不正确", Toast.LENGTH_SHORT).show();
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
                    //获取第一次登录标识
                    jumpToMainActivity();
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Logger.i(TAG + logs);
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
                    Logger.d(TAG + "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    Logger.i(TAG + "Unhandled msg - " + msg.what);
            }
        }
    };
}
