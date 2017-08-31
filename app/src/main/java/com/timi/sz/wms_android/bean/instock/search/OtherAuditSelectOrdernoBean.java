package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc 其他审核-选单
 * author: timi
 * create at: 2017-08-31 13:32
 */

public class OtherAuditSelectOrdernoBean implements Parcelable{
    public String orderNo;
    public int waitPointNum;
    public int havePointNum;
    public String  date;
    public int inStockNum;

    public OtherAuditSelectOrdernoBean(String orderNo,
                                       int waitPointNum,
                                       int havePointNum,
                                       String date,
                                       int inStockNum) {
        this.orderNo = orderNo;
        this.waitPointNum = waitPointNum;
        this.havePointNum = havePointNum;
        this.date = date;
        this.inStockNum = inStockNum;
    }

    protected OtherAuditSelectOrdernoBean(Parcel in) {
        orderNo = in.readString();
        waitPointNum = in.readInt();
        havePointNum = in.readInt();
        date = in.readString();
        inStockNum = in.readInt();
    }

    public static final Creator<OtherAuditSelectOrdernoBean> CREATOR = new Creator<OtherAuditSelectOrdernoBean>() {
        @Override
        public OtherAuditSelectOrdernoBean createFromParcel(Parcel in) {
            return new OtherAuditSelectOrdernoBean(in);
        }

        @Override
        public OtherAuditSelectOrdernoBean[] newArray(int size) {
            return new OtherAuditSelectOrdernoBean[size];
        }
    };

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
