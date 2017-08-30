package com.timi.sz.wms_android.http.api;

/**
 * 网络请求的通用返回
 * author: timi
 * create at: 2017-08-21 15:18
 */
public class CommonResult<T> {


    /**
     * result : null
     * targetUrl : null
     * success : false
     * error : {"code":0,"message":"登录失败","details":"用户未绑定组织,无法登录","validationErrors":null}
     * unAuthorizedRequest : false
     * __abp : true
     */

    private T result;
    private String targetUrl;
    private boolean success;
    private ErrorBean error;
    private boolean unAuthorizedRequest;
    private boolean __abp;

    @Override
    public String toString() {
        return "CommonResult{" +
                "result=" + result +
                ", targetUrl='" + targetUrl + '\'' +
                ", success=" + success +
                ", error=" + error +
                ", unAuthorizedRequest=" + unAuthorizedRequest +
                ", __abp=" + __abp +
                '}';
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public boolean isUnAuthorizedRequest() {
        return unAuthorizedRequest;
    }

    public void setUnAuthorizedRequest(boolean unAuthorizedRequest) {
        this.unAuthorizedRequest = unAuthorizedRequest;
    }

    public boolean is__abp() {
        return __abp;
    }

    public void set__abp(boolean __abp) {
        this.__abp = __abp;
    }

    public static class ErrorBean {
        /**
         * code : 0
         * message : 登录失败
         * details : 用户未绑定组织,无法登录
         * validationErrors : null
         */

        private int code;
        private String message;
        private String details;
        private Object validationErrors;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public Object getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(Object validationErrors) {
            this.validationErrors = validationErrors;
        }
    }
}
