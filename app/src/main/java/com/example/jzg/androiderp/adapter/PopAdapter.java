package com.example.jzg.androiderp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jzg.androiderp.R;



/**
 * author: guochen
 * date: 2016/6/21 16:05
 * email: 
 */
public class PopAdapter extends BaseAdapter{

    private String [] strs;
    private Context context;
    private int checkItemPosition = 0;

    public PopAdapter(String[] strs, Context context) {
        this.strs = strs;
        this.context = context;
    }
    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.pop_item,null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(strs[position]);
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.drop_down_selected));
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.mipmap.drop_down_checked), null);
            } else {
                viewHolder.textView.setTextColor(context.getResources().getColor(R.color.drop_down_unselected));
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView textView;
    }
}
