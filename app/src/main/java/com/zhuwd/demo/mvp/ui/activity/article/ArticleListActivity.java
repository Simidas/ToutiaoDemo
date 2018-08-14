package com.zhuwd.demo.mvp.ui.activity.article;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhuwd.demo.R;
import com.zhuwd.demo.mvp.service.RetrofitHelper;
import com.zhuwd.demo.mvp.service.base.BaseActivity;
import com.zhuwd.demo.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 2018/8/13.
 */

public class ArticleListActivity extends BaseActivity{


    private TextView tvSent;
    private Context mContext;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_article_list;
    }

    @Override
    protected void findView() {
        tvSent = (TextView) findViewById(R.id.tv_sent);
    }

    @Override
    protected void init() {
        mContext = this;
        tvSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        Call<ArticleBean> articleListData = RetrofitHelper.getInstance(mContext)
                .getServer()
                .getArticleListData(1);

        articleListData.enqueue(new Callback<ArticleBean>() {
            @Override
            public void onResponse(Call<ArticleBean> call, Response<ArticleBean> response) {
                ArticleBean body = response.body();

            }

            @Override
            public void onFailure(Call<ArticleBean> call, Throwable t) {
                ToastUtil.showToast(mContext, t.getMessage());
            }
        });
    }

}
