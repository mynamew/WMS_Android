package com.timi.sz.wms_android.bean.bluetooth;

/**
 * $dsc  蓝牙信息的实体
 * author: timi
 * create at: 2017-11-22 14:18
 */

public class BlueToothInfo {
    public BlueToothInfo(String name, int states) {
        this.name = name;
        this.states = states;
    }

    public String name;
    public int states;
}
