package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc  产成品搜索订单号的返回
 * author: timi
 * create at: 2017-08-31 11:18
 */

public class FinishGoodsOrdernoBean implements Parcelable{
    public String orderNo;
    public int waitPointNum;
    public int havePointNum;
    public String  date;
    public int inStockNum;

    public FinishGoodsOrdernoBean(String orderNo, int waitPointNum, int havePointNum, String date, int inStockNum) {
        this.orderNo = orderNo;
        this.waitPointNum = waitPointNum;
        this.havePointNum = havePointNum;
        this.date = date;
        this.inStockNum = inStockNum;
    }

    protected FinishGoodsOrdernoBean(Parcel in) {
        orderNo = in.readString();
        waitPointNum = in.readInt();
        havePointNum = in.readInt();
        date = in.readString();
        inStockNum = in.readInt();
    }

    public static final Creator<FinishGoodsOrdernoBean> CREATOR = new Creator<FinishGoodsOrdernoBean>() {
        @Override
        public FinishGoodsOrdernoBean createFromParcel(Parcel in) {
            return new FinishGoodsOrdernoBean(in);
        }

        @Override
        public FinishGoodsOrdernoBean[] newArray(int size) {
            return new FinishGoodsOrdernoBean[size];
        }
    };

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getWaitPointNum() {
        return waitPointNum;
    }

    public void setWaitPointNum(int waitPointNum) {
        this.waitPointNum = waitPointNum;
    }

    public int getHavePointNum() {
        return havePointNum;
    }

    public void setHavePointNum(int havePointNum) {
        this.havePointNum = havePointNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getInStockNum() {
        return inStockNum;
    }

    public void setInStockNum(int inStockNum) {
        this.inStockNum = inStockNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNo);
        dest.writeInt(waitPointNum);
        dest.writeInt(havePointNum);
        dest.writeString(date);
        dest.writeInt(inStockNum);
    }
}
