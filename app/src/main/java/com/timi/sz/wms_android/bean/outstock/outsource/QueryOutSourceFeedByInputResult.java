package com.timi.sz.wms_android.bean.outstock.outsource;

import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;

import java.util.List;

/**
 * $dsc 根据委外补料单末尾号查询未审核的委外补料单的返回值
 * author: timi
 * create at: 2017-11-09 10:04
 */

public class QueryOutSourceFeedByInputResult {

    /**
     * summaryResults : {"isLotPick":true,"isRegion":false,"billId":2,"billCode":"0000021731","billDate":"2016-11-29","supplierName":"深圳市引导电子有限公司","createrName":"mes","scanId":0,"qty":6152,"waitQty":6152,"scanQty":0,"warehouseId":0,"warehouseName":null,"regionId":0,"regionName":null}
     * detailResults : [{"detailId":2,"line":2,"materialId":304,"materialCode":"10101010305","materialName":"贴片电阻","materialStandard":"1R/0805/±1%","materialAttribute":"","warehouseId":11,"warehouseName":"深圳市引导电子有限公司","qty":860,"waitQty":860,"scanQty":0},{"detailId":9,"line":9,"materialId":97,"materialCode":"10101010097","materialName":"贴片电阻","materialStandard":"4.7K/0603/±1%","materialAttribute":"","warehouseId":11,"warehouseName":"深圳市引导电子有限公司","qty":4282,"waitQty":4282,"scanQty":0},{"detailId":11,"line":11,"materialId":310,"materialCode":"10101010311","materialName":"贴片电阻","materialStandard":"5.6K/0603/±1%","materialAttribute":"","warehouseId":11,"warehouseName":"深圳市引导电子有限公司","qty":1010,"waitQty":1010,"scanQty":0}]
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
         * isLotPick : true
         * isRegion : false
         * billId : 2
         * billCode : 0000021731
         * billDate : 2016-11-29
         * supplierName : 深圳市引导电子有限公司
         * createrName : mes
         * scanId : 0
         * qty : 6152
         * waitQty : 6152
         * scanQty : 0
         * warehouseId : 0
         * warehouseName : null
         * regionId : 0
         * regionName : null
         */

        private boolean isLotPick;
        private boolean isRegion;
        private int billId;
        private String billCode;
        private String billDate;
        private String supplierName;
        private String createrName;
        private int scanId;
        private int qty;
        private int waitQty;
        private int scanQty;
        private int warehouseId;
        private String warehouseName;
        private int regionId;
        private String regionName;

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

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
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
    }


}
