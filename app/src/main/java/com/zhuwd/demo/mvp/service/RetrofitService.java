package com.zhuwd.demo.mvp.service;

import com.zhuwd.demo.mvp.service.entity.Book;
import com.zhuwd.demo.mvp.ui.activity.article.ArticleBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    /**
     * 玩android文字列表页数据
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Call<ArticleBean> getArticleListData(@Path("page") int page);


}
