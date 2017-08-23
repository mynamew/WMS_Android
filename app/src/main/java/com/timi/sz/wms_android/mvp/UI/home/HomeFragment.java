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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.LogUitls;
import com.timi.sz.wms_android.http.message.BaseMessage;
import com.timi.sz.wms_android.http.message.event.HomeEvent;
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

import static android.app.Activity.RESULT_OK;
import static com.timi.sz.wms_android.base.uils.Constants.REQUEST_CODE;

/**
 * 主页的碎片
 * author: timi
 * create at: 2017-08-17 11:30
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.iv_home_todayin)
    ImageView ivHomeTodayin;
    @BindView(R.id.tv_home_todayin)
    TextView tvHomeTodayin;
    @BindView(R.id.tv_home_todayin_num)
    TextView tvHomeTodayinNum;
    @BindView(R.id.tv_home_todayin_detail)
    TextView tvHomeTodayinDetail;
    @BindView(R.id.iv_home_todayout)
    ImageView ivHomeTodayout;
    @BindView(R.id.tv_home_todayout)
    TextView tvHomeTodayout;
    @BindView(R.id.tv_home_todayout_num)
    TextView tvHomeTodayoutNum;
    @BindView(R.id.tv_home_todayout_detail)
    TextView tvHomeTodayoutDetail;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ll_home_in_lib)
    LinearLayout llHomeInLib;
    @BindView(R.id.ll_home_out_lib)
    LinearLayout llHomeOutLib;
    @BindView(R.id.tv_home_query)
    TextView tvHomeQuery;
    @BindView(R.id.ll_home_quality)
    LinearLayout llHomeQuality;
    Unbinder unbinder;
    @BindView(R.id.tv_home_instock)
    TextView tvHomeInstock;
    @BindView(R.id.tv_home_outstock)
    TextView tvHomeOutstock;
    @BindView(R.id.tv_home_stock_in)
    TextView tvHomeStockIn;
    @BindView(R.id.tv_home_quilty)
    TextView tvHomeQuilty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        unbinder = ButterKnife.bind(this, view);
        BaseMessage.register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_home_scan, R.id.tv_home_todayin_detail, R.id.tv_home_todayout_detail, R.id.ll_home_in_lib, R.id.ll_home_out_lib, R.id.tv_home_stock_in, R.id.tv_home_query, R.id.ll_home_quality})
    public void onViewClicked(View view) {
        Intent it = new Intent();
        switch (view.getId()) {
            case R.id.tv_home_todayin_detail://今日入库详情
                break;
            case R.id.tv_home_todayout_detail://今日出库详情
                break;
            case R.id.ll_home_in_lib://入库作业
                it.setClass(getActivity(), StockInActivity.class);
                break;
            case R.id.ll_home_out_lib://出库作业
                it.setClass(getActivity(), StockOutActivity.class);
                break;
            case R.id.tv_home_stock_in://库内作业
                it.setClass(getActivity(), StockInWorkActivity.class);
                break;
            case R.id.tv_home_query://查询
                it.setClass(getActivity(), QueryActivity.class);
                break;
            case R.id.ll_home_quality://质量作业
                it.setClass(getActivity(), QulityActivity.class);
                break;
            case R.id.iv_home_scan://扫码
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 60);
                } else {
                    //权限已经被授予，在这里直接写要执行的相应方法即可
                    Intent intent = new Intent(getActivity(), CommonScanActivity.class);

                    String pointMsg = getResources().getString(R.string.scan_point_title);
                    Bundle bundle = new Bundle();
                    bundle.putString("pointMsg", pointMsg);
                    intent.putExtras(bundle);

                    intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_ALL_MODE);
                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
        }
        startActivity(it);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        LogUitls.d("扫码的结果--->", bundle.getString("result"));
                    }
                }
                break;
        }
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
        tvHomeTodayin.setText(getResources().getString(R.string.home_today_in));
        tvHomeTodayout.setText(getResources().getString(R.string.home_today_out));
        tvHomeTodayinDetail.setText(getResources().getString(R.string.home_detail));
        tvHomeTodayoutDetail.setText(getResources().getString(R.string.home_detail));
        tvHomeInstock.setText(getResources().getString(R.string.home_in_lib));
        tvHomeOutstock.setText(getResources().getString(R.string.home_out_lib));
        tvHomeQuery.setText(getResources().getString(R.string.home_query));
        tvHomeQuilty.setText(getResources().getString(R.string.home_quilty));
    }
}
