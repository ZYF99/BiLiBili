package com.zyf.bilibili;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;
    GridView gridView;
    GridAdapter gr;
    MZBannerView bannerView;

    View headerView;

    ListAdapter adapter;
    List<String> bannerList;
    List<Bean_mainList> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.listfragment, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerview);
        smartRefreshLayout = view.findViewById(R.id.smart);
        list = new ArrayList<>();

        Bean_mainList item = new Bean_mainList("AAAAAA", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563613955276&di=9098ad471181ee4647fcf102dabadb8f&imgtype=0&src=http%3A%2F%2Fuploads.5068.com%2Fallimg%2F1712%2F151-1G20G13635.jpg");
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new ListAdapter(R.layout.cell_list, list);
        recyclerView.setAdapter(adapter);

        headerView = LayoutInflater.from(getContext()).inflate(R.layout.list_header,null);

        adapter.addHeaderView(headerView);
        gridView = headerView.findViewById(R.id.grid);
        bannerView = headerView.findViewById(R.id.banner);

        bannerList = new ArrayList<>();
        bannerList.add("http://img2.imgtn.bdimg.com/it/u=921654557,388014813&fm=26&gp=0.jpg");
        bannerList.add("http://img3.imgtn.bdimg.com/it/u=2530660329,534202878&fm=26&gp=0.jpg");
        bannerList.add("http://img4.imgtn.bdimg.com/it/u=3084857086,2439333052&fm=26&gp=0.jpg");
        bannerList.add("http://img3.imgtn.bdimg.com/it/u=394591684,703198697&fm=26&gp=0.jpg");


        bannerView.setPages(bannerList, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

        List<Bean_grid>gridList = new ArrayList<>();

        for (int i=0;i<=9;i++) {
            gridList.add(new Bean_grid("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563613955277&di=6ef2aa3c16c5fad44b55d320d9905a70&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170625%2F613ac5512df641f5b466f451cf0f831d_th.png", "王者荣耀"));
        }
        gr = new GridAdapter(getContext(),gridList);
        gridView.setAdapter(gr);



        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getContext(),"刷新了！",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        smartRefreshLayout.finishRefresh();
                        adapter.setEnableLoadMore(true);
                    }
                },1000);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Toast.makeText(getContext(),"加载数据了！",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.loadMoreEnd();
                    }
                },1000);
            }
        });

    }

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.cel_banner,null);
            mImageView = view.findViewById(R.id.banner_img);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            Glide.with(context).load(data).into(mImageView);

        }
    }

}
