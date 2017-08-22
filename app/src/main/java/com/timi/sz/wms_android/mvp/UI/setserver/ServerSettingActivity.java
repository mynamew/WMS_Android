package com.timi.sz.wms_android.mvp.UI.setserver;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.base.uils.Constants;
import com.timi.sz.wms_android.base.uils.LanguageUtils;
import com.timi.sz.wms_android.base.uils.PackageUtils;
import com.timi.sz.wms_android.base.uils.SpUtils;
import com.timi.sz.wms_android.mvp.UI.login.LoginActivity;
import com.timi.sz.wms_android.mvp.base.view.BaseNoMvpActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 服务设置的界面
 * author: timi
 * create at: 2017/8/22 13:11
 */
public class ServerSettingActivity extends BaseNoMvpActivity implements View.OnClickListener {
    @BindView(R.id.tv_ss_name)
    TextView tvSsSetversName;
    @BindView(R.id.tv_ss_language)
    TextView tvSsLanguage;
    @BindView(R.id.tv_ss_version)
    TextView tvSsVersion;
    @BindView(R.id.ll_ss_servers)
    LinearLayout llSsServers;
    @BindView(R.id.ll_language)
    LinearLayout llLanguage;

    @Override
    public int setLayoutId() {
        return R.layout.activity_server_setting;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
    }

    @Override
    public void initView() {
        //设置监听器
        llSsServers.setOnClickListener(this);
        llLanguage.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tvSsVersion.setText(String.format(getString(R.string.login_version), PackageUtils.getAppVersionName(this)));
    }

    /**
     * 显示下拉框 选择语言
     *
     * @param view
     */
    private PopupWindow mPop = null;

    public void showSelectLanguagePopwindow(View view) {
        //初始化
        if (null == mPop) {
            mPop = new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View content = LayoutInflater.from(this).inflate(R.layout.popwindow_select_language, null);
            content.findViewById(R.id.tv_language_simple).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSsLanguage.setText(getString(R.string.language_simple));
                    SpUtils.getInstance().putLocaleLanguage("zh-CN");
                    LanguageUtils.switchAppLanguage(ServerSettingActivity.this);
                    mPop.dismiss();
                }
            });
            content.findViewById(R.id.tv_language_trad).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSsLanguage.setText(getString(R.string.language_tradtional));
                    SpUtils.getInstance().putLocaleLanguage("zh-TW");
                    LanguageUtils.switchAppLanguage(ServerSettingActivity.this);
                    mPop.dismiss();
                }
            });
            content.findViewById(R.id.tv_language_en).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvSsLanguage.setText(getString(R.string.language_english));
                    SpUtils.getInstance().putLocaleLanguage("en");
                    LanguageUtils.switchAppLanguage(ServerSettingActivity.this);
                    mPop.dismiss();
                }
            });
            mPop.setContentView(content);
            mPop.setOutsideTouchable(false);
            mPop.setTouchable(true);
            mPop.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
            mPop.setAnimationStyle(R.style.popWindow_animation_top);
        }
        if (null != mPop) {
            mPop.showAsDropDown(view);
        }
    }

    /**
     * 显示下拉框 选择语言
     *
     * @param view
     */
    private PopupWindow mPopSelectServer = null;

    public void showSelectServerPopwindow(View view) {
        //初始化
        if (null == mPopSelectServer) {
            mPopSelectServer = new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View content = LayoutInflater.from(this).inflate(R.layout.popwindow_select_server, null);
            RecyclerView rlvContent = (RecyclerView) content.findViewById(R.id.rlv_select_server);
            final ArrayList<String> datas = new ArrayList<>();
            //循环遍历 所有的key 并存储起来
            Set<String> keys = Constants.SERVER_URLS.keySet();
            Iterator i = keys.iterator();//先迭代出来
            while (i.hasNext()) {//遍历
                datas.add((String) i.next());
            }
            //初始化 adapter
            CommonSimpleTypeAdapter<String> commonSimpleTypeAdapter = new CommonSimpleTypeAdapter<String>(datas) {
                @Override
                public int getLayoutId(int viewType) {
                    return R.layout.item_select_server;
                }

                @Override
                public void convert(CommonViewHolder holder, String o, int position) {
                    holder.getTextView(R.id.tv_select_server).setText(o);
                }
            };
            //设置adapter
            rlvContent.setAdapter(commonSimpleTypeAdapter);
            //设置布局管理器
            rlvContent.setLayoutManager(new LinearLayoutManager(this));
            //recycleview 点击事件
            commonSimpleTypeAdapter.setOnItemClickListener(R.id.tv_select_server, new CommonSimpleTypeAdapter.ItemClickListener() {
                @Override
                public void onItemClicked(View view, int position) {
                    //设置请求的baseurl  存储到sp中
                    SpUtils.getInstance().putString(Constants.SP_BASE_URL, Constants.SERVER_URLS.get(datas.get(position)));
                    //设置文字
                    tvSsSetversName.setText(datas.get(position));
                    //popwindow  小时
                    mPopSelectServer.dismiss();
                }
            });
            mPopSelectServer.setContentView(content);
            mPopSelectServer.setOutsideTouchable(false);
            mPopSelectServer.setTouchable(true);
            mPopSelectServer.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
            mPopSelectServer.setAnimationStyle(R.style.popWindow_animation_top);
        }
        if (null != mPopSelectServer) {
            mPopSelectServer.showAsDropDown(view);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_ss_servers://选择服务器
                //隐藏选择语言的下拉框
                if (null != mPop && mPop.isShowing()) {
                    mPop.dismiss();
                }
                //显示和隐藏选择服务器的下拉框
                if (null != mPopSelectServer && mPopSelectServer.isShowing()) {
                    mPopSelectServer.dismiss();
                } else {
                    showSelectServerPopwindow(view);
                }
                break;
            case R.id.ll_language://选择语言
                //隐藏选择服务器的下拉框
                if (null != mPopSelectServer && mPopSelectServer.isShowing()) {
                    mPopSelectServer.dismiss();
                }
                //显示和隐藏选择语言的下拉框
                if (null != mPop && mPop.isShowing()) {
                    mPop.dismiss();
                } else {
                    showSelectLanguagePopwindow(view);
                }
                break;
            case R.id.btn_ss__confirm://确认选择
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
