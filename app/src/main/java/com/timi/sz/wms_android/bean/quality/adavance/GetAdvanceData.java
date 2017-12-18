package com.timi.sz.wms_android.bean.quality.adavance;

import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;

import java.util.List;

/**
 * $dsc 高级质检数据
 * author: timi
 * create at: 2017-10-17 13:27
 */

public class GetAdvanceData {

    /**
     * normalSummary : {"receiptId":9695,"receiptDetailId":23468,"receiptCode":"DH160921011","receiptDate":"2017-09-11","sourceBillCode":"CG160918120","supplierName":"深圳市日科实业有限公司","creater":null,"materialId":330,"materialCode":"10101010331","materialName":"贴片电阻","materialStandard":"47K/0402/±5%","materialAttribute":null,"receiveQty":10000,"sampleQty":80,"ngQty":0,"rejectQty":0,"qcStatus":0,"qcResult":0,"isBarCode":false,"barcodeSource":1}
     * advanceSummary : {"qcType":2,"currentStrict":"正常","currentLevel":"I","sampleCode":"J","currentAQL":"0.65","acceptAQL":0,"rejectAQL":2,"beginQty":3201,"endQty":10000,"qcQty":0,"fatalQty":0,"seriousQty":0,"commonlyQty":0,"slightQty":0}
     * checkItem : null
     * checkItemData : null
     * barcodeData : null
     * faultData : [{"faultId":1,"faultCode":"001","faultName":"性能不良","faultDesc":"指产品表面擦伤刮伤","faultQty":0},{"faultId":2,"faultCode":"002","faultName":"外观不良","faultDesc":"外观不良","faultQty":0},{"faultId":3,"faultCode":"003","faultName":"尺寸不良","faultDesc":"尺寸不良","faultQty":0}]
     */

    private NormalSummaryBean normalSummary;
    private AdvanceSummaryBean advanceSummary;
    private Object checkItem;
    private Object checkItemData;
    private List<NormalQualityData.BarcodeDataBean> barcodeData;
    private List<FaultDataBean> faultData;

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

    public Object getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(Object checkItem) {
        this.checkItem = checkItem;
    }

    public Object getCheckItemData() {
        return checkItemData;
    }

    public void setCheckItemData(Object checkItemData) {
        this.checkItemData = checkItemData;
    }

    public List<NormalQualityData.BarcodeDataBean> getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(List<NormalQualityData.BarcodeDataBean> barcodeData) {
        this.barcodeData = barcodeData;
    }

    public List<FaultDataBean> getFaultData() {
        return faultData;
    }

    public void setFaultData(List<FaultDataBean> faultData) {
        this.faultData = faultData;
    }

    public static class NormalSummaryBean {
        /**
         * receiptId : 9695
         * receiptDetailId : 23468
         * receiptCode : DH160921011
         * receiptDate : 2017-09-11
         * sourceBillCode : CG160918120
         * supplierName : 深圳市日科实业有限公司
         * creater : null
         * materialId : 330
         * materialCode : 10101010331
         * materialName : 贴片电阻
         * materialStandard : 47K/0402/±5%
         * materialAttribute : null
         * receiveQty : 10000
         * sampleQty : 80
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
         * qcType : 2
         * currentStrict : 正常
         * currentLevel : I
         * sampleCode : J
         * currentAQL : 0.65
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

        public int getAcceptAQL() {
            return aqlAcceptQty;
        }

        public void setAcceptAQL(int acceptAQL) {
            this.aqlAcceptQty = acceptAQL;
        }

        public int getRejectAQL() {
            return aqlRejectQty;
        }

        public void setRejectAQL(int rejectAQL) {
            this.aqlRejectQty = rejectAQL;
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

    public static class FaultDataBean {
        /**
         * faultId : 1
         * faultCode : 001
         * faultName : 性能不良
         * faultDesc : 指产品表面擦伤刮伤
         * faultQty : 0
         */

        private int faultId;
        private String faultCode;
        private String faultName;
        private String faultDesc;
        private int faultQty;

        public String getqC_DefectGrade() {
            return qC_DefectGrade;
        }

        public void setqC_DefectGrade(String qC_DefectGrade) {
            this.qC_DefectGrade = qC_DefectGrade;
        }

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
    }
}
