package com.timi.sz.wms_android.base.uils;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * 系统键盘显示、隐藏工具类
 *
 * @author Administrator
 */
public class InputMethodUtils {
    /**
     * 显示系统软键盘
     *
     * @param view
     */
    public static void show(final View view) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask()

                       {

                           public void run()

                           {

                               InputMethodManager inputManager =

                                       (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                               inputManager.showSoftInput(view, 0);

                           }

                       },

                998);
    }

    /**
     * 隐藏系统软键盘
     *
     * @param context
     * @param view
     */
    public static void hide(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                view.getWindowToken(), 0);
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hide(Activity activity) {
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        /**
         * 软键盘是否隐藏
         */
        if (isSoftShowing(activity)) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 判断软键盘是否显示
     *
     * @param activity
     * @return
     */
    private static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }

    /**
     * 显示系统软键盘
     *
     * @param context
     */
    public void show(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 判定是否需要隐藏
     */

    private static boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private static void HideSoftInput(IBinder token, Context context) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hidSoftInput(MotionEvent ev, Activity context) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = context.getCurrentFocus();
            if (isHideInput(view, ev)) {
                InputMethodUtils.HideSoftInput(view.getWindowToken(), context);
            }
        }
    }

    public static void hideSoftInputAndEditView(MotionEvent ev, Activity context, View rootView) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = context.getCurrentFocus();
            if (isHideInput(view, ev)) {
                rootView.setVisibility(View.GONE);
                InputMethodUtils.HideSoftInput(view.getWindowToken(), context);
            }
        }
    }


    public static void setTextCanCopy(TextView view) {
// 		view.setFocusableInTouchMode(true);
// 		view.setFocusable(true);
// 		view.setClickable(true);
// 		view.setLongClickable(true);
// 		view.setMovementMethod( ArrowKeyMovementMethod.getInstance());
// 		view.setText(view.getText(),BufferType.SPANNABLE );
        view.setTextIsSelectable(true);
    }

    /**
     * 过滤掉特殊字符和汉字
     *
     * @author timi.
     * @date 2017/3/23
     * @time 10:35
     */
    public static String stringFilterChineseAndSymbol(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 过滤掉特殊字符
     *
     * @author timi.
     * @date 2017/3/23
     * @time 10:35
     */
    public static String stringFilterSymbol(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z0-9\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * 实现文本复制功能
     * add by wangqianzhou
     *
     * @param content
     */
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString();
    }
}
