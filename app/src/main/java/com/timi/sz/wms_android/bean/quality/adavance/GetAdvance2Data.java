package com.timi.sz.wms_android.bean.quality.adavance;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-18 16:52
 */

public class GetAdvance2Data {

    /**
     * normalSummary : {"receiptId":10224,"receiptDetailId":24805,"receiptCode":"DH161119004","receiptDate":"2017-09-11","sourceBillCode":"CG161116099","supplierName":"深圳市日科实业有限公司","creater":null,"materialId":333,"materialCode":"10101010334","materialName":"贴片电阻","materialStandard":"1R2/0805/±5%","materialAttribute":null,"receiveQty":5000,"sampleQty":200,"ngQty":0,"rejectQty":0,"qcStatus":0,"qcResult":0,"isBarCode":false,"barcodeSource":1}
     * advanceSummary : {"qcType":3,"currentStrict":"正常","currentLevel":"II","sampleCode":"L","currentAQL":"0.15","acceptAQL":0,"rejectAQL":2,"beginQty":3201,"endQty":10000,"qcQty":0,"fatalQty":0,"seriousQty":0,"commonlyQty":0,"slightQty":0}
     * checkItem : [{"checkItemId":3,"checkItemCode":"001","checkItemName":"宽度","judgeType":1,"unit":"厘米","limitLow":2.2,"limitHigh":2.5,"stardard":2.3,"remark":"手工填充","faultData":[{"faultId":28,"faultCode":"F013","faultName":"过宽","faultDesc":null,"faultQty":0},{"faultId":29,"faultCode":"F014","faultName":"过窄","faultDesc":null,"faultQty":0}]},{"checkItemId":2,"checkItemCode":"2","checkItemName":"温度","judgeType":1,"unit":"℃","limitLow":40.5,"limitHigh":40.8,"stardard":40.6,"remark":"手工填充","faultData":[{"faultId":30,"faultCode":"F015","faultName":"温度过高","faultDesc":null,"faultQty":0},{"faultId":31,"faultCode":"F016","faultName":"温度过低","faultDesc":null,"faultQty":0}]},{"checkItemId":1,"checkItemCode":"1","checkItemName":"外观","judgeType":2,"unit":" ","limitLow":0,"limitHigh":0,"stardard":0,"remark":"手工填充","faultData":[{"faultId":22,"faultCode":"F007","faultName":"表面凸起","faultDesc":null,"faultQty":0},{"faultId":23,"faultCode":"F008","faultName":"表面凹陷","faultDesc":null,"faultQty":0},{"faultId":24,"faultCode":"F009","faultName":"有裂缝","faultDesc":null,"faultQty":0},{"faultId":25,"faultCode":"F010","faultName":"破损","faultDesc":null,"faultQty":0},{"faultId":26,"faultCode":"F011","faultName":"沙眼","faultDesc":null,"faultQty":0}]}]
     * checkItemData : null
     * barcodeData : null
     * faultData : null
     */

    private NormalSummaryBean normalSummary;
    private AdvanceSummaryBean advanceSummary;
    private Object checkItemData;
    private Object barcodeData;
    private Object faultData;
    private List<CheckItemBean> checkItem;

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

    public Object getCheckItemData() {
        return checkItemData;
    }

    public void setCheckItemData(Object checkItemData) {
        this.checkItemData = checkItemData;
    }

    public Object getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(Object barcodeData) {
        this.barcodeData = barcodeData;
    }

    public Object getFaultData() {
        return faultData;
    }

    public void setFaultData(Object faultData) {
        this.faultData = faultData;
    }

    public List<CheckItemBean> getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(List<CheckItemBean> checkItem) {
        this.checkItem = checkItem;
    }

    public static class NormalSummaryBean {
        /**
         * receiptId : 10224
         * receiptDetailId : 24805
         * receiptCode : DH161119004
         * receiptDate : 2017-09-11
         * sourceBillCode : CG161116099
         * supplierName : 深圳市日科实业有限公司
         * creater : null
         * materialId : 333
         * materialCode : 10101010334
         * materialName : 贴片电阻
         * materialStandard : 1R2/0805/±5%
         * materialAttribute : null
         * receiveQty : 5000
         * sampleQty : 200
         * ngQty : 0
         * rejectQty : 0
         * qcStatus : 0
         * qcResult : 0
         * isBarCode : false
         * barcodeSource : 1
         */

        private int receiptId;
        private int receiptDetailId;
        private String receiptCode;
        private String receiptDate;
        private String sourceBillCode;
        private String supplierName;
        private Object creater;
        private int materialId;
        private String materialCode;
        private String materialName;
        private String materialStandard;
        private Object materialAttribute;
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

        public Object getCreater() {
            return creater;
        }

        public void setCreater(Object creater) {
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

        public Object getMaterialAttribute() {
            return materialAttribute;
        }

        public void setMaterialAttribute(Object materialAttribute) {
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
         * currentStrict : 正常
         * currentLevel : II
         * sampleCode : L
         * currentAQL : 0.15
         * acceptAQL : 0
         * rejectAQL : 2
         * beginQty : 3201
         * endQty : 10000
         * qcQty : 0
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
        private int acceptAQL;
        private int rejectAQL;
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

        public int getAcceptAQL() {
            return acceptAQL;
        }

        public void setAcceptAQL(int acceptAQL) {
            this.acceptAQL = acceptAQL;
        }

        public int getRejectAQL() {
            return rejectAQL;
        }

        public void setRejectAQL(int rejectAQL) {
            this.rejectAQL = rejectAQL;
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
         * checkItemId : 3
         * checkItemCode : 001
         * checkItemName : 宽度
         * judgeType : 1
         * unit : 厘米
         * limitLow : 2.2
         * limitHigh : 2.5
         * stardard : 2.3
         * remark : 手工填充
         * faultData : [{"faultId":28,"faultCode":"F013","faultName":"过宽","faultDesc":null,"faultQty":0},{"faultId":29,"faultCode":"F014","faultName":"过窄","faultDesc":null,"faultQty":0}]
         */

        private int checkItemId;
        private String checkItemCode;
        private String checkItemName;
        private int judgeType;
        private String unit;
        private double limitLow;
        private double limitHigh;
        private double stardard;
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

        public double getLimitLow() {
            return limitLow;
        }

        public void setLimitLow(double limitLow) {
            this.limitLow = limitLow;
        }

        public double getLimitHigh() {
            return limitHigh;
        }

        public void setLimitHigh(double limitHigh) {
            this.limitHigh = limitHigh;
        }

        public double getStardard() {
            return stardard;
        }

        public void setStardard(double stardard) {
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
             * faultId : 28
             * faultCode : F013
             * faultName : 过宽
             * faultDesc : null
             * faultQty : 0
             */

            private int faultId;
            private String faultCode;
            private String faultName;
            private Object faultDesc;
            private int faultQty;

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

            public Object getFaultDesc() {
                return faultDesc;
            }

            public void setFaultDesc(Object faultDesc) {
                this.faultDesc = faultDesc;
            }

            public int getFaultQty() {
                return faultQty;
            }

            public void setFaultQty(int faultQty) {
                this.faultQty = faultQty;
            }
        }
    }
}
