package com.zyf.bilibili.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.zyf.bilibili.R;
import com.zyf.bilibili.adapter.PagAdapter;
import com.zyf.bilibili.fragment.ListFragment;
import com.zyf.bilibili.statusBarHelper.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    PagAdapter pagAdapter;
    List<Fragment>fragmentList = new ArrayList<>();
    List<String>titles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tab);
        initWidget();
    }

    //初始化控件
    void initWidget(){
        //状态栏字体黑色
        StatusBarUtil.setStatusTextColor(true, this);

        ListFragment listFragment1= new ListFragment();
        ListFragment listFragment2 = new ListFragment();
        ListFragment listFragment3 = new ListFragment();
        ListFragment listFragment4 = new ListFragment();
        ListFragment listFragment5 = new ListFragment();
        ListFragment listFragment6 = new ListFragment();
        fragmentList.add(listFragment1);
        fragmentList.add(listFragment2);
        fragmentList.add(listFragment3);
        fragmentList.add(listFragment4);
        fragmentList.add(listFragment5);
        fragmentList.add(listFragment6);

        titles.add("直播");
        titles.add("推荐");
        titles.add("热门");
        titles.add("追番");
        titles.add("影视");
        titles.add("70年");

        tabLayout.setupWithViewPager(viewPager);

        pagAdapter = new PagAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(pagAdapter);
    }


}
