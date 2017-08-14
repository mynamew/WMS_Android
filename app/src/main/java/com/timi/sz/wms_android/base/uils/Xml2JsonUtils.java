package com.timi.sz.wms_android.base.uils;

import com.google.gson.Gson;
import com.timi.sz.wms_android.bean.TestBean;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * 用于 webservice xml 返回 做处理
 */

public class Xml2JsonUtils<T> {
    /**
     * xml  转换成 json
     *
     * @param response
     * @param clazz
     * @return
     * @throws IOException
     */
    public T toJson(ResponseBody response, Class<T> clazz) throws IOException {
        T t = null;
        String oldData = null;
        oldData = response.string();
        int start = oldData.indexOf(">", 38);
        int end = oldData.indexOf("<", 41);
        String data = oldData.substring(start + 1, end);
        t = new Gson().fromJson(data, clazz);
        return t;
    }
}
