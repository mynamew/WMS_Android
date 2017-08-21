package com.timi.sz.wms_android.bean;

/**
 * author: timi
 * create at: 2017-08-21 09:
 * 用户信息的bean
 */
public class UserInfoBean {
    public String userNum;
    public String userName;
    public String userTel;
    public String userSex;
    public String userDepart;
    public String userFrom;
    public String userRoot;

    public UserInfoBean() {

    }

    public UserInfoBean(String userDepart, String userFrom, String userName, String userNum, String userRoot, String userSex, String userTel) {
        this.userDepart = userDepart;
        this.userFrom = userFrom;
        this.userName = userName;
        this.userNum = userNum;
        this.userRoot = userRoot;
        this.userSex = userSex;
        this.userTel = userTel;
    }
}
