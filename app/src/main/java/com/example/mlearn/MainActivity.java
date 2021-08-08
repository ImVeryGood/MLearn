package com.example.mlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mlearn.muti_type.AdapterUtils;


import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {
    private List<ModelBean> ms = new ArrayList<>();
    private RecyclerView recyclerView;
    private RadioGroup radioGroup;
    private final String TAG = this.getClass().getSimpleName();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();


    }

    private void initData() {
        for (int i = 0; i <= 20; i++) {
            ModelBean model = new ModelBean();
            model.setName("Main" + i);
            model.setAge(i);
            if (i == 0) model.setType(101);
            if (i == 1) model.setType(102);
            else if (i == 2 || i == 3 || i == 4 || i == 5) model.setType(106);
            else if (i == 6 || i == 7) model.setType(107);
            ms.add(model);
        }

        // 初始化MultiTypeAdapter
        MultiTypeAdapter adapter = new MultiTypeAdapter(ms);
        /**
         * 设置多布局
         */
        AdapterUtils.registerAdapter(this, adapter, new DataGet() {
            @Override
            public void onGetData(ModelBean itemAction) {
                if (itemAction.getType() == 102) {
                    int index = adapter.getItems().indexOf(itemAction);
                    ms.get(index).setAge(1000);
                    recyclerView.post(() -> adapter.notifyItemChanged(index));
                } else if (itemAction.getType() == 107) {
                    int index = adapter.getItems().indexOf(itemAction);
                    ms.get(index).setAge(1111);
                    recyclerView.post(() -> adapter.notifyItemChanged(index));
                }

            }
        });

        adapter.setItems(ms);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 12);
        /**
         * 设置单个item占据的个数
         */
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int type = ms.get(i).getType();
                if (type == 101 || type == 102) {
                    return 12;
                } else if (type == 106) {
                    return 3;
                } else if (type == 107) {
                    return 6;
                } else {
                    return 2;
                }

            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("ResourceType")
    private void initView() {
        recyclerView = this.findViewById(R.id.recyclerView);
        radioGroup = findViewById(R.id.rg_bottom);

        for (int i = 0; i < 4; i++) {
            AppCompatRadioButton button=new AppCompatRadioButton(this);
            RadioGroup.LayoutParams layoutParams=new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setText("我是按钮"+i);//设置文本
            button.setId(i);//设置ID
            button.setGravity(Gravity.CENTER); //设置剧中
            button.setTextColor(getResources().getColorStateList(R.drawable.rg_text_color));
            button.setButtonDrawable(null);
            button.setMaxLines(1);
            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable=getResources().getDrawable(R.drawable.select_draw);
            //drawable.setBounds(30,30,30,30);
            //设置图片在文字的位置，在那个位置，就把drawable放到哪里
            button.setCompoundDrawablesWithIntrinsicBounds(null,drawable,null,null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;// 设置button 宽度平分
            radioGroup.addView(button,params);
        }
        radioGroup.check(0);
    }
}