package com.example.mlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.mlearn.muti_type.AdapterUtils;


import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {
    private List<ModelBean> ms = new ArrayList<>();
    private RecyclerView recyclerView;
    private final String TAG = this.getClass().getSimpleName();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = this.findViewById(R.id.recyclerView);


        for (int i = 0; i <= 20; i++) {
            ModelBean model = new ModelBean();
            model.setName("Main" + i);
            model.setAge(i);
            if (i == 0) model.setType(101);
            if (i==1) model.setType(102);
            else if ( i == 2 || i == 3 || i == 4||i == 5 ) model.setType(106);
            else if (i == 6 || i == 7) model.setType(107);
            ms.add(model);
        }

        // 初始化MultiTypeAdapter
        MultiTypeAdapter adapter = new MultiTypeAdapter(ms);
        /**
         * 设置多布局
         */
        AdapterUtils.registerAdapter(adapter, new DataGet() {
            @Override
            public void onGetData(ModelBean itemAction) {
               int index=  adapter.getItems().indexOf(itemAction);
               ms.get(index).setAge(1000);
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemChanged(index);
                    }
                });
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
                if (type == 101||type==102) {
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
}