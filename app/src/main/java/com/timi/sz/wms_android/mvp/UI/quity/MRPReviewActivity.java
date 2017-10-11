package com.timi.sz.wms_android.mvp.UI.quity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.timi.sz.wms_android.R;
import com.timi.sz.wms_android.base.adapter.CommonSimpleHeaderAndFooterTypeAdapter;
import com.timi.sz.wms_android.base.adapter.CommonViewHolder;
import com.timi.sz.wms_android.view.excelview.FixedGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MRPReviewActivity extends AppCompatActivity {

    @BindView(R.id.rlv)
    RecyclerView rlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrpreview);
        ButterKnife.bind(this);
        List<String> datas=new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            datas.add("实打实打算"+i);
//        }
        rlv.setAdapter(new CommonSimpleHeaderAndFooterTypeAdapter<String>(datas) {

            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_quality;
            }

            @Override
            public void convert(CommonViewHolder holder, String s, int position) {
              holder.getTextView(R.id.tv_quality).setText(s);
            }

            @Override
            protected int getHeaderLayoutId() {
                return R.layout.header_quality;
            }
        });
        rlv.setLayoutManager(new FixedGridLayoutManager());
    }
}
