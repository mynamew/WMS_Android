package com.timi.sz.wms_android.bean.quality.normal;

import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-26 09:47
 */

public class CommitNormalData {

    /**
     * UserId : 2
     * MAC : 00-50-56-C0-00-01
     * OrgId : 1
     * ReceiptId : 10356
     * ReceiptDetailId : 25131
     * SampleQty : 100
     * NGQty : 5
     * RejectQty : 5
     * QCStatus : 2
     * QCResult : 1
     * Remark : string
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
