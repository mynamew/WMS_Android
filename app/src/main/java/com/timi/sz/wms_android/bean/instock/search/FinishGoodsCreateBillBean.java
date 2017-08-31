package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc 产成品生单查询的返回bean
 * author: timi
 * create at: 2017-08-31 13:24
 */

public class FinishGoodsCreateBillBean implements Parcelable{
    public String orderNo;
    public String  date;
    public int ordernoNum;
    public int haveInStockNum;
    public int canInStockNum;
    public int waitPointNum;
    public int havePointNum;

    public FinishGoodsCreateBillBean(String orderNo,
                                     String date,
                                     int ordernoNum,
                                     int haveInStockNum,
                                     int canInStockNum,
                                     int waitPointNum,
                                     int havePointNum) {
        this.orderNo = orderNo;
        this.date = date;
        this.ordernoNum = ordernoNum;
        this.haveInStockNum = haveInStockNum;
        this.canInStockNum = canInStockNum;
        this.waitPointNum = waitPointNum;
        this.havePointNum = havePointNum;
    }

    protected FinishGoodsCreateBillBean(Parcel in) {
        orderNo = in.readString();
        date = in.readString();
        ordernoNum = in.readInt();
        haveInStockNum = in.readInt();
        canInStockNum = in.readInt();
        waitPointNum = in.readInt();
        havePointNum = in.readInt();
    }

    public static final Creator<FinishGoodsCreateBillBean> CREATOR = new Creator<FinishGoodsCreateBillBean>() {
        @Override
        public FinishGoodsCreateBillBean createFromParcel(Parcel in) {
            return new FinishGoodsCreateBillBean(in);
        }

        @Override
        public FinishGoodsCreateBillBean[] newArray(int size) {
            return new FinishGoodsCreateBillBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNo);
        dest.writeString(date);
        dest.writeInt(ordernoNum);
        dest.writeInt(haveInStockNum);
        dest.writeInt(canInStockNum);
        dest.writeInt(waitPointNum);
        dest.writeInt(havePointNum);
    }
}
