package com.timi.sz.wms_android.mvp.UI.deviceinfo;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.NetWorkUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备信息
 * author: timi
 * create at: 2017/8/24 16:38
 */
public class DeviceInfoActivity extends BaseActivity<DeviceInfoView, DeviceInfoPresenter> implements DeviceInfoView {
    @BindView(R.id.et_set_deviceinfo_num)
    EditText etSetDeviceinfoNum;
    @BindView(R.id.tv_set_deviceinfo_mac)
    TextView tvSetDeviceinfoMac;
    @BindView(R.id.tv_set_deviceinfo_ip)
    TextView tvSetDeviceInfoIp;

    @Override
    public int setLayoutId() {
        return R.layout.activity_device_info;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.deviceinfo));
        //设置mac地址
        String strMac = PackageUtils.getLocalMacAddressFromBusybox();
        tvSetDeviceinfoMac.setText(String.format(getString(R.string.set_deviceinfo_mac), TextUtils.isEmpty(strMac) ? "" : strMac));
        //设置ip地址
        String strIp = NetWorkUtils.getIP(this.getApplicationContext());
        tvSetDeviceInfoIp.setText(String.format(getString(R.string.set_deviceinfo_ip), TextUtils.isEmpty(strIp) ? "" : strIp));
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public DeviceInfoPresenter createPresenter() {
        return new DeviceInfoPresenter(this);
    }

    @Override
    public DeviceInfoView createView() {
        return this;
    }

    @OnClick(R.id.btn_deviceinfo_confirm)
    public void onViewClicked() {
        String setDeviceinfoNum = etSetDeviceinfoNum.getText().toString().trim();
        //判断设备编号是否为空
        if (TextUtils.isEmpty(setDeviceinfoNum)) {
            ToastUtils.showShort(DeviceInfoActivity.this, getString(R.string.please_input_device_num));
            return;
        }
        // TODO: 2017/8/24 进行网络请求设置 设备信息
    }



}
