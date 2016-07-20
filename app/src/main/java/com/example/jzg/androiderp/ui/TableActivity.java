package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.adapter.TableLeftAdapter;
import com.example.jzg.androiderp.adapter.TableRightAdapter;
import com.example.jzg.androiderp.utils.DataUtil;
import com.example.jzg.androiderp.view.MyListView;
import com.example.jzg.androiderp.view.MySyncHorizontalScrollView;
import com.example.jzg.androiderp.vo.AppraisersData;
import com.example.jzg.androiderp.vo.CityItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * author: guochen
 * date: 2016/6/22 10:46
 * email:
 */
public class TableActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rightTitleHorscrollView)
    MySyncHorizontalScrollView rightTitleHorscrollView;
    @BindView(R.id.contentListViewLeft)
    MyListView contentListViewLeft;//名称这一列的listView
    @BindView(R.id.contentListViewRight)
    MyListView contentListViewRight;  //名称这一列右边的数据listView
    @BindView(R.id.rightContentHorscrollView)
    MySyncHorizontalScrollView rightContentHorscrollView;
    @BindView(R.id.tv_item)
    TextView tvItem;
    private List<CityItem.DataBean> citys = new ArrayList<>();//城市
    private List<AppraisersData.DataBean> AppraisersDatas = new ArrayList<>();//表格
    private TableLeftAdapter tableLeftAdapter;
    private TableRightAdapter tableRightAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        citys.clear();
        AppraisersDatas.clear();
        CityItem cityData = DataUtil.getCityData(this);
        AppraisersData contentData = DataUtil.getContentData(this);
        if (cityData != null) {
            citys.addAll(cityData.getData());
        }
        if (contentData != null) {
            AppraisersDatas.addAll(contentData.getData());
        }
    }

    private void initView() {
        tvItem.setOnClickListener(this);
        //ScrollView关联ListView
        OverScrollDecoratorHelper.setUpOverScroll(rightTitleHorscrollView);
        OverScrollDecoratorHelper.setUpOverScroll(rightContentHorscrollView);
        //ListView关联ScrollView
        rightTitleHorscrollView.setmSyncView(rightContentHorscrollView);
        rightContentHorscrollView.setmSyncView(rightTitleHorscrollView);

        if (AppraisersDatas.size() > 0) {
            tableLeftAdapter = new TableLeftAdapter(this, AppraisersDatas);
            tableRightAdapter = new TableRightAdapter(this, AppraisersDatas);
            contentListViewLeft.setAdapter(tableLeftAdapter);
            contentListViewRight.setAdapter(tableRightAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        AppraisersData contentData = DataUtil.getContentDatas(this);
        if (contentData != null) {
            AppraisersDatas.clear();
            AppraisersDatas.addAll(contentData.getData());
            tableLeftAdapter.notifyDataSetChanged();
            tableRightAdapter.notifyDataSetChanged();
//            contentListViewLeft.setAdapter(tableLeftAdapter);
//            contentListViewRight.setAdapter(tableRightAdapter);
        }
    }
}
