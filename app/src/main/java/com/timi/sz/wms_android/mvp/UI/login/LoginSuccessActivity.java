package com.timi.sz.wms_android.mvp.UI.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSuccessActivity extends AutoLayoutActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.statuscolor));
        tvTitle.setText("首次登陆");
        List<LoginBean.DataBean> datas = getIntent().getParcelableArrayListExtra("userinfo");
        if (null != datas) {
//            LogUitls.d("传递过来的数据--->"+ datas.get(0).toString());
            LoginBean.DataBean dataBean = datas.get(0);
            tvLoginSuccessName.setText(String.format(getString(R.string.login_success_name), dataBean.getUser_name()));
            tvLoginSuccessSex.setText(String.format(getString(R.string.login_success_sex), "男"));
            tvLoginSuccessTel.setText(String.format(getString(R.string.login_success_tel), "159958444889"));
            tvLoginSuccessNum.setText(String.format(getString(R.string.login_success_name), "2345311"));
            tvLoginSuccessDepart.setText(String.format(getString(R.string.login_success_depart), "假的数据的部门"));
        }
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
}
