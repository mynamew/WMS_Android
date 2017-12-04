package com.timi.sz.wms_android.bean.stockin_work.stock_query;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-04 01:16
 */

public class QueryStockContainerResult {

    /**
     * summaryResults : {"containerNo":"011021104","containerType":"货架","materialCount":4,"totalCount":27}
     * detailResults : [{"lotNo":"2017-08-01","materialId":3912,"materialCode":"20301010017","materialName":"750前壳(带音频)","materialStandard":"烤漆/哑黑/压铸锌合金/69*43*23/钻孔","materialAttribute":null,"qty":10},{"lotNo":"2017-08-01","materialId":3928,"materialCode":"20301010033","materialName":"750后壳","materialStandard":"烤漆/哑黑/压铸锌合金/69*43*33","materialAttribute":null,"qty":6},{"lotNo":"2017-08-12","materialId":3928,"materialCode":"20301010033","materialName":"750后壳","materialStandard":"烤漆/哑黑/压铸锌合金/69*43*33","materialAttribute":null,"qty":3},{"lotNo":"2017-08-15","materialId":3910,"materialCode":"20301010015","materialName":"753A前壳（无孔）","materialStandard":"喷粉/黑色/压铸铝合金/69*43*18.2/不带音频，盐雾840H","materialAttribute":null,"qty":8}]
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
         * containerNo : 011021104
         * containerType : 货架
         * materialCount : 4
         * totalCount : 27
         */

        private String containerNo;
        private String containerType;
        private int materialCount;
        private int totalCount;

        public String getContainerNo() {
            return containerNo;
        }

        public void setContainerNo(String containerNo) {
            this.containerNo = containerNo;
        }

        public String getContainerType() {
            return containerType;
        }

        public void setContainerType(String containerType) {
            this.containerType = containerType;
        }

        public int getMaterialCount() {
            return materialCount;
        }

        public void setMaterialCount(int materialCount) {
            this.materialCount = materialCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

    public static class DetailResultsBean {
        /**
         * lotNo : 2017-08-01
         * materialId : 3912
         * materialCode : 20301010017
         * materialName : 750前壳(带音频)
         * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
         * materialAttribute : null
         * qty : 10
         */

        private String lotNo;
        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private Object materialAttribute;
        private int qty;

        public String getLotNo() {
            return lotNo;
        }

        public void setLotNo(String lotNo) {
            this.lotNo = lotNo;
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

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }
}
