package com.zhuwd.demo.mvp.service.presenter;

import android.content.Context;
import android.content.Intent;

import com.zhuwd.demo.mvp.service.entity.Book;
import com.zhuwd.demo.mvp.service.manager.DataManager;
import com.zhuwd.demo.mvp.service.view.BookView;
import com.zhuwd.demo.mvp.service.view.View;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lenovo on 2018/7/4.
 * 主要操作的具体实现
 *
 */

public class BookPresenter implements Presenter {

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private BookView mBookView;
    private Book mBook;
    public BookPresenter (Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        mBookView = (BookView)view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }
    public void getSearchBooks(String name,String tag,int start,int count){
        mCompositeSubscription.add(manager.getSearchBooks(name,tag,start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {
                        if (mBook != null){
                            mBookView.onSuccess(mBook);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mBookView.onError("请求失败！！");
                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }
                })
        );
    }

}
