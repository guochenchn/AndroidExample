package com.example.jzg.androiderp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.vo.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * author: guochen
 * date: 2016/7/20 14:24
 * email: 
 */
public class DetailFragment extends Fragment{

    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);//注册
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootViwe = inflater.inflate(R.layout.fragment_detail, container,false);
        textView = (TextView) rootViwe.findViewById(R.id.item_detail);
        return rootViwe;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//返注册
    }

    /**
     * priority  表示优先级,值越大,优先级越高 可以不写
     * ThreadMode.MAIN：表示无论事件是在哪个线程发布出来的，该事件订阅方法onEvent都会在UI线程中执行，这个在Android中是非常有用的，因为在Android中只能在UI线程中更新UI，所有在此模式下的方法是不能执行耗时操作的。

     ThreadMode.POSTING：表示事件在哪个线程中发布出来的，事件订阅函数onEvent就会在这个线程中运行，也就是说发布事件和接收事件在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。

     ThreadMode.BACKGROUND：表示如果事件在UI线程中发布出来的，那么订阅函数onEvent就会在子线程中运行，如果事件本来就是在子线程中发布出来的，那么订阅函数直接在该子线程中执行。

     ThreadMode.AYSNC：使用这个模式的订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程来执行订阅函数。

     参数不能直接转int值
     * @param str
     */

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 1)
    public void OnEvent(String str){
        textView.setText(str);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 0)
    public void OnEvent(MyEvent myEvent){
        Toast.makeText(getActivity(), myEvent.getmMessage(), Toast.LENGTH_SHORT).show();
    }


}
