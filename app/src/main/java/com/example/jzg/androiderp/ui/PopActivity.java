package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.adapter.PopAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: guochen
 * date: 2016/6/21 15:29
 * email:
 */
public class PopActivity extends BaseActivity {
    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;

  //  private String[] strings = new String[]{"年龄","星座"};
    private String strings[] = {"年龄","星座"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private List<View> list = new ArrayList<>();
    private ListView agelistview;
    private ListView constellationslv;
    private PopAdapter popAdapter;
    private PopAdapter popAdapter1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        ButterKnife.bind(this);
        initData();
        initListener();

    }

    private void initListener() {
        agelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position==0?strings[0]:ages[position]);
                dropDownMenu.closeMenu();

            }
        });

        constellationslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popAdapter1.setCheckItem(position);
                dropDownMenu.setTabText(position==0?strings[1]:constellations[position]);
                dropDownMenu.closeMenu();

            }
        });
    }

    private void initData() {
        dropDownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PopActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        agelistview = new ListView(this);
        agelistview.setDividerHeight(0);
        popAdapter = new PopAdapter(ages,appContext);
        agelistview.setAdapter(popAdapter);

        constellationslv = new ListView(this);
        constellationslv.setDividerHeight(0);
        popAdapter1 = new PopAdapter(constellations,appContext);
        constellationslv.setAdapter(popAdapter1);
        list.add(agelistview);
        list.add(constellationslv);
        dropDownMenu.setDropDownMenu(Arrays.asList(strings),list,contentView);

    }
}
