package com.example.mlearn.muti_type;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * fragment adapter
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> buttonBeanList;
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }
   public void setFragmentList(List<Fragment> buttonBeanList){
       this.buttonBeanList = buttonBeanList;
       notifyDataSetChanged();
   }
    @Override
    public int getCount() {
        return buttonBeanList==null?0:buttonBeanList.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return buttonBeanList.get(position);
    }
}
