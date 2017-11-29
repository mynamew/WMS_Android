package com.timi.sz.wms_android.bean.outstock.other;

import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;

import java.util.List;

/**
 * $dsc  其他出库
 * author: timi
 * create at: 2017-11-28 15:33
 */

public class QueryOtherOutStockByInputResult {

    /**
     * summaryResults : {"rob":0,"billId":169,"billCode":"0000000213","billDate":"2014-12-12","deptName":"","createrName":"方艳冬","scanId":0,"qty":19,"waitQty":19,"scanQty":0,"isLotPick":true,"isRegion":false,"warehouseId":0,"warehouseName":null,"regionId":0,"regionName":null,"isSplitPrint":true}
     * detailResults : [{"detailId":20017,"line":1,"materialId":1484,"materialCode":"10202010036","materialName":"电源线(135M#）","materialStandard":"135M#/6P航空公-6*浸锡/0.6米","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":19,"waitQty":19,"scanQty":0}]
     */

    private SummaryResultsBean summaryResults;
    private List<DetailResultsBean> detailResults;

    public SummaryResultsBean getSummaryResults() {
        return summaryResults;
    }

    public void setSummaryResults(SummaryResultsBean summaryResults) {
        this.summaryResults = summaryResults;
    }

    public List<DetailResultsBean> getDetailResults() {
        return detailResults;
    }

    public void setDetailResults(List<DetailResultsBean> detailResults) {
        this.detailResults = detailResults;
    }

    public static class SummaryResultsBean {
        /**
         * rob : 0
         * billId : 169
         * billCode : 0000000213
         * billDate : 2014-12-12
         * deptName :
         * createrName : 方艳冬
         * scanId : 0
         * qty : 19
         * waitQty : 19
         * scanQty : 0
         * isLotPick : true
         * isRegion : false
         * warehouseId : 0
         * warehouseName : null
         * regionId : 0
         * regionName : null
         * isSplitPrint : true
         */

        private int rob;
        private int billId;
        private String billCode;
        private String billDate;
        private String deptName;
        private String createrName;
        private int scanId;
        private int qty;
        private int waitQty;
        private int scanQty;
        private boolean isLotPick;
        private boolean isRegion;
        private int warehouseId;
        private Object warehouseName;
        private int regionId;
        private Object regionName;
        private boolean isSplitPrint;

        public int getRob() {
            return rob;
        }

        public void setRob(int rob) {
            this.rob = rob;
        }

        public int getBillId() {
            return billId;
        }

        public void setBillId(int billId) {
            this.billId = billId;
        }

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }

        public String getBillDate() {
            return billDate;
        }

        public void setBillDate(String billDate) {
            this.billDate = billDate;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getCreaterName() {
            return createrName;
        }

        public void setCreaterName(String createrName) {
            this.createrName = createrName;
        }

        public int getScanId() {
            return scanId;
        }

        public void setScanId(int scanId) {
            this.scanId = scanId;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public int getWaitQty() {
            return waitQty;
        }

        public void setWaitQty(int waitQty) {
            this.waitQty = waitQty;
        }

        public int getScanQty() {
            return scanQty;
        }

        public void setScanQty(int scanQty) {
            this.scanQty = scanQty;
        }

        public boolean isIsLotPick() {
            return isLotPick;
        }

        public void setIsLotPick(boolean isLotPick) {
            this.isLotPick = isLotPick;
        }

        public boolean isIsRegion() {
            return isRegion;
        }

        public void setIsRegion(boolean isRegion) {
            this.isRegion = isRegion;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public Object getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(Object warehouseName) {
            this.warehouseName = warehouseName;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public Object getRegionName() {
            return regionName;
        }

        public void setRegionName(Object regionName) {
            this.regionName = regionName;
        }

        public boolean isIsSplitPrint() {
            return isSplitPrint;
        }

        public void setIsSplitPrint(boolean isSplitPrint) {
            this.isSplitPrint = isSplitPrint;
        }
    }
}
