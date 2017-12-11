package com.timi.sz.wms_android.bean.quality.adavance;

import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-18 16:52
 */

public class GetAdvance2Data {

    /**
     * normalSummary : {"receiptId":8709,"receiptDetailId":20651,"receiptCode":"DH160628005","receiptDate":"2017-09-11","sourceBillCode":"CG160425242","supplierName":"深圳市宝安区大浪恒昶塑胶电子厂","creater":null,"materialId":3912,"materialCode":"20301010017","materialName":"750前壳(带音频)","materialStandard":"烤漆/哑黑/压铸锌合金/69*43*23/钻孔","materialAttribute":null,"receiveQty":600,"sampleQty":125,"ngQty":0,"rejectQty":0,"qcStatus":2,"qcResult":1,"isBarCode":false,"barcodeSource":1}
     * advanceSummary : {"qcType":3,"currentStrict":"加严","currentLevel":"III","sampleCode":"K","currentAQL":"0.65","aqlAcceptQty":0,"aqlRejectQty":3,"beginQty":501,"endQty":501,"qcQty":1,"fatalQty":0,"seriousQty":0,"commonlyQty":0,"slightQty":0}
     * checkItem : [{"checkItemId":44,"checkItemCode":"ITEM01","checkItemName":"PQC检验项目01","judgeType":0,"unit":"","limitLow":3,"limitHigh":2,"stardard":3,"remark":"","faultData":[{"faultId":22,"faultCode":"F007","faultName":"表面凸起","faultDesc":"","faultQty":0,"qC_DefectGrade":"A"}]}]
     * checkItemData : [{"sampleSeq":1,"checkItemId":44,"qcValue":"0","qcResult":0,"faultId":0,"remark":""}]
     * barcodeData : [{"BarcodeNo":"实收数","PackQty":44,"CurrentQty":"0","RejectQty":0}]
     * faultData : [{"faultId":2,"faultCode":"002","faultName":"外观不良","faultDesc":"外观不良","faultQty":2,"qC_DefectGrade":"B"}]
     */

    private NormalSummaryBean normalSummary;
    private AdvanceSummaryBean advanceSummary;
    private List<CheckItemBean> checkItem;
    private List<CheckItemDataBean> checkItemData;
    private List<NormalQualityData.BarcodeDataBean> barcodeData;
    private List<FaultDataBeanX> faultData;

    public NormalSummaryBean getNormalSummary() {
        return normalSummary;
    }

    public void setNormalSummary(NormalSummaryBean normalSummary) {
        this.normalSummary = normalSummary;
    }

    public AdvanceSummaryBean getAdvanceSummary() {
        return advanceSummary;
    }

    public void setAdvanceSummary(AdvanceSummaryBean advanceSummary) {
        this.advanceSummary = advanceSummary;
    }

    public List<CheckItemBean> getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(List<CheckItemBean> checkItem) {
        this.checkItem = checkItem;
    }

    public List<CheckItemDataBean> getCheckItemData() {
        return checkItemData;
    }

    public void setCheckItemData(List<CheckItemDataBean> checkItemData) {
        this.checkItemData = checkItemData;
    }

    public List<NormalQualityData.BarcodeDataBean> getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(List<NormalQualityData.BarcodeDataBean> barcodeData) {
        this.barcodeData = barcodeData;
    }

    public List<FaultDataBeanX> getFaultData() {
        return faultData;
    }

    public void setFaultData(List<FaultDataBeanX> faultData) {
        this.faultData = faultData;
    }

    public static class NormalSummaryBean {
        /**
         * receiptId : 8709
         * receiptDetailId : 20651
         * receiptCode : DH160628005
         * receiptDate : 2017-09-11
         * sourceBillCode : CG160425242
         * supplierName : 深圳市宝安区大浪恒昶塑胶电子厂
         * creater : null
         * materialId : 3912
         * materialCode : 20301010017
         * materialName : 750前壳(带音频)
         * materialStandard : 烤漆/哑黑/压铸锌合金/69*43*23/钻孔
         * materialAttribute : null
         * receiveQty : 600
         * sampleQty : 125
         * ngQty : 0
         * rejectQty : 0
         * qcStatus : 2
         * qcResult : 1
         * isBarCode : false
         * barcodeSource : 1
         */

        private int receiptId;
        private int receiptDetailId;
        private String receiptCode;
        private String receiptDate;
        private String sourceBillCode;
        private String supplierName;
        private String creater;
        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private String materialAttribute;
        private int receiveQty;
        private int sampleQty;
        private int ngQty;
        private int rejectQty;
        private int qcStatus;
        private int qcResult;
        private boolean isBarCode;
        private int barcodeSource;

        public int getReceiptId() {
            return receiptId;
        }

        public void setReceiptId(int receiptId) {
            this.receiptId = receiptId;
        }

        public int getReceiptDetailId() {
            return receiptDetailId;
        }

        public void setReceiptDetailId(int receiptDetailId) {
            this.receiptDetailId = receiptDetailId;
        }

        public String getReceiptCode() {
            return receiptCode;
        }

        public void setReceiptCode(String receiptCode) {
            this.receiptCode = receiptCode;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
        }

