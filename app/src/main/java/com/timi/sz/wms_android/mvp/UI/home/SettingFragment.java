package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.LanguageUtils;
import com.timi.sz.wms_android.base.uils.QRCodeUtil;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.HomeEvent;
import com.timi.sz.wms_android.mvp.UI.deviceinfo.DeviceInfoActivity;
import com.timi.sz.wms_android.mvp.UI.login.LoginActivity;
import com.timi.sz.wms_android.mvp.UI.update_password.UpdatePasswordActivity;
import com.timi.sz.wms_android.mvp.UI.userinfo.UserInfoActivity;
import com.timi.sz.wms_android.view.MyDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人设置的碎片
 * author: timi
 * create at: 2017-08-17 11:34
 */
public class SettingFragment extends Fragment implements SetFragmentDataCallBack {
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
    private Unbinder unbinder;

    private UserInfoBean bean = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        unbinder = ButterKnife.bind(this, view);
        if(null!=bean){
            tvSetUsername.setText(bean.userName);
            tvSetUsercode.setText(bean.userDepart);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_set_update_team,R.id.tv_set_language, R.id.tv_set_exit, R.id.tv_set_server, R.id.btn_set_exit, R.id.rl_set_update_version, R.id.iv_set_qr, R.id.rl_set_qr, R.id.tv_set_userinfo, R.id.tv_set_deviceinfo, R.id.tv_set_update_psw, R.id.tv_set_about})
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
                break;
            case R.id.rl_set_update_version://更新版本
                //开始下载的操作
                ((MainActivity)getActivity()).getPresenter().downLoadApk("http://7xk9dj.com1.z0.glb.clouddn.com/BGAUpdateDemo_v1.0.1_debug.apk");
                break;
            case R.id.btn_set_exit://退出登录
                //退出登录 跳转到登录界面
                ((MainActivity) getActivity()).jumpToLoginActivity();
                break;
            case R.id.tv_set_exit://退出登录
                //退出登录 跳转到登录界面
                ((MainActivity) getActivity()).jumpToLoginActivity();
                break;
            case R.id.tv_set_language://选择语言
                showSelectLanguageDialog(view);
                break;
            case R.id.tv_set_server://服务配置
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


}
