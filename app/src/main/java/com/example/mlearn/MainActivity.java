package com.example.mlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.example.mlearn.muti_type.AdapterUtils;
import com.example.mlearn.muti_type.ContentFragment;
import com.example.mlearn.muti_type.FragmentAdapter;
import com.example.mlearn.muti_type.bean.ButtonBean;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {
    private List<ModelBean> ms = new ArrayList<>();
    private RecyclerView recyclerView;
    private RadioGroup radioGroup;
    private final String TAG = this.getClass().getSimpleName();
    private List<ButtonBean> buttonBeans;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private FragmentAdapter fragmentAdapter;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }


    @SuppressLint("ResourceType")
    private void initView() {
        recyclerView = this.findViewById(R.id.recyclerView);
        radioGroup = findViewById(R.id.rg_bottom);
        mViewPager = findViewById(R.id.view_pager);
        fragmentList = new ArrayList<>();
        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(fragmentAdapter);
        buttonBeans = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ButtonBean bean = new ButtonBean();
            bean.setName("title:" + i);
            buttonBeans.add(bean);
        }
        for (int i = 0; i < buttonBeans.size(); i++) {
            AppCompatRadioButton button = new AppCompatRadioButton(this);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setText(buttonBeans.get(i).getName());//设置文本
            button.setId(i);//设置ID
            button.setGravity(Gravity.CENTER); //设置剧中
            //button.setTextColor(getResources().getColorStateList(R.drawable.rg_text_color));
            button.setTextColor(setColor(R.color.red, R.color.black));
            button.setButtonDrawable(null);
            button.setMaxLines(1);
            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(R.drawable.select_draw);
            //drawable.setBounds(30,30,30,30);
            //设置图片在文字的位置，在那个位置，就把drawable放到哪里
            //button.setCompoundDrawablesWithIntrinsicBounds(null, setDrawable(), null, null);
            getDrawable(button);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;// 设置button 宽度平分
            radioGroup.addView(button, params);
            ContentFragment contentFragment=new ContentFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title","title="+i);
            contentFragment.setArguments(bundle);
            fragmentList.add(contentFragment);

        }
        fragmentAdapter.setFragmentList(fragmentList);
        radioGroup.check(0);
        mViewPager.setCurrentItem(0,false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mViewPager.setCurrentItem(i,false);
            }
        });
    }


    /**
     * 通过colorStateList 设置颜色
     *
     * @param selected
     * @param normal
     * @return int colorInt = Color.parseColor("#AABBCCDD");
     */
    public ColorStateList setColor(int selected, int normal) {
        int[] colors = new int[]{getResources().getColor(selected), getResources().getColor(normal)};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 设置图片
     *
     * @return
     */
    public StateListDrawable setDrawable() {
        StateListDrawable listDrawable = new StateListDrawable();
        int checked = android.R.attr.state_checked;
        listDrawable.addState(new int[]{checked}, getDrawable(R.mipmap.ic_launcher));
        listDrawable.addState(new int[]{}, getDrawable(R.mipmap.img));
        listDrawable.setBounds(10, 10, 10, 10);
        return listDrawable;
    }

    public void getDrawable(RadioButton radioButton) {
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if (message.what == 1) {
                    List<Drawable> list = (List<Drawable>) message.obj;
                    if (list != null && list.size() == 2) {
                        StateListDrawable bg = new StateListDrawable();
                        bg.addState(new int[]{android.R.attr.state_checked,}, list.get(1));
                        bg.addState(new int[]{}, list.get(0));
                        radioButton.setCompoundDrawablesWithIntrinsicBounds(null, bg, null, null);
                    }
                }
                return false;
            }
        });
        List<Drawable> list = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    Drawable drawable1 = Glide.with(radioButton.getContext())
                            .asDrawable()
                            //.load(getDrawable(R.mipmap.img))
                            .load("https://weimai-zhihuiyiyuan.oss-cn-hangzhou.aliyuncs.com/weimai-zhihuiyiyuan/hand-hospital/2021/05/14/3451024c-1886-4d37-b2dd-cb8a50d46b9b.image/png")
                            .submit()
                            .get();
                    list.add(0, drawable1);
                    Drawable drawable2 = Glide.with(radioButton.getContext())
                            .asDrawable()
                            .load(getDrawable(R.mipmap.icon))
                            .submit()
                            .get();
                    list.add(1, drawable2);
                    message.obj = list;
                    handler.sendMessage(message);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}