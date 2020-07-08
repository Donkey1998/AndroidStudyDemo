package com.example.androidstudydemo.ListViewDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidstudydemo.R;

import java.util.List;

/**
 * Created by Donkey
 * on 11:38 AM
 */
public class MyAdapter extends BaseAdapter {
    private List<Integer> mData;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<Integer> mData) {
        this.mData = mData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder = new ViewHolder();
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.list_view_item, null);
            viewHolder.imageView = convertView.findViewById(R.id.listview_image);
            viewHolder.textView = convertView.findViewById(R.id.listview_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.textView.setText(mData.get(position).toString());
        return convertView;
    }

    private final class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
