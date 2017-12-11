package com.timi.sz.wms_android.bean.quality.normal;

import java.util.List;

/**
 * $dsc  普通质检的数据
 * author: timi
 * create at: 2017-10-16 13:35
 */

public class NormalQualityData {

    /**
     * normalSummary : {"receiptId":10395,"receiptDetailId":25188,"receiptCode":"DH171207008","receiptDate":"2017-12-07","sourceBillCode":"CG161204008","supplierName":"深圳市慧视电子有限公司","creater":null,"materialId":2,"materialCode":"10101010002","materialName":"贴片电阻","materialStandard":"10R/0402/±5%","materialAttribute":"","receiveQty":16,"sampleQty":10,"ngQty":1,"rejectQty":1,"qcStatus":2,"qcResult":1,"isBarCode":true,"barcodeSource":1}
     * faultData : [{"faultId":3,"faultCode":"P001","faultName":"外观不良","faultDesc":"","faultQty":1,"qC_DefectGrade":"B"},{"faultId":4,"faultCode":"P002","faultName":"性能不良","faultDesc":"","faultQty":0,"qC_DefectGrade":"B"},{"faultId":5,"faultCode":"P003","faultName":"功能不良","faultDesc":"","faultQty":0,"qC_DefectGrade":"B"},{"faultId":6,"faultCode":"P004","faultName":"尺寸不良","faultDesc":"","faultQty":0,"qC_DefectGrade":"B"}]
     * barcodeData : [{"barcodeNo":"CT2017121100037","packQty":2,"currentQty":1,"rejectQty":1}]
     */

    private NormalSummaryBean normalSummary;
    private List<FaultDataBean> faultData;
    private List<BarcodeDataBean> barcodeData;

    public NormalSummaryBean getNormalSummary() {
        return normalSummary;
    }

    public void setNormalSummary(NormalSummaryBean normalSummary) {
        this.normalSummary = normalSummary;
    }

    public List<FaultDataBean> getFaultData() {
        return faultData;
    }

    public void setFaultData(List<FaultDataBean> faultData) {
        this.faultData = faultData;
    }

    public List<BarcodeDataBean> getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(List<BarcodeDataBean> barcodeData) {
        this.barcodeData = barcodeData;
    }

    public static class NormalSummaryBean {
        /**
         * receiptId : 10395
         * receiptDetailId : 25188
         * receiptCode : DH171207008
         * receiptDate : 2017-12-07
         * sourceBillCode : CG161204008
         * supplierName : 深圳市慧视电子有限公司
         * creater : null
         * materialId : 2
         * materialCode : 10101010002
         * materialName : 贴片电阻
         * materialStandard : 10R/0402/±5%
         * materialAttribute :
         * receiveQty : 16
         * sampleQty : 10
         * ngQty : 1
         * rejectQty : 1
         * qcStatus : 2
         * qcResult : 1
         * isBarCode : true
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

    public static class FaultDataBean {
        /**
         * faultId : 3
         * faultCode : P001
         * faultName : 外观不良
         * faultDesc :
         * faultQty : 1
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

    public static class BarcodeDataBean {
        /**
         * barcodeNo : CT2017121100037
         * packQty : 2
         * currentQty : 1
         * rejectQty : 1
         */

        private String barcodeNo;
        private int packQty;
        private int currentQty;
        private int rejectQty;

        public String getBarcodeNo() {
            return barcodeNo;
        }

        public void setBarcodeNo(String barcodeNo) {
            this.barcodeNo = barcodeNo;
        }

        public int getPackQty() {
            return packQty;
        }

        public void setPackQty(int packQty) {
            this.packQty = packQty;
        }

        public int getCurrentQty() {
            return currentQty;
        }

        public void setCurrentQty(int currentQty) {
            this.currentQty = currentQty;
        }

        public int getRejectQty() {
            return rejectQty;
        }

        public void setRejectQty(int rejectQty) {
            this.rejectQty = rejectQty;
        }
    }
}
