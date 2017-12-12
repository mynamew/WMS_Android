package com.timi.sz.wms_android.http.message.event;

import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.message.BaseEvent;

/**
 * 主页的事件
 * author: timi
 * create at: 2017-08-23 16:32
 */
public class QualityEvent extends BaseEvent {
    public NormalQualityData.BarcodeDataBean getNewBarDataBean() {
        return newBarDataBean;
    }

    public void setNewBarDataBean(NormalQualityData.BarcodeDataBean newBarDataBean) {
        this.newBarDataBean = newBarDataBean;
    }

    private NormalQualityData.BarcodeDataBean newBarDataBean;
    public QualityEvent(String event) {
        super(event);
    }
    //质检成功
    public static final String QUALITY_SUCCESS = "QUALITY_SUCCESS";
    //质检确认（用于关闭普通质检 高级质检的界面）
    public static final String QUALITY_CONFRIM = "QUALITY_CONFRIM";
    //质检拒收成功  更新数据（用于当质检拒收后 更新前面的数据防止用户不点击 质检拒收界面的确认按钮）
    public static final String QUALITY_REJECT_SUCCESS = "QUALITY_REJECT_SUCCESS";
}
