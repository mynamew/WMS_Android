package com.timi.sz.wms_android.bean.outstock.outsource;

import java.util.List;

/**
 * $dsc 获取委外订单明细的发料数据（制单流程）的返回  获得委外订单一条明细或者合并发料的所有明细汇总的发料数据。
 * author: timi
 * create at: 2017-11-14 09:46
 */

public class GetWWDetailPickDataResult {


   /**
     * totalQty : 5600
     * totalWaitQty : 5600
     * totalScanQty : 0
     * materialResults : [{"line":1,"materialId":480,"materialCode":"10102010075","materialName":"贴片电容","materialStandard":"106/10uF/0805/10V/±10%/X7R","materialAttribute":"","qty":3200,"waitQty":3200,"scanQty":0},{"line":2,"materialId":502,"materialCode":"10102010097","materialName":"贴片电容","materialStandard":"475/4.7uF/0805/25V/±10%","materialAttribute":"","qty":1600,"waitQty":1600,"scanQty":0},{"line":3,"materialId":856,"materialCode":"10105010069","materialName":"稳压电源IC","materialStandard":"ADS5208C25/SOT-25","materialAttribute":"","qty":800,"waitQty":800,"scanQty":0}]
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

    public static class MaterialResultsBean {
        /**
         * line : 1
         * materialId : 480
         * materialCode : 10102010075
         * materialName : 贴片电容
         * materialStandard : 106/10uF/0805/10V/±10%/X7R
         * materialAttribute :
         * qty : 3200
         * waitQty : 3200
         * scanQty : 0
         */

        private int line;
        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private String materialAttribute;
        private int qty;
        private int waitQty;
        private int scanQty;

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
