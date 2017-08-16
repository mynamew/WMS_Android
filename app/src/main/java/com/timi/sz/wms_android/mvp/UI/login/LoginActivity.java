package com.timi.sz.wms_android.mvp.UI.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 * author: timi
 * create at: 2017/8/16 8:57
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.tv_login_version)
    TextView tvLoginVersion;
    @BindView(R.id.tv_login_setvers_name)
    TextView tvLoginSetversName;
    @BindView(R.id.ll_servers)
    LinearLayout llServers;
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

    private LoginBean bean = null;

    @Override
    public void getLoginResult(LoginBean bean) {
        //存储 登录的返回
        this.bean = bean;
//        Logger.d("登录的返回的Code--->" + bean.getCode());
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
            if (null != bean) {
                intent.putParcelableArrayListExtra("userinfo", (ArrayList<? extends Parcelable>) bean.getData());
            }
            startActivity(intent);
        } else {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        //设置 第一次登录为 false
        SpUtils.getInstance().putBoolean(Constants.IS_FIRST_LOG, true);
        onBackPressed();
    }
}
