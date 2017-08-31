package com.timi.sz.wms_android.bean.instock.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-30 16:15
 */

public class SendOrdernoBean implements Parcelable{
    public SendOrdernoBean(String num, String buyer, String from, String date, List<SendOrdernoBean.MarterialBean> data) {
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
    public List<SendOrdernoBean.MarterialBean> data;

    protected SendOrdernoBean(Parcel in) {
        num = in.readString();
        buyer = in.readString();
        from = in.readString();
        date = in.readString();
    }

    public static final Creator<SendOrdernoBean> CREATOR = new Creator<SendOrdernoBean>() {
        @Override
        public SendOrdernoBean createFromParcel(Parcel in) {
            return new SendOrdernoBean(in);
        }

        @Override
        public SendOrdernoBean[] newArray(int size) {
            return new SendOrdernoBean[size];
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
    }

    public static class MarterialBean implements Parcelable {
        public MarterialBean(String lineNum,
                             String materialCode,
                             String buyNum,
                             int sendNum,
                             int receiveNum,
                             String sourceOrderno,
                             String arriveNum,
                             String inStockNum,
                             String pointNum,
                             String spareNum) {
            this.lineNum = lineNum;
            this.materialCode = materialCode;
            this.buyNum = buyNum;
            this.sendNum = sendNum;
            this.receiveNum = receiveNum;
            this.sourceOrderno = sourceOrderno;
            this.arriveNum = arriveNum;
            this.inStockNum = inStockNum;
            this.pointNum = pointNum;
            this.spareNum = spareNum;
        }

        public String lineNum;
        public String materialCode;
        public String buyNum;
        public int sendNum;
        public int  receiveNum;
        public String  sourceOrderno;
        public String arriveNum;
        public String inStockNum;
        public String pointNum;
        public String spareNum;

        protected MarterialBean(Parcel in) {
            lineNum = in.readString();
            materialCode = in.readString();
            buyNum = in.readString();
            sendNum = in.readInt();
            receiveNum = in.readInt();
            sourceOrderno = in.readString();
            arriveNum = in.readString();
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
            dest.writeInt(sendNum);
            dest.writeInt(receiveNum);
            dest.writeString(sourceOrderno);
            dest.writeString(arriveNum);
            dest.writeString(inStockNum);
            dest.writeString(pointNum);
            dest.writeString(spareNum);
        }
    }
}
