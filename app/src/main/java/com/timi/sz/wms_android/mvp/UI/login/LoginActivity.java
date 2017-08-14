package com.timi.sz.wms_android.mvp.UI.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.http.api.ApiService;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cn.jpush.android.api.JPushInterface.a.s;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView, View.OnClickListener {

    private TextView tv_login_name;
    private TextView tv_login_version;
    private TextView tv_login_setvers_name;
    private ImageView tv_login_setvers_select;
    private EditText tv_login_username;
    private EditText tv_login_password;
    private CheckBox cb_login_rempsw;
    private TextView tv_login_info;
    private Button btn_login;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.text_login_login));
    }

    @Override
    public void initView() {
        tv_login_name = (TextView) findViewById(R.id.tv_login_name);
        tv_login_name.setOnClickListener(this);
        tv_login_version = (TextView) findViewById(R.id.tv_login_version);
        tv_login_version.setOnClickListener(this);
        tv_login_setvers_name = (TextView) findViewById(R.id.tv_login_setvers_name);
        tv_login_setvers_name.setOnClickListener(this);
        tv_login_setvers_select = (ImageView) findViewById(R.id.tv_login_setvers_select);
        tv_login_setvers_select.setOnClickListener(this);
        tv_login_username = (EditText) findViewById(R.id.tv_login_username);
        tv_login_username.setOnClickListener(this);
        tv_login_password = (EditText) findViewById(R.id.tv_login_password);
        tv_login_password.setOnClickListener(this);
        cb_login_rempsw = (CheckBox) findViewById(R.id.cb_login_rempsw);
        cb_login_rempsw.setOnClickListener(this);
        tv_login_info = (TextView) findViewById(R.id.tv_login_info);
        tv_login_info.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login://登录
                submit();
                break;
        }
    }

    /**
     * 登录
     */
    private void submit() {
        String username = tv_login_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = tv_login_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //登录请求
        getPresenter().getLoginResult(username, password);
    }

    @Override
    public void getLoginResult(LoginBean bean) {
        Logger.d("登录的返回的Code--->" + bean.getCode());
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }
}
