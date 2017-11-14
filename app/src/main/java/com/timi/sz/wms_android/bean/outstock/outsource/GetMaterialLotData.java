package com.timi.sz.wms_android.bean.outstock.outsource;

import java.util.List;

/**
 * $dsc 获得物料批次拣货信息
 * author: timi
 * create at: 2017-11-10 14:50
 */

public class GetMaterialLotData {

    /**
     * controlType : 0
     * fifoIntervalDays : 0
     * lotDetail : [{"locationCode":"011021202","dateCode":"2017-08-01","period":"2017-09-15","lotUseQty":146,"lotScanQty":0},{"locationCode":"011021202","dateCode":"2017-08-01","period":"2017-09-20","lotUseQty":14,"lotScanQty":0},{"locationCode":"011021202","dateCode":"2017-08-01","period":"2018-09-01","lotUseQty":455,"lotScanQty":0},{"locationCode":"011021202","dateCode":"2017-08-12","period":"2017-09-20","lotUseQty":3,"lotScanQty":0}]
     */

    private int controlType;
    private int fifoIntervalDays;
    private List<LotDetailBean> lotDetail;

    public int getControlType() {
        return controlType;
    }

    public void setControlType(int controlType) {
        this.controlType = controlType;
    }

    public int getFifoIntervalDays() {
        return fifoIntervalDays;
    }

    public void setFifoIntervalDays(int fifoIntervalDays) {
        this.fifoIntervalDays = fifoIntervalDays;
    }

    public List<LotDetailBean> getLotDetail() {
        return lotDetail;
    }

    public void setLotDetail(List<LotDetailBean> lotDetail) {
        this.lotDetail = lotDetail;
    }

    public static class LotDetailBean {
        /**
         * locationCode : 011021202
         * dateCode : 2017-08-01
         * period : 2017-09-15
         * lotUseQty : 146
         * lotScanQty : 0
         */

        private String locationCode;
        private String dateCode;
        private String period;
        private int lotUseQty;
        private int lotScanQty;

        public String getLocationCode() {
            return locationCode;
        }

        public void setLocationCode(String locationCode) {
            this.locationCode = locationCode;
        }

        public String getDateCode() {
            return dateCode;
        }

        public void setDateCode(String dateCode) {
            this.dateCode = dateCode;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public int getLotUseQty() {
            return lotUseQty;
        }

        public void setLotUseQty(int lotUseQty) {
            this.lotUseQty = lotUseQty;
        }

        public int getLotScanQty() {
            return lotScanQty;
        }

        public void setLotScanQty(int lotScanQty) {
            this.lotScanQty = lotScanQty;
        }
    }
}
