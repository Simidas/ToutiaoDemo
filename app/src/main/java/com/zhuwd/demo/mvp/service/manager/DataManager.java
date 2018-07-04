package com.zhuwd.demo.mvp.service.manager;

import android.content.Context;

import com.zhuwd.demo.mvp.service.RetrofitHelper;
import com.zhuwd.demo.mvp.service.RetrofitService;
import com.zhuwd.demo.mvp.service.entity.Book;

import rx.Observable;

/**
 * Created by Lenovo on 2018/7/4.
 * 为了方便调用RetrofitService中的方法
 *  相当于是封装了一层RetrofitService中的方法
 * 为的是不重复建立RetrofitService 的实例化
 * 只要保证调用DataManager实例一次（例如 做成一个全局变量），则mRetrofitService也是一个实例变量
 *
 */

public class DataManager {

    private RetrofitService mRetrofitService;

    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    public Observable<Book> getSearchBooks(String name, String tag, int start, int count){
        return mRetrofitService.getSearchBook2(name,tag,start,count);
    }

}
