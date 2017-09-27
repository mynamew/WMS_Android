package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.base.uils.NetWorkUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SDCardUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.HomeEvent;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.fl_home_content)
    FrameLayout flHomeContent;
    @BindView(R.id.iv_home_img)
    ImageView ivHomeImg;
    @BindView(R.id.iv_home_txt)
    ImageView ivHomeTxt;
    @BindView(R.id.tv_home_set)
    TextView tvHomeSet;
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

    }

    @Override
    public void initData() {
        //加载首页
        intentIndex(0);
        String userName = SpUtils.getInstance().getUserName();
        String password = SpUtils.getInstance().getPassword();
        String tenacyName = SpUtils.getInstance().gettenancyName();
        String mac = PackageUtils.getMac();
        /**
         * 获取版本
         */
        getPresenter().getVersion(tenacyName, userName, password, mac);
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
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
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
        setHomeTabCheckStatus(index == 0);
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
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageLanguageUpdata(HomeEvent event) {
        tvHomeSet.setText(getResources().getString(R.string.home_set));
    }

    /**
     * 设置主页下方tab 的选中状态
     * @param isHome
     */
    public void setHomeTabCheckStatus(boolean isHome) {
        ivHomeImg.setSelected(isHome);
        ivHomeTxt.setSelected(isHome);
        tvHomeSet.setSelected(!isHome);
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
        ToastUtils.showShort(this, "安装包下载完成请安装");
        /**
         * 下载完成 存储已下载更新的安装包的标识
         */
        SpUtils.getInstance().putBoolean(Constants.IS_HAVE_DOWNLOAD_NEW, true);
        /**
         * 调用系统方法进行安装
         */
        Uri uri = Uri.fromFile(new File(SDCardUtils.getAPKPath(this), Constants.APK_NAME));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void getVersion(VersionBean bean) {
        try {
            String versionName = PackageUtils.getVersionName(MainActivity.this);
            if (!versionName.equals(bean.getObjectReturn().getVersion())) {
                /**
                 * 若已存在apk包
                 */
                File file = new File(SDCardUtils.getAPKPath(this), Constants.APK_NAME);
                if (file.exists()) {
                    /**
                     * 获取存储的apk中的version code
                     */
                    PackageInfo packageArchiveInfo = getPackageManager().getPackageArchiveInfo(SDCardUtils.getAPKPath(this), PackageManager.GET_ACTIVITIES);
                    int versionCode = packageArchiveInfo.versionCode;
                    /**
                     * 如果已经下载的apk版本号和返回的版本号相同 则直接提示安装
                     */
                    if (versionCode == Integer.parseInt(bean.getObjectReturn().getVersion())) {
                        /**
                         * 调用系统方法进行安装
                         */
                        Uri uri = Uri.fromFile(new File(SDCardUtils.getAPKPath(this), Constants.APK_NAME));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(uri, "application/vnd.android.package-archive");
                        startActivity(intent);
                        return;
                    }
                }
                /**
                 * 若版本不一致 设置需要版本更新
                 */
                SpUtils.getInstance().putBoolean(Constants.IS_HAVE_DOWNLOAD_NEW, false);
                /**
                 * 如果是wifi 下 自动下载apk  并提醒更新
                 */
                if (NetWorkUtils.checkedNetWorkType(this) == NetWorkUtils.WIFI) {
                    getPresenter().downLoadApk(bean.getObjectReturn().getUrl());
                }
            } else {
//                ToastUtils.showShort("已是最新版本，无需更新");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_home, R.id.rl_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_home:
                intentIndex(0);
                break;
            case R.id.rl_mine:
                intentIndex(1);
                break;
        }
    }
}

