package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.fragment.NetFragment;
import com.example.jzg.androiderp.fragment.loacFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * author: guochen
 * date: 2016/6/13 17:02
 * email:
 */
public class LoadPicActivity extends BaseActivity {

    private static final String TAG_ONE = "netFragment";
    private static final String TAG_TWO = "localFragment";
    @BindView(R.id.tv_text1)
    Button tvText1;
    @BindView(R.id.tv_text2)
    Button tvText2;
    private Fragment currentFragment;//用来存储当前的Fragment
    private FragmentManager fragmentManager;
    private Fragment netFragment;
    private Fragment localFragment;
    private String currentTag;//存储当前Fragment的Tag值

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadpic);
        ButterKnife.bind(this);
        initFragment();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {//这里要记得判断,不然......
            fragmentManager.beginTransaction().add(R.id.ft,
                    netFragment, TAG_ONE).commit();
            currentFragment = netFragment;
            currentTag = TAG_ONE;
        } else {
            netFragment = fragmentManager.findFragmentByTag(TAG_ONE);
            localFragment = fragmentManager.findFragmentByTag(TAG_TWO);
            fragmentManager.beginTransaction()
                    .show(netFragment)
                    .hide(localFragment)
                    // .hide(...)
                    .commit();

        }
//        getFromNetwork();
    }

    private void initFragment() {
        netFragment = new NetFragment();
        localFragment = new loacFragment();
    }


    @OnClick({R.id.tv_text1, R.id.tv_text2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_text1:
                tvText1.setBackground(getDrawable(R.drawable.loginbtn));
                tvText2.setBackground(null);
                switchContent(currentFragment, netFragment, TAG_ONE);
                break;
            case R.id.tv_text2:
                tvText1.setBackground(null);
                tvText2.setBackground(getDrawable(R.drawable.loginbtn));
                switchContent(currentFragment, localFragment, TAG_TWO);
                break;
        }
    }

    public void switchContent(Fragment from, Fragment to, String Tag) {
        if (currentFragment != to) {
            currentFragment = to;
            currentTag = Tag;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.ft, to, Tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
