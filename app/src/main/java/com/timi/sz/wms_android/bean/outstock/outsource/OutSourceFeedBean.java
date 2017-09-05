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
    public int waitPointNum;
    public int havePointNum;
    public List<MaterialBean> datas;

    class MaterialBean {
        public String materialCode;
        public String materialName;
        public int sendNum;
        public int haveReceiveNum;
        public int pointNum;


    }
}
