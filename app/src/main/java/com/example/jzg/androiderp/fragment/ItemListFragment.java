package com.example.jzg.androiderp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jzg.androiderp.vo.MyEvent;
import com.example.jzg.androiderp.vo.StickyEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author: guochen
 * date: 2016/7/20 13:50
 * email: 
 */
public class ItemListFragment extends ListFragment {
    private List<String> datas = new ArrayList<>();
    private List<MyEvent> dataMessage = new ArrayList<>();
    private List<StickyEvent> stickyMessage = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//注册
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); //反注册
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datas.add("Item1");
        datas.add("Item2");
        datas.add("Item3");
        datas.add("Item4");
        dataMessage.add(new MyEvent("fragment1"));
        dataMessage.add(new MyEvent("fragment2"));
        dataMessage.add(new MyEvent("fragment3"));
        dataMessage.add(new MyEvent("fragment4"));
        stickyMessage.add(new StickyEvent("stickyfragment1"));
        stickyMessage.add(new StickyEvent("stickyfragment2"));
        stickyMessage.add(new StickyEvent("stickyfragment3"));
        stickyMessage.add(new StickyEvent("stickyfragment4"));

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, datas));
    }

    /**
     * 当点击的时候这里也会执行.并先执行
     * @param myEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 10)
    public void OnEvent(MyEvent myEvent){
        Logger.e(myEvent.getmMessage()+"-- ItemListFragment");
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        EventBus.getDefault().post(dataMessage.get(position));
        EventBus.getDefault().post(datas.get(position));
        /**
         * 发送一个粘性事件,当一个activity没有被注册的时候找个事件会保存在内存中,当找个activity注册了,就会调用相应的方法
         */
        EventBus.getDefault().postSticky(stickyMessage.get(position));
    }
}
