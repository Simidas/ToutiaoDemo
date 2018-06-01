package com.zhuwd.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView mNav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initToolbar();
        initDrawer();
        initNavigationViewListener();
    }


    /**
     * 设置侧拉栏点击的监听
     *
     */
    private void initNavigationViewListener() {
        mNav_view = findViewById(R.id.nav_view);
        mNav_view.setNavigationItemSelectedListener(this);

    }

    /**
     * toolbar和Drawerlayout关联，产生返回键和三条杠转化的动画效果
     */
    private void initDrawer() {
        mDrawer = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);//channge the icon,改变图标
        actionBarDrawerToggle.syncState();////show the default icon and sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
        mDrawer.setDrawerListener(actionBarDrawerToggle);

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("仿头条");
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.Green));
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.Blue));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_account:
                Toast.makeText(this,"个人",Toast.LENGTH_SHORT).show();
                mDrawer.closeDrawers();
                break;

            case R.id.nav_setting:
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
                mDrawer.closeDrawers();
                break;

            case R.id.nav_share:
                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
                mDrawer.closeDrawers();
                break;
            default:
                break;
        }
        return false;
    }
}
