package com.example.mlearn.view_pager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mlearn.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private MViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        initData();
    }

    private void initData() {
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("VP"+i);
        }
        mViewPager.setVPData(list);
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);

    }
}