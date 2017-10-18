package com.timi.sz.wms_android.bean.quality.normal;

import java.util.List;

/**
 * $dsc  普通质检的数据
 * author: timi
 * create at: 2017-10-16 13:35
 */

public class NormalQualityData {

    /**
     * normalSummary : {"receiptId":10356,"receiptDetailId":25131,"receiptCode":"DH161201001","receiptDate":"2017-09-11","sourceBillCode":"CG161130225","supplierName":"乐清市柯创电子有限公司","creater":null,"materialId":1286,"materialCode":"10111020063","materialName":"2P针座","materialStandard":"ZH-1.5间距/2P/卧式贴片","materialAttribute":null,"receiveQty":4000,"sampleQty":20,"ngQty":0,"rejectQty":0,"qcStatus":1,"qcResult":1,"isBarCode":false,"barcodeSource":1}
     * faultData : [{"faultId":2,"faultCode":"002","faultName":"外观不良","faultDesc":"外观不良","faultQty":2,"qC_DefectGrade":"B"}]
     * barcodeData : null
     */

    private NormalSummaryBean normalSummary;
    private Object barcodeData;
    private List<FaultDataBean> faultData;

    public NormalSummaryBean getNormalSummary() {
        return normalSummary;
    }

    public void setNormalSummary(NormalSummaryBean normalSummary) {
        this.normalSummary = normalSummary;
    }

    public Object getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(Object barcodeData) {
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
         * receiptId : 10356
         * receiptDetailId : 25131
         * receiptCode : DH161201001
         * receiptDate : 2017-09-11
         * sourceBillCode : CG161130225
         * supplierName : 乐清市柯创电子有限公司
         * creater : null
         * materialId : 1286
         * materialCode : 10111020063
         * materialName : 2P针座
         * materialStandard : ZH-1.5间距/2P/卧式贴片
         * materialAttribute : null
         * receiveQty : 4000
         * sampleQty : 20
         * ngQty : 0
         * rejectQty : 0
         * qcStatus : 1
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

    public static class FaultDataBean {
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
