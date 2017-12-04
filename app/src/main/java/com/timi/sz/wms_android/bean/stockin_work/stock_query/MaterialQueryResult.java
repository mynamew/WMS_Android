package com.timi.sz.wms_android.bean.stockin_work.stock_query;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-04 01:17
 */

public class MaterialQueryResult {


    /**
     * summaryResults : {"materialId":3912,"materialCode":"20301010017","materialName":"750前壳(带音频)","materialStandard":"烤漆/哑黑/压铸锌合金/69*43*23/钻孔","materialAttribute":null,"totalQty":10}
     * detailResults : [{"lotNo":"2017-08-01","locationNo":"011021104","qty":10}]
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
         * materialId : 3912
         * materialCode : 20301010017
         * materialName : 750前壳(带音频)
         * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
         * materialAttribute : null
         * totalQty : 10
         */

        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private String materialAttribute;
        private int totalQty;

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

        public int getTotalQty() {
            return totalQty;
        }

        public void setTotalQty(int totalQty) {
            this.totalQty = totalQty;
        }
    }

    public static class DetailResultsBean {
        /**
         * lotNo : 2017-08-01
         * locationNo : 011021104
         * qty : 10
         */

        private String lotNo;
        private String locationNo;
        private int qty;

        public String getLotNo() {
            return lotNo;
        }

        public void setLotNo(String lotNo) {
            this.lotNo = lotNo;
        }

        public String getLocationNo() {
            return locationNo;
        }

        public void setLocationNo(String locationNo) {
            this.locationNo = locationNo;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }
}
