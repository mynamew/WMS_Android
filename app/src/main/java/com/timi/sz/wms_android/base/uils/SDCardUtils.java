package com.timi.sz.wms_android.base.uils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * author: timi
 * create at: 2017-08-24 09:39
 */
public class SDCardUtils {
    /**
     * 将文件写入本地
     *
     * @param responseBody 请求结果全体
     * @param destFileDir  目标文件夹
     * @param destFileName 目标文件名
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    public static File saveFile(ResponseBody responseBody, String destFileDir, String destFileName) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = responseBody.byteStream();
            final long total = responseBody.contentLength();
            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
//                //这里就是对进度的监听回调
                LogUitls.e("下载的进度-->"+finalSum * 100 / total+"%");
//                onProgress((int) (finalSum * 100 / total),total);
            }
            fos.flush();

            return file;

        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取SD卡的状态
     *
     * @return
     */
    public static String getState() {
        return Environment.getExternalStorageState();
    }

    /**
     * SD卡是否可用
     *
     * @return 只有当SD卡已经安装并且准备好了才返回true
     */
    public static boolean isAvailable() {
        return getState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡的根目录
     *
     * @return null：不存在SD卡
     */
    public static File getRootDirectory() {
        return isAvailable() ? Environment.getExternalStorageDirectory() : null;
    }

    /**
     * 获取SD卡的根路径
     *
     * @return null：不存在SD卡
     */
    public static String getRootPath() {
        File rootDirectory = getRootDirectory();
        return rootDirectory != null ? rootDirectory.getPath() : null;
    }

    /**
     * 获取SD卡总的容量
     *
     * @return 总容量；-1：SD卡不可用
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static long getTotalSize() {
        if (isAvailable()) {
            StatFs statFs = new StatFs(getRootDirectory().getPath());
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return statFs.getBlockCount() * statFs.getBlockSize();
            } else {
                return statFs.getBlockCount() * statFs.getBlockSize();
            }
        } else {
            return -1;
        }
    }

    /**
     * 获取SD卡中可用的容量
     *
     * @return 可用的容量；-1：SD卡不可用
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static long getAvailableSize() {
        if (isAvailable()) {
            StatFs statFs = new StatFs(getRootDirectory().getPath());
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return statFs.getAvailableBlocks() * statFs.getBlockSize();
            } else {
                return statFs.getAvailableBlocks() * statFs.getBlockSize();
            }
        } else {
            return -1;
        }
    }

    //公共目录 安装包
    public static String getAPKPath(Context context) {
        File vedioFile = new File(context.getExternalCacheDir(), "wms/apk");
        if (!vedioFile.exists()) {//如果文件夹不存在，创建文件夹
            vedioFile.mkdirs();
        }
        return vedioFile.getAbsolutePath();
    }
}
