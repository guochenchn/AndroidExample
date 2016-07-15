package com.example.jzg.androiderp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jzg.androiderp.R;
import com.example.jzg.androiderp.vo.AppraisersData;

import java.util.List;

/**
 * author: guochen
 * date: 2016/7/15 15:30
 * email: 
 */
public class TableRightAdapter extends BaseAdapter{
    private Context context;
    private List<AppraisersData.DataBean> datas;
    public TableRightAdapter(Context context, List<AppraisersData.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_right, null);
            viewHolder.tv_item1 = (TextView)convertView.findViewById(R.id.tv_item1);
            viewHolder.tv_item2 = (TextView)convertView.findViewById(R.id.tv_item2);
            viewHolder.tv_item3 = (TextView)convertView.findViewById(R.id.tv_item3);
            viewHolder.tv_item4 = (TextView)convertView.findViewById(R.id.tv_item4);
            viewHolder.tv_item5 = (TextView)convertView.findViewById(R.id.tv_item5);
            viewHolder.tv_item6 = (TextView)convertView.findViewById(R.id.tv_item6);
//            viewHolder.tv_item7 = (TextView)convertView.findViewById(R.id.tv_item7);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        Product product = objects.get(position);
        viewHolder.tv_item1.setText(datas.get(position).getCityName() + "");
        viewHolder.tv_item2.setText(datas.get(position).getConfirmCount() + "");
        viewHolder.tv_item3.setText(datas.get(position).getSixCount() + "");//(product.getSell() - product.getLastClose()) * 100 / product.getSell() + ""
        viewHolder.tv_item4.setText(datas.get(position).getEighteenCount() + "");
        viewHolder.tv_item5.setText(datas.get(position).getOffLineCount() + "");
        viewHolder.tv_item6.setText(datas.get(position).getSignCount() + "");
//        viewHolder.tv_item7.setText(NumberUtil.beautifulDouble(product.getLastClose()));
        return convertView;
    }
    class ViewHolder {
        TextView tv_item1, tv_item2, tv_item3, tv_item4, tv_item5, tv_item6, tv_item7;
    }

}
