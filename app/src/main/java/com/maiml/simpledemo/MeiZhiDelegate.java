package com.maiml.simpledemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.maiml.common.base.mvp.AppDelegate;

import java.util.ArrayList;
import java.util.List;

import me.fangx.haorefresh.HaoRecyclerView;


public class MeiZhiDelegate extends AppDelegate {


     HaoRecyclerView recycleview;
     SwipeRefreshLayout swiperefresh;
     FrameLayout rootView;

    private List<GankIoBean> datas = new ArrayList<>();
    private MeiZhiListAdapter mainListAdapter;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_mei_zhi;
    }


    @Override
    public void initWidget() {
        super.initWidget();

        recycleview = get(R.id.recycleview);
        swiperefresh = get(R.id.swiperefresh);
         rootView = get(R.id.root_view);

        swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        mainListAdapter = new MeiZhiListAdapter(this.getActivity(),datas);
        recycleview.setAdapter(mainListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(layoutManager);
//        if(fabButton != null){
//            fabButton.attachToRecyclerView(recycleview);
//        }

        //设置自定义加载中和到底了效果
//        ProgressBar progressBar = new ProgressBar(activity);
//        progressBar.setIndeterminate(false);
//        // 给progressbar准备一个FrameLayout的LayoutParams
//        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        // 设置对其方式为：屏幕居中对其
//        lp.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
//        progressBar.setLayoutParams(lp);
//        recycleview.setFootLoadingView(progressBar);

        TextView textView = new TextView(getActivity());
        textView.setText("已经到底啦~");
        recycleview.setFootEndView(textView);

    }

    public void reFreshDataChange(List<GankIoBean> list){
        datas.clear();
        datas.addAll(list);
        mainListAdapter.notifyDataSetChanged();

    }


    public void loadMoreDataChange(List<GankIoBean> list){
        datas.clear();
        datas.addAll(list);
        mainListAdapter.notifyDataSetChanged();
    }

    public HaoRecyclerView getRecycleview() {
        return recycleview;
    }

    public SwipeRefreshLayout getSwiperefresh() {
        return swiperefresh;
    }
}
