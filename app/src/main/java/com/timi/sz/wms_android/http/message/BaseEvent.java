package com.timi.sz.wms_android.http.message;

/**
 * 事件的基类
 * author: timi
 * create at: 2017-08-23 16:27
 */
public class BaseEvent {
    private String event;

    public BaseEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
