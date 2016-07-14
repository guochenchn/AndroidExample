package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.HorizontalScrollView;
import android.widget.ListView;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.table.CHScrollView2;
import com.example.jzg.androiderp.table.ScrollAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: guochen
 * date: 2016/6/22 10:58
 * email: 
 */
public class HListViewActivity extends BaseActivity{
    public HorizontalScrollView mTouchView;
    //装入所有的HScrollView
    protected List<CHScrollView2> mHScrollViews = new ArrayList<CHScrollView2>();
    //定义map集合的key值
    private String[] cols = new String[]{"标题", "列1", "列2", "列3", "列4", "列5",
            "列6", "列7", "列8", "列9"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hlistactivity);
        initView();
    }

    private void initView() {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();//存储所有的数据
        Map<String, String> data = null;//存储每一行的数据
        CHScrollView2 headerScroll = (CHScrollView2) findViewById(R.id.item_scroll_title);
        //添加头滑动事件
        mHScrollViews.add(headerScroll);
        ListView mListView = (ListView) findViewById(R.id.hlistview_scroll_list);

        for (int i = 0; i < 100; i++) {
            data = new HashMap<String, String>();
            data.put("标题", "北京" + i);
            for (int j = 1; j < cols.length; j++) {
                data.put(cols[j], "郑州" + j + "_" + i);
            }
            datas.add(data);
        }


        ScrollAdapter   mAdapter = new ScrollAdapter(this, datas, R.layout.common_item_hlistview                       //R.layout.item
                , cols
                , new int[]{R.id.item_titlev
                , R.id.item_datav1
                , R.id.item_datav2
                , R.id.item_datav3
                , R.id.item_datav4
                , R.id.item_datav5
                , R.id.item_datav6
                , R.id.item_datav7
                , R.id.item_datav8}
                , mHScrollViews
                , mListView);
        mListView.setAdapter(mAdapter);
    }

    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        for (CHScrollView2 scrollView : mHScrollViews) {
            //防止重复滑动
            if (mTouchView != scrollView)
                scrollView.smoothScrollTo(l, t);
        }
    }
}
