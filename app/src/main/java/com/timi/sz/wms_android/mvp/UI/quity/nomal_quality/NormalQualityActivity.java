package com.timi.sz.wms_android.mvp.UI.quity.nomal_quality;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 普通质检
 * author: timi
 * create at: 2017/9/6 17:22
 */
public class NormalQualityActivity extends BaseActivity<NormalQualityView, NormalQualityPresenter> implements NormalQualityView {


    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_orderno)
    TextView tvOrderno;
    @BindView(R.id.tv_receive_material_date)
    TextView tvReceiveMaterialDate;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_supplier)
    TextView tvSupplier;
    @BindView(R.id.tv_material_code)
    TextView tvMaterialCode;
    @BindView(R.id.tv_material_name)
    TextView tvMaterialName;
    @BindView(R.id.tv_material_model)
    TextView tvMaterialModel;
    @BindView(R.id.tv_receive_num)
    TextView tvReceiveNum;
    @BindView(R.id.et_spot_check_num)
    EditText etSpotCheckNum;
    @BindView(R.id.et_refuse_receive_num)
    EditText etRefuseReceiveNum;
    @BindView(R.id.et_badness_num)
    TextView tvBadnessNum;
    @BindView(R.id.et_badness_percent)
    TextView tvBadnessPercent;
    @BindView(R.id.rlv_quality)
    RecyclerView rlvQuality;
    @BindView(R.id.rd_qualified)
    RadioButton rdQualified;
    @BindView(R.id.rd_unqualified)
    RadioButton rdUnqualified;
    @BindView(R.id.rg_qualified)
    RadioGroup rgQualified;

    @Override
    public int setLayoutId() {
        return R.layout.activity_normal_quality;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.normal_quality_title));
        /**
         * 设置从质检清单获取到的数据，设置到当前界面
         */
        setTextViewText(tvOrderno, R.string.receive_pro_num, "0120030405");
        setTextViewText(tvReceiveMaterialDate, R.string.receive_material_date, "2016-06-01");
        setTextViewText(tvOrderNum, R.string.order_no, "CD20160001");
        setTextViewText(tvSupplier, R.string.buy_from, "深圳超然科技股份有限公司");
        setTextViewText(tvMaterialCode, R.string.material_code, "CD7000101");
        setTextViewText(tvMaterialName, R.string.material_name, "滑轨双孔梁496-蓝色");
        setTextViewText(tvMaterialModel, R.string.material_model, "Slide Beam0824-496铝挤压加工");
        setTextViewText(tvReceiveNum, R.string.receive_num, "30");
    }

    @Override
    public void initView() {
        /**
         * 默认选择合格
         */
        rdQualified.setChecked(true);

    }

    @Override
    public void initData() {

    }

    @Override
    public NormalQualityPresenter createPresenter() {
        return new NormalQualityPresenter(this);
    }

    @Override
    public NormalQualityView createView() {
        return this;
    }

    @OnClick(R.id.tv_next)
    public void onViewClicked() {
        /**
         * 抽检数
         */
        String spotCheckNum = etSpotCheckNum.getText().toString();
        if (TextUtils.isEmpty(spotCheckNum)) {
            ToastUtils.showShort(getString(R.string.please_input_spot_check_num));
            return;
        }
        /**
         * 拒收数
         */
        String refuseReceiveNum = etRefuseReceiveNum.getText().toString();
        /**
         * 不良总数
         */
        String badnessNum = tvBadnessNum.getText().toString();
        /**
         * 不良率
         */
        String badnessPercent = tvBadnessPercent.getText().toString();
        /**
         * 是否合格
         */
        boolean isQualified =rdQualified.isChecked();
        /**
         * 网络请求 进行下一步
         */
    }

}
