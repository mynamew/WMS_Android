package com.timi.sz.wms_android.bean.outstock.product;

import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;

import java.util.List;

/**
 * $dsc 生产领料单末尾号查询（审核流程）的返回
 * author: timi
 * create at: 2017-11-17 10:22
 */

public class QueryProductPickByInputResult {

    /**
     * summaryResults : {"srcBillType":23,"destBillType":23,"isMerge":false,"isLotPick":true,"isRegion":false,"billId":53078,"billCode":"0000021721","billDate":"2016-12-14","deptName":"生产部","createrName":"mes","scanId":0,"qty":75,"waitQty":75,"scanQty":0,"warehouseId":0,"warehouseName":null,"regionId":0,"regionName":null,"isSplitPrint":true}
     * materialResults : [{"detailId":390066,"line":6,"materialId":4869,"materialCode":"20701010001","materialName":"遥控器","materialStandard":"硅胶按键/6个按键","materialAttribute":"","warehouseId":2,"warehouseName":"半成品","qty":75,"waitQty":75,"scanQty":0}]
     */

    private SummaryResultsBean summaryResults;
    private List<MaterialResultsBean> materialResults;

    public SummaryResultsBean getSummaryResults() {
        return summaryResults;
    }

    public void setSummaryResults(SummaryResultsBean summaryResults) {
        this.summaryResults = summaryResults;
    }

    public List<MaterialResultsBean> getMaterialResults() {
        return materialResults;
    }

    public void setMaterialResults(List<MaterialResultsBean> materialResults) {
        this.materialResults = materialResults;
    }

    public static class SummaryResultsBean {
        /**
         * srcBillType : 23
         * destBillType : 23
         * isMerge : false
         * isLotPick : true
         * isRegion : false
         * billId : 53078
         * billCode : 0000021721
         * billDate : 2016-12-14
         * deptName : 生产部
         * createrName : mes
         * scanId : 0
         * qty : 75
         * waitQty : 75
         * scanQty : 0
         * warehouseId : 0
         * warehouseName : null
         * regionId : 0
         * regionName : null
         * isSplitPrint : true
         */

        private int srcBillType;
        private int destBillType;
        private boolean isMerge;
        private boolean isLotPick;
        private boolean isRegion;
        private int billId;
        private String billCode;
        private String billDate;
        private String deptName;
        private String createrName;
        private int scanId;
        private int qty;
        private int waitQty;
        private int scanQty;
        private int warehouseId;
        private String warehouseName;
        private int regionId;
        private String regionName;
        private boolean isSplitPrint;

        public int getSrcBillType() {
            return srcBillType;
        }

        public void setSrcBillType(int srcBillType) {
            this.srcBillType = srcBillType;
        }

        public int getDestBillType() {
            return destBillType;
        }

        public void setDestBillType(int destBillType) {
            this.destBillType = destBillType;
        }

        public boolean isIsMerge() {
            return isMerge;
        }

        public void setIsMerge(boolean isMerge) {
            this.isMerge = isMerge;
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

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
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
