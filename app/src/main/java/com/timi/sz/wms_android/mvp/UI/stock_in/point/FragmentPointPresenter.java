package com.timi.sz.wms_android.mvp.UI.stock_in.point;

import android.content.Context;

import com.timi.sz.wms_android.bean.instock.search.BuyOrdernoBean;
import com.timi.sz.wms_android.bean.instock.PointMaterialBean;
import com.timi.sz.wms_android.bean.instock.search.SendOrdernoBean;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:18
 */

public class FragmentPointPresenter extends MvpBasePresenter<FragmentPointView> {
    FragmentPointModel model = null;
    private HttpSubscriber<BuyOrdernoBean> buyOrdernoBeanHttpSubscriber;
    private HttpSubscriber<SendOrdernoBean> sendOrdernoBeanHttpSubscriber;
    private HttpSubscriber<PointMaterialBean> savePointMaterialHttpSubscriber;

    public FragmentPointPresenter(Context context) {
        super(context);
        model = new FragmentPointModel();
    }

    /**
     * 采购单的搜索返回
     *
     * @param scanStr
     */
    public void buyOdernoQuery(String scanStr) {
        if (null == buyOrdernoBeanHttpSubscriber) {
            buyOrdernoBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BuyOrdernoBean>() {
                @Override
                public void onSuccess(BuyOrdernoBean buyOrdernoBean) {

                }

                @Override
                public void onError(String errorMsg) {
                    //请求失败 加入假数据
                    List<BuyOrdernoBean.MarterialBean> datas = new ArrayList<>();
                    for (int i = 0; i < 20; i++) {
                        datas.add(new BuyOrdernoBean.MarterialBean(i + "", "M42324232" + i, "50", "50", "100", "10", "20"));
                    }
                    BuyOrdernoBean buyOrdernoBean = new BuyOrdernoBean("B789678", "邢力丰", "深圳超然科技股份有限公司", "2017-8-29", datas);
                    getView().buyOrdernoQuery(buyOrdernoBean);
                }
            });
        }
        model.buyOrdernoQuery(scanStr, buyOrdernoBeanHttpSubscriber);
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
     * @param orderno
     * @param pointNum
     * @param spareNum
     */
    public void savePointMaterial(final String orderno, final int pointNum, final int spareNum){
        if (null == savePointMaterialHttpSubscriber) {
            savePointMaterialHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<PointMaterialBean>() {
                @Override
                public void onSuccess(PointMaterialBean pointMaterialBean) {
                    getView().savePointMaterial(pointMaterialBean);
                }

                @Override
                public void onError(String errorMsg) {
                    getView().savePointMaterial(new PointMaterialBean(true));
                }
            });
        }
        model.savePointMaterial(orderno, pointNum, spareNum, savePointMaterialHttpSubscriber);
    }
}
