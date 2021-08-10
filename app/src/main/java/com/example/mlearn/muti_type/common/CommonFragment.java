package com.example.mlearn.muti_type.common;

import androidx.fragment.app.Fragment;

public abstract class CommonFragment extends Fragment {
    private boolean isLoaded = false ;//控制是否执行懒加载

    @Override
    public void onResume() {
        super.onResume();
        judgeLazyInit();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setUserVisibleHint(!hidden);;
        judgeLazyInit();


    }


    private void judgeLazyInit() {
        if (!isLoaded && !isHidden()) {
            lazyInit();
            isLoaded = true;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoaded = false;
    }

    //懒加载方法
    protected abstract void lazyInit();
}
