package com.timi.sz.wms_android.mvp.UI.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.HomeEvent;
import com.timi.sz.wms_android.mvp.UI.bluetooth.BluetoothActivity;
import com.timi.sz.wms_android.mvp.UI.query.QueryActivity;
import com.timi.sz.wms_android.mvp.UI.quity.QulityActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in.StockInActivity;
import com.timi.sz.wms_android.mvp.UI.stock_in_work.StockInWorkActivity;
import com.timi.sz.wms_android.mvp.UI.stock_out.StockOutActivity;
import com.timi.sz.wms_android.qrcode.CommonScanActivity;
import com.timi.sz.wms_android.qrcode.utils.Constant;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;

/**
 * 主页的碎片
 * author: timi
 * create at: 2017-08-17 11:30
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.tv_home_instock)
    TextView tvHomeInstock;
    @BindView(R.id.tv_home_outstock)
    TextView tvHomeOutstock;
    @BindView(R.id.tv_home_stock_in)
    TextView tvHomeStockIn;
    @BindView(R.id.tv_home_quilty)
    TextView tvHomeQuilty;
    @BindView(R.id.tv_home_query)
    TextView tvHomeQuery;
    Unbinder unbinder;
    @BindView(R.id.tv_today_instock_tip)
    TextView tvTodayInstockTip;
    @BindView(R.id.tv_today_outstock_tip)
    TextView tvTodayOutstockTip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        BaseMessage.register(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    /**
     * 接受语言改变的事件 更改文字
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessageLanguageUpdata(HomeEvent event) {
        tvTodayInstockTip.setText(getResources().getString(R.string.home_today_in));
        tvTodayOutstockTip.setText(getResources().getString(R.string.home_today_out));
        tvHomeInstock.setText(getResources().getString(R.string.home_in_lib));
        tvHomeOutstock.setText(getResources().getString(R.string.home_out_lib));
        tvHomeStockIn.setText(getResources().getString(R.string.home_lib_in_deal));
        tvHomeQuery.setText(getResources().getString(R.string.home_query));
        tvHomeQuilty.setText(getResources().getString(R.string.home_quilty));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_home_todayin_num, R.id.rl_today_in, R.id.tv_home_todayout_num, R.id.tv_home_instock, R.id.tv_home_outstock, R.id.tv_home_stock_in, R.id.tv_home_quilty, R.id.tv_home_query})
    public void onViewClicked(View view) {
        Intent it=new Intent();
        switch (view.getId()) {
            case R.id.tv_home_todayin_num:
                it.setClass(getActivity(), BluetoothActivity.class);
                break;
            case R.id.tv_home_todayout_num:
                it.setClass(getActivity(), BluetoothActivity.class);
                break;
            case R.id.tv_home_instock://入库作业
                it.setClass(getActivity(), StockInActivity.class);
                break;
            case R.id.tv_home_outstock:
                it.setClass(getActivity(), StockOutActivity.class);
                break;
            case R.id.tv_home_stock_in:
                it.setClass(getActivity(), StockInWorkActivity.class);
                break;
            case R.id.tv_home_quilty:
                it.setClass(getActivity(), QulityActivity.class);
                break;
            case R.id.tv_home_query:
                it.setClass(getActivity(), QueryActivity.class);
                break;
            case R.id.rl_today_in://今日入库
                it.setClass(getActivity(), BluetoothActivity.class);
                break;
            case R.id.rl_today_out://进入出库
                it.setClass(getActivity(), BluetoothActivity.class);
                break;
        }
        getActivity().startActivity(it);
    }

}
