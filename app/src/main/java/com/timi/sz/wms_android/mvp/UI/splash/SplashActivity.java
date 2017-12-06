package com.timi.sz.wms_android.mvp.UI.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.bean.GetPDA_ParameterResult;
import com.timi.sz.wms_android.mvp.UI.home.MainActivity;
import com.timi.sz.wms_android.mvp.UI.login.LoginActivity;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * splash activity
 * author: timi
 * create at: 2017/8/16 9:51
 */
public class SplashActivity extends BaseActivity<SplashView, SplashPresenter> implements SplashView {
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
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentBackground(this);
        SpUtils.getInstance().putBaseUrl(Constants.BASE_URL);
        SpUtils.getInstance().puttenancyName("Default");
        SpUtils.getInstance().putLocaleLanguage("zh-CN");
        SpUtils.getInstance().putUserName("admin");
        SpUtils.getInstance().putPassword("123qwe");
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
//        Map<String, Object> params = new HashMap<>();
//        params.put("UserId", SpUtils.getInstance().getUserId());
//        params.put("OrgId", SpUtils.getInstance().getOrgId());
//        params.put("mac", PackageUtils.getMac());
//        getPresenter().getPDAParams(params);
    }

    @Override
    public SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public SplashView createView() {
        return this;
    }

    @Override
    public void getPDAParams(GetPDA_ParameterResult o) {
        SpUtils.getInstance().putIsGiveGoods(o.isIsGiveGoods());
        SpUtils.getInstance().putIsMaterialAttribute(o.isIsMaterialAttribute());
        SpUtils.getInstance().putIsBillList(o.isIsBillList());
    }
}
