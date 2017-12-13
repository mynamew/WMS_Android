package com.timi.sz.wms_android.base.uils;

import android.content.Context;
import android.support.annotation.IdRes;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.timi.sz.wms_android.R;

/**
 * $dsc radiobutton的工具类
 * author: timi
 * create at: 2017-12-12 15:17
 */

public class RadioButtonRestoreUtils {
    /**
     * 设置radiobutton
     * @param radioGroup
     * @param listener
     * @param mContext
     */
    public static void restoredRadioButton(RadioButton radioButton, RadioGroup radioGroup, RadioGroup.OnCheckedChangeListener listener, Context mContext) {
        radioGroup.setOnCheckedChangeListener(null);//checked监听事件失效
        //清除radioButton的选中状态，只保留背景色
        radioButton.setClickable(true);
        radioButton.setChecked(false);
        int childCount = radioGroup.getChildCount();
        RadioButton childAt = null;
        //将所有radioButton背景色置为未选中状态,目的是清除之前的设置
        for (int i = 0; i < childCount; i++) {
            childAt = (RadioButton) radioGroup.getChildAt(i);
            childAt.setBackgroundResource(R.drawable.bg_rectangle_login_unselected);
//            childAt.setTextColor(mContext.getResources().getColor(R.color.device_text_color_normal));
        }
        //将之前选中的radioButton背景色设为选中状态
        radioButton.setBackgroundResource(R.drawable.bg_rectangle_login_selected);
//        radioButton.setTextColor(mContext.getResources().getColor(R.color.device_text_color_focus));
        radioGroup.setOnCheckedChangeListener(listener);//重新添加
    }
}
