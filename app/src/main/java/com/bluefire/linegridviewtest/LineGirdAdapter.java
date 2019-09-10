package com.bluefire.linegridviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by BlueFire on 2019/9/9  16:11
 * Describe:
 */
public class LineGirdAdapter extends BaseAdapter {

    private List<Map<String, Object>> dataList;
    private LayoutInflater layoutInflater;

    public LineGirdAdapter(Context context, List<Map<String, Object>> list) {
        this.dataList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.grid_view_item_layout, null);
            holder.text = convertView.findViewById(R.id.textView);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText((String)dataList.get(position).get("text"));
        holder.imageView.setImageResource((int)dataList.get(position).get("img"));
        return convertView;

    }

    class ViewHolder {
        ImageView imageView;
        TextView text;
    }

}
