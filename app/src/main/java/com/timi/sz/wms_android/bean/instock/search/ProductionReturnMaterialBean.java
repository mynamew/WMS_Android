package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-31 13:37
 */

public class ProductionReturnMaterialBean implements Parcelable{
    public String orderNo;
    public int waitPointNum;
    public int havePointNum;
    public String  date;
    public int inStockNum;

    public ProductionReturnMaterialBean(String orderNo,
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

    protected ProductionReturnMaterialBean(Parcel in) {
        orderNo = in.readString();
        waitPointNum = in.readInt();
        havePointNum = in.readInt();
        date = in.readString();
        inStockNum = in.readInt();
    }

    public static final Creator<ProductionReturnMaterialBean> CREATOR = new Creator<ProductionReturnMaterialBean>() {
        @Override
        public ProductionReturnMaterialBean createFromParcel(Parcel in) {
            return new ProductionReturnMaterialBean(in);
        }

        @Override
        public ProductionReturnMaterialBean[] newArray(int size) {
            return new ProductionReturnMaterialBean[size];
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
