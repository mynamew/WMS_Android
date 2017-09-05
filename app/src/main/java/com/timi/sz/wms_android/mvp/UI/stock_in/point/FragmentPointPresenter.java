package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:18
 */

public class FragmentPointPresenter extends MvpBasePresenter<FragmentPointView> {
    FragmentPointModel model = null;
    private HttpSubscriber<BuyOrdernoBean> buyOrdernoBeanHttpSubscriber;
    private HttpSubscriber<SendOrdernoBean> sendOrdernoBeanHttpSubscriber;
    private HttpSubscriber<Integer> savePointMaterialHttpSubscriber;

    public FragmentPointPresenter(Context context) {
        super(context);
        model = new FragmentPointModel();
    }

    /**
     * 采购单的搜索返回
     *
     */
    public void buyOdernoQuery(final int orgId, final int userId, final String mac, final String billNo) {
        if (null == buyOrdernoBeanHttpSubscriber) {
            buyOrdernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyOrdernoBean>() {
                @Override
                public void onSuccess(BuyOrdernoBean buyOrdernoBean) {

                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.buyOrdernoQuery(orgId,userId,mac,billNo, buyOrdernoBeanHttpSubscriber);
    }

    /**
     * 送货单的搜索返回
     *
     * @param scanStr
     */
    public void sendOdernoQuery(String scanStr) {
        if (null == sendOrdernoBeanHttpSubscriber) {
            sendOrdernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<SendOrdernoBean>() {
                @Override
                public void onSuccess(SendOrdernoBean sendBean) {

                }

                @Override
                public void onError(String errorMsg) {
                    //请求失败 加入假数据
                    List<SendOrdernoBean.MarterialBean> datas = new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        datas.add(new SendOrdernoBean.MarterialBean(i + "", "M42324232" + i, "50", 50, 100, "10", "20","10", "20","20"));
                    }
                    SendOrdernoBean buyOrdernoBean = new SendOrdernoBean("B789678", "邢力丰", "深圳超然科技股份有限公司", "2017-8-29", datas);
                    getView().sendOrdernoQuery(buyOrdernoBean);
                }
            });
        }
        model.sendOrdernoQuery(scanStr, sendOrdernoBeanHttpSubscriber);
    }

    /**
     * 保存物料清点的方法
     */
    public void savePointMaterial(Map<String,Object> params){
         getView().showProgressDialog();
        if (null == savePointMaterialHttpSubscriber) {
            savePointMaterialHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Integer>() {
                @Override
                public void onSuccess(Integer result) {
                    getView().savePointMaterial(result);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.savePointMaterial(params, savePointMaterialHttpSubscriber);
    }
}
