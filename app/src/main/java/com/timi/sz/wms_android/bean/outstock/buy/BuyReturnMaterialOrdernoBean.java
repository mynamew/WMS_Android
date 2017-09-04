package com.timi.sz.wms_android.bean.outstock.buy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc 退料单的实体
 * author: timi
 * create at: 2017-09-01 15:37
 */

public class BuyReturnMaterialOrdernoBean implements Parcelable{
    public String orderNo;
    public String date;
    public String supplier;
    public String buyer;
    public int buyNum;
    public int instockNum;
    public MaterialBean bean;

    public BuyReturnMaterialOrdernoBean(String orderNo, String date, String supplier, String buyer, int buyNum, int instockNum, MaterialBean bean) {
        this.orderNo = orderNo;
        this.date = date;
        this.supplier = supplier;
        this.buyer = buyer;
        this.buyNum = buyNum;
        this.instockNum = instockNum;
        this.bean = bean;
    }

    protected BuyReturnMaterialOrdernoBean(Parcel in) {
        orderNo = in.readString();
        date = in.readString();
        supplier = in.readString();
        buyer = in.readString();
        buyNum = in.readInt();
        instockNum = in.readInt();
        bean = in.readParcelable(MaterialBean.class.getClassLoader());
    }

    public static final Creator<BuyReturnMaterialOrdernoBean> CREATOR = new Creator<BuyReturnMaterialOrdernoBean>() {
        @Override
        public BuyReturnMaterialOrdernoBean createFromParcel(Parcel in) {
            return new BuyReturnMaterialOrdernoBean(in);
        }

        @Override
        public BuyReturnMaterialOrdernoBean[] newArray(int size) {
            return new BuyReturnMaterialOrdernoBean[size];
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
        dest.writeString(supplier);
        dest.writeString(buyer);
        dest.writeInt(buyNum);
        dest.writeInt(instockNum);
        dest.writeParcelable(bean, flags);
    }
}
