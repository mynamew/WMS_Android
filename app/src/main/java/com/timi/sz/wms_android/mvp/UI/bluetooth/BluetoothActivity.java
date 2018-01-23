package com.timi.sz.wms_android.mvp.UI.bluetooth;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.BaseRecyclerAdapter;
import com.timi.sz.wms_android.base.adapter.RecyclerViewHolder;
import com.timi.sz.wms_android.base.uils.BluetoothUtils;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE_OPEN_GPS;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE_PERMISSION_LOCATION;

/**
 * 测试蓝牙功能
 * author: timi
 * create at: 2018/1/17 14:58
 */
public class BluetoothActivity extends AppCompatActivity {

    @BindView(R.id.iv_print)
    ImageView ivPrint;
    @BindView(R.id.rl_show_print)
    RelativeLayout rlShowPrint;
    @BindView(R.id.rlv_print)
    RecyclerView rlvPrint;
    @BindView(R.id.ll_print_device)
    LinearLayout llPrintDevice;
    private BluetoothAdapter bluetoothAdapter;
    //蓝牙适配器的数据源
    private List<BluetoothDevice> pairedPrinterDevices;
    //蓝牙的适配器
    private BaseRecyclerAdapter<BluetoothDevice> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rl_show_print)
    public void onViewClicked() {
        ivPrint.setSelected(!ivPrint.isSelected());
        //获取蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //当前设备不支持蓝牙
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "本地蓝牙不可用", Toast.LENGTH_SHORT).show();
            ivPrint.setSelected(false);
            return;
        }
        if (ivPrint.isSelected()) {//打开蓝牙 并且显示已连接的打印设备
            //蓝牙是否打开
            if (BluetoothUtils.isBluetoothOn()) {//打开的状态下
                checkPermissions();
            } else {//未打开，则去打开蓝牙
                turnOnBluetooth();
            }
        } else {
            bluetoothAdapter.disable();
        }
    }

    /**
     * 打开蓝牙
     */
    private void turnOnBluetooth() {
        BluetoothUtils.openBluetooth(this);
        // 请求打开 Bluetooth
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_BLUETOOTH_ON) {//打开蓝牙的请求
            if (resultCode == 1000) {//打开了蓝牙，检测权限
                checkPermissions();
            } else {
                ivPrint.setSelected(false);
            }
        }
        if (requestCode == REQUEST_CODE_OPEN_GPS) {
            if (checkGPSIsOpen()) {
                //扫描周围蓝牙设备
                initBluetoothList();
            } else {
                ivPrint.setSelected(false);
            }
        }
    }

    /**
     * 检测权限
     */
    private void checkPermissions() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "本地蓝牙不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        //打开蓝牙
        if (!bluetoothAdapter.isEnabled()) {
            turnOnBluetooth();
            return;
        }

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, REQUEST_CODE_PERMISSION_LOCATION);
        }
    }

    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.notifyTitle)
                            .setMessage(R.string.gpsNotifyMsg)
                            .setNegativeButton(R.string.cancel,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            ivPrint.setSelected(false);
                                        }
                                    })
                            .setPositiveButton(R.string.setting,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, REQUEST_CODE_OPEN_GPS);
                                        }
                                    })

                            .setCancelable(false)
                            .show();
                } else {

                }
                break;
        }
    }

    /**
     * 判断是否开启了定位
     *
     * @return
     */
    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return false;
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    /**
     * 初始化 蓝牙列表
     */
    public void initBluetoothList() {
        pairedPrinterDevices = BluetoothUtils.getPairedPrinterDevices();
        if (null == adapter) {
            adapter = new BaseRecyclerAdapter<BluetoothDevice>(this, pairedPrinterDevices) {
                @Override
                protected int getItemLayoutId(int viewType) {
                    return R.layout.item_bluetooth_info;
                }

                @Override
                protected void bindData(RecyclerViewHolder holder, int position, BluetoothDevice item) {
                    holder.setTextView(R.id.tv_bluetooth_name, item.getName());
                    holder.setTextView(R.id.tv_states, item.getBondState()==BluetoothDevice.BOND_BONDED?"已配对":"未配对");
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int pos) {
                    BluetoothDevice btDev = bluetoothAdapter.getRemoteDevice(pairedPrinterDevices.get(pos).getAddress());
                    BluetoothUtils.connectDevice(btDev);
                }
            });
            rlvPrint.setAdapter(adapter);
            rlvPrint.setLayoutManager(new LinearLayoutManager(this));
        } else
            adapter.notifyDataSetChanged();
    }
}
