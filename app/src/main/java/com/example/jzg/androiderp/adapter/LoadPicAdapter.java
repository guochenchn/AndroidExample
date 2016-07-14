package com.example.jzg.androiderp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.vo.Data;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * /**
 * author: guochen
 * date: 2016/6/13 18:18
 * email:
 */
public class LoadPicAdapter extends RecyclerView.Adapter<LoadPicAdapter.ViewHolder> {
    public MyOnItemClickListener myOnItemClickListener;
    private List<Data> datas;
    private Context context;
    public LoadPicAdapter(List<Data> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.loadpic_item,null,false);
        View view = View.inflate(context, R.layout.loadpic_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.relativielayout.setTag(position);
//        holder.mTextView.setText(datas.get(position).getTitle());
//        holder.mTextView1.setText(datas.get(position).getHeight());
//        holder.mTextView2.setText(datas.get(position).getWidth());
//        holder.mTextView3.setText(datas.get(position).getClassX());
        Uri imageUri = Uri.parse(datas.get(position).getThumburl());
        holder.simpleDraweeView.setImageURI(imageUri);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setMyOnItemClickListener( MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }
    public interface MyOnItemClickListener{
        public void onItemClickLister(View view,int position);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public RelativeLayout relativielayout;
        public  SimpleDraweeView simpleDraweeView;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_text);
            mTextView1 = (TextView) view.findViewById(R.id.tv_text1);
            mTextView2 = (TextView) view.findViewById(R.id.tv_text2);
            mTextView3= (TextView) view.findViewById(R.id.tv_text3);
            relativielayout= (RelativeLayout) view.findViewById(R.id.relativielayout);
            simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.simpledraweeview);
            relativielayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(myOnItemClickListener!=null){
                myOnItemClickListener.onItemClickLister(relativielayout,(Integer) relativielayout.getTag());
            }
        }
    }

}
