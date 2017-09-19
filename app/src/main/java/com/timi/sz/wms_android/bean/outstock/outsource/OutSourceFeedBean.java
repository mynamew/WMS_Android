package com.timi.sz.wms_android.bean.outstock.outsource;


import java.util.List;

/**
 * $dsc  委外补料单 返回结果
 * author: timi
 * create at: 2017-09-05 08:55
 */

public class OutSourceFeedBean {

    public String Orderno;
    public String date;
    public String libName;
    public String area;
    public int sendNum;
    public int receiveNum;
    public int canReceiveNum;
    public int haveGetNum;

    public OutSourceFeedBean(String orderno, String date, String libName, String area, int sendNum, int receiveNum, int canReceiveNum, int haveGetNum, int waitPointNum, int havePointNum, List<MaterialBean> datas) {
        Orderno = orderno;
        this.date = date;
        this.libName = libName;
        this.area = area;
        this.sendNum = sendNum;
        this.receiveNum = receiveNum;
        this.canReceiveNum = canReceiveNum;
        this.haveGetNum = haveGetNum;
        this.waitPointNum = waitPointNum;
        this.havePointNum = havePointNum;
        this.datas = datas;
    }

    public int waitPointNum;
    public int havePointNum;
    public List<MaterialBean> datas;

    public static class MaterialBean {
        public String materialCode;
        public String materialName;
        public int sendNum;
        public int haveReceiveNum;
        public int pointNum;

        public MaterialBean(String materialCode, String materialName, int sendNum, int haveReceiveNum, int pointNum) {
            this.materialCode = materialCode;
            this.materialName = materialName;
            this.sendNum = sendNum;
            this.haveReceiveNum = haveReceiveNum;
            this.pointNum = pointNum;
        }
    }
}
