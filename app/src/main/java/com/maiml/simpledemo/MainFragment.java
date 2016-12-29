package com.maiml.simpledemo;



import com.maiml.common.base.mvp.FragmentPresenter;



public class MainFragment extends FragmentPresenter<MainDelegate> {




    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }
}
