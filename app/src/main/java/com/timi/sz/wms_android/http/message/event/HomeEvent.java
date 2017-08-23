package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * 主页的事件
 * author: timi
 * create at: 2017-08-23 16:32
 */
public class HomeEvent extends BaseEvent {
    public HomeEvent(String event) {
        super(event);
    }
    //APP的语言改变了
    public static final String LANGUAGE_UPDATE = "LANGUAGE_UPDATE";
}
