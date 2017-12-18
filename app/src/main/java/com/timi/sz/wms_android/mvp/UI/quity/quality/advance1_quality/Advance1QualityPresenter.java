package com.timi.sz.wms_android.mvp.UI.quity.quality.advance1_quality;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvance1Data;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvanceData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-18 16:57
 */

public class Advance1QualityPresenter extends MvpBasePresenter<Advance1QualityView> {
    private Advance1QualityModel model;
    private HttpSubscriber<GetAdvanceData> getAdvanceDataHttpSubscriber;
    private HttpSubscriber<Object> setAdvance1QualityDataHttpSubscriber;
    private HttpSubscriber<Object> submitFinishHttpSubscriber;

    public Advance1QualityPresenter(Context context) {
        super(context);
        model = new Advance1QualityModel();
    }

    /**
     * 获取普通质检的数据
     *
     * @param params
     */
    public void getAdvance1Data(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getAdvanceDataHttpSubscriber) {
            getAdvanceDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetAdvanceData>() {
                @Override
                public void onSuccess(GetAdvanceData o) {
                    getView().getAdvance1Data(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                    /**
                     * 测试数据
                     */
                    GetAdvanceData getAdvanceData = new GetAdvanceData();
                    GetAdvanceData.NormalSummaryBean bean = new GetAdvanceData.NormalSummaryBean();
                    bean.setReceiptId(9695);
                    bean.setReceiptDetailId(23468);
                    bean.setReceiptCode("DH160921011");
                    bean.setReceiptDate("receiptDate");
                    bean.setSourceBillCode("sourceBillCode");
                    bean.setSupplierName("深圳市日科实业有限公司");
                    bean.setCreater("");
                    bean.setMaterialId(330);
                    bean.setMaterialCode("10101010331");
                    bean.setMaterialName("贴片电阻");
                    bean.setMaterialStandard("47K/0402/±5%");
                    bean.setMaterialAttribute("");
                    bean.setReceiveQty(10000);
                    bean.setSampleQty(80);
                    bean.setQcStatus(0);
                    bean.setQcResult(0);
                    bean.setIsBarCode(false);
                    bean.setBarcodeSource(1);
                    getAdvanceData.setNormalSummary(bean);
                    GetAdvanceData.AdvanceSummaryBean advance = new GetAdvanceData.AdvanceSummaryBean();
                    advance.setQcType(2);
                    advance.setCurrentStrict("正常");
                    advance.setCurrentLevel("Ⅰ");
                    advance.setSampleCode("J");
                    advance.setCurrentAQL("0.65");
                    advance.setAcceptAQL(0);
                    advance.setRejectAQL(2);
                    advance.setBeginQty(3201);
                    advance.setEndQty(10000);
                    advance.setQcQty(0);
                    advance.setFatalQty(0);
                    advance.setSeriousQty(0);
                    advance.setCommonlyQty(0);
                    advance.setSlightQty(0);
                    getAdvanceData.setAdvanceSummary(advance);

                    List<GetAdvanceData.FaultDataBean> datas = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        GetAdvanceData.FaultDataBean fault = new GetAdvanceData.FaultDataBean();
                        fault.setFaultId(i);
                        fault.setFaultCode("00" + i);
                        fault.setFaultName(i + "不良");
                        fault.setFaultQty(0);
                        fault.setqC_DefectGrade(i % 2 == 0 ? "A" : "C");
                        datas.add(fault);
                    }

                    getAdvanceData.setFaultData(datas);
                    getView().getAdvance1Data(getAdvanceData);
                }
            });
        }
        model.getAdvacen1Data(params, getAdvanceDataHttpSubscriber);
    }

    /**
     * 设置普通质检的数据
     *
     * @param params
     */
    public void setAdvance1Data(CommitAdvance1Data params) {
        getView().showProgressDialog();
        if (null == setAdvance1QualityDataHttpSubscriber) {
            setAdvance1QualityDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().setAdvance1Data();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.setAdvance1Data(params, setAdvance1QualityDataHttpSubscriber);
    }

    /**
     * 质检确认
     *
     * @param params
     */
    public void submitFinish(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == submitFinishHttpSubscriber) {
            submitFinishHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().submitFinish();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.submitFinish(params, submitFinishHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != getAdvanceDataHttpSubscriber) {
            getAdvanceDataHttpSubscriber.unSubscribe();
            getAdvanceDataHttpSubscriber = null;
        }
        if (null != setAdvance1QualityDataHttpSubscriber) {
            setAdvance1QualityDataHttpSubscriber.unSubscribe();
            setAdvance1QualityDataHttpSubscriber = null;
        }
        if (null != submitFinishHttpSubscriber) {
            submitFinishHttpSubscriber.unSubscribe();
            submitFinishHttpSubscriber = null;
        }
    }
}
