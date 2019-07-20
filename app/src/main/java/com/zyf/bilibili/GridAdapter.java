package com.zyf.bilibili;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private List<Bean_grid> provinceBeanList;
    private LayoutInflater layoutInflater;
    Context context;

    public GridAdapter(Context context, List<Bean_grid> provinceBeanList) {
        this.provinceBeanList = provinceBeanList;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return provinceBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinceBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cell_grid, null);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.grid_name);
            holder.im = convertView.findViewById(R.id.grid_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean_grid provinceBean = provinceBeanList.get(position);
        if (provinceBean != null) {
            holder.tv_name.setText(provinceBean.getName());
            Glide.with(context).load(provinceBean.getImgUrl()).into(holder.im);
        }
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        ImageView im;
    }


}
