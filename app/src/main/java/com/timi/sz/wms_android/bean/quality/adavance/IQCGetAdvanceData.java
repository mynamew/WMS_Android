package com.timi.sz.wms_android.bean.quality.adavance;

import java.util.List;

/**
 * $dsc   获取高级质检2质检项目
 * author: timi
 * create at: 2017-12-13 14:21
 */

public class IQCGetAdvanceData {

    /**
     * qcQty : 1
     * checkItemData : [{"sampleSeq":1,"checkItemId":10,"qcValue":"10.8","qcResult":0,"faultId":0,"remark":"长度测试"},{"sampleSeq":1,"checkItemId":12,"qcValue":"OK","qcResult":0,"faultId":0,"remark":"外观测试"},{"sampleSeq":1,"checkItemId":13,"qcValue":"0.05","qcResult":0,"faultId":0,"remark":"布式硬度"},{"sampleSeq":1,"checkItemId":14,"qcValue":"OK","qcResult":0,"faultId":0,"remark":"直径测试"}]
     */

    private float qcQty;
    private List<GetAdvance2Data.CheckItemDataBean> checkItemData;

    public float getQcQty() {
        return qcQty;
    }

    public void setQcQty(float qcQty) {
        this.qcQty = qcQty;
    }

    public List<GetAdvance2Data.CheckItemDataBean> getCheckItemData() {
        return checkItemData;
    }

    public void setCheckItemData(List<GetAdvance2Data.CheckItemDataBean> checkItemData) {
        this.checkItemData = checkItemData;
    }


}
