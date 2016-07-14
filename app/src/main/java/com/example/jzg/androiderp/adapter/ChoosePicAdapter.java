package com.example.jzg.androiderp.adapter;/**
 * author: gcc
 * date: 2016/6/19 16:05
 * email:
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.vo.ImageItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * author: guochen
 * date: 2016/6/19 16:05
 * email: 
 */
public class ChoosePicAdapter extends RecyclerView.Adapter<ChoosePicAdapter.ViewHolder> {
    public MyOnItemClickListener myOnItemClickListener;
    private List<ImageItem> datas;
    private Context context;
    private int[] defaultPics;
    public ChoosePicAdapter(List<ImageItem> datas, Context context,int[] defaultPics){
        this.datas = datas;
        this.context = context;
        this.defaultPics = defaultPics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.choosepic_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.relativeLayout.setTag(position);
            if(TextUtils.isEmpty(datas.get(position).getImageTub())){  //如果缩略图为空,就用默认图片
                holder.pic_delete.setVisibility(View.GONE);
                ImageLoader.getInstance().displayImage("drawable://" + defaultPics[position], holder.pic);
            }else{
                holder.pic_delete.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage("file:///" + datas.get(position).getImageTub(), holder.pic);
            }

        holder.textView.setText(datas.get(position).getPicName());
        holder.pic_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText("您确定要删除该照片吗？")
                        .setConfirmText("是的").setCancelText("取消")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                int i = holder.getAdapterPosition();
                                deleteFile(i);
                                datas.get(i).setImagePath("");
                                datas.get(i).setImageTub("");
                                datas.get(i).setPicId(0);
                                notifyDataSetChanged();
                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 删除本地文件
     * @param position
     */
    private void deleteFile(int position) {
        File file1 = new File(datas.get(position).getImagePath());
        File file2 = new File(datas.get(position).getImageTub());
        file1.delete();
        file2.delete();
    }

    public void setMyOnItemClickListener( MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public interface MyOnItemClickListener{
        public void onItemClickLister(View view,int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pic;
        ImageView pic_delete;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.pic_item);
            pic_delete = (ImageView) itemView.findViewById(R.id.pic_delete);
            textView = (TextView) itemView.findViewById(R.id.name_text);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl);
            relativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(myOnItemClickListener!=null){
                myOnItemClickListener.onItemClickLister(relativeLayout,(Integer)relativeLayout.getTag());
            }
        }
    }
}
