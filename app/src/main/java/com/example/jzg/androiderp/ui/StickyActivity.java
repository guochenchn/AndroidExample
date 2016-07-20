package com.example.jzg.androiderp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jzg.androiderp.Base.BaseActivity;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.vo.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: guochen
 * date: 2016/7/20 15:58
 * email:
 */
public class StickyActivity extends BaseActivity {
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessage(StickyEvent stickyEvent){
        tvText.setText(stickyEvent.getMessage());
         Toast.makeText(this, "收到一个粘性事件"+stickyEvent.getMessage(), Toast.LENGTH_SHORT).show();
        EventBus.getDefault().removeStickyEvent(StickyEvent.class);//删除粘性事件
    }

}
