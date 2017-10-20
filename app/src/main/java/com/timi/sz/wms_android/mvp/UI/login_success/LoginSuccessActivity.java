package com.timi.sz.wms_android.mvp.UI.login_success;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSuccessActivity extends BaseActivity<LoginSuccessView, LoginSuccessPresenter> implements LoginSuccessView {

    @BindView(R.id.tv_login_success_name)
    TextView tvLoginSuccessName;
    @BindView(R.id.tv_login_success_sex)
    TextView tvLoginSuccessSex;
    @BindView(R.id.tv_login_success_num)
    TextView tvLoginSuccessNum;
    @BindView(R.id.tv_login_success_depart)
    TextView tvLoginSuccessDepart;
    @BindView(R.id.tv_login_success_tel)
    TextView tvLoginSuccessTel;
    @BindView(R.id.btn_login_success_confirm)
    Button btnLoginSuccessConfirm;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.activity_login_success)
    LinearLayout activityLoginSuccess;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login_success;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        tvTitle.setText("首次登陆");

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        /**
         * 获取用户信息
         */
        int userId = SpUtils.getInstance().getUserId();
        String mac =  PackageUtils.getMac();
        getPresenter().getUserInfo(userId,mac);
    }

    @Override
    public LoginSuccessPresenter createPresenter() {
        return new LoginSuccessPresenter(this);
    }

    @Override
    public LoginSuccessView createView() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_login_success_confirm)
    public void confirm() {
        startActivity(new Intent(LoginSuccessActivity.this, MainActivity.class));
        onBackPressed();
    }

    @OnClick(R.id.iv_title_back)
    public void back() {
        startActivity(new Intent(LoginSuccessActivity.this, MainActivity.class));
        onBackPressed();
    }

    @Override
    public void getUserinfo(UserInfoBean bean) {
        if (null != bean) {
            tvLoginSuccessName.setText(String.format(getString(R.string.name), bean.userName));
            tvLoginSuccessSex.setText(String.format(getString(R.string.sex), bean.userSex));
            tvLoginSuccessTel.setText(String.format(getString(R.string.tel),bean.userTel));
            tvLoginSuccessNum.setText(String.format(getString(R.string.work_num), bean.userNum));
            tvLoginSuccessDepart.setText(String.format(getString(R.string.depart), bean.userDepart));
        }
    }
}
