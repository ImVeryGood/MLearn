package com.example.mlearn.muti_type;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mlearn.DataGet;
import com.example.mlearn.ModelBean;
import com.example.mlearn.muti_type.holder.ItemADBinder;
import com.example.mlearn.muti_type.holder.ItemCardBinder;
import com.example.mlearn.muti_type.holder.ItemFourBinder;
import com.example.mlearn.muti_type.holder.ItemMainBinder;
import com.example.mlearn.muti_type.holder.ItemTitleBinder;


import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

public class AdapterUtils {
    public static void registerAdapter(Context mContext,MultiTypeAdapter typeAdapter, DataGet dataGet){
        typeAdapter.register(ModelBean.class)
                .to(
                        new ItemMainBinder(),
                        new ItemFourBinder(),
                        new ItemADBinder(dataGet),
                        new ItemCardBinder(dataGet,mContext),
                        new ItemTitleBinder()
                ).withClassLinker(new ClassLinker<ModelBean>() {
            @NonNull
            @Override
            public Class<? extends ItemViewBinder<ModelBean, ?>> index(int position, @NonNull ModelBean modelBean) {
                Class claZZ = null;
                int type = modelBean.getType();
                switch (type) {
                    case 101:
                        claZZ = ItemADBinder.class;
                        break;
                    case 102:
                        claZZ=ItemTitleBinder.class;
                        break;
                    case 106:
                        claZZ = ItemFourBinder.class;
                        break;
                    case 107:
                        claZZ = ItemCardBinder.class;
                        break;
                    default:
                        claZZ = ItemFourBinder.class;
                }
                return claZZ;
            }
        });
    }

}
