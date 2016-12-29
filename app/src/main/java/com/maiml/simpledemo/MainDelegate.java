package com.maiml.simpledemo;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.maiml.common.base.mvp.AppDelegate;

public class MainDelegate extends AppDelegate {

     TabLayout tabLayout;
     ViewPager viewPager;
     LinearLayout llContent;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_main;
    }


    @Override
    public void initWidget() {
        super.initWidget();

        tabLayout = get(R.id.tab_layout);
        viewPager = get(R.id.view_pager);

        llContent = get(R.id.ll_content);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        viewPager.setAdapter(new MainFragmentAdapter(((FragmentActivity) getActivity()).getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }




    public class MainFragmentAdapter extends FragmentPagerAdapter {
        private String[] mTab = new String[]{"福利"};

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return new MeiZhiFragment();
        }

        @Override
        public int getCount() {
            return mTab.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTab[position];
        }
    }


}
