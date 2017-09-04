package com.timi.sz.wms_android.bean;

/**
 * $dsc app版本
 * author: timi
 * create at: 2017-09-04 13:55
 */

public class VersionBean {

    @Override
    public String toString() {
        return "VersionBean{" +
                "ObjectReturn=" + ObjectReturn +
                ", Status=" + Status +
                ", Message='" + Message + '\'' +
                '}';
    }

    public VersionBean(ObjectReturnBean objectReturn, boolean status, String message) {
        ObjectReturn = objectReturn;
        Status = status;
        Message = message;
    }

    /**
     * ObjectReturn : {"Version":"2.0","Url":"http://localhost:8705/NewVersion/Guru_MES.apk"}
     * Status : true
     * Message :
     */

    private ObjectReturnBean ObjectReturn;
    private boolean Status;
    private String Message;

    public ObjectReturnBean getObjectReturn() {
        return ObjectReturn;
    }

    public void setObjectReturn(ObjectReturnBean ObjectReturn) {
        this.ObjectReturn = ObjectReturn;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public static class ObjectReturnBean {
        public ObjectReturnBean(String version, String url) {
            Version = version;
            Url = url;
        }

        /**
         * Version : 2.0
         * Url : http://localhost:8705/NewVersion/Guru_MES.apk
         */

        private String Version;
        private String Url;

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }
}
