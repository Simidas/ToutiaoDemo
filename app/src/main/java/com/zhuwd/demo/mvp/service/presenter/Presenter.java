package com.zhuwd.demo.mvp.service.presenter;

import android.content.Intent;

import com.zhuwd.demo.mvp.service.view.View;

/**
 * Created by Lenovo on 2018/7/4.
 *
 * View 传的是自己定义的 接口view
 */

public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(View view);

    void attachIncomingIntent(Intent intent);//暂时没用到

}
