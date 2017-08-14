package com.timi.sz.wms_android.mvp.UI.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.TestBean;
import com.timi.sz.wms_android.http.RxBusMsg.RxBusCode;
import com.timi.sz.wms_android.http.RxBusMsg.RxBusMsg;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import gorden.rxbus2.RxBus;
import gorden.rxbus2.Subscribe;
import gorden.rxbus2.ThreadMode;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView resultTv;
    private String result;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        RxBus.get().register(this);
    }

    @Override
    public void initView() {
        resultTv = (TextView) findViewById(R.id.result);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                result = "";
                resultTv.setText("");
                RxBus.get().send(RxBusCode.CODE_TEST, new RxBusMsg("23333", "23"));
                getPresenter().getMainMsg(1, 10, "json1");
            }
        });

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
        Logger.e("接收到的post--->" + msg.getName());
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unRegister(this);
        getPresenter().unRegistHttpSubscriber();
        super.onDestroy();
    }

    @Override
    public void onGetMainMsg(TestBean tb) {
        for (TestBean.ListBean bean : tb.getList()) {
            result += bean.toString();
        }
        resultTv.setText(result);
    }
}

