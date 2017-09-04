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
        handler.sendEmptyMessageDelayed(0, 2000);
    }
}
