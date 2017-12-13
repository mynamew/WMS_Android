package com.timi.sz.wms_android.bean.quality.adavance;

import java.util.List;

/**
 * $dsc  用于记录 高级质检数据的检验项目
 * author: timi
 * create at: 2017-12-12 18:51
 */

public class ListCheckItemBean {
    private int sampleSeq;
    private List<GetAdvance2Data.CheckItemDataBean> checkItemBeen;//检验项目
    public int getSampleSeq() {
        return sampleSeq;
    }
    public void setSampleSeq(int sampleSeq) {
        this.sampleSeq = sampleSeq;
    }


    public void setCheckItemBeen(List<GetAdvance2Data.CheckItemDataBean> checkItemBeen) {
        this.checkItemBeen = checkItemBeen;
    }
    public List<GetAdvance2Data.CheckItemDataBean> getCheckItemBeen() {
        return checkItemBeen;
    }

}
