package com.timi.sz.wms_android.bean.outstock.outsource;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-11-14 09:16
 */

public class QueryOutSourcePickByInputResult {

    /**
     * summaryResults : {"srcBillType":20,"destBillType":20,"isMerge":false,"isLotPick":true,"isRegion":false,"billId":1307,"billCode":"0000000510","billDate":"2014-05-21","supplierName":"2014/5/21 0:00:00","createrName":"方艳冬","scanId":0,"qty":1008,"waitQty":1008,"scanQty":0,"warehouseId":0,"warehouseName":null,"RegionId":0,"regionName":null}
     * materialResults : [{"detailId":15609,"line":35,"materialId":198,"materialCode":"10101010198","materialName":"贴片电阻","materialStandard":"330R/1206/±5%","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":504,"waitQty":504,"scanQty":0},{"detailId":15626,"line":52,"materialId":447,"materialCode":"10102010042","materialName":"贴片电容","materialStandard":"224/220nF/0603/25V/±10%","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":504,"waitQty":504,"scanQty":0}]
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
         * srcBillType : 20
         * destBillType : 20
         * isMerge : false
         * isLotPick : true
         * isRegion : false
         * billId : 1307
         * billCode : 0000000510
         * billDate : 2014-05-21
         * supplierName : 2014/5/21 0:00:00
         * createrName : 方艳冬
         * scanId : 0
         * qty : 1008
         * waitQty : 1008
         * scanQty : 0
         * warehouseId : 0
         * warehouseName : null
         * RegionId : 0
         * regionName : null
         */

        private int srcBillType;
        private int destBillType;
        private boolean isMerge;
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
        private int RegionId;
        private String regionName;

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
            return RegionId;
        }

        public void setRegionId(int RegionId) {
            this.RegionId = RegionId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }
    }

    public static class MaterialResultsBean {
        /**
         * detailId : 15609
         * line : 35
         * materialId : 198
         * materialCode : 10101010198
         * materialName : 贴片电阻
         * materialStandard : 330R/1206/±5%
         * materialAttribute :
         * warehouseId : 1
         * warehouseName : 原材料仓
         * qty : 504
         * waitQty : 504
         * scanQty : 0
         */

        private int detailId;
        private int line;
        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private String materialAttribute;
        private int warehouseId;
        private String warehouseName;
        private int qty;
        private int waitQty;
        private int scanQty;

        public int getDetailId() {
            return detailId;
        }

        public void setDetailId(int detailId) {
            this.detailId = detailId;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getMaterialId() {
            return materialId;
        }

        public void setMaterialId(int materialId) {
            this.materialId = materialId;
        }

        public String getMaterialCode() {
            return materialCode;
        }

        public void setMaterialCode(String materialCode) {
            this.materialCode = materialCode;
        }

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public String getMaterialStandard() {
            return materialStandard;
        }

        public void setMaterialStandard(String materialStandard) {
            this.materialStandard = materialStandard;
        }

        public String getMaterialAttribute() {
            return materialAttribute;
        }

        public void setMaterialAttribute(String materialAttribute) {
            this.materialAttribute = materialAttribute;
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
    }
}
