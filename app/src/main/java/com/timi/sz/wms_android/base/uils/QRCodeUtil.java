package com.timi.sz.wms_android.base.uils;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.zxing.WriterException;
import com.timi.sz.wms_android.qrcode.zxing.encode.EncodingHandler;

import java.io.UnsupportedEncodingException;

/**
 * 二维码生成工具
 * author: timi
 * create at: 2017/8/18 9:02
 */

public class QRCodeUtil {

    /**
     * 生成二维码
     */
    public static Bitmap createQRCode(String content) {
        Bitmap qrCode = null;
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showShort("用户信息有误，扫码失败！");
        } else {
            try {
                qrCode = EncodingHandler.create2Code(content, 400);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return qrCode;
    }

    /**
     * 生成条形码
     */
    public static Bitmap createBarCode(String content) {
        Bitmap barCode = null;
        try {
            barCode = EncodingHandler.createBarCode(content, 600, 300);
        } catch (Exception e) {
            ToastUtils.showShort("条形码生成失败！");
            e.printStackTrace();
        }
        return barCode;
    }
}
