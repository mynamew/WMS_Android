package com.timi.sz.wms_android.bean.quality.adavance;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-27 09:15
 */

public class CommitAdvance1Data {

    /**
     * UserId : 2
     * MAC : 00-50-56-C0-00-01
     * OrgId : 1
     * ReceiptId : 9695
     * ReceiptDetailId : 23468
     * SampleQty : 80
     * NGQty : 5
     * RejectQty : 5
     * QCStatus : 3
     * QCResult : 3
     * Remark : string
     * CurrentStrict : 正常
     * CurrentLevel : I
     * SampleCode : J
     * BeginQty : 3201
     * EndQty : 10000
     * CurrentAQL : 0.65
     * AQLAcceptQty : 0
     * AQLRejectQty : 2
     * FatalQty : 1
     * SeriousQty : 2
     * CommonlyQty : 2
     * SlightQty : 0
     * faultData : [{"FaultId":2,"FaultQty":2},{"FaultId":3,"FaultQty":3}]
     */

    private int UserId;
    private String MAC;
    private int OrgId;
    private int ReceiptId;
    private int ReceiptDetailId;
    private int SampleQty;
    private int NGQty;
    private int RejectQty;
    private int QCStatus;
    private int QCResult;
    private String Remark;
    private String CurrentStrict;
    private String CurrentLevel;
    private String SampleCode;
    private int BeginQty;
    private int EndQty;
    private String CurrentAQL;
    private int AQLAcceptQty;
    private int AQLRejectQty;
    private int FatalQty;
    private int SeriousQty;
    private int CommonlyQty;
    private int SlightQty;
    private List<FaultDataBean> faultData;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public int getOrgId() {
        return OrgId;
    }

    public void setOrgId(int OrgId) {
        this.OrgId = OrgId;
    }

    public int getReceiptId() {
        return ReceiptId;
    }

    public void setReceiptId(int ReceiptId) {
        this.ReceiptId = ReceiptId;
    }

    public int getReceiptDetailId() {
        return ReceiptDetailId;
    }

    public void setReceiptDetailId(int ReceiptDetailId) {
        this.ReceiptDetailId = ReceiptDetailId;
    }

    public int getSampleQty() {
        return SampleQty;
    }

    public void setSampleQty(int SampleQty) {
        this.SampleQty = SampleQty;
    }

    public int getNGQty() {
        return NGQty;
    }

    public void setNGQty(int NGQty) {
        this.NGQty = NGQty;
    }

    public int getRejectQty() {
        return RejectQty;
    }

    public void setRejectQty(int RejectQty) {
        this.RejectQty = RejectQty;
    }

    public int getQCStatus() {
        return QCStatus;
    }

    public void setQCStatus(int QCStatus) {
        this.QCStatus = QCStatus;
    }

    public int getQCResult() {
        return QCResult;
    }

    public void setQCResult(int QCResult) {
        this.QCResult = QCResult;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getCurrentStrict() {
        return CurrentStrict;
    }

    public void setCurrentStrict(String CurrentStrict) {
        this.CurrentStrict = CurrentStrict;
    }

    public String getCurrentLevel() {
        return CurrentLevel;
    }

    public void setCurrentLevel(String CurrentLevel) {
        this.CurrentLevel = CurrentLevel;
    }

    public String getSampleCode() {
        return SampleCode;
    }

    public void setSampleCode(String SampleCode) {
        this.SampleCode = SampleCode;
    }

    public int getBeginQty() {
        return BeginQty;
    }

    public void setBeginQty(int BeginQty) {
        this.BeginQty = BeginQty;
    }

    public int getEndQty() {
        return EndQty;
    }

    public void setEndQty(int EndQty) {
        this.EndQty = EndQty;
    }

    public String getCurrentAQL() {
        return CurrentAQL;
    }

    public void setCurrentAQL(String CurrentAQL) {
        this.CurrentAQL = CurrentAQL;
    }

    public int getAQLAcceptQty() {
        return AQLAcceptQty;
    }

    public void setAQLAcceptQty(int AQLAcceptQty) {
        this.AQLAcceptQty = AQLAcceptQty;
    }

    public int getAQLRejectQty() {
        return AQLRejectQty;
    }

    public void setAQLRejectQty(int AQLRejectQty) {
        this.AQLRejectQty = AQLRejectQty;
    }

    public int getFatalQty() {
        return FatalQty;
    }

    public void setFatalQty(int FatalQty) {
        this.FatalQty = FatalQty;
    }

    public int getSeriousQty() {
        return SeriousQty;
    }

    public void setSeriousQty(int SeriousQty) {
        this.SeriousQty = SeriousQty;
    }

    public int getCommonlyQty() {
        return CommonlyQty;
    }

    public void setCommonlyQty(int CommonlyQty) {
        this.CommonlyQty = CommonlyQty;
    }

    public int getSlightQty() {
        return SlightQty;
    }

    public void setSlightQty(int SlightQty) {
        this.SlightQty = SlightQty;
    }

    public List<FaultDataBean> getFaultData() {
        return faultData;
    }

    public void setFaultData(List<FaultDataBean> faultData) {
        this.faultData = faultData;
    }

    public static class FaultDataBean {
        /**
         * FaultId : 2
         * FaultQty : 2
         */

        private int FaultId;
        private int FaultQty;

        public int getFaultId() {
            return FaultId;
        }

        public void setFaultId(int FaultId) {
            this.FaultId = FaultId;
        }

        public int getFaultQty() {
            return FaultQty;
        }

        public void setFaultQty(int FaultQty) {
            this.FaultQty = FaultQty;
        }
    }
}
