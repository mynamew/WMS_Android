package com.timi.sz.wms_android.bean.stockin_work.query;

/**
 * $dsc 扫描调入 查询的结果
 * author: timi
 * create at: 2017-10-10 10:11
 */

public class AllotScanResult {

    /**
     * summaryResults : {"isLotPick":false,"isRegion":false,"billId":176,"billCode":"0000000218","billDate":"2015-05-19","outOwner":"","inOwner":"","createrName":"方艳冬","scanId":0,"qty":50,"waitQty":50,"scanQty":0,"warehouseId":0,"warehouseName":null,"regionId":0,"regionName":null,"isSplitPrint":false}
     * detailResults : null
     */

    private SummaryResultsBean summaryResults;
    private Object detailResults;

    public SummaryResultsBean getSummaryResults() {
        return summaryResults;
    }

    public void setSummaryResults(SummaryResultsBean summaryResults) {
        this.summaryResults = summaryResults;
    }

    public Object getDetailResults() {
        return detailResults;
    }

    public void setDetailResults(Object detailResults) {
        this.detailResults = detailResults;
    }

    public static class SummaryResultsBean {
        /**
         * isLotPick : false
         * isRegion : false
         * billId : 176
         * billCode : 0000000218
         * billDate : 2015-05-19
         * outOwner :
         * inOwner :
         * createrName : 方艳冬
         * scanId : 0
         * qty : 50
         * waitQty : 50
         * scanQty : 0
         * warehouseId : 0
         * warehouseName : null
         * regionId : 0
         * regionName : null
         * isSplitPrint : false
         */

        private boolean isLotPick;
        private boolean isRegion;
        private int billId;
        private String billCode;
        private String billDate;
        private String outOwner;
        private String inOwner;
        private String createrName;
        private int scanId;
        private int qty;
        private int waitQty;
        private int scanQty;
        private int warehouseId;
        private Object warehouseName;
        private int regionId;
        private Object regionName;
        private boolean isSplitPrint;

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

        public String getOutOwner() {
            return outOwner;
        }

        public void setOutOwner(String outOwner) {
            this.outOwner = outOwner;
        }

        public String getInOwner() {
            return inOwner;
        }

        public void setInOwner(String inOwner) {
            this.inOwner = inOwner;
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
