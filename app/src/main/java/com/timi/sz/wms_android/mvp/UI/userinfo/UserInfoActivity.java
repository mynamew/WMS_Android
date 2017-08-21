package com.timi.sz.wms_android.mvp.UI.userinfo;

import android.os.Bundle;
import android.widget.TextView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.bean.UserInfoBean;
import com.timi.sz.wms_android.mvp.base.BaseActivity;

import butterknife.BindView;

/**
 * 个人信息的显示界面
 * author: timi
 * create at: 2017/8/21 9:00
 */
public class UserInfoActivity extends BaseActivity<UserInfoView,UserInfroPresenter> implements UserInfoView{
    @BindView(R.id.tv_set_userinfo_num)
    TextView tvSetUserinfoNum;
    @BindView(R.id.tv_set_userinfo_name)
    TextView tvSetUserinfoName;
    @BindView(R.id.tv_set_userinfo_sex)
    TextView tvSetUserinfoSex;
    @BindView(R.id.tv_set_userinfo_tel)
    TextView tvSetUserinfoTel;
    @BindView(R.id.tv_set_userinfo_depart)
    TextView tvSetUserinfoDepart;
    @BindView(R.id.tv_set_userinfo_from)
    TextView tvSetUserinfoFrom;
    @BindView(R.id.tv_set_userinfo_root)
    TextView tvSetUserinfoRoot;

    @Override
    public int setLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("用户信息");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //获取用户信息
        getPresenter().getUserInfoFromSp();
    }

    @Override
    public UserInfroPresenter createPresenter() {
        return new UserInfroPresenter(this);
    }

    @Override
    public UserInfoView createView() {
        return this;
    }

    @Override
    public void setSpUserInfo(UserInfoBean bean) {
        //用户编号
        tvSetUserinfoNum.setText(String.format(getString(R.string.set_userinfo_num),bean.userNum));
        //用户编号
        tvSetUserinfoName.setText(String.format(getString(R.string.set_userinfo_name),bean.userName));
        //用户编号
        tvSetUserinfoSex.setText(String.format(getString(R.string.set_userinfo_sex),bean.userSex));
        //用户编号
        tvSetUserinfoDepart.setText(String.format(getString(R.string.set_userinfo_depart),bean.userDepart));
        //用户编号
        tvSetUserinfoTel.setText(String.format(getString(R.string.set_userinfo_tel),bean.userTel));
        //用户编号
        tvSetUserinfoFrom.setText(String.format(getString(R.string.set_userinfo_from),bean.userFrom));
        //用户编号
        tvSetUserinfoRoot.setText(String.format(getString(R.string.set_userinfo_root),bean.userRoot));
        //用户编号
        tvSetUserinfoNum.setText(String.format(getString(R.string.set_userinfo_num),bean.userNum));
    }
}
