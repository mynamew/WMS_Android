package com.timi.sz.wms_android.bean.instock.search;

import java.util.List;

/**
 * $dsc  采购单的bean
 * author: timi
 * create at: 2017-08-30 16:06
 */

public class BuyOrdernoBean   {


    /**
     * summaryResults : {"poCode":"WX141215258","receiveId":0,"poDate":"2014-12-15","bizType":12,"supplierName":"深圳市五维视野有限公司","purEmployeeName":null,"id":205}
     * detailResults : [{"poLine":1,"materialId":3714,"materialCode":"20103010006","materialName":"708按键板(数字)","materialStandard":"六按键/152*15*1.6/装数字显示器","materialAttribute":null,"poQty":5158,"arrivalQty":5158,"inStockQty":5158,"countQty":0,"giveQty":0,"id":455}]
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
         * poCode : WX141215258
         * receiveId : 0
         * poDate : 2014-12-15
         * bizType : 12
         * supplierName : 深圳市五维视野有限公司
         * purEmployeeName : null
         * id : 205
         */

        private String poCode;
        private int receiveId;
        private String poDate;
        private int bizType;
        private String supplierName;
        private String purEmployeeName;
        private int id;

        public String getPoCode() {
            return poCode;
        }

        public void setPoCode(String poCode) {
            this.poCode = poCode;
        }

        public int getReceiveId() {
            return receiveId;
        }

        public void setReceiveId(int receiveId) {
            this.receiveId = receiveId;
        }

        public String getPoDate() {
            return poDate;
        }

        public void setPoDate(String poDate) {
            this.poDate = poDate;
        }

        public int getBizType() {
            return bizType;
        }

        public void setBizType(int bizType) {
            this.bizType = bizType;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getPurEmployeeName() {
            return purEmployeeName;
        }

        public void setPurEmployeeName(String purEmployeeName) {
            this.purEmployeeName = purEmployeeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class DetailResultsBean {
        /**
         * poLine : 1
         * materialId : 3714
         * materialCode : 20103010006
         * materialName : 708按键板(数字)
         * materialStandard : 六按键/152*15*1.6/装数字显示器
         * materialAttribute : null
         * poQty : 5158
         * arrivalQty : 5158
         * inStockQty : 5158
         * countQty : 0
         * giveQty : 0
         * id : 455
         */

        private int poLine;
        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private Object materialAttribute;
        private int poQty;
        private int arrivalQty;
        private int inStockQty;
        private int countQty;
        private int giveQty;
        private int id;

        public int getPoLine() {
            return poLine;
        }

        public void setPoLine(int poLine) {
            this.poLine = poLine;
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

        public Object getMaterialAttribute() {
            return materialAttribute;
        }

        public void setMaterialAttribute(Object materialAttribute) {
            this.materialAttribute = materialAttribute;
        }

        public int getPoQty() {
            return poQty;
        }

        public void setPoQty(int poQty) {
            this.poQty = poQty;
        }

        public int getArrivalQty() {
            return arrivalQty;
        }

        public void setArrivalQty(int arrivalQty) {
            this.arrivalQty = arrivalQty;
        }

        public int getInStockQty() {
            return inStockQty;
        }

        public void setInStockQty(int inStockQty) {
            this.inStockQty = inStockQty;
        }

        public int getCountQty() {
            return countQty;
        }

        public void setCountQty(int countQty) {
            this.countQty = countQty;
        }

        public int getGiveQty() {
            return giveQty;
        }

        public void setGiveQty(int giveQty) {
            this.giveQty = giveQty;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
