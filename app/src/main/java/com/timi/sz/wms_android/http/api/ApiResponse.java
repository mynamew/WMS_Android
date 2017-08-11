package com.timi.sz.wms_android.http.api;

/**
 * 统一返回实体
 *
 * @param <T>
 */
public class ApiResponse<T> {
    private int code;
    private String reason;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return reason;
    }

    public void setMsg(String msg) {
        this.reason = msg;
    }

    public T getDatas() {
        return result;
    }

    public void setDatas(T datas) {
        this.result = datas;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("code=" +code+" reason=" + reason);
        if (null != result) {
            sb.append(" result:" + result.toString());
        }
        return sb.toString();
    }
}