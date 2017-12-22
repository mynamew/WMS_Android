package com.timi.sz.wms_android.bean.outstock.pick;

import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-21 08:47
 */

public class GetSalesOutSotckByInputForPickResult {

    /**
     * summaryResults : {"isLotPick":true,"isRegion":false,"billId":110,"billCode":"CK140616002","billDate":"2014-06-16","customerName":"上海卧虎环保科技有限公司","createrName":"方艳冬","scanId":0,"qty":50,"waitQty":50,"scanQty":0,"warehouseId":0,"warehouseName":null,"regionId":0,"regionName":null,"isSplitPrint":true,"isCarton":false,"cartonNo":0}
     * detailResults : [{"detailId":259,"line":1,"materialId":4943,"materialCode":"30101010056","materialName":"MS-708DC显示器","materialStandard":"三路音视频，三路触发/7寸数字屏/24V/13P大D公（139A#)","materialAttribute":"","warehouseId":3,"warehouseName":"成品仓","qty":50,"waitQty":50,"scanQty":0}]
     */

    private SummaryResultsBean summaryResults;
    private List<MaterialResultsBean> detailResults;

    public SummaryResultsBean getSummaryResults() {
        return summaryResults;
    }

    public void setSummaryResults(SummaryResultsBean summaryResults) {
        this.summaryResults = summaryResults;
    }

    public List<MaterialResultsBean> getDetailResults() {
        return detailResults;
    }

    public void setDetailResults(List<MaterialResultsBean> detailResults) {
        this.detailResults = detailResults;
    }

    public static class SummaryResultsBean {
        /**
         * isLotPick : true
         * isRegion : false
         * billId : 110
         * billCode : CK140616002
         * billDate : 2014-06-16
         * customerName : 上海卧虎环保科技有限公司
         * createrName : 方艳冬
         * scanId : 0
         * qty : 50
         * waitQty : 50
         * scanQty : 0
         * warehouseId : 0
         * warehouseName : null
         * regionId : 0
         * regionName : null
         * isSplitPrint : true
         * isCarton : false
         * cartonNo : 0
         */

        private boolean isLotPick;
        private boolean isRegion;
        private int billId;
        private String billCode;
        private String billDate;
        private String customerName;
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
        private boolean isCarton;
        private int cartonNo;

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

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
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

        public boolean isIsCarton() {
            return isCarton;
        }

        public void setIsCarton(boolean isCarton) {
            this.isCarton = isCarton;
        }

        public int getCartonNo() {
            return cartonNo;
        }

        public void setCartonNo(int cartonNo) {
            this.cartonNo = cartonNo;
        }
    }
}
