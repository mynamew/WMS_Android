package com.timi.sz.wms_android.mvp.UI.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.timi.sz.wms_android.mvp.UI.login.LoginActivity;
import com.zhy.autolayout.AutoLayoutActivity;

/**
  * splash activity
  * author: timi
  * create at: 2017/8/16 9:51
  */
public class SplashActivity extends AutoLayoutActivity {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //是否是第一次登录  跳转登录还是主页
            if (!SpUtils.getInstance().getBoolean(Constants.IS_FIRST_LOG)) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
//            if (SpUtils.getInstance().getBoolean(Constants.IS_FIRST_LOG)) {
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            } else {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            }
            onBackPressed();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTranslucentBackground(this);
        //设置用户信息
        SpUtils.getInstance().putUserid("a12345678");
        SpUtils.getInstance().putString(Constants.USER_NAME,"邢力丰");
        SpUtils.getInstance().putString(Constants.USER_SEX,"男");
        SpUtils.getInstance().putString(Constants.USER_NUM,"123456789");
        SpUtils.getInstance().putString(Constants.USER_DEPART,"研发部");
        SpUtils.getInstance().putString(Constants.USER_TEL,"15754628825");
        SpUtils.getInstance().putString(Constants.USER_FROM,"玖坤深圳总部");
        SpUtils.getInstance().putString(Constants.USER_ROOT,"玖坤苏州分公司");
        SpUtils.getInstance().putBaseUrl("http://222.92.132.196:9000/");
        handler.sendEmptyMessageDelayed(0, 2000);
    }
}
