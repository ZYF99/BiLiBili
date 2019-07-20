package com.zyf.bilibili.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyf.bilibili.R;
import com.zyf.bilibili.bean.Bean_mainList;

import java.util.List;

public class ListAdapter extends BaseQuickAdapter<Bean_mainList, BaseViewHolder> {

    public ListAdapter(int layoutResId, @Nullable List<Bean_mainList> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean_mainList item) {
        helper.setText(R.id.cell_name,item.getName());
        Glide.with(mContext).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.cell_img));
    }
}
