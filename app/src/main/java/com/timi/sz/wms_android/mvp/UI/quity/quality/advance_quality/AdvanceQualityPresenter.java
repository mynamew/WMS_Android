package com.timi.sz.wms_android.mvp.UI.quity.quality.advance_quality;

import android.content.Context;

import com.timi.sz.wms_android.base.uils.ToastUtils;
import com.timi.sz.wms_android.bean.quality.RequestUpdateCheckitemBean;
import com.timi.sz.wms_android.bean.quality.adavance.CommitAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.GetAdvance2Data;
import com.timi.sz.wms_android.bean.quality.adavance.IQCGetAdvanceData;
import com.timi.sz.wms_android.bean.quality.adavance.UpdateCheckItemResult;
import com.timi.sz.wms_android.bean.quality.normal.NormalQualityData;
import com.timi.sz.wms_android.http.callback.OnResultCallBack;
import com.timi.sz.wms_android.http.subscriber.HttpSubscriber;
import com.timi.sz.wms_android.mvp.base.presenter.impl.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * $dsc
 * author: timi
 * create at: 2017-10-12 14:54
 */

public class AdvanceQualityPresenter extends MvpBasePresenter<AdvanceQualityView> {
    private AdvanceQualityModel model;
    private HttpSubscriber<GetAdvance2Data> getAdvance2DataHttpSubscriber;
    private HttpSubscriber<Object> setAdvance2DataHttpSubscriber;
    private HttpSubscriber<Object> submitFinishHttpSubscriber;
    private HttpSubscriber<UpdateCheckItemResult> updateCheckItemDataHttpSubscriber;
    private HttpSubscriber<IQCGetAdvanceData> getCheckItemDataHttpSubscriber;

    public AdvanceQualityPresenter(Context context) {
        super(context);
        model = new AdvanceQualityModel();
    }

    /**
     * 获取高级质检2的数据
     *
     * @param params
     */
    public void getAdvance2Data(Map<String, Object> params) {
        getView().showProgressDialog();
        if (null == getAdvance2DataHttpSubscriber) {
            getAdvance2DataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<GetAdvance2Data>() {
                @Override
                public void onSuccess(GetAdvance2Data o) {
                    getView().getAdvance2Data(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
//        GetAdvance2Data getAdvance2Data = new GetAdvance2Data();
//        GetAdvance2Data.AdvanceSummaryBean advanceSummaryBean = new GetAdvance2Data.AdvanceSummaryBean();
//        advanceSummaryBean.setAqlAcceptQty(1);
//        advanceSummaryBean.setAqlRejectQty(2);
//        advanceSummaryBean.setBeginQty(7);
//        advanceSummaryBean.setCommonlyQty(11);
//        advanceSummaryBean.setCurrentLevel("Ⅲ");
//        advanceSummaryBean.setCurrentStrict("Normal");
//        advanceSummaryBean.setEndQty(12);
//        advanceSummaryBean.setFatalQty(0);
//        advanceSummaryBean.setSeriousQty(0);
//        advanceSummaryBean.setCommonlyQty(0);
//        advanceSummaryBean.setSlightQty(0);
//        advanceSummaryBean.setQcQty(4);
//        advanceSummaryBean.setQcType(3);
//        getAdvance2Data.setAdvanceSummary(advanceSummaryBean);
//        List<NormalQualityData.BarcodeDataBean> barDatas = new ArrayList<>();
//        NormalQualityData.BarcodeDataBean barcodeDataBean = new NormalQualityData.BarcodeDataBean();
//        barcodeDataBean.setPackQty(11);
//        barcodeDataBean.setCurrentQty(10);
//        barcodeDataBean.setRejectQty(10);
//        barcodeDataBean.setBarcodeNo("CT2356234234242");
//        barDatas.add(barcodeDataBean);
//        getAdvance2Data.setBarcodeData(barDatas);
//        List<GetAdvance2Data.CheckItemBean> checkItemBeanDatas = new ArrayList<>();
//        GetAdvance2Data.CheckItemBean checkItemBean = new GetAdvance2Data.CheckItemBean();
//        checkItemBean.setCheckItemName("长度");
//        checkItemBean.setCheckItemCode("P10");
//        checkItemBean.setCheckItemId(10);
//        List<GetAdvance2Data.CheckItemBean.FaultDataBean> mFaultDatas = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            GetAdvance2Data.CheckItemBean.FaultDataBean faultDataBeanX = new GetAdvance2Data.CheckItemBean.FaultDataBean();
//            faultDataBeanX.setFaultCode("F001");
//            faultDataBeanX.setFaultDesc("");
//            faultDataBeanX.setFaultId(7);
//            faultDataBeanX.setFaultName("过长");
//            faultDataBeanX.setFaultQty(0);
//            faultDataBeanX.setQC_DefectGrade("Z");
//            mFaultDatas.add(faultDataBeanX);
//        }
//        checkItemBean.setFaultData(mFaultDatas);
//        checkItemBean.setCheckItemCode("P10");
//        checkItemBeanDatas.add(checkItemBean);
//        getAdvance2Data.setCheckItem(checkItemBeanDatas);
//        GetAdvance2Data.NormalSummaryBean normalSummaryBean = new GetAdvance2Data.NormalSummaryBean();
//        normalSummaryBean.setBarcodeSource(0);
//        normalSummaryBean.setCreater("啊实打实");
//        normalSummaryBean.setIsBarCode(true);
//        normalSummaryBean.setMaterialAttribute("附加属性");
//        normalSummaryBean.setMaterialCode("物料code");
//        normalSummaryBean.setMaterialId(1393);
//        normalSummaryBean.setMaterialName("物料名称");
//        normalSummaryBean.setMaterialStandard("物料型号");
//        normalSummaryBean.setNgQty(0);
//        normalSummaryBean.setQcResult(0);
//        normalSummaryBean.setQcStatus(0);
//        normalSummaryBean.setReceiptCode("DH171208003");
//        normalSummaryBean.setReceiptDate("2017-09-08");
//        normalSummaryBean.setSampleQty(11);
//        normalSummaryBean.setSupplierName("阿斯顿撒");
//        normalSummaryBean.setReceiveQty(11);
//        normalSummaryBean.setSourceBillCode("CD16162361623");
//        getAdvance2Data.setNormalSummary(normalSummaryBean);
//        List<GetAdvance2Data.FaultDataBeanX> mFaultDatasX = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            GetAdvance2Data.FaultDataBeanX faultDataBeanX = new GetAdvance2Data.FaultDataBeanX();
//            faultDataBeanX.setFaultCode("F001");
//            faultDataBeanX.setFaultDesc("");
//            faultDataBeanX.setFaultId(7);
//            faultDataBeanX.setFaultName("过长");
//            faultDataBeanX.setFaultQty(0);
//            faultDataBeanX.setQC_DefectGrade("Z");
//            mFaultDatasX.add(faultDataBeanX);
//        }
//        getAdvance2Data.setFaultData(mFaultDatasX);
//        List<GetAdvance2Data.CheckItemDataBean> checkItemData = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            GetAdvance2Data.CheckItemDataBean checkItemDataBean = new GetAdvance2Data.CheckItemDataBean();
//            checkItemDataBean.setQcResult(i % 2 == 0 ? 0 : 1);
//            checkItemDataBean.setSampleSeq(i <= 5 ? 1 : 2);
//            checkItemDataBean.setCheckItemId(10);
//            checkItemDataBean.setFaultId(7);
//            checkItemDataBean.setRemark("");
//            checkItemDataBean.setQcValue("234.2");
//            checkItemData.add(checkItemDataBean);
//        }
//        getAdvance2Data.setCheckItemData(checkItemData);
//        getView().getAdvance2Data(getAdvance2Data);

                }
            });
        }
        model.getAdvance2Data(params, getAdvance2DataHttpSubscriber);
    }

