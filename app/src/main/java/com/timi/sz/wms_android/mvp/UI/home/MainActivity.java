package com.timi.sz.wms_android.mvp.UI.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.http.RxBusMsg.RxBusCode;
import com.timi.sz.wms_android.http.RxBusMsg.RxBusMsg;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.receiver.ExampleUtil;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import gorden.rxbus2.RxBus;
import gorden.rxbus2.Subscribe;
import gorden.rxbus2.ThreadMode;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    //是否处于后台
    public static boolean isForeground = false;
    //入库数
    @BindView(R.id.tv_home_todayin)
    TextView tvHomeTodayin;
    //出库数
    @BindView(R.id.tv_home_todayout)
    TextView tvHomeTodayout;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        RxBus.get().register(this);
        StatusBarUtil.setColor(this, Color.parseColor("#353338"));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public MainView createView() {
        return this;
    }

    /**
     * rxbus
     */
    @Subscribe(code = RxBusCode.CODE_TEST, threadMode = ThreadMode.MAIN)
    public void getRxBusPost(RxBusMsg msg) {
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unRegister(this);
        getPresenter().unRegistHttpSubscriber();
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }
    /**
     * @param view
     */
    @OnClick({R.id.iv_home_home, R.id.iv_home_setting,R.id.tv_home_lib_in, R.id.tv_home_query, R.id.tv_home_todayin_detail, R.id.tv_home_todayout_detail, R.id.ll_home_in_lib, R.id.ll_home_out_lib, R.id.ll_home_quality})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_todayin_detail://今日入库详情
                ToastUtils.showShort(MainActivity.this, "今日入库详情");
                break;
            case R.id.tv_home_todayout_detail://今日出库详情
                ToastUtils.showShort(MainActivity.this, "今日出库详情");
                break;
            case R.id.ll_home_in_lib://入库作业
                ToastUtils.showShort(MainActivity.this, "入库作业");
                break;
            case R.id.ll_home_out_lib://出库作业
                ToastUtils.showShort(MainActivity.this, "出库作业");
                break;
            case R.id.ll_home_quality://质量作业
                ToastUtils.showShort(MainActivity.this, "质量作业");
                break;
            case R.id.tv_home_lib_in://库内作业
                ToastUtils.showShort(MainActivity.this, "库内作业");
                break;
            case R.id.tv_home_query://综合查询
                ToastUtils.showShort(MainActivity.this, "综合查询");
                break;
            case R.id.iv_home_home://主页
                ToastUtils.showShort(MainActivity.this, "主页");
                break;
            case R.id.iv_home_setting://设置
                ToastUtils.showShort(MainActivity.this, "设置");
                break;
        }
    }


}

