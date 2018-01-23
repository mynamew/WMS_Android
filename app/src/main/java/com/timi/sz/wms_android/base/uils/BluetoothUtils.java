package com.timi.sz.wms_android.base.uils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import com.timi.sz.wms_android.qrcode.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
/**
  * 蓝牙的工具类
  * author: timi
  * create at: 2018/1/5 10:15
  */
public class BluetoothUtils {

	/**
	 * 蓝牙是否打开
	 */
	public static boolean isBluetoothOn() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter != null)
			// 蓝牙已打开
			if (mBluetoothAdapter.isEnabled())
				return true;

		return false;
	}

	/**
	 * 获取所有已配对的设备
	 */
	public static List<BluetoothDevice> getPairedDevices() {
		List deviceList = new ArrayList<>();
		Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				deviceList.add(device);
			}
		}
		return deviceList;
	}

	/**
	 * 获取所有已配对的打印类设备
	 */
	public static List<BluetoothDevice> getPairedPrinterDevices() {
		return getSpecificDevice(BluetoothClass.Device.Major.IMAGING);
	}

	/**
	 * 从已配对设配中，删选出某一特定类型的设备展示
	 * @param deviceClass
	 * @return
	 */
	public static List<BluetoothDevice> getSpecificDevice(int deviceClass){
		List<BluetoothDevice> devices = BluetoothUtils.getPairedDevices();
		List<BluetoothDevice> printerDevices = new ArrayList<>();

		for (BluetoothDevice device : devices) {
			BluetoothClass klass = device.getBluetoothClass();
			// 关于蓝牙设备分类参考 http://stackoverflow.com/q/23273355/4242112
			if (klass.getMajorDeviceClass() == deviceClass)
				printerDevices.add(device);
		}

		return printerDevices;
	}

	/**
	 * 弹出系统对话框，请求打开蓝牙
	 */
	public static void openBluetooth(Activity activity) {
		Intent requestBluetoothOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		requestBluetoothOn.setAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		requestBluetoothOn.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 1000);
		activity.startActivityForResult(requestBluetoothOn, Constants.REQUEST_CODE_BLUETOOTH_ON);

	}

	public static BluetoothSocket connectDevice(BluetoothDevice device) {
		BluetoothSocket socket = null;
		try {
			socket = device.createRfcommSocketToServiceRecord(
					UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
			socket.connect();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException closeException) {
				return null;
			}
			return null;
		}
		return socket;
	}

}