        public String getSourceBillCode() {
            return sourceBillCode;
        }

        public void setSourceBillCode(String sourceBillCode) {
            this.sourceBillCode = sourceBillCode;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getCreater() {
            return creater;
        }

        public void setCreater(String creater) {
            this.creater = creater;
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

        public int getReceiveQty() {
            return receiveQty;
        }

        public void setReceiveQty(int receiveQty) {
            this.receiveQty = receiveQty;
        }

        public int getSampleQty() {
            return sampleQty;
        }

        public void setSampleQty(int sampleQty) {
            this.sampleQty = sampleQty;
        }

        public int getNgQty() {
            return ngQty;
        }

        public void setNgQty(int ngQty) {
            this.ngQty = ngQty;
        }

        public int getRejectQty() {
            return rejectQty;
        }

        public void setRejectQty(int rejectQty) {
            this.rejectQty = rejectQty;
        }

        public int getQcStatus() {
            return qcStatus;
        }

        public void setQcStatus(int qcStatus) {
            this.qcStatus = qcStatus;
        }

        public int getQcResult() {
            return qcResult;
        }

        public void setQcResult(int qcResult) {
            this.qcResult = qcResult;
        }

        public boolean isIsBarCode() {
            return isBarCode;
        }

        public void setIsBarCode(boolean isBarCode) {
            this.isBarCode = isBarCode;
        }

        public int getBarcodeSource() {
            return barcodeSource;
        }

        public void setBarcodeSource(int barcodeSource) {
            this.barcodeSource = barcodeSource;
        }
    }

    public static class AdvanceSummaryBean {
        /**
         * qcType : 3
         * currentStrict : 加严
         * currentLevel : III
         * sampleCode : K
         * currentAQL : 0.65
         * aqlAcceptQty : 0
         * aqlRejectQty : 3
         * beginQty : 501
         * endQty : 501
         * qcQty : 1
         * fatalQty : 0
         * seriousQty : 0
         * commonlyQty : 0
         * slightQty : 0
         */

        private int qcType;
        private String currentStrict;
        private String currentLevel;
        private String sampleCode;
        private String currentAQL;
        private int aqlAcceptQty;
        private int aqlRejectQty;
        private int beginQty;
        private int endQty;
        private int qcQty;
        private int fatalQty;
        private int seriousQty;
        private int commonlyQty;
        private int slightQty;

        public int getQcType() {
            return qcType;
        }

        public void setQcType(int qcType) {
            this.qcType = qcType;
        }

        public String getCurrentStrict() {
            return currentStrict;
        }

        public void setCurrentStrict(String currentStrict) {
            this.currentStrict = currentStrict;
        }

        public String getCurrentLevel() {
            return currentLevel;
        }

        public void setCurrentLevel(String currentLevel) {
            this.currentLevel = currentLevel;
        }

        public String getSampleCode() {
            return sampleCode;
        }

        public void setSampleCode(String sampleCode) {
            this.sampleCode = sampleCode;
        }

        public String getCurrentAQL() {
            return currentAQL;
        }

        public void setCurrentAQL(String currentAQL) {
            this.currentAQL = currentAQL;
        }

        public int getAqlAcceptQty() {
            return aqlAcceptQty;
        }

        public void setAqlAcceptQty(int aqlAcceptQty) {
            this.aqlAcceptQty = aqlAcceptQty;
        }

        public int getAqlRejectQty() {
            return aqlRejectQty;
        }

        public void setAqlRejectQty(int aqlRejectQty) {
            this.aqlRejectQty = aqlRejectQty;
        }

        public int getBeginQty() {
            return beginQty;
        }

        public void setBeginQty(int beginQty) {
            this.beginQty = beginQty;
        }

        public int getEndQty() {
            return endQty;
        }

        public void setEndQty(int endQty) {
            this.endQty = endQty;
        }

        public int getQcQty() {
            return qcQty;
        }

        public void setQcQty(int qcQty) {
            this.qcQty = qcQty;
        }

        public int getFatalQty() {
            return fatalQty;
        }

        public void setFatalQty(int fatalQty) {
            this.fatalQty = fatalQty;
        }

        public int getSeriousQty() {
            return seriousQty;
        }

        public void setSeriousQty(int seriousQty) {
            this.seriousQty = seriousQty;
        }

        public int getCommonlyQty() {
            return commonlyQty;
        }

        public void setCommonlyQty(int commonlyQty) {
            this.commonlyQty = commonlyQty;
        }

        public int getSlightQty() {
            return slightQty;
        }

        public void setSlightQty(int slightQty) {
            this.slightQty = slightQty;
        }
    }

    public static class CheckItemBean {
        /**
         * checkItemId : 44
         * checkItemCode : ITEM01
         * checkItemName : PQC检验项目01
         * judgeType : 0
         * unit :
         * limitLow : 3
         * limitHigh : 2
         * stardard : 3
         * remark :
         * faultData : [{"faultId":22,"faultCode":"F007","faultName":"表面凸起","faultDesc":"","faultQty":0,"qC_DefectGrade":"A"}]
         */

        private int checkItemId;
        private String checkItemCode;
        private String checkItemName;
        private int judgeType;
        private String unit;
        private float limitLow;
        private float limitHigh;
        private float stardard;
        private String remark;
        private List<FaultDataBean> faultData;

        public int getCheckItemId() {
            return checkItemId;
        }

        public void setCheckItemId(int checkItemId) {
            this.checkItemId = checkItemId;
        }

        public String getCheckItemCode() {
            return checkItemCode;
        }

        public void setCheckItemCode(String checkItemCode) {
            this.checkItemCode = checkItemCode;
        }

        public String getCheckItemName() {
            return checkItemName;
        }

        public void setCheckItemName(String checkItemName) {
            this.checkItemName = checkItemName;
        }

        public int getJudgeType() {
            return judgeType;
        }

        public void setJudgeType(int judgeType) {
            this.judgeType = judgeType;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public float getLimitLow() {
            return limitLow;
        }

        public void setLimitLow(float limitLow) {
            this.limitLow = limitLow;
        }

        public float getLimitHigh() {
            return limitHigh;
        }

        public void setLimitHigh(float limitHigh) {
            this.limitHigh = limitHigh;
        }

        public float getStardard() {
            return stardard;
        }

        public void setStardard(float stardard) {
            this.stardard = stardard;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<FaultDataBean> getFaultData() {
            return faultData;
        }

        public void setFaultData(List<FaultDataBean> faultData) {
            this.faultData = faultData;
        }

        public static class FaultDataBean {
            /**
             * faultId : 22
             * faultCode : F007
             * faultName : 表面凸起
             * faultDesc :
             * faultQty : 0
             * qC_DefectGrade : A
             */

            private int faultId;
            private String faultCode;
            private String faultName;
            private String faultDesc;
            private int faultQty;
            private String qC_DefectGrade;

            public int getFaultId() {
                return faultId;
            }

            public void setFaultId(int faultId) {
                this.faultId = faultId;
            }

            public String getFaultCode() {
                return faultCode;
            }

            public void setFaultCode(String faultCode) {
                this.faultCode = faultCode;
            }

            public String getFaultName() {
                return faultName;
            }

            public void setFaultName(String faultName) {
                this.faultName = faultName;
            }

            public String getFaultDesc() {
                return faultDesc;
            }

            public void setFaultDesc(String faultDesc) {
                this.faultDesc = faultDesc;
            }

            public int getFaultQty() {
                return faultQty;
            }

            public void setFaultQty(int faultQty) {
                this.faultQty = faultQty;
            }

            public String getQC_DefectGrade() {
                return qC_DefectGrade;
            }

            public void setQC_DefectGrade(String qC_DefectGrade) {
                this.qC_DefectGrade = qC_DefectGrade;
            }
        }
    }

    public static class CheckItemDataBean {
        /**
         * sampleSeq : 1
         * checkItemId : 44
         * qcValue : 0
         * qcResult : 0
         * faultId : 0
         * remark :
         */

        private int sampleSeq;
        private int checkItemId;
        private String qcValue;
        private int qcResult;
        private int faultId;
        private String remark;

        public int getSampleSeq() {
            return sampleSeq;
        }

        public void setSampleSeq(int sampleSeq) {
            this.sampleSeq = sampleSeq;
        }

        public int getCheckItemId() {
            return checkItemId;
        }

        public void setCheckItemId(int checkItemId) {
            this.checkItemId = checkItemId;
        }

        public String getQcValue() {
            return qcValue;
        }

        public void setQcValue(String qcValue) {
            this.qcValue = qcValue;
        }

        public int getQcResult() {
            return qcResult;
        }

        public void setQcResult(int qcResult) {
            this.qcResult = qcResult;
        }

        public int getFaultId() {
            return faultId;
        }

        public void setFaultId(int faultId) {
            this.faultId = faultId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }


    public static class FaultDataBeanX {
        /**
         * faultId : 2
         * faultCode : 002
         * faultName : 外观不良
         * faultDesc : 外观不良
         * faultQty : 2
         * qC_DefectGrade : B
         */

        private int faultId;
        private String faultCode;
        private String faultName;
        private String faultDesc;
        private int faultQty;
        private String qC_DefectGrade;

        public int getFaultId() {
            return faultId;
        }

        public void setFaultId(int faultId) {
            this.faultId = faultId;
        }

        public String getFaultCode() {
            return faultCode;
        }

        public void setFaultCode(String faultCode) {
            this.faultCode = faultCode;
        }

        public String getFaultName() {
            return faultName;
        }

        public void setFaultName(String faultName) {
            this.faultName = faultName;
        }

        public String getFaultDesc() {
            return faultDesc;
        }

        public void setFaultDesc(String faultDesc) {
            this.faultDesc = faultDesc;
        }

        public int getFaultQty() {
            return faultQty;
        }

        public void setFaultQty(int faultQty) {
            this.faultQty = faultQty;
        }

        public String getQC_DefectGrade() {
            return qC_DefectGrade;
        }

        public void setQC_DefectGrade(String qC_DefectGrade) {
            this.qC_DefectGrade = qC_DefectGrade;
        }
    }
}
