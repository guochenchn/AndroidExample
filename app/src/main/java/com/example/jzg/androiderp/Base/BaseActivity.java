package com.example.jzg.androiderp.Base;/**
 * author: gcc
 * date: 2016/6/13 13:57
 * email:
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jzg.androiderp.app.AppContext;

/**
 * author: guochen
 * date: 2016/6/13 13:57
 * email: 
 */
public class BaseActivity extends AppCompatActivity {
    public AppContext appContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = (AppContext) getApplication();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
