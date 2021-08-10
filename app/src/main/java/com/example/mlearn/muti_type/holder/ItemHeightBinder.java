package com.example.mlearn.muti_type.holder;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlearn.ModelBean;
import com.example.mlearn.R;

import me.drakeet.multitype.ItemViewBinder;

public class ItemHeightBinder extends ItemViewBinder<ModelBean, ItemHeightBinder.MHolder> {

    @NonNull
    @Override
    protected MHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MHolder(inflater.inflate(R.layout.height_layout, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull MHolder holder, @NonNull ModelBean item) {
        holder.mNameView.setText("height:"+item.getAge());
        Log.d("TAG", "onBindViewHolder: 11");

    }

    public class MHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;

        public MHolder(@NonNull View itemView) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.text_age);

        }
    }
}
