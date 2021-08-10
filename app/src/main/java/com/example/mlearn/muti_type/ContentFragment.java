package com.example.mlearn.muti_type;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mlearn.DataGet;
import com.example.mlearn.ModelBean;
import com.example.mlearn.R;
import com.example.mlearn.muti_type.common.CommonFragment;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;


public class ContentFragment extends CommonFragment {
    private String TAG=getClass().getSimpleName();
    private String title;
    private List<ModelBean> ms = new ArrayList<>();
    private RecyclerView recyclerView;
    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView textView = view.findViewById(R.id.title);
        textView.setText(title);
        recyclerView=view.findViewById(R.id.recyclerView);
       initRvData();
    }
    private void initRvData() {

        // 初始化MultiTypeAdapter
        MultiTypeAdapter adapter = new MultiTypeAdapter(ms);
        /**
         * 设置多布局
         */
        AdapterUtils.registerAdapter(getActivity(), adapter, new DataGet() {
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 12);
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

    @Override
    protected void lazyInit() {
        Log.d(TAG, "onCreate: title=lazyInit"+title);
        for (int i = 0; i <= 20; i++) {
            ModelBean model = new ModelBean();
            model.setName(title+":" + i);
            model.setAge(i);
            if (i == 0) model.setType(101);
            if (i == 1) model.setType(102);
            else if (i == 2 || i == 3 || i == 4 || i == 5) model.setType(106);
            else if (i == 6 || i == 7) model.setType(107);
            ms.add(model);
        }
    }
}