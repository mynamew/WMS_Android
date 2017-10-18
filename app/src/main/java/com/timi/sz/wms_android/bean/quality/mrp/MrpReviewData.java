package com.timi.sz.wms_android.bean.quality.mrp;

import com.google.gson.annotations.SerializedName;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-17 15:42
 */

public class MrpReviewData {

    private int orgId;
    private int QCId;
    private int receiptId;
    private String receiptCode;
    private String receiptDate;
    private int receiptDetailId;
    private int receiptLine;
    private int materialId;
    private String materialCode;
    private String materialName;
    private String materialStandard;
    private Object materialAttribute;
    private String supplierName;
    private int sourceBillType;
    private int sourceBillId;
    private String sourceBillCode;
    private int qcType;
    private int qcStatus;
    private int qcResult;
    private int receiveQty;
    private int sampleQty;
    private int passQty;
    private int ngQty;
    private int rejectQty;
    private String checker;
    private String checkDate;
    private String currentStrict;
    private String currentLevel;
    private String sampleCode;
    private String currentAQL;
    private int aqlAcceptQty;
    private int aqlRejectQty;
    private int fatalQty;
    private int seriousQty;
    private int commonlyQty;
    private int slightQty;

    public int getQCId() {
        return QCId;
    }

    public void setQCId(int QCId) {
        this.QCId = QCId;
    }
    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
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

    public int getReceiptDetailId() {
        return receiptDetailId;
    }

    public void setReceiptDetailId(int receiptDetailId) {
        this.receiptDetailId = receiptDetailId;
    }

    public int getReceiptLine() {
        return receiptLine;
    }

    public void setReceiptLine(int receiptLine) {
        this.receiptLine = receiptLine;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(int sourceBillType) {
        this.sourceBillType = sourceBillType;
    }

    public int getSourceBillId() {
        return sourceBillId;
    }

    public void setSourceBillId(int sourceBillId) {
        this.sourceBillId = sourceBillId;
    }

    public String getSourceBillCode() {
        return sourceBillCode;
    }

    public void setSourceBillCode(String sourceBillCode) {
        this.sourceBillCode = sourceBillCode;
    }

    public int getQcType() {
        return qcType;
    }

    public void setQcType(int qcType) {
        this.qcType = qcType;
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

    public int getPassQty() {
        return passQty;
    }

    public void setPassQty(int passQty) {
        this.passQty = passQty;
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

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
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
