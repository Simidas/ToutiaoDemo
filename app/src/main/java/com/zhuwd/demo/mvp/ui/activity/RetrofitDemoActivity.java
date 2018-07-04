package com.zhuwd.demo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuwd.demo.R;
import com.zhuwd.demo.mvp.service.entity.Book;
import com.zhuwd.demo.mvp.service.presenter.BookPresenter;
import com.zhuwd.demo.mvp.service.view.BookView;

/**
 * Created by Lenovo on 2018/7/4.
 */

public class RetrofitDemoActivity extends AppCompatActivity implements BookView{


    private Button btNetwork;
    private TextView tvContent;
    private BookPresenter mBookPresenter = new BookPresenter(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retro_text);

        initView();
        mBookPresenter.onCreate();
        mBookPresenter.attachView(this);
    }

    private void initView() {
        btNetwork = (Button) findViewById(R.id.bt_network);
        tvContent = (TextView) findViewById(R.id.tv_content);

        btNetwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getRetrofit();
//                getRxjava();
                mBookPresenter.getSearchBooks("金瓶梅", null, 0, 1);

            }
        });

    }


    @Override
    public void onSuccess(Book mBook) {
        tvContent.setText("Rxjava  "+mBook.getBooks().get(0).getAuthor().get(0));
    }

    @Override
    public void onError(String result) {
        Toast.makeText(this,"错误啦",Toast.LENGTH_SHORT);
    }


//    public void getRetrofit() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.douban.com/v2/")
//                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
//                .build();
//
//        RetrofitService service = retrofit.create(RetrofitService.class);
//
//        Call<Book> call = service.getSearchBook("金瓶梅",null,0,1);
//
//        call.enqueue(new Callback<Book>() {
//            @Override
//            public void onResponse(Call<Book> call, Response<Book> response) {
//                tvContent.setText(response.body().getBooks().get(0).getAuthor().get(0));
//            }
//
//            @Override
//            public void onFailure(Call<Book> call, Throwable t) {
//
//            }
//        });
//    }
//
//    public void getRxjava() {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.douban.com/v2/")
//                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//
//        RetrofitService service = retrofit.create(RetrofitService.class);
//
//        Observable<Book> observable = service.getSearchBook2("金瓶梅", null, 0, 1);
//
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Book>() {
//
//                    @Override
//                    public void onCompleted() {
//                        //所有事件都完成，可以做些操作。。。
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Book book) {//这里的book就是我们请求接口返回的实体类
//                        tvContent.setText(book.getBooks().get(0).getAuthor().get(0));
//                    }
//                });
//
//
//    }


}
