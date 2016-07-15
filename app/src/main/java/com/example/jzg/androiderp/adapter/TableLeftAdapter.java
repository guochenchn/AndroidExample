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
 * date: 2016/7/15 15:24
 * email: 
 */
public class TableLeftAdapter extends BaseAdapter {

    private Context context;
    private List<AppraisersData.DataBean> datas;
    public TableLeftAdapter(Context context, List<AppraisersData.DataBean> datas) {
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
            convertView = View.inflate(context, R.layout.item_content_left, null);
            viewHolder.tv_item = (TextView)convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.tv_item.setText(datas.get(position).getName());
        return convertView;
    }

class ViewHolder {
    TextView tv_item;
}
}
