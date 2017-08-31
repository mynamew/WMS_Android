package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * $dsc  采购单的bean
 * author: timi
 * create at: 2017-08-30 16:06
 */

public class BuyOrdernoBean  implements Parcelable{
    public BuyOrdernoBean(String num, String buyer, String from, String date, List<MarterialBean> data) {
        this.num = num;
        this.buyer = buyer;
        this.from = from;
        this.date = date;
        this.data = data;
    }

    public String num;
    public String buyer;
    public String from;
    public String date;
    public List<MarterialBean> data;

    protected BuyOrdernoBean(Parcel in) {
        num = in.readString();
        buyer = in.readString();
        from = in.readString();
        date = in.readString();
        data = in.createTypedArrayList(MarterialBean.CREATOR);
    }

    public static final Creator<BuyOrdernoBean> CREATOR = new Creator<BuyOrdernoBean>() {
        @Override
        public BuyOrdernoBean createFromParcel(Parcel in) {
            return new BuyOrdernoBean(in);
        }

        @Override
        public BuyOrdernoBean[] newArray(int size) {
            return new BuyOrdernoBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(num);
        dest.writeString(buyer);
        dest.writeString(from);
        dest.writeString(date);
        dest.writeTypedList(data);
    }


    public static class MarterialBean implements Parcelable {
        public MarterialBean(String lineNum, String materialCode, String buyNum, String arriveNum, String inStockNum, String pointNum, String spareNum) {
            this.lineNum = lineNum;
            this.materialCode = materialCode;
            this.buyNum = buyNum;
            this.arriveNum = Integer.parseInt(arriveNum);
            this.inStockNum = inStockNum;
            this.pointNum = pointNum;
            this.spareNum = spareNum;
        }

        public String lineNum;
        public String materialCode;
        public String buyNum;
        public int arriveNum;
        public String inStockNum;
        public String pointNum;
        public String spareNum;

        protected MarterialBean(Parcel in) {
            lineNum = in.readString();
            materialCode = in.readString();
            buyNum = in.readString();
            arriveNum = in.readInt();
            inStockNum = in.readString();
            pointNum = in.readString();
            spareNum = in.readString();
        }

        public static final Creator<MarterialBean> CREATOR = new Creator<MarterialBean>() {
            @Override
            public MarterialBean createFromParcel(Parcel in) {
                return new MarterialBean(in);
            }

            @Override
            public MarterialBean[] newArray(int size) {
                return new MarterialBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(lineNum);
            dest.writeString(materialCode);
            dest.writeString(buyNum);
            dest.writeInt(arriveNum);
            dest.writeString(inStockNum);
            dest.writeString(pointNum);
            dest.writeString(spareNum);
        }
    }
}
