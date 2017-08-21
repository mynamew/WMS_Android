package com.timi.sz.wms_android.mvp.UI.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.statusutils.StatusBarUtil;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.http.RxBusMsg.RxBusCode;
import com.timi.sz.wms_android.http.RxBusMsg.RxBusMsg;
import com.timi.sz.wms_android.mvp.base.BaseActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;
import gorden.rxbus2.RxBus;
import gorden.rxbus2.Subscribe;
import gorden.rxbus2.ThreadMode;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    //是否处于后台
    public static boolean isForeground = false;
    @BindView(R.id.tv_main_top)
    TextView tvMainTop;
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
    @OnClick({R.id.rd_home_home, R.id.rd_home_set, R.id.iv_home_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rd_home_home://主页
                intentIndex(0);
                break;
            case R.id.rd_home_set://设置
                intentIndex(1);
                break;
            case R.id.iv_home_scan://扫码
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 60);
                } else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    Intent intent = new Intent(this, CommonScanActivity.class);

                    String pointMsg = getResources().getString(R.string.scan_point_title);
                    Bundle bundle = new Bundle();
                    bundle.putString("pointMsg", pointMsg);
                    intent.putExtras(bundle);

                    intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        LogUitls.d("扫码的结果--->", bundle.getString("result"));
                    }
                }
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
        tvMainTop.setText(index == 0 ? getString(R.string.home) : getString(R.string.home_set));
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

    @Override
    public void setSpUserInfo(UserInfoBean bean) {
        LogUitls.e(bean.userName);
        //设置数据
        ((SetFragmentDataCallBack) setFM).setData(bean);
    }

}

