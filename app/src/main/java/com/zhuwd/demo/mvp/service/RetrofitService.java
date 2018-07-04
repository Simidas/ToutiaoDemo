package com.zhuwd.demo.mvp.service;

import com.zhuwd.demo.mvp.service.entity.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lenovo on 2018/7/4.
 */

public interface RetrofitService {

    @GET("book/search")
    Call<Book> getSearchBook(@Query("q") String name,
                             @Query("tag") String tag,
                             @Query("start ") int start,
                             @Query("count") int count);


    @GET("book/search")
    Observable<Book> getSearchBook2(@Query("q") String name,
                                    @Query("tag") String tag,
                                    @Query("start ") int start,
                                    @Query("count") int count);

}
