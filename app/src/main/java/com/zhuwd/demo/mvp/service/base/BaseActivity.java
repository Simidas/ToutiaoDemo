package com.zhuwd.demo.mvp.service.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lenovo on 2018/8/13.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());
        findView();
        init();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void findView();

    protected abstract void init();
}
