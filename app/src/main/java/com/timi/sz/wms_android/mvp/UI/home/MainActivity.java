package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.SDCardUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.HomeEvent;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    //是否处于后台
    public static boolean isForeground = false;
    @BindView(R.id.rd_home_home)
    RadioButton rdHomeHome;
    @BindView(R.id.rd_home_set)
    RadioButton rdHomeSet;
    @BindView(R.id.rl_home_botom)
    RadioGroup rlHomeBotom;
    @BindView(R.id.fl_home_content)
    FrameLayout flHomeContent;
    //data
    private Fragment homeFM;
    private Fragment setFM;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        BaseMessage.register(this);
    }

    @Override
    public void initView() {
        //设置选中
        rdHomeHome.setChecked(true);
    }

    @Override
    public void initData() {
        //加载首页
        intentIndex(0);
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public MainView createView() {
        return this;
    }
    @Override
    protected void onDestroy() {
        getPresenter().unRegistHttpSubscriber();
        BaseMessage.unregister(this);
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
    @OnClick({R.id.rd_home_home, R.id.rd_home_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rd_home_home://主页
                intentIndex(0);
                break;
            case R.id.rd_home_set://设置
                intentIndex(1);
                break;

        }
    }
    /**
     * 主页下方按钮的点击事件
     *
     * @param index
     */
    private void intentIndex(int index) {
        FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
        hideFragment(trans);
        //设置按钮状态
        rdHomeHome.setChecked(index == 0);
        rdHomeSet.setChecked(index == 1);
        //首页上方的文字
        switch (index) {
            case 0://主页
                if (homeFM == null) {
                    homeFM = new HomeFragment();
                    trans.add(R.id.fl_home_content, homeFM);
                } else {
                    trans.show(homeFM);
                }
                break;
            case 1://个人设置
                //初始化fragment
                if (setFM == null) {
                    setFM = new SettingFragment();
                    trans.add(R.id.fl_home_content, setFM);
                } else {
                    trans.show(setFM);
                }
                //加载用户信息数据
                getPresenter().getUserInfoFromSp();
                break;

            default:

                break;
        }

        try {
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏
     *
     * @param trans
     */
    private void hideFragment(FragmentTransaction trans) {
        if (homeFM != null) trans.hide(homeFM);
        if (setFM != null) trans.hide(setFM);
    }

    /**
     * 接受语言改变的事件 更改文字
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageLanguageUpdata(HomeEvent event){
        rdHomeHome.setText(getResources().getString(R.string.home));
        rdHomeSet.setText(getResources().getString(R.string.home_set));
    }
    @Override
    public void setSpUserInfo(UserInfoBean bean) {
        LogUitls.e(bean.userName);
        //设置数据
        ((SetFragmentDataCallBack) setFM).setData(bean);
    }

    @Override
    public void installApk() {
        // TODO: 2017/8/24  提示安装安装包
        ToastUtils.showShort(this,"安装包下载完成请安装");
        Uri uri = Uri.fromFile(new File(SDCardUtils.getAPKPath(this), Constants.APK_NAME));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

}

