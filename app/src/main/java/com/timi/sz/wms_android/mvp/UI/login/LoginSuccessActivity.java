package com.timi.sz.wms_android.mvp.UI.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.bean.LoginBean;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginSuccessActivity extends AutoLayoutActivity {

    @Bind(R.id.tv_login_success_name)
    TextView tvLoginSuccessName;
    @Bind(R.id.tv_login_success_sex)
    TextView tvLoginSuccessSex;
    @Bind(R.id.tv_login_success_num)
    TextView tvLoginSuccessNum;
    @Bind(R.id.tv_login_success_depart)
    TextView tvLoginSuccessDepart;
    @Bind(R.id.tv_login_success_tel)
    TextView tvLoginSuccessTel;
    @Bind(R.id.btn_login_success_confirm)
    Button btnLoginSuccessConfirm;
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_title_right)
    TextView tvTitleRight;
    @Bind(R.id.activity_login_success)
    LinearLayout activityLoginSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(this, Color.parseColor(Constants.StatusColorStr));
        tvTitle.setText("首次登陆");
        List<LoginBean.DataBean> datas = getIntent().getParcelableArrayListExtra("userinfo");
        if (null != datas) {
            Logger.d("传递过来的数据--->",datas.get(0).toString());
            LoginBean.DataBean dataBean = datas.get(0);
            tvLoginSuccessName.setText(getString(R.string.name_login_success) + dataBean.getUser_name());
            tvLoginSuccessSex.setText(getString(R.string.sex_login_success) + "男");
            tvLoginSuccessTel.setText(getString(R.string.tel_login_success) + "159958444889");
            tvLoginSuccessNum.setText(getString(R.string.num_login_success) + "2345311");
            tvLoginSuccessDepart.setText(getString(R.string.num_login_success) + "假的数据的部门");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
