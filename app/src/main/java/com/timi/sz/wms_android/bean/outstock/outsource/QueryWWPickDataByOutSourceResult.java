package com.timi.sz.wms_android.bean.outstock.outsource;

import com.timi.sz.wms_android.bean.outstock.outsource.common.DetailResultsBean;
import com.timi.sz.wms_android.bean.outstock.outsource.common.MaterialResultsBean;

import java.util.List;

/**
 * $dsc 委外订单末尾号查询（制单流程）的返回结果
 * author: timi
 * create at: 2017-11-14 09:40
 */

public class QueryWWPickDataByOutSourceResult {


    /**
     * summaryResults : {"srcBillType":12,"destBillType":0,"isMerge":false,"isLotPick":true,"isRegion":false,"billId":629,"billCode":"WX161128791","billDate":"2016-11-28","supplierName":"深圳市引导电子有限公司","createrName":"李在梅","scanId":0,"qty":0,"waitQty":0,"scanQty":0,"warehouseId":0,"warehouseName":null,"regionId":0,"regionName":null,"isSplitPrint":true}
     * detailResults : [{"detailId":1356,"poLine":1,"materialId":3609,"materialCode":"20101010056","materialName":"705数字主板（V1.9)","materialStandard":"三路视频，三路触发，无音频/7寸数字屏/12V/123*96*1.6/版本V1.9","materialAttribute":"","poQty":3200,"wipQty":0},{"detailId":1357,"poLine":2,"materialId":3686,"materialCode":"20102010061","materialName":"757M无线主板SMT","materialStandard":"2.4G数字无线，Mars方案，PC3089,N制/丝印MS-757M_PC3089 V1.0/55*38*1.6","materialAttribute":"","poQty":900,"wipQty":0},{"detailId":1358,"poLine":3,"materialId":3796,"materialCode":"20105010009","materialName":"770灯板","materialStandard":"φ5，24颗灯，波长850，90度/φ54*1.2","materialAttribute":"","poQty":4000,"wipQty":0}]
     * materialResults : [{"detailId":0,"line":1,"materialId":480,"materialCode":"10102010075","materialName":"贴片电容","materialStandard":"106/10uF/0805/10V/±10%/X7R","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":3200,"waitQty":3200,"scanQty":0},{"detailId":0,"line":2,"materialId":502,"materialCode":"10102010097","materialName":"贴片电容","materialStandard":"475/4.7uF/0805/25V/±10%","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":3216,"waitQty":3216,"scanQty":0},{"detailId":0,"line":3,"materialId":856,"materialCode":"10105010069","materialName":"稳压电源IC","materialStandard":"ADS5208C25/SOT-25","materialAttribute":"","warehouseId":1,"warehouseName":"原材料仓","qty":1600,"waitQty":1600,"scanQty":0}]
     */

    private SummaryResultsBean summaryResults;
    private List<DetailResultsBean> detailResults;
    private List<MaterialResultsBean> materialResults;

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

    public List<MaterialResultsBean> getMaterialResults() {
        return materialResults;
    }

    public void setMaterialResults(List<MaterialResultsBean> materialResults) {
        this.materialResults = materialResults;
    }

    public static class SummaryResultsBean {
        /**
         * srcBillType : 12
         * destBillType : 0
         * isMerge : false
         * isLotPick : true
         * isRegion : false
         * billId : 629
         * billCode : WX161128791
         * billDate : 2016-11-28
         * supplierName : 深圳市引导电子有限公司
         * createrName : 李在梅
         * scanId : 0
         * qty : 0
         * waitQty : 0
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

        public boolean isIsSplitPrint() {
            return isSplitPrint;
        }

        public void setIsSplitPrint(boolean isSplitPrint) {
            this.isSplitPrint = isSplitPrint;
        }
    }



}
