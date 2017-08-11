package com.timi.sz.wms_android.mvp.base.presenter.impl;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.content.Context;

import com.timi.sz.wms_android.mvp.base.presenter.MvpPresenter;
import com.timi.sz.wms_android.mvp.base.view.MvpView;


/**
 * 抽象类 统一管理View层绑定和解除绑定
 * 
 * @author Dream
 *
 * @param <V>
 */
public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

	private WeakReference<Context> weakContext;
	private WeakReference<V> weakView;
	private V proxyView;

	public MvpBasePresenter(Context context) {
		this.weakContext = new WeakReference<Context>(context);
	}

	public Context getContext() {
		return weakContext.get();
	}

	public V getView() {
		return proxyView;
	}

	/**
	 * 用于检查View是否为空对象
	 * 
	 * @return
	 */
	public boolean isAttachView() {
		if (this.weakView != null && this.weakView.get() != null) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void attachView(V view) {
		this.weakView = new WeakReference<V>(view);
		MvpViewInvocationHandler invocationHandler = new MvpViewInvocationHandler(
				this.weakView.get());
		// 在这里采用动态代理
		proxyView = (V) Proxy.newProxyInstance(
				view.getClass().getClassLoader(), view.getClass()
						.getInterfaces(), invocationHandler);
	}

	@Override
	public void dettachView() {
		if (this.weakView != null) {
			this.weakView.clear();
			this.weakView = null;
		}
	}

	private class MvpViewInvocationHandler implements InvocationHandler {

		private MvpView mvpView;

		public MvpViewInvocationHandler(MvpView mvpView) {
			this.mvpView = mvpView;
		}

		@Override
		public Object invoke(Object arg0, Method arg1, Object[] arg2)
				throws Throwable {
			if (isAttachView()) {
				return arg1.invoke(mvpView, arg2);
			}
			return null;
		}

	}

}
