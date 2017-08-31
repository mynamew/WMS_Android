package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc 委外退料
 * author: timi
 * create at: 2017-08-31 13:36
 */

public class OutReturnMaterialBean implements Parcelable{
    public String orderNo;
    public int waitPointNum;
    public int havePointNum;
    public String  date;
    public int inStockNum;

    public OutReturnMaterialBean(String orderNo,
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

    protected OutReturnMaterialBean(Parcel in) {
        orderNo = in.readString();
        waitPointNum = in.readInt();
        havePointNum = in.readInt();
        date = in.readString();
        inStockNum = in.readInt();
    }

    public static final Creator<OutReturnMaterialBean> CREATOR = new Creator<OutReturnMaterialBean>() {
        @Override
        public OutReturnMaterialBean createFromParcel(Parcel in) {
            return new OutReturnMaterialBean(in);
        }

        @Override
        public OutReturnMaterialBean[] newArray(int size) {
            return new OutReturnMaterialBean[size];
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
