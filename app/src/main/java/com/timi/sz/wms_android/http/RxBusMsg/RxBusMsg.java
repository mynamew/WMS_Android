package com.timi.sz.wms_android.http.RxBusMsg;

/**
 * Created by jzk on 2017/8/9.
 */

public class RxBusMsg {
    public RxBusMsg(String Name, String age) {
        this.Name = Name;
        this.age = age;
    }

    private String Name;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
