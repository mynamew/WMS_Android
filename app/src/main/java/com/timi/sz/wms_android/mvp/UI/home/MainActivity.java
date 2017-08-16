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
        setAlias();
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

    /*********************
     * 设置别名
     **************************************************************************************/
    private void setAlias() {
        String alias = "aa123456";
        if (!ExampleUtil.isValidTagAndAlias(alias)) {
            ToastUtils.showShort(MainActivity.this, "别名设置格式不正确");
            return;
        }

        // 调用 Handler 来异步设置别名
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0://第一次登录 和设置别名一起操作 （设置别名也只设置一次）
                    Logger.i(TAG + "设置别名成功");
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Logger.i(TAG + logs);
                    // 延迟 60 秒来调用 Handler 设置别名
                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
        }
    };


    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
//                    Logger.d(TAG + "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
//                    Logger.i(TAG + "Unhandled msg - " + msg.what);
            }
        }
    };

    /**
     *
     * @param view
     */
    @OnClick({R.id.tv_home_lib_in, R.id.tv_home_query, R.id.tv_home_todayin_detail, R.id.tv_home_todayout_detail, R.id.ll_home_in_lib, R.id.ll_home_out_lib, R.id.ll_home_quality})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_todayin_detail://今日入库详情
                ToastUtils.showShort(MainActivity.this,"今日入库详情");
                break;
            case R.id.tv_home_todayout_detail://今日出库详情
                ToastUtils.showShort(MainActivity.this,"今日出库详情");
                break;
            case R.id.ll_home_in_lib://入库作业
                ToastUtils.showShort(MainActivity.this,"入库作业");
                break;
            case R.id.ll_home_out_lib://出库作业
                ToastUtils.showShort(MainActivity.this,"出库作业");
                break;
            case R.id.ll_home_quality://质量作业
                ToastUtils.showShort(MainActivity.this,"质量作业");
                break;
            case R.id.tv_home_lib_in://库内作业
                ToastUtils.showShort(MainActivity.this,"库内作业");
                break;
            case R.id.tv_home_query://综合查询
                ToastUtils.showShort(MainActivity.this,"综合查询");
                break;
        }
    }
}

