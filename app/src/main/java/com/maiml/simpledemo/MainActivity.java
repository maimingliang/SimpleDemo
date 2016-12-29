package com.maiml.simpledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.maiml.common.base.mvp.ActivityPresenter;
import com.maiml.common.utils.LogUtil;

public class MainActivity extends ActivityPresenter<SimpleDelegate> {


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
    }


    @Override
    protected Class getDelegateClass() {
        return SimpleDelegate.class;
    }


    @Override
    protected void onNetworkDisConnected() {
        super.onNetworkDisConnected();
        LogUtil.d("连接网络失败");
//        viewDelegate.disConnect();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                viewDelegate.openDrawer();
                return true;
            case R.id.action_settings:
//                Bundle extras = new Bundle();
//                extras.putString(BaseWebActivity.BUNDLE_KEY_URL, "https://github.com/fangx");
//                extras.putString(BaseWebActivity.BUNDLE_KEY_TITLE, "关于我");
//                readyGo(BaseWebActivity.class, extras);
                break;
            case R.id.action_share:
//                BaseUtil.share(this, "分享项目地址", "https://github.com/fangx/ZhiHuMVP");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
