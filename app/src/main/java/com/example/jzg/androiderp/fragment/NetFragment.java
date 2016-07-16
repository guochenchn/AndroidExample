package com.example.jzg.androiderp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jzg.androiderp.Interface.PicInterface;
import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.adapter.LoadPicAdapter;
import com.example.jzg.androiderp.presenter.DataManager;
import com.example.jzg.androiderp.presenter.PicPresenter;
import com.example.jzg.androiderp.ui.DetailsActivity;
import com.example.jzg.androiderp.vo.Data;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetFragment extends Fragment implements LoadPicAdapter.MyOnItemClickListener, PicInterface, XRecyclerView.LoadingListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.x_recyclerview)
    XRecyclerView xRecyclerview;
    private PicPresenter picPresenter;
    private LoadPicAdapter loadPicAdapter;
    private List<Data> listDatas;
    private View emptyview;


    public NetFragment() {

    }

    public static NetFragment newInstance(String param1, String param2) {
        NetFragment fragment = new NetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net, container, false);
        emptyview = View.inflate(getActivity(), R.layout.empty_layout, null);
        ButterKnife.bind(this, view);
        listDatas = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerview.setLayoutManager(layoutManager);
        xRecyclerview.setLoadingListener(this);
        ((ViewGroup) xRecyclerview.getParent()).addView(emptyview);
        xRecyclerview.setEmptyView(emptyview);
        loadPicAdapter = new LoadPicAdapter(listDatas, getActivity());
        xRecyclerview.setAdapter(loadPicAdapter);
        loadPicAdapter.setMyOnItemClickListener(this);
        picPresenter = new PicPresenter(DataManager.getInstance());
        picPresenter.attachView(this);
        picPresenter.loadPic();
//        getFromNetwork();
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
    @Override
    public void onItemClickLister(View view,int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("url", listDatas.get(position).getUrl());
        startActivity(intent);
    }
    /**
     * 第一次联网
     *
     * @param datas
     */
    @Override
    public void showDatas(List<Data> datas) {
        if (datas.size() > 0) {
            listDatas.addAll(datas);
            if (loadPicAdapter == null) {
                loadPicAdapter = new LoadPicAdapter(listDatas, getActivity().getApplicationContext());
                xRecyclerview.setAdapter(loadPicAdapter);
            } else {
                loadPicAdapter.notifyDataSetChanged();
            }
            Data.saveInTx(datas);//把数据存储到数据库
        }
//        else {
//            ((ViewGroup) xRecyclerview.getParent()).addView(emptyview);
//            xRecyclerview.setEmptyView(emptyview);
//        }
    }

    /**
     * 加载更多的数据回调
     *
     * @param datas
     */
    @Override
    public void showMoreDatas(List<Data> datas) {
        Toast.makeText(getActivity(), "showMoreDatas", Toast.LENGTH_SHORT).show();
        if (datas !=null) {
            listDatas.addAll(datas);
            if (loadPicAdapter == null) {
                loadPicAdapter = new LoadPicAdapter(listDatas, getActivity().getApplicationContext());
                xRecyclerview.setAdapter(loadPicAdapter);
            } else {
                loadPicAdapter.notifyDataSetChanged();
            }
        }
        xRecyclerview.loadMoreComplete();//停止上拉加载
    }
    /**
     * 下拉刷新的数据回调
     *
     * @param datas
     */
    @Override
    public void showRefreshDatas(List<Data> datas) {
        if (datas !=null) {
            listDatas.clear();
            listDatas.addAll(datas);
            if (loadPicAdapter == null) {
                loadPicAdapter = new LoadPicAdapter(listDatas, getActivity().getApplicationContext());
                xRecyclerview.setAdapter(loadPicAdapter);
            } else {
                loadPicAdapter.notifyDataSetChanged();
            }
        }
//        else {
//            ((ViewGroup) xRecyclerview.getParent()).addView(emptyview);
//            xRecyclerview.setEmptyView(emptyview);
//        }
        xRecyclerview.refreshComplete();//停止下拉刷新
    }

    @Override
    public void showError(String error) {
        xRecyclerview.refreshComplete();//停止下拉刷新
        xRecyclerview.loadMoreComplete();//停止上拉加载
        listDatas.clear();
        if (loadPicAdapter == null) {
            loadPicAdapter = new LoadPicAdapter(listDatas, getActivity().getApplicationContext());
            xRecyclerview.setAdapter(loadPicAdapter);
        } else {
            loadPicAdapter.notifyDataSetChanged();
        }
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    /**
     * 下拉刷新的监听
     */
    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "onRefresh", Toast.LENGTH_SHORT).show();
        picPresenter.loadrefreshPic();
    }

    /**
     * 上拉加载的监听
     */
    @Override
    public void onLoadMore() {
        Toast.makeText(getActivity(), "onLoadMore", Toast.LENGTH_SHORT).show();
        picPresenter.loadMorePic();
    }




    private void getFromNetwork() {
        OkHttpClient client = new OkHttpClient();
        //下面这句话显得尤为重要，加入后才能拦截到http请求。
        //   client.networkInterceptors().add(new StethoInterceptor());
        //构建请求
        Request request = new Request.Builder()
                .url("http://api.laifudao.com/open/tupian.json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e("出错了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                if (body != null) {
                    Logger.e(body);
                }
            }

        });
    }

}
