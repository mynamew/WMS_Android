package com.timi.sz.wms_android.bean.outstock;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc 退料单的bean
 * author: timi
 * create at: 2017-08-29 14:30
 */

public class OrderNoBean implements Parcelable {
    public String name;
    public String orderno;
    public String date;
    public String returnTotalNum;
    public String returnHaveScanNum;

    public OrderNoBean(String name, String orderno, String date, String returnTotalNum, String returnHaveScanNum, String returnWaitScanlNum) {
        this.name = name;
        this.orderno = orderno;
        this.date = date;
        this.returnTotalNum = returnTotalNum;
        this.returnHaveScanNum = returnHaveScanNum;
        this.returnWaitScanlNum = returnWaitScanlNum;
    }

    protected OrderNoBean(Parcel in) {
        name = in.readString();
        orderno = in.readString();
        date = in.readString();
        returnTotalNum = in.readString();
        returnHaveScanNum = in.readString();
        returnWaitScanlNum = in.readString();
    }

    public static final Creator<OrderNoBean> CREATOR = new Creator<OrderNoBean>() {
        @Override
        public OrderNoBean createFromParcel(Parcel in) {
            return new OrderNoBean(in);
        }

        @Override
        public OrderNoBean[] newArray(int size) {
            return new OrderNoBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReturnTotalNum() {
        return returnTotalNum;
    }

    public void setReturnTotalNum(String returnTotalNum) {
        this.returnTotalNum = returnTotalNum;
    }

    public String getReturnHaveScanNum() {
        return returnHaveScanNum;
    }

    public void setReturnHaveScanNum(String returnHaveScanNum) {
        this.returnHaveScanNum = returnHaveScanNum;
    }

    public String getReturnWaitScanlNum() {
        return returnWaitScanlNum;
    }

    public void setReturnWaitScanlNum(String returnWaitScanlNum) {
        this.returnWaitScanlNum = returnWaitScanlNum;
    }

    public String returnWaitScanlNum;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(orderno);
        dest.writeString(date);
        dest.writeString(returnTotalNum);
        dest.writeString(returnHaveScanNum);
        dest.writeString(returnWaitScanlNum);
    }
}
