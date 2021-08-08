package com.example.mlearn.muti_type;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.mlearn.ModelBean;

public class FunctionItemClick implements View.OnClickListener {
    private ModelBean modelBean;
    private Context mContext;

    public FunctionItemClick(ModelBean modelBean, Context mContext) {
        this.modelBean = modelBean;
        this.mContext = mContext;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mContext, modelBean.getAge() + "", Toast.LENGTH_SHORT).show();
    }
}
