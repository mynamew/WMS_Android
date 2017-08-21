package com.timi.sz.wms_android.http.api;

import java.util.List;

/**
 * 网络请求的通用返回
 * author: timi
 * create at: 2017-08-21 15:18
 */
public class CommonResult<T> {
    /**
     * 返回的泛型
     */
    private T result;
    /**
     * 用于重定向到指定的URL，暂不使用
     */
    private String targetUrl;
    /**
     * 调用API结果信息，ture表示执行成功,false表示执行失败
     */
    private boolean success;
    /**
     * 返回token验证信息，如果返回false，跳转到登录页面
     */
    private boolean unAuthorizedRequest;
    /**
     * 固定为true
     */
    private boolean __abp;//
    /**
     * error  信息
     */
    private Error error;

    /**
     * 错误信息
     */
    class Error {
        /**
         * 错误的code
         */
        private int code;
        /**
         * 错误的message
         */
        private String message;
        /**
         * 错误的detail
         */
        private String detail;
        /**
         * 输入的错误返回
         */
        private List<InputErrorResult> validationErrors;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<InputErrorResult> getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(List<InputErrorResult> validationErrors) {
            this.validationErrors = validationErrors;
        }
    }

    public boolean is__abp() {
        return __abp;
    }

    public void set__abp(boolean __abp) {
        this.__abp = __abp;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public boolean isUnAuthorizedRequest() {
        return unAuthorizedRequest;
    }

    public void setUnAuthorizedRequest(boolean unAuthorizedRequest) {
        this.unAuthorizedRequest = unAuthorizedRequest;
    }

    /**
     * 输入的错误返回
     */
    class InputErrorResult {
        private String message;
        private String[] member;

        public String[] getMember() {
            return member;
        }

        public void setMember(String[] member) {
            this.member = member;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
