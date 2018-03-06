package com.timi.sz.wms_android.bean.query.response;

import java.util.List;

/**
 * $dsc SN追溯的返回结果
 * author: timi
 * create at: 2018-02-27 14:12
 */

public class QueryBarcodeTracesResult {


    /**
     * barCodeStatus : 1
     * parentBoxBarCode : null
     * barCodeType : 0
     * currentQty : 4
     * wareHouseName : 原材料仓
     * locationCode : 01X011202
     * materialCode : null
     * dateCode : 2017-12-02T00:00:00+08:00
     * materialName : 24V升压电源板后焊
     * barcodeTraacesDetail : [{"CreationTime":"2018-01-09 9:18:17","InOutType":2,"BillCode":"0000000153","SourceBillType":53,"TargetBillType":53,"LocationCode":"01X011104","Qty":960}]
     */

    private int barCodeStatus;
    private String parentBoxBarCode;
    private int barCodeType;
    private int currentQty;
    private String wareHouseName;
    private String locationCode;
    private String materialCode;
    private String dateCode;
    private String materialName;
    private String materialAttribute;
    private List<BarcodeTraacesDetailBean> barcodeTracesDetail;

    public int getBarCodeStatus() {
        return barCodeStatus;
    }

    public void setBarCodeStatus(int barCodeStatus) {
        this.barCodeStatus = barCodeStatus;
    }

    public String getParentBoxBarCode() {
        return parentBoxBarCode;
    }

    public void setParentBoxBarCode(String parentBoxBarCode) {
        this.parentBoxBarCode = parentBoxBarCode;
    }

    public int getBarCodeType() {
        return barCodeType;
    }

    public void setBarCodeType(int barCodeType) {
        this.barCodeType = barCodeType;
    }

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public List<BarcodeTraacesDetailBean> getBarcodeTraacesDetail() {
        return barcodeTracesDetail;
    }

    public void setBarcodeTraacesDetail(List<BarcodeTraacesDetailBean> barcodeTraacesDetail) {
        this.barcodeTracesDetail = barcodeTraacesDetail;
    }

    public static class BarcodeTraacesDetailBean {
        /**
         * CreationTime : 2018-01-09 9:18:17
         * InOutType : 2
         * BillCode : 0000000153
         * SourceBillType : 53
         * TargetBillType : 53
         * LocationCode : 01X011104
         * Qty : 960
         */

        private String CreationTime;
        private int InOutType;
        private String BillCode;
        private int SourceBillType;
        private int TargetBillType;
        private String LocationCode;
        private int Qty;

        public String getCreationTime() {
            return CreationTime;
        }

        public void setCreationTime(String CreationTime) {
            this.CreationTime = CreationTime;
        }

        public int getInOutType() {
            return InOutType;
        }

        public void setInOutType(int InOutType) {
            this.InOutType = InOutType;
        }

        public String getBillCode() {
            return BillCode;
        }

        public void setBillCode(String BillCode) {
            this.BillCode = BillCode;
        }

        public int getSourceBillType() {
            return SourceBillType;
        }

        public void setSourceBillType(int SourceBillType) {
            this.SourceBillType = SourceBillType;
        }

        public int getTargetBillType() {
            return TargetBillType;
        }

        public void setTargetBillType(int TargetBillType) {
            this.TargetBillType = TargetBillType;
        }

        public String getLocationCode() {
            return LocationCode;
        }

        public void setLocationCode(String LocationCode) {
            this.LocationCode = LocationCode;
        }

        public int getQty() {
            return Qty;
        }

        public void setQty(int Qty) {
            this.Qty = Qty;
        }
    }

    public String getMaterialAttribute() {
        return materialAttribute;
    }

    public void setMaterialAttribute(String materialAttribute) {
        this.materialAttribute = materialAttribute;
    }
}
