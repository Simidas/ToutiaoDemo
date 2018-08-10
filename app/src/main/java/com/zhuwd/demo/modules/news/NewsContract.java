package com.zhuwd.demo.modules.news;

import com.zhuwd.demo.modules.base.BasePresenter;
import com.zhuwd.demo.modules.base.BaseView;

/**
 * Created by Lenovo on 2018/8/3.
 * 接口契约管理类
 */

public interface NewsContract {

    interface View extends BaseView<Presenter> {
        void setUiContent();
    }

    interface Presenter extends BasePresenter {
        void getNewsBean();
    }


}
