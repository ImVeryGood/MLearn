package com.example.mlearn.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mlearn.MainActivity;
import com.example.mlearn.R;
import com.example.mlearn.muti_type.MRecyclerView;
import com.example.mlearn.time.TimeActivity;
import com.example.mlearn.view_pager.ViewPagerActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private MRecyclerView mRecyclerView;
    private List<PageBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        PageBean pageBean = new PageBean();
        pageBean.setName("MultiType");
        pageBean.setDes("动态设施布局以及底部导航");
        list.add(pageBean);
        PageBean pageBean2 = new PageBean();
        pageBean2.setName("ViewPager");
        pageBean2.setDes("viewpage example");
        list.add(pageBean2);
        PageBean pageBean3 = new PageBean();
        pageBean3.setName("Time+BorderTextView");
        pageBean3.setDes("Time BorderTextView example");
        list.add(pageBean3);
        mRecyclerView.setData(list);
        mRecyclerView.setOnItemClick((position, name) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(HomeActivity.this, ViewPagerActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(HomeActivity.this, TimeActivity.class));
                    break;
                default:

            }
        });

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.m_rv);
    }


}