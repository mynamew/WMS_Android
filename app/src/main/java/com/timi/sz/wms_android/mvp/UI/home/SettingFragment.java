package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LanguageUtils;
import com.timi.sz.wms_android.base.uils.NetWorkUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.QRCodeUtil;
import com.timi.sz.wms_android.base.uils.SDCardUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.bean.VersionBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.HomeEvent;
import com.timi.sz.wms_android.mvp.UI.about.AboutActivity;
import com.timi.sz.wms_android.mvp.UI.deviceinfo.DeviceInfoActivity;
import com.timi.sz.wms_android.mvp.UI.update_password.UpdatePasswordActivity;
import com.timi.sz.wms_android.mvp.UI.userinfo.UserInfoActivity;
import com.timi.sz.wms_android.mvp.base.BaseFragment;
import com.timi.sz.wms_android.view.MyDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人设置的碎片
 * author: timi
 * create at: 2017-08-17 11:34
 */
public class SettingFragment extends BaseFragment<SetFragmentView, SetFragmentPresenter> implements SetFragmentDataCallBack, SetFragmentView {
    @BindView(R.id.iv_set_headicon)
    ImageView ivSetHeadicon;
    @BindView(R.id.tv_set_username)
    TextView tvSetUsername;
    @BindView(R.id.tv_set_usercode)
    TextView tvSetUsercode;
    @BindView(R.id.iv_set_qr)
    ImageView ivSetQr;
    @BindView(R.id.rl_set_qr)
    RelativeLayout rlSetQr;
    @BindView(R.id.tv_set_new_version)
    TextView tvSetNewVersion;
    @BindView(R.id.tv_set_userinfo)
    TextView tvSetUserinfo;
    @BindView(R.id.tv_set_deviceinfo)
    TextView tvSetDeviceinfo;
    @BindView(R.id.tv_set_update_psw)
    TextView tvSetUpdatePsw;
    @BindView(R.id.tv_set_language)
    TextView tvSetLanguage;
    @BindView(R.id.tv_set_server)
    TextView tvSetServer;
    @BindView(R.id.tv_set_about)
    TextView tvSetAbout;
    @BindView(R.id.tv_set_update_version)
    TextView tvSetUpdateVersion;
    @BindView(R.id.tv_set_update_team)
    TextView tvSetUpdateTeam;
    @BindView(R.id.tv_set_exit)
    TextView btnSetExit;
    Unbinder unbinder;
    private UserInfoBean bean = null;

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_set_update_team, R.id.tv_set_language, R.id.tv_set_exit, R.id.tv_set_server, R.id.btn_set_exit, R.id.rl_set_update_version, R.id.iv_set_qr, R.id.rl_set_qr, R.id.tv_set_userinfo, R.id.tv_set_deviceinfo, R.id.tv_set_update_psw, R.id.tv_set_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_set_qr://二维码 弹窗 生成二维码
                showQRDialog("杨政a12345678");
                break;
            case R.id.rl_set_qr://弹窗 二维码
                showQRDialog("杨政a12345678");
                break;
            case R.id.tv_set_userinfo://跳转到用户信息
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.tv_set_deviceinfo://跳转到设备信息
                startActivity(new Intent(getActivity(), DeviceInfoActivity.class));
                break;
            case R.id.tv_set_update_psw://跳转到设置密码
                startActivity(new Intent(getActivity(), UpdatePasswordActivity.class));
                break;
            case R.id.tv_set_about://跳转到关于
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.rl_set_update_version://更新版本
                String userName = SpUtils.getInstance().getUserName();
                String password = SpUtils.getInstance().getPassword();
                String tenacyName = SpUtils.getInstance().gettenancyName();
                String mac = PackageUtils.getMac();
                /**
                 * 显示进度条
                 */
                showProgressDialog();
                /**
                 * 获取版本
                 */
                getPresenter().getVersion(tenacyName, userName, password, mac);
                break;
            case R.id.btn_set_exit://退出登录
                shwoLogoutDialog();
                break;
            case R.id.tv_set_exit://退出登录
                //退出登录 跳转到登录界面
                shwoLogoutDialog();
                break;
            case R.id.tv_set_language://选择语言
                showSelectLanguageDialog(view);
                break;
            case R.id.tv_set_server://服务配置
                shwoServerSetDialog();
                break;
            case R.id.tv_set_update_team://组织切换
                break;
        }
    }

    /**
     * 显示 二维码弹窗
     *
     * @param content 二维码内容
     */
    private void showQRDialog(String content) {
        //弹窗
        new MyDialog(getActivity(), R.layout.dialog_qr_userinfo).setImageViewListener(R.id.iv_set_qr_userinfo, QRCodeUtil.createQRCode(content), new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(MyDialog dialog) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 显示退出登录的Dialog
     */
    private MyDialog mLogoutDialog = null;

    /**
     * 显示退出登录的提示框
     */
    public void shwoLogoutDialog() {
        if (null == mLogoutDialog) {
            mLogoutDialog = new MyDialog(getActivity(), R.layout.dialog_logout)
                    .setButtonListener(R.id.tv_logout_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.tv_logout_confirm, getString(R.string.confirm), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            // TODO: 2017/8/25 做登录的数据的清除工作
                            dialog.dismiss();
                            SpUtils.getInstance().putBoolean(Constants.IS_FIRST_LOG, false);                            ((MainActivity) getActivity()).jumpToLoginActivity();
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mLogoutDialog.show();
    }

    /**
     * 显示服务配置的Dialog
     */
    private MyDialog mServerSetDialog = null;

    /**
     * 显示服务配置的提示框
     */
    public void shwoServerSetDialog() {
        if (null == mServerSetDialog) {
            mServerSetDialog = new MyDialog(getActivity(), R.layout.dialog_logout)
                    .setTextViewContent(R.id.tv_title, getString(R.string.server_set))
                    .setTextViewContent(R.id.tv_content, getString(R.string.reset_server_will_logout))
                    .setButtonListener(R.id.tv_logout_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.tv_logout_confirm, getString(R.string.confirm), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            // TODO: 2017/8/25 做登录的数据的清除工作
                            dialog.dismiss();
                            //显示服务配置弹出框
                            ((MainActivity) getActivity()).jumpToLoginActivity(true);
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mServerSetDialog.show();
    }

    /**
     * 显示下拉框 选择语言
     *
     * @param view
     */
    private MyDialog mSelectLanguageDialog = null;

    public void showSelectLanguageDialog(View view) {
        if (null == mSelectLanguageDialog) {
            mSelectLanguageDialog = new MyDialog(getContext(), R.layout.popwindow_select_language)
                    .setTextViewListener(R.id.tv_language_simple, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            setCurrentActivityLanguage(0);
                        }
                    })
                    .setTextViewListener(R.id.tv_language_trad, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            setCurrentActivityLanguage(1);
                        }
                    })
                    .setTextViewListener(R.id.tv_language_en, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            setCurrentActivityLanguage(2);
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mSelectLanguageDialog.show();
    }

    /**
     * 设置当前语言
     *
     * @param index
     */
    private void setCurrentActivityLanguage(int index) {
        switch (index) {
            case 0://简体
                SpUtils.getInstance().putLocaleLanguage("zh-CN");
                break;
            case 1://繁体
                SpUtils.getInstance().putLocaleLanguage("zh-TW");
                break;
            case 2://英文
                SpUtils.getInstance().putLocaleLanguage("en");
                break;
        }
        //存储选择的语言
        LanguageUtils.switchAppLanguage(getActivity());
        //设置弹出框的文字
        mSelectLanguageDialog.getTextView(R.id.tv_language_simple).setText(getResources().getString(R.string.language_simple));
        mSelectLanguageDialog.getTextView(R.id.tv_language_trad).setText(getResources().getString(R.string.language_tradtional));
        mSelectLanguageDialog.getTextView(R.id.tv_language_en).setText(getResources().getString(R.string.language_english));
        //设置 设置界面的文字
        tvSetUserinfo.setText(getResources().getString(R.string.set_userinfo));
        tvSetDeviceinfo.setText(getResources().getString(R.string.set_device_info));
        tvSetUpdatePsw.setText(getResources().getString(R.string.set_update_psw));
        tvSetLanguage.setText(getResources().getString(R.string.set_language));
        tvSetServer.setText(getResources().getString(R.string.server_set));
        tvSetAbout.setText(getResources().getString(R.string.set_about));
        tvSetUpdateVersion.setText(getResources().getString(R.string.set_update_version));
        btnSetExit.setText(getResources().getString(R.string.set_exit));
        tvSetUpdateTeam.setText(getResources().getString(R.string.set_team));
        //发送事件 更新主界面的文字
        BaseMessage.post(new HomeEvent(HomeEvent.LANGUAGE_UPDATE));
        //弹出框消失
        mSelectLanguageDialog.dismiss();
    }

    @Override
    public void setData(UserInfoBean bean) {
        this.bean = bean;
    }


    @Override
    public void installApk() {
        // TODO: 2017/8/24  提示安装安装包
        ToastUtils.showShort(getActivity(), "安装包下载完成请安装");
        /**
         * 下载完成 存储已下载更新的安装包的标识
         */
        SpUtils.getInstance().putBoolean(Constants.IS_HAVE_DOWNLOAD_NEW, true);
        /**
         * 调用系统方法进行安装
         */
        Uri uri = Uri.fromFile(new File(SDCardUtils.getAPKPath(getActivity()), Constants.APK_NAME));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void getVersion(final VersionBean bean) {
        try {
            String versionName = PackageUtils.getVersionName(getActivity());
            if (!versionName.equals(bean.getObjectReturn().getVersion())) {
                /**
                 * 若已存在apk包
                 */
                File file = new File(SDCardUtils.getAPKPath(getActivity()), Constants.APK_NAME);
                if (file.exists()) {
                    /**
                     * 获取存储的apk中的version code
                     */
                    PackageInfo packageArchiveInfo = getActivity().getPackageManager().getPackageArchiveInfo(SDCardUtils.getAPKPath(getActivity()), PackageManager.GET_ACTIVITIES);
                    int versionCode = packageArchiveInfo.versionCode;
                    /**
                     * 如果已经下载的apk版本号和返回的版本号相同 则直接提示安装
                     */
                    if (versionCode == Integer.parseInt(bean.getObjectReturn().getVersion())) {
                        /**
                         * 调用系统方法进行安装
                         */
                        Uri uri = Uri.fromFile(new File(SDCardUtils.getAPKPath(getActivity()), Constants.APK_NAME));
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
                if (NetWorkUtils.checkedNetWorkType(getActivity()) == NetWorkUtils.WIFI) {
                    ToastUtils.showShort("正在下载更新。。。请稍后");
                    getPresenter().downLoadApk(bean.getObjectReturn().getUrl());
                } else {
                    new MyDialog(getActivity(), R.layout.dialog_logout)
                            .setTextViewContent(R.id.tv_title, "下载安装包")
                            .setTextViewContent(R.id.tv_content, "您当前处于非Wifi状态，是否进行下载？")
                            .setTextViewListener(R.id.tv_logout_cancel, new MyDialog.DialogClickListener() {
                                @Override
                                public void dialogClick(MyDialog dialog) {
                                    dialog.dismiss();
                                }
                            }).setTextViewListener(R.id.tv_logout_confirm, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                            ToastUtils.showShort("正在下载更新。。。请稍后");
                            getPresenter().downLoadApk(bean.getObjectReturn().getUrl());
                        }
                    }).show();
                }
            } else {
                ToastUtils.showShort("已是最新版本，无需更新");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initData() {
        if (null != bean) {
            tvSetUsername.setText(bean.userName);
            tvSetUsercode.setText(bean.userDepart);
        }
    }

    @Override
    public SetFragmentPresenter createPresenter() {
        return new SetFragmentPresenter(getActivity());
    }

    @Override
    public SetFragmentView createView() {
        return this;
    }

}
