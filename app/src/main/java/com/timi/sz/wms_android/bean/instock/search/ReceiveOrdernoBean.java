package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

import com.timi.sz.wms_android.R;

/**
 * $dsc  收货单
 * author: timi
 * create at: 2017-08-31 11:11
 */

public class ReceiveOrdernoBean implements Parcelable {

    public String orderNo;
    public int totalNum;
    public int qualifiedNum;
    public int inStockNum;
    public int canInStockNum;
    public int waitPointNum;
    public int havePointNum;
    public String  date;

    public ReceiveOrdernoBean(String orderNo, int totalNum, int qualifiedNum, int inStockNum, int canInStockNum, int waitPointNum, int havePointNum, String date) {
        this.orderNo = orderNo;
        this.totalNum = totalNum;
        this.qualifiedNum = qualifiedNum;
        this.inStockNum = inStockNum;
        this.canInStockNum = canInStockNum;
        this.waitPointNum = waitPointNum;
        this.havePointNum = havePointNum;
        this.date = date;
    }

    protected ReceiveOrdernoBean(Parcel in) {
        orderNo = in.readString();
        totalNum = in.readInt();
        qualifiedNum = in.readInt();
        inStockNum = in.readInt();
        canInStockNum = in.readInt();
        waitPointNum = in.readInt();
        havePointNum = in.readInt();
        date = in.readString();
    }

    public static final Creator<ReceiveOrdernoBean> CREATOR = new Creator<ReceiveOrdernoBean>() {
        @Override
        public ReceiveOrdernoBean createFromParcel(Parcel in) {
            return new ReceiveOrdernoBean(in);
        }

        @Override
        public ReceiveOrdernoBean[] newArray(int size) {
            return new ReceiveOrdernoBean[size];
        }
    };

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(int qualifiedNum) {
        this.qualifiedNum = qualifiedNum;
    }

    public int getInStockNum() {
        return inStockNum;
    }

    public void setInStockNum(int inStockNum) {
        this.inStockNum = inStockNum;
    }

    public int getCanInStockNum() {
        return canInStockNum;
    }

    public void setCanInStockNum(int canInStockNum) {
        this.canInStockNum = canInStockNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNo);
        dest.writeInt(totalNum);
        dest.writeInt(qualifiedNum);
        dest.writeInt(inStockNum);
        dest.writeInt(canInStockNum);
        dest.writeInt(waitPointNum);
        dest.writeInt(havePointNum);
        dest.writeString(date);
    }
}
