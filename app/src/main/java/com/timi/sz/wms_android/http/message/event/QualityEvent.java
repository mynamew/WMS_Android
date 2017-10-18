package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * 主页的事件
 * author: timi
 * create at: 2017-08-23 16:32
 */
public class QualityEvent extends BaseEvent {
    public QualityEvent(String event) {
        super(event);
    }
    //质检成功
    public static final String QUALITY_SUCCESS = "QUALITY_SUCCESS";
    //质检确认（用于关闭普通质检 高级质检的界面）
    public static final String QUALITY_CONFRIM = "QUALITY_CONFRIM";
}
