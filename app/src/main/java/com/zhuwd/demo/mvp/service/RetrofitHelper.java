package com.zhuwd.demo.mvp.service;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.zhuwd.demo.util.LoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 2018/7/4.
 * 用于Retrofit的初始化工作
 * 获得RetrofitService
 *
 */

public class RetrofitHelper {

    private Context mContext;

    OkHttpClient client = new OkHttpClient.Builder()
                                .addNetworkInterceptor(new LoggerInterceptor("Toutiao"))
                                .build();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().setLenient().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext){
        this.mContext = mContext;
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .client(client)
                .addConverterFactory(factory)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }


}
