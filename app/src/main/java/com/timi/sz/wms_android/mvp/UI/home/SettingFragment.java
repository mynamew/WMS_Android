package com.timi.sz.wms_android.mvp.UI.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.QRCodeUtil;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.bean.UserInfoBean;
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
    @BindView(R.id.tv_set_userinfo)
    TextView tvSetUserinfo;
    @BindView(R.id.tv_set_deviceinfo)
    TextView tvSetDeviceinfo;
    @BindView(R.id.tv_set_update_psw)
    TextView tvSetUpdatePsw;
    @BindView(R.id.tv_set_about)
    TextView tvSetAbout;
    @BindView(R.id.tv_set_update_version)
    TextView tvSetUpdateVersion;
    @BindView(R.id.tv_set_new_version)
    TextView tvSetNewVersion;
    private Unbinder unbinder;

    private UserInfoBean bean = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        unbinder = ButterKnife.bind(this, view);
        tvSetUsername.setText(bean.userName);
        tvSetUsercode.setText(bean.userDepart);
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
    @OnClick({R.id.btn_set_exit, R.id.rl_set_update_version, R.id.iv_set_qr, R.id.rl_set_qr, R.id.tv_set_userinfo, R.id.tv_set_deviceinfo, R.id.tv_set_update_psw, R.id.tv_set_about})
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
                break;
            case R.id.tv_set_update_psw://跳转到设置密码
                break;
            case R.id.tv_set_about://跳转到关于
                break;
            case R.id.rl_set_update_version://更新版本
                break;
            case R.id.btn_set_exit://退出登录
                //退出登录 跳转到登录界面
                ((MainActivity) getActivity()).jumpToLoginActivity();
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

    @Override
    public void setData(UserInfoBean bean) {
        this.bean = bean;
    }


}
