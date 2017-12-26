package com.timi.sz.wms_android.mvp.UI.quity.update_barcode;

import android.content.Context;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.BarcodeData;
import com.timi.sz.wms_android.bean.quality.update_barcode.BarEditGetUnInstockBarcodeData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-13 08:50
 */

public class UpdateBarcodePresenter extends MvpBasePresenter<UpdateBarcodeView> {
    private UpdateBarcodeModel model;
    private HttpSubscriber<BarEditGetUnInstockBarcodeData> barcodeDataHttpSubscriber;
    private HttpSubscriber<Object> setBarcodeDataHttpSubscriber;

    public UpdateBarcodePresenter(Context context) {
        super(context);
        model = new UpdateBarcodeModel();
    }

    /**
     * 通过条码获取质检条码的数据
     *
     * @param params
     * @param result
     */
    public void barEditGetUnInstockBarcodeData(Map<String, Object> params, final String result) {
        getView().showProgressDialog();
        if (null == barcodeDataHttpSubscriber) {
            barcodeDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<BarEditGetUnInstockBarcodeData>() {
                @Override
                public void onSuccess(BarEditGetUnInstockBarcodeData o) {
                    getView().barEditGetUnInstockBarcodeData(o, result);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(getContext().getString(R.string.scan_get_data_fail_repeat_input));
                }
            });
        }
        model.barEditGetUnInstockBarcodeData(params, barcodeDataHttpSubscriber);
    }

    /**
     * 提交未入库的条码的数据
     *
     * @param params
     * @param packQty
     */
    public void barEditSetUnInstockBarcodeData(Map<String, Object> params, final int packQty, final int rejectNum) {
        getView().showProgressDialog();
        if (null == setBarcodeDataHttpSubscriber) {
            setBarcodeDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().barEditSetUnInstockBarcodeData(packQty,rejectNum);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.barEditSetUnInstockBarcodeData(params, setBarcodeDataHttpSubscriber);
    }



    @Override
    public void dettachView() {
        super.dettachView();
        if (null != barcodeDataHttpSubscriber) {
            barcodeDataHttpSubscriber.unSubscribe();
            barcodeDataHttpSubscriber = null;
        }
        if (null != setBarcodeDataHttpSubscriber) {
            setBarcodeDataHttpSubscriber.unSubscribe();
            setBarcodeDataHttpSubscriber = null;
        }
    }
}