    /**
     * 获取普通质检的数据
     *
     * @param data
     */
    public void setAdvance2Data(CommitAdvanceData data) {
        getView().showProgressDialog();
        if (null == setAdvance2DataHttpSubscriber) {
            setAdvance2DataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<Object>() {
                @Override
                public void onSuccess(Object o) {
                    getView().setAdvance2Data();
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.setAdvance2Data(data, setAdvance2DataHttpSubscriber);
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

    /**
     * 修改高检二质检项目
     *
     * @param params
     */
    public void updateCheckItemData(RequestUpdateCheckitemBean params) {
        getView().showProgressDialog();
        if (null == updateCheckItemDataHttpSubscriber) {
            updateCheckItemDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<UpdateCheckItemResult>() {
                @Override
                public void onSuccess(UpdateCheckItemResult o) {
                    getView().updateCheckItemData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        model.IQCUpdateAdvance2Item(params, updateCheckItemDataHttpSubscriber);
    }

    /**
     * 获取高检二质检项目
     *
     * @param params
     */
    public void getAdvanceCheckItemData(Map<String, Object> params ) {
        getView().showProgressDialog();
        if (null == getCheckItemDataHttpSubscriber) {
            getCheckItemDataHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<IQCGetAdvanceData>() {
                @Override
                public void onSuccess(IQCGetAdvanceData o) {
                    getView().getAdvanceCheckItemData(o);
                }

                @Override
                public void onError(String errorMsg) {
                    ToastUtils.showShort(errorMsg);
//                    IQCGetAdvanceData iqcGetAdvanceData = new IQCGetAdvanceData();
//                    iqcGetAdvanceData.setQcQty(2);
//                    List<GetAdvance2Data.CheckItemDataBean> mdatas = new ArrayList<>();
//                    for (int i = 0; i < 4; i++) {
//                        GetAdvance2Data.CheckItemDataBean checkItemDataBean = new GetAdvance2Data.CheckItemDataBean();
//                        checkItemDataBean.setCheckItemId(i);
//                        checkItemDataBean.setQcResult(0);
//                        checkItemDataBean.setFaultId(1);
//                        checkItemDataBean.setQcValue("234");
//                        checkItemDataBean.setSampleSeq(i);
//                        mdatas.add(checkItemDataBean);
//                    }
//                    iqcGetAdvanceData.setCheckItemData(mdatas);
//                    getView().getAdvanceCheckItemData(iqcGetAdvanceData,isFristload);
                }
            });
        }
        model.IQCGetAdvanceData(params, getCheckItemDataHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != getAdvance2DataHttpSubscriber) {
            getAdvance2DataHttpSubscriber.unSubscribe();
            getAdvance2DataHttpSubscriber = null;
        }
        if (null != submitFinishHttpSubscriber) {
            submitFinishHttpSubscriber.unSubscribe();
            submitFinishHttpSubscriber = null;
        }
        if (null != setAdvance2DataHttpSubscriber) {
            setAdvance2DataHttpSubscriber.unSubscribe();
            setAdvance2DataHttpSubscriber = null;
        }
    }
}
