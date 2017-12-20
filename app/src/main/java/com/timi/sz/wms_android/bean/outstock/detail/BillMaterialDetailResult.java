package com.timi.sz.wms_android.bean.outstock.detail;

import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;

import java.util.List;

/**
 * $dsc  生单的单据详情（委外生单 委外调拨  生产生单  生产调拨  等等）
 * author: timi
 * create at: 2017-11-21 14:49
 */

public class BillMaterialDetailResult {

    /**
     * totalQty : 403
     * totalWaitQty : 403
     * totalScanQty : 0
     * materialResults : [{"detailId":0,"line":1,"materialId":1219,"materialCode":"10111010002","materialName":"端子","materialStandard":"DJ611-6.3A/散装","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":303,"waitQty":303,"scanQty":0},{"detailId":0,"line":2,"materialId":1254,"materialCode":"10111020031","materialName":"3P胶壳","materialStandard":"3P母壳/DJ7031-6.3-11/适配DJ611-6.3A三个端子","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":100,"waitQty":100,"scanQty":0}]
     */

    private int totalQty;
    private int totalWaitQty;
    private int totalScanQty;
    private List<MaterialResultsBean> materialResults;

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getTotalWaitQty() {
        return totalWaitQty;
    }

    public void setTotalWaitQty(int totalWaitQty) {
        this.totalWaitQty = totalWaitQty;
    }

    public int getTotalScanQty() {
        return totalScanQty;
    }

    public void setTotalScanQty(int totalScanQty) {
        this.totalScanQty = totalScanQty;
    }

    public List<MaterialResultsBean> getMaterialResults() {
        return materialResults;
    }

    public void setMaterialResults(List<MaterialResultsBean> materialResults) {
        this.materialResults = materialResults;
    }
}
