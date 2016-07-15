package com.example.jzg.androiderp.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.adapter.LoadPicAdapter;
import com.example.jzg.androiderp.vo.Data;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * author: guochen
 * date: 2016/6/16 12:01
 * email:
 */
public class loacFragment extends Fragment implements XRecyclerView.LoadingListener, LoadPicAdapter.MyOnItemClickListener{
//    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;
    private List<Data> loacDatas = new ArrayList<>();
    private Iterator<Data> listdatas;
    private View emptyview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
//different
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loac, container, false);
        emptyview = View.inflate(getActivity(), R.layout.empty_layout, null);
         listdatas = Data.findAll(Data.class);
        if (listdatas != null) {
            initData();
            initView(view);//初始化View
        }
        return view;
    }

    private void initView(View view) {
        recyclerview = (XRecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setLoadingListener(this);

        if(loacDatas==null){
            ((ViewGroup) recyclerview.getParent()).addView(emptyview);
            recyclerview.setEmptyView(emptyview);
            LoadPicAdapter  loadPicAdapter = new LoadPicAdapter(loacDatas, getActivity());
            recyclerview.setAdapter(loadPicAdapter);
            loadPicAdapter.setMyOnItemClickListener(this);
        }else {
            LoadPicAdapter loadPicAdapter = new LoadPicAdapter(loacDatas, getActivity());
            recyclerview.setAdapter(loadPicAdapter);
            loadPicAdapter.setMyOnItemClickListener(this);
        }
    }

    private void initData() {
        while (listdatas.hasNext()) {
            loacDatas.add(listdatas.next());
        }
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "没有更多数据刷新", Toast.LENGTH_SHORT).show();
        recyclerview.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(getActivity(), "没有更多数据加载", Toast.LENGTH_SHORT).show();
        recyclerview.loadMoreComplete();
    }

    @Override
    public void onItemClickLister(View view,int position) {
        Toast.makeText(getActivity(), "点击了"+position, Toast.LENGTH_SHORT).show();
    }
}
