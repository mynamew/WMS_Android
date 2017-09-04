package com.timi.sz.wms_android.bean.outstock.buy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * $dsc 物料的bean
 * author: timi
 * create at: 2017-08-29 13:39
 */

public class MaterialBean  implements Parcelable{
    public String MaterialName;
    public String MaterialCode;
    public String MaterialModel;
    public String MaterialBuyNum;

    public MaterialBean(String materialName, String materialCode, String materialModel, String materialBuyNum) {
        MaterialName = materialName;
        MaterialCode = materialCode;
        MaterialModel = materialModel;
        MaterialBuyNum = materialBuyNum;
    }

    protected MaterialBean(Parcel in) {
        MaterialName = in.readString();
        MaterialCode = in.readString();
        MaterialModel = in.readString();
        MaterialBuyNum = in.readString();
    }

    public static final Creator<MaterialBean> CREATOR = new Creator<MaterialBean>() {
        @Override
        public MaterialBean createFromParcel(Parcel in) {
            return new MaterialBean(in);
        }

        @Override
        public MaterialBean[] newArray(int size) {
            return new MaterialBean[size];
        }
    };

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
    }

    public String getMaterialModel() {
        return MaterialModel;
    }

    public void setMaterialModel(String materialModel) {
        MaterialModel = materialModel;
    }

    public String getMaterialBuyNum() {
        return MaterialBuyNum;
    }

    public void setMaterialBuyNum(String materialBuyNum) {
        MaterialBuyNum = materialBuyNum;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(MaterialName);
        dest.writeString(MaterialCode);
        dest.writeString(MaterialModel);
        dest.writeString(MaterialBuyNum);
    }
}
