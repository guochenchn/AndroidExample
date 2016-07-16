package com.example.jzg.androiderp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 郭陈晨 on 2016/7/16.
 */
public class ToolBarActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ButterKnife.bind(this);
        //设置主标题及颜色
        toolbar.setTitle("ToolBar");
        toolbar.setTitleTextColor(Color.BLUE);

        //设置次标题及颜色
        toolbar.setSubtitle("次标题");
        toolbar.setSubtitleTextColor(Color.WHITE);

        //设置导航栏图标
        toolbar.setNavigationIcon(R.mipmap.icon_placeholder);
        //给导航栏设置监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolBarActivity.this, "这是一个导航栏", Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setLogo(R.mipmap.del_car_pic);
        toolbar.setTitleMarginStart(ScreenUtils.dip2px(this,50));//设置标题与左边的距离

        toolbar.inflateMenu(R.menu.ac_toolbar_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String result = "";
                switch (item.getItemId()) {
                    case R.id.ac_toolbar_copy:
                        result = "Copy";
                        break;
                    case R.id.ac_toolbar_cut:
                        result = "Cut";
                        break;
                    case R.id.ac_toolbar_del:
                        result = "Del";
                        break;
                    case R.id.ac_toolbar_edit:
                        result = "Edit";
                        break;
                    case R.id.ac_toolbar_email:
                        result = "Email";
                        break;
                }
                Toast.makeText(ToolBarActivity.this, result, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
