package com.timi.sz.wms_android.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;
/** 
  * 自定义的popwindow
  * author: timi    
  * create at: 2017/8/18 9:05
  */  
public class MyPopWindow
{
	
	public interface OnActionListener
	{
		void onShow(PopupWindow pop, long uid, View content);
		void onDismiss(PopupWindow pop, long uid, View content);
	}
	
	
	private static Thread _td;
	
	@SuppressLint({"InflateParams", "WrongConstant"})
	static public PopupWindow popup(final Activity ct, ViewGroup parent, int resid, final long uid, final OnActionListener lis)
	{
		final View sub = LayoutInflater.from(ct).inflate(resid, parent, false);
		final PopupWindow pop = new PopupWindow(sub, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
		pop.setAnimationStyle(android.R.style.Animation_InputMethod);
		pop.setFocusable(true);
		pop.setTouchable(true);
		pop.setOutsideTouchable(true);
		pop.update();
		pop.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		pop.setSoftInputMode(LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		
		pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				setWindowAlpha(ct, 1.0f);
				if(lis!=null) lis.onDismiss(pop, uid, sub);
			}
		});
		
		pop.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		
		setWindowAlpha(ct, 0.6f);
		if(lis!=null) lis.onShow(pop, uid, sub);
		
		return pop;
	}
	
	static private void setWindowAlpha(final Activity ct, final float alpha)
	{
		if(_td!=null && _td.isAlive()) {
			_td.interrupt();
			_td = null;
		}
		_td = new Thread(new Runnable() {
			@Override
			public void run() {
				final LayoutParams lp = ct.getWindow().getAttributes();
				if(alpha==1){
					while(lp.alpha<alpha){
						try {
							Thread.sleep(10);
							ct.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									float nt = lp.alpha+0.01f;
									if(nt>alpha) nt=alpha;
									lp.alpha = nt;
									ct.getWindow().setAttributes(lp);
								}
							});
						} catch (InterruptedException e) {
							ct.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									lp.alpha = alpha;
									ct.getWindow().setAttributes(lp);
								}
							});
						}
					}
				}else{
					while(lp.alpha>alpha){
						try {
							Thread.sleep(10);
							ct.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									float nt = lp.alpha-0.01f;
									if(nt<alpha) nt=alpha;
									lp.alpha = nt;
									ct.getWindow().setAttributes(lp);
								}
							});
						} catch (InterruptedException e) {
							ct.runOnUiThread(new Runnable() {
								@Override
								public void run() {
									lp.alpha = alpha;
									ct.getWindow().setAttributes(lp);
								}
							});
						}
					}
				}
			}
		});
		_td.start();
	}
}
