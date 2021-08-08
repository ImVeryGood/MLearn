package com.example.mlearn.muti_type.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlearn.DataGet;
import com.example.mlearn.ModelBean;
import com.example.mlearn.R;
import com.example.mlearn.muti_type.FunctionItemClick;

import me.drakeet.multitype.ItemViewBinder;

public class ItemCardBinder extends ItemViewBinder<ModelBean, ItemCardBinder.MHolder> {
private DataGet dataGet;
private Context mContext;

    public ItemCardBinder(DataGet dataGet, Context mContext) {
        this.dataGet = dataGet;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    protected MHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MHolder(inflater.inflate(R.layout.card_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull MHolder holder, @NonNull ModelBean item) {
        holder.mNameView.setText("card"+item.getAge());
        holder.constraintLayout.setOnClickListener(new FunctionItemClick(item,mContext));
        dataGet.onGetData(item);
    }

    public class MHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;
        private ConstraintLayout constraintLayout;
        public MHolder(@NonNull View itemView) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.text_age);
            constraintLayout=itemView.findViewById(R.id.card_layout);

        }
    }
}
