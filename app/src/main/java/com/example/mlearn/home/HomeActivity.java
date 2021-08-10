package com.example.mlearn.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mlearn.MainActivity;
import com.example.mlearn.R;
import com.example.mlearn.muti_type.MRecyclerView;

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
        mRecyclerView.setData(list);
        mRecyclerView.setOnItemClick((position, name) -> startActivity(new Intent(HomeActivity.this, MainActivity.class)));

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.m_rv);
    }


}