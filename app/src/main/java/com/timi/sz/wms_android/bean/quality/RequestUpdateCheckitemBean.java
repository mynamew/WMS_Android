package com.timi.sz.wms_android.bean.quality;

/**
 * $dsc
 * author: timi
 * create at: 2017-12-18 00:45
 */

public class RequestUpdateCheckitemBean {

    /**
     * UserId : 55
     * MAC : 00-50-56-C0-00-01
     * OrgId : 1
     * ReceiptId : 10403
     * ReceiptDetailId : 25197
     * QCQty : 1
     * ItemData : {"CheckItemId":10,"QCValue":"10.9","QCResult":0,"Remark":"长度测试"}
     */

    private int UserId;
    private String MAC;
    private int OrgId;
    private int ReceiptId;
    private int ReceiptDetailId;
    private int QCQty;
    private ItemDataBean ItemData;

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

    public int getQCQty() {
        return QCQty;
    }

    public void setQCQty(int QCQty) {
        this.QCQty = QCQty;
    }

    public ItemDataBean getItemData() {
        return ItemData;
    }

    public void setItemData(ItemDataBean ItemData) {
        this.ItemData = ItemData;
    }

    public static class ItemDataBean {
        /**
         * CheckItemId : 10
         * QCValue : 10.9
         * QCResult : 0
         * Remark : 长度测试
         */

        private int CheckItemId;
        private String QCValue;
        private int QCResult;
        private String Remark;

        public int getCheckItemId() {
            return CheckItemId;
        }

        public void setCheckItemId(int CheckItemId) {
            this.CheckItemId = CheckItemId;
        }

        public String getQCValue() {
            return QCValue;
        }

        public void setQCValue(String QCValue) {
            this.QCValue = QCValue;
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
    }
}
