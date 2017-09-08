package com.timi.sz.wms_android.bean.instock.search;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-30 16:15
 */

public class SendOrdernoBean {

    /**
     * summaryResults : {"asnCode":"ASN0001","receiveId":4,"asnDate":"2017-08-31","bizType":10,"supplierName":"深圳市翔飞科技有限公司","createrName":null,"id":1}
     * detailResults : [{"poLine":1,"materialId":3912,"materialCode":"20301010017","materialName":"750前壳(带音频)","materialStandard":"烤漆/哑黑/压铸锌合金/69*43*23/钻孔","materialAttribute":null,"dnQty":100,"recvQty":7,"poQty":20000,"arrivalQty":10672,"inStockQty":11932,"countQty":19,"giveQty":2,"id":1},{"poLine":2,"materialId":3928,"materialCode":"20301010033","materialName":"750后壳","materialStandard":"烤漆/哑黑/压铸锌合金/69*43*33","materialAttribute":null,"dnQty":9,"recvQty":0,"poQty":20000,"arrivalQty":11119,"inStockQty":12379,"countQty":null,"giveQty":null,"id":2},{"poLine":3,"materialId":4008,"materialCode":"20301010113","materialName":"750后壳螺母","materialStandard":"烤漆/哑黑/结构钢/φ11*13","materialAttribute":null,"dnQty":8,"recvQty":0,"poQty":20000,"arrivalQty":11769,"inStockQty":13029,"countQty":null,"giveQty":null,"id":3}]
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
         * asnCode : ASN0001
         * receiveId : 4
         * asnDate : 2017-08-31
         * bizType : 10
         * supplierName : 深圳市翔飞科技有限公司
         * createrName : null
         * id : 1
         */

        private String asnCode;
        private int receiveId;
        private String asnDate;
        private int bizType;
        private String supplierName;
        private String createrName;
        private int id;

        public String getAsnCode() {
            return asnCode;
        }

        public void setAsnCode(String asnCode) {
            this.asnCode = asnCode;
        }

        public int getReceiveId() {
            return receiveId;
        }

        public void setReceiveId(int receiveId) {
            this.receiveId = receiveId;
        }

        public String getAsnDate() {
            return asnDate;
        }

        public void setAsnDate(String asnDate) {
            this.asnDate = asnDate;
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

        public String getCreaterName() {
            return createrName;
        }

        public void setCreaterName(String createrName) {
            this.createrName = createrName;
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
         * materialId : 3912
         * materialCode : 20301010017
         * materialName : 750前壳(带音频)
         * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
         * materialAttribute : null
         * dnQty : 100
         * recvQty : 7
         * poQty : 20000
         * arrivalQty : 10672
         * inStockQty : 11932
         * countQty : 19
         * giveQty : 2
         * id : 1
         */

        private int poLine;
        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private Object materialAttribute;
        private int dnQty;
        private int recvQty;
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

        public int getDnQty() {
            return dnQty;
        }

        public void setDnQty(int dnQty) {
            this.dnQty = dnQty;
        }

        public int getRecvQty() {
            return recvQty;
        }

        public void setRecvQty(int recvQty) {
            this.recvQty = recvQty;
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
