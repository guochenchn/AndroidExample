package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.adapter.LoadPicAdapter;

/**
 * author: guochen
 * date: 2016/7/12 16:53
 * email:
 */
public class CoordinatorLayoutActivity extends BaseActivity implements LoadPicAdapter.MyOnItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//不加这句话不能使用Toolbar
        setContentView(R.layout.activity_coordinatorlayout);
        initView();
        initData();
    }

    private void initView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* 给左上角图标的左边加上一个返回的图标,必须在清单文件中加上
         <meta-data
        android:name="android.support.PARENT_ACTIVITY"
        android:value=".ui.MainActivity" />*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("CollapsingToolbarLayout");

        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(R.mipmap.wangyi).centerCrop().into(imageView);

    }

    private void initData() {

    }

    @Override
    public void onItemClickLister(View view, int position) {

    }
}
