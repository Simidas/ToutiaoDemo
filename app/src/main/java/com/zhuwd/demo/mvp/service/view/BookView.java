package com.zhuwd.demo.mvp.service.view;

import com.zhuwd.demo.mvp.service.entity.Book;

/**
 * Created by Lenovo on 2018/7/4.
 */

public interface BookView extends View{
    void onSuccess(Book mBook);
    void onError(String result);

}
