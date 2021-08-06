package com.example.mlearn.muti_type.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlearn.ModelBean;
import com.example.mlearn.R;

import me.drakeet.multitype.ItemViewBinder;

public class ItemMainBinder extends ItemViewBinder<ModelBean, ItemMainBinder.MHolder> {

    @NonNull
    @Override
    protected MHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MHolder(inflater.inflate(R.layout.main_layout, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull MHolder holder, @NonNull ModelBean item) {
        holder.mNameView.setText(item.getName());
    }

    public class MHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;

        public MHolder(@NonNull View itemView) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.text_main);

        }
    }
}
