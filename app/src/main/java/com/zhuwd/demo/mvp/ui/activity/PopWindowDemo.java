package com.zhuwd.demo.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhuwd.demo.R;
import com.zhuwd.demo.util.CustomPopWindow;

/**
 * Created by Lenovo on 2018/8/7.
 */

public class PopWindowDemo extends AppCompatActivity {

    private TextView tvMain;
    private TextView tvLeftBtn;
    private TextView tvRightBtn;
    private TextView tvBottomleftBtn;
    private TextView tvBottomrightBtn;
    private TextView tvTopBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        tvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//                View popView = inflater.inflate(R.layout.activity_pop, null);
//                PopupWindow popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//                popupWindow.showAtLocation(tvMain, Gravity.LEFT, 0, 0);

                CustomPopWindow mCustomPopWindow1 = new CustomPopWindow.PopupWindowBuilder(PopWindowDemo.this)
                        .setView(R.layout.activity_pop)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .create()
                        .showAtLocation(tvMain, Gravity.BOTTOM,0,20);

            }
        });

        tvLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(PopWindowDemo.this)
                        .setView(R.layout.activity_pop)//显示的布局
                        .create()//创建PopupWindow
                        .showAsDropDown(tvLeftBtn, tvLeftBtn.getWidth(), -tvLeftBtn.getHeight());//显示PopupWindow
            }
        });

        tvRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(PopWindowDemo.this)
                        .setView(R.layout.activity_pop)//显示的布局
                        .create()//创建PopupWindow
                        .showAsDropDown(tvRightBtn,0,0);//显示PopupWindow
            }
        });

        tvTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] positions = new int[2];
                tvTopBtn.getLocationOnScreen(positions);
                View view = LayoutInflater.from(PopWindowDemo.this).inflate(R.layout.activity_pop,null);

                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(PopWindowDemo.this)
                        .setView(view)//显示的布局
                        .setClippingEnable(false)
                        .create()//创建PopupWindow
                        .showAtLocation(tvTopBtn,Gravity.START|Gravity.TOP,positions[0] + ((view.getWidth()/2)-(tvTopBtn.getWidth()/2)) , positions[1]+ tvTopBtn.getHeight());//显示PopupWindow
            }
        });

    }

    private void initView() {
        tvMain = (TextView) findViewById(R.id.tv_main);
        tvLeftBtn = (TextView) findViewById(R.id.tv_left_btn);
        tvRightBtn = (TextView) findViewById(R.id.tv_right_btn);
        tvBottomleftBtn = (TextView) findViewById(R.id.tv_bottomleft_btn);
        tvBottomrightBtn = (TextView) findViewById(R.id.tv_bottomright_btn);
        tvTopBtn = (TextView) findViewById(R.id.tv_top_btn);
    }
}
