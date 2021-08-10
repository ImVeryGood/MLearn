package com.example.mlearn.muti_type;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlearn.R;
import com.example.mlearn.home.PageBean;
import com.example.mlearn.home.RecycleViewDivider;
import com.example.mlearn.home.setItemClick;

import java.util.List;

public class MRecyclerView extends RecyclerView {
    private Adapter adapter;
    private setItemClick setItemClick;

    public MRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context mContext) {
        setLayoutManager(new LinearLayoutManager(mContext));
        addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.HORIZONTAL));
        adapter = new Adapter();
    }

    public void setData(List<PageBean> list) {
        setAdapter(adapter);
        if (list != null) {
            adapter.setData(list);
        }
    }
    public void setOnItemClick(setItemClick setItemClick) {
        this.setItemClick = setItemClick;
    }
    public class Adapter extends RecyclerView.Adapter<Adapter.RVHolder> {
        private List<PageBean> modelBeans;

        public void setData(List<PageBean> list) {
            this.modelBeans = list;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item_layout, parent, false);
            return new RVHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RVHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.tvName.setText(modelBeans.get(position).getName());
            holder.tvDes.setText(modelBeans.get(position).getDes());
            holder.linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                 setItemClick.onItemClick(position, modelBeans.get(position).getName());
                }
            });

        }

        @Override
        public int getItemCount() {
            return modelBeans == null ? 0 : modelBeans.size();
        }

        public class RVHolder extends ViewHolder {
            private TextView tvName;
            private TextView tvDes;
            private LinearLayout linearLayout;

            public RVHolder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.name);
                tvDes = itemView.findViewById(R.id.des);
                linearLayout = itemView.findViewById(R.id.ll_container);
            }
        }
    }
}

