package com.timi.sz.wms_android.bean.outstock.product;

import java.util.List;

/**
 * $dsc 获取生产订单明细的发料数据（制单流程）的返回
 * author: timi
 * create at: 2017-11-17 10:20
 */

public class GetPrdDetailPickDataResult {

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

    public static class MaterialResultsBean {
        /**
         * detailId : 0
         * line : 1
         * materialId : 1219
         * materialCode : 10111010002
         * materialName : 端子
         * materialStandard : DJ611-6.3A/散装
         * materialAttribute :
         * warehouseId : 1
         * warehouseName : 原材料仓
         * qty : 303
         * waitQty : 303
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
