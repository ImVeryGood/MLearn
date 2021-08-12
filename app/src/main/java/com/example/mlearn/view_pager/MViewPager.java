package com.example.mlearn.view_pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mlearn.R;

import java.util.List;

public class MViewPager extends ViewPager {
    private VPAdapter vpAdapter;

    public MViewPager(@NonNull Context context) {
        super(context);
        init();
    }

    public MViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @SuppressLint("WrongConstant")
    public void init() {
       setPageTransformer(false,new VerticalPageTransformer());
        vpAdapter = new VPAdapter();
        setAdapter(vpAdapter);
    }

    public void setVPData(List<String> list) {
        vpAdapter.setData(list);

    }

    public class VPAdapter extends PagerAdapter {
        private List<String> list;

        public void setData(List<String> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.page_item_layout, null);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(container.getRootView());
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) {
                // [-Infinity,-1)
                // 当前页的上一页
            } else if (position <= 1) {
                // [-1,1]
                // 抵消默认幻灯片过渡
                view.setTranslationX(view.getWidth() * -position);

                //设置从上滑动到Y位置
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

            } else {
                // (1,+Infinity]
                // 当前页的下一页
            }
        }
    }
}
