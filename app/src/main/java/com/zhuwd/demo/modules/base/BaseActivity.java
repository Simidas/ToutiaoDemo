package com.zhuwd.demo.modules.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Lenovo on 2018/8/2.
 * 基类
 *
 */

public class BaseActivity extends AppCompatActivity {

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }


//    /**
//     * 管理Rxjava的生命周期
//     * @param <X>
//     * @return
//     */
//    public <X> AutoDisposeConverter<X> bindAutoDispose() {
//        return AutoDispose.autoDisposable(
//                AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
//    }

}